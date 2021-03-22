package com.ripple.view.lifecycle

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent


/**
 * <pre>
 *   @author : leo
 *   @time   : 2021/03/22
 *   @desc   :
 * </pre>
 */
class RippleLifecycleAdapter(private val lifecycle: BaseLifecycle) : LifecycleObserver {


    /**
     * onResume
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onLifecycleResume() {
        Log.i("${javaClass.simpleName}", "onLifecycleResume")
        lifecycle.onResume()
    }


    /**
     * onPause
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onLifecyclePause() {
        Log.i("${javaClass.simpleName}", "onLifecyclePause")
        lifecycle.onPause()
    }


    /**
     * onDestroy
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onLifecycleDestroy() {
        Log.i("${javaClass.simpleName}", "onLifecycleDestroy")
        lifecycle.onDestroy()
    }
}