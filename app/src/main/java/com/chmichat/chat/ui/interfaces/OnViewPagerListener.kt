package com.chmichat.chat.ui.interfaces

import android.view.View

/**
 * @Author 20342
 * @Date 2019/8/17 20:39
 */
interface OnViewPagerListener {

    /**
     * 初始化
     */
    fun onInitComplete(view: View)

    /**
     * 释放
     */
    fun onPageRelease(isNext:Boolean ,  position:Int,  view:View)

    /**
     * 选中
     */
    fun onPageSelected(position:Int ,  isBottom:Boolean,  view:View)
}