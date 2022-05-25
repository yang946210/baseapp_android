package com.yang.ktbase

import androidx.arch.core.executor.ArchTaskExecutor
import androidx.lifecycle.*
import java.util.concurrent.ConcurrentHashMap

/**
 * livedata实现事件总线
 */
object LiveDataBus {

    /**
     * 事件集合
     */
    private val liveDataMap by lazy {
         ConcurrentHashMap<String, BusLiveData<*>>()
    }

    fun <T> with(eventName: String): BusLiveData<T> {
        var liveData = liveDataMap[eventName]
        if (liveData == null) {
            liveData = BusLiveData<T>(eventName)
            liveDataMap[eventName] = liveData
        }
        return liveData as BusLiveData<T>
    }

    /**
     * LiveData包装类
     */
    class BusLiveData<T>(private var eventName: String) : MutableLiveData<T>() {
        fun postData(t: T) {
            postValue(t)
        }

        override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
            observer(owner, observer)
        }

        fun observer(owner: LifecycleOwner, observer: Observer<in T>) {
            //容器销毁时移除liveData
            owner.lifecycle.addObserver(LifecycleEventObserver { _, event ->
                if (event == Lifecycle.Event.ON_DESTROY && !hasObservers()) {
                    liveDataMap.remove(eventName)
                }
            })
            super.observe(owner, observer)
            solveStick(observer)
        }

        /**
         * 使用反射将mLastVersion的值改为和mVersion相同，就可以解决粘性数据问题
         */
        private fun solveStick(observer: Observer<in T>) {
            val liveDataClass = LiveData::class.java

            //先拿到observer.mLastVersion，mLastVersion在ObserverWrapper中，ObserverWrapper又存放在mObservers中

            //获取mObserversFiled
            val mObserversFiled = liveDataClass.getDeclaredField("mObservers")
            mObserversFiled.isAccessible = true

            //获取map值,this就是LiveData
            val mObserversMap: Any = mObserversFiled.get(this)

            //拿到mObserversMap的class
            val mObserversMapClass = mObserversMap.javaClass

            //获取map的get方法
            val mObserversMapGet = mObserversMapClass.getDeclaredMethod("get", Any::class.java)
            mObserversMapGet.isAccessible = true

            //执行map的get方法,获取ObserverWrapper
            var observerWrapper: Any? = null
            val invokeEntry = mObserversMapGet.invoke(mObserversMap, observer)
            if (invokeEntry != null && invokeEntry is Map.Entry<*, *>) {
                observerWrapper = invokeEntry.value
            } else {
                throw Exception("observerWrapper error")
            }

            //获取ObserverWrapper中的mLastVersion
            //由于这里获取到的的是ObserverWrapper的子类LifecycleBoundObserver或AlwaysActiveObserver
            //所以需要先用superclass获取超类
            val observerWrapperSuperClass = observerWrapper?.javaClass?.superclass
            val mLastVersionFiled = observerWrapperSuperClass?.getDeclaredField("mLastVersion")
            mLastVersionFiled?.isAccessible = true

            //获取mVersion
            val mVersionFiled = liveDataClass.getDeclaredField("mVersion")
            mVersionFiled.isAccessible = true
            //拿到mVersion的值
            val mVersion = mVersionFiled.get(this)

            //将mLastVersion的值设置成跟mVersion相同
            mLastVersionFiled?.set(observerWrapper, mVersion)
        }
    }

}



