package com.yang.appkt.ext

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.yang.ktbase.ext.logD


fun Fragment.logLifeCycle() {
    val eventTag = "${this::class.hashCode()}${this::class.java.name}"
    lifecycle.addObserver(LifecycleEventObserver { _, e ->
        when (e) {
            Lifecycle.Event.ON_CREATE -> "OnCreate".logD(eventTag)
            Lifecycle.Event.ON_START -> "OnStart".logD(eventTag)
            Lifecycle.Event.ON_RESUME -> "OnResume".logD(eventTag)
            Lifecycle.Event.ON_PAUSE -> "OnPause".logD(eventTag)
            Lifecycle.Event.ON_STOP -> "OnStop".logD(eventTag)
            Lifecycle.Event.ON_DESTROY -> "OnDestroy".logD(eventTag)
            Lifecycle.Event.ON_ANY -> "OnAny".logD(eventTag)
            else -> {
                e.toString().logD(eventTag)
            }
        }
    })
}
