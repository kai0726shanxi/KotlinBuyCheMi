package com.chmichat.chat.view

import android.content.Context
import android.support.v4.widget.NestedScrollView
import android.util.AttributeSet
import android.view.MotionEvent

class JudgeNestedScrollView : NestedScrollView {
    private var isNeedScroll = true
    private var xDistance: Float = 0.toFloat()
    private var yDistance: Float = 0.toFloat()
    private var xLast: Float = 0.toFloat()
    private var yLast: Float = 0.toFloat()

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        when (ev.action) {
            MotionEvent.ACTION_DOWN -> {
                yDistance = 0f
                xDistance = yDistance
                xLast = ev.x
                yLast = ev.y
            }
            MotionEvent.ACTION_MOVE -> {
                val curX = ev.x
                val curY = ev.y
                xDistance += Math.abs(curX - xLast)
                yDistance += Math.abs(curY - yLast)
                xLast = curX
                yLast = curY
                return if (xDistance > yDistance) {
                    false
                } else isNeedScroll
            }
        }
        return super.onInterceptTouchEvent(ev)
    }

    /*
    改方法用来处理NestedScrollView是否拦截滑动事件
     */
    fun setNeedScroll(isNeedScroll: Boolean) {
        this.isNeedScroll = isNeedScroll
    }
}
