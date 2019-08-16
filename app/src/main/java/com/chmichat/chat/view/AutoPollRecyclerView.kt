package com.chmichat.chat.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.Rect
import android.graphics.Shader
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration

import java.lang.ref.WeakReference

class AutoPollRecyclerView(context: Context, attrs: AttributeSet?) : RecyclerView(context, attrs) {
    internal var autoPollTask: AutoPollTask? = null
    internal var autoPollTask1: AutoPollTask1

    private var isrefresh: Boolean = false
    private var index = 0
    private var running: Boolean = false //标示是否正在自动轮询
    private var canRun: Boolean = false//标示是否可以自动轮询,可在不需要的是否置false
    private val mTouchSlop: Int

    internal var lastY = 0

    internal lateinit var mPaint: Paint
    private var layerId: Int = 0
    private var linearGradient: LinearGradient? = null
    private var preWidth = 0// Recyclerview宽度动态变化时，监听每一次的宽度

    fun setIsrefresh(isrefresh: Boolean) {
        this.isrefresh = isrefresh
    }

    init {
        // autoPollTask = new AutoPollTask(this);
        autoPollTask1 = AutoPollTask1(this)
        mTouchSlop = ViewConfiguration.get(context).scaledTouchSlop
    }

    /*** 持续滑动（走马灯） */
    internal class AutoPollTask(reference: AutoPollRecyclerView) : Runnable {
        private val mReference: WeakReference<*>        //使用弱引用持有外部类引用->防止内存泄漏

        init {
            this.mReference = WeakReference(reference)
        }

        override fun run() {
            val recyclerView = mReference.get() as AutoPollRecyclerView
            if (recyclerView != null && recyclerView.running && recyclerView.canRun) {
                recyclerView.scrollBy(2, 2)
                recyclerView.postDelayed(recyclerView.autoPollTask, TIME_AUTO_POLL)
            }
        }
    }

    /**** 一次只能滑一个item（轮播图） */
    internal class AutoPollTask1//使用弱引用持有外部类引用->防止内存泄漏
    (reference: AutoPollRecyclerView) : Runnable {
        private val mReference: WeakReference<*>

        init {
            this.mReference = WeakReference(reference)
        }

        override fun run() {
            val recyclerView = mReference.get() as AutoPollRecyclerView
            if (recyclerView != null && recyclerView.running && recyclerView.canRun) {
                if (recyclerView.isrefresh) {
                    recyclerView.index = 0
                    recyclerView.setIsrefresh(false)
                }
                recyclerView.index = recyclerView.index + 4
                recyclerView.smoothScrollToPosition(recyclerView.index)
                recyclerView.postDelayed(recyclerView.autoPollTask1, TIME_AUTO_POLL_1)
            }
        }
    }//开启:如果正在运行,先停止->再开启

    fun start() {
        if (running) stop()
        canRun = true
        running = true
        // postDelayed(autoPollTask,TIME_AUTO_POLL);
        postDelayed(autoPollTask1, TIME_AUTO_POLL_1)
    }

    fun stop() {
        running = false
        // removeCallbacks(autoPollTask);
        removeCallbacks(autoPollTask1)
    }//取消RecyclerView的惯性，使每次手动只能滑一个

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        val action = ev.action
        when (action) {
            MotionEvent.ACTION_DOWN -> {
                lastY = ev.rawY.toInt()
                if (running) stop()
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL, MotionEvent.ACTION_OUTSIDE -> {
                val nowY = ev.rawY.toInt()
                if (nowY - lastY > mTouchSlop) {//向下滑动
                    smoothScrollToPosition(if (index == 0) 0 else --index)
                    if (canRun) start()
                    return true
                } else if (lastY - nowY > mTouchSlop) {//向上滑动
                    smoothScrollToPosition(++index)
                    if (canRun) start()
                    return true
                }
            }
        }
        return super.dispatchTouchEvent(ev)
    }// 实现渐变效果

    fun doTopGradualEffect(itemViewWidth: Int) {
        mPaint = Paint()
        // dst_in 模式，实现底层透明度随上层透明度进行同步显示（即上层为透明时，下层就透明，并不是上层覆盖下层)
        val xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_IN)
        mPaint.xfermode = xfermode
        addItemDecoration(object : RecyclerView.ItemDecoration() {

            override fun onDrawOver(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State?) {

                super.onDrawOver(canvas, parent, state)

                // 当linearGradient为空即第一次绘制 或 Recyclerview宽度发生改变时，重新计算透明位置

                if (linearGradient == null || preWidth != parent.width) {

                    // 透明位置从最后一个 itemView 的一半处到 Recyclerview 的最右边

                    linearGradient = LinearGradient((parent.width - itemViewWidth / 2).toFloat(), 0.0f, parent.width.toFloat(), 0.0f, intArrayOf(Color.BLACK, 0), null, Shader.TileMode.CLAMP)

                    preWidth = parent.width

                }

                mPaint.xfermode = xfermode

                mPaint.shader = linearGradient

                canvas.drawRect(0.0f, 0.0f, parent.right.toFloat(), parent.bottom.toFloat(), mPaint)

                mPaint.xfermode = null

                canvas.restoreToCount(layerId)

            }

            override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State?) {

                super.onDraw(c, parent, state)

                // 此处 Paint的参数这里传的null， 在传入 mPaint 时会出现第一次打开黑屏闪现的问题

                // 注意 saveLayer 不能省也不能移动到onDrawOver方法里

                layerId = c.saveLayer(0.0f, 0.0f, parent.width.toFloat(), parent.height.toFloat(), null, Canvas.ALL_SAVE_FLAG)

            }

            override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State?) {

                // 该方法作用自行百度

                super.getItemOffsets(outRect, view, parent, state)

            }

        })

    }

    companion object {
        private val TIME_AUTO_POLL: Long = 16
        private val TIME_AUTO_POLL_1: Long = 5000
    }
}


