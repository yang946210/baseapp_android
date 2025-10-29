package com.yang.ktbase.vm

import androidx.lifecycle.ViewModel
import com.yang.ktbase.util.logD
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty


/**
 * ViewModel基类
 */
abstract class BaseViewModel : ViewModel(){


    override fun onCleared() {
        super.onCleared()
        "ViewModel onCleared :${this::class.java.simpleName}".logD()
    }
}




/**
 * 让 MutableStateFlow + StateFlow 定义更加优雅
 *
 * 用法：
 * private val titleData by stateFlowOf(ResponseData<JsonArray>())
 */
fun <T> stateFlowOf(initial: T) = StateFlowDelegate(initial)

class StateFlowDelegate<T>(initial: T) :
    ReadOnlyProperty<Any?, Pair<MutableStateFlow<T>, StateFlow<T>>> {

    private val mutable = MutableStateFlow(initial)
    private val state = mutable.asStateFlow()
    override fun getValue(thisRef: Any?, property: KProperty<*>): Pair<MutableStateFlow<T>, StateFlow<T>> =
        mutable to state
}

