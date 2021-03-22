package com.ripple.view.lifecycle

/**
 * <pre>
 *   @author : leo
 *   @time   : 2021/03/22
 *   @desc   : Base Lifecycle Observer
 * </pre>
 */
interface BaseLifecycle {

    /**
     * onResume
     */
    fun onResume()

    /**
     * onPause
     */
    fun onPause()

    /**
     * onDestroy
     */
    fun onDestroy() {
    }
}