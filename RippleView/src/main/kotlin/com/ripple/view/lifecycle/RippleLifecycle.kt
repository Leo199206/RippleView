package com.ripple.view.lifecycle

import com.ripple.view.RippleView
import java.lang.ref.WeakReference

/**
 * <pre>
 *   @author : leo
 *   @time   : 2021/03/22
 *   @desc   :
 * </pre>
 */
class RippleLifecycle(view: RippleView) : BaseLifecycle {
    private val reference = WeakReference(view)
    override fun onResume() {
        reference.get()?.onResume()
    }

    override fun onPause() {
        reference.get()?.onPause()
    }

    override fun onDestroy() {
        reference.get()?.onStop()
    }
}