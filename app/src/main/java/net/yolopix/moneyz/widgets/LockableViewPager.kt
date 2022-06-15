package net.yolopix.moneyz.widgets

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager

/**
 * A regular view pager where the scrolling can be programmatically disabled
 */
class LockableViewPager(context: Context, attrs: AttributeSet) : ViewPager(context, attrs) {

    /** If the scrolling should be locked */
    var isLocked = false

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return if (isLocked) false else super.onInterceptTouchEvent(ev)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        return if (isLocked) false else super.onTouchEvent(ev)
    }
}