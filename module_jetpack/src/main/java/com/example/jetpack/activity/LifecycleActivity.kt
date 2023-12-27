package com.example.jetpack.activity

import android.os.Bundle
import androidx.lifecycle.*
import com.example.lib_jetpack.databinding.ActivityLifecycleBinding


import com.yang.appkt.viewmodel.LifecycleViewModel
import com.yang.ktbase.base.BaseActivity
import com.yang.ktbase.extorutil.logD



class LifecycleActivity : BaseActivity<LifecycleViewModel, ActivityLifecycleBinding>() {

    override fun initView(savedInstanceState: Bundle?) {


        lifecycle.currentState.toString().logD()

        lifecycle.addObserver(MLifecycleObserver())

        lifecycle.addObserver(LifecycleEventObserver{ _, event->
            val eventTag="==event======"
            when(event){
                Lifecycle.Event.ON_CREATE->"OnCreate".logD(eventTag)
                Lifecycle.Event.ON_START->"OnStart".logD(eventTag)
                Lifecycle.Event.ON_RESUME->"OnResume".logD(eventTag)
                Lifecycle.Event.ON_PAUSE->"OnPause".logD(eventTag)
                Lifecycle.Event.ON_STOP->"OnStop".logD(eventTag)
                Lifecycle.Event.ON_DESTROY->"OnDestroy".logD(eventTag)
                Lifecycle.Event.ON_ANY->"OnAny".logD(eventTag)
                else -> {"else".logD(eventTag)}
            }
        })
    }



    class MLifecycleObserver : DefaultLifecycleObserver {
        private val defaultTag="========default==="
        override fun onCreate(owner: LifecycleOwner) {
            super.onCreate(owner)
            "onCreate".logD(defaultTag)
        }

        override fun onStart(owner: LifecycleOwner) {
            super.onStart(owner)
            "onStart".logD(defaultTag)
        }

        override fun onResume(owner: LifecycleOwner) {
            super.onResume(owner)
            "onResume".logD(defaultTag)
        }

        override fun onPause(owner: LifecycleOwner) {
            super.onPause(owner)
            "onPause".logD(defaultTag)
        }

        override fun onStop(owner: LifecycleOwner) {
            super.onStop(owner)
            "onStop".logD(defaultTag)
        }

        //永远不会被调用
        override fun onDestroy(owner: LifecycleOwner) {
            super.onDestroy(owner)
            "onDestroy".logD(defaultTag)
        }
    }


}



