package net.yolopix.moneyz.widgets

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager

class LockableViewPager(context: Context, attrs: AttributeSet) : ViewPager(context, attrs) {

    var isLocked = false

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return if (isLocked) false else super.onInterceptTouchEvent(ev)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        return if (isLocked) false else super.onTouchEvent(ev)
    }
}