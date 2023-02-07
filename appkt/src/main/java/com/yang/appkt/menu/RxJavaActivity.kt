package com.yang.appkt.menu

import android.os.Bundle
import android.text.TextUtils
import com.yang.appkt.databinding.ActivityOkHttpBinding
import com.yang.ktbase.base.BaseBindActivity
import com.yang.ktbase.ext.logD
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Flow.Subscriber
import java.util.concurrent.TimeUnit

class RxJavaActivity : BaseBindActivity<ActivityOkHttpBinding>() {

    override fun initView(savedInstanceState: Bundle?) {
        binding.apply {
            tvFlowable.setOnClickListener {
                operator()
            }
            tvObservable.setOnClickListener {
                operator2()
            }

        }
    }

    /**
     * 基本操作符
     */
    private fun operator() {
        Flowable.just("1", "1","2","1", "3", "4", "5","5")
            .subscribeOn(Schedulers.io()) //指定被观察者线程
            .observeOn(AndroidSchedulers.mainThread())  //指定观察者线程
            .skip(1)  //发射前跳过多少个,打印123455(Flowable,Observable可用)。
            .debounce(1,TimeUnit.SECONDS)   //防重复点击，上一次发送的一秒内的数据都无效(Flowable,Observable可用)。
            .distinct()     //去重，去掉数据源重复数据,打印12345 (Flowable,Observable可用)。
            .distinctUntilChanged()     //去掉相邻重复数据。打印121345 (Flowable,Observable可用)。
            .filter { it.length==2 }    //过滤数据,false表示数据会被过滤掉
            .ofType(String.Companion::class.java)
            .doOnEach { it?.value?.toString()?.logD(tag = "rxJava") }   //数据源每发送一次数据就调用一次。
            .doOnNext { "on next do".logD(tag = "rxJava") }    //数据源每次调用onNext() 之前都会先回调该方法。
            .doOnError { "on error do".logD(tag = "rxJava") }   //数据源每次调用onError() 之前会回调该方法。
            .doOnComplete { "on complete do".logD(tag = "rxJava") }    //数据源每次调用onComplete() 之前会回调该方法
            .doOnSubscribe { "on sub do".logD(tag = "rxJava") }   //数据源每次调用onSubscribe() 之后会回调该方法
            //.elementAt(0)//获取第0个数据，打印1
            //.first("0")   //发送数据源第一个数据，没有用默认的()。
            .subscribe { it.toString().logD(tag = "rxJava result") }
    }

    /**
     * 连接操作符
     */
    private fun operator2(){
        var getData = Observable.just("aaa", "bbb")
        var postData = Observable.just("ccc", "ddd","eee")

        getData.startWith(postData) //将指定数据源合并在另外数据源的开头。
            .subscribe {it.toString().logD("rxJava result") }
        Observable.merge(getData,postData).subscribe(object:Observer<String>{
            override fun onSubscribe(d: Disposable) {
            }

            override fun onNext(t: String) {
            }

            override fun onError(e: Throwable) {
            }

            override fun onComplete() {
            }

        })

    }


}