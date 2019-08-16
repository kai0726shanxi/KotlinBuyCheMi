package com.chmichat.chat.ui.activity.me

import android.graphics.Color
import android.view.View
import com.chmichat.chat.R
import com.chmichat.chat.base.BaseActivity
import com.chmichat.chat.utils.StatusBarUtil
import kotlinx.android.synthetic.main.title_bar_layout.*

/**
 * @Author 20342
 * 消息通知
 * @Date 2019/8/13 17:21
 */
class MessageNotificationActivity:BaseActivity(),View.OnClickListener {
    override fun onClick(v: View?) {
        when{
            v?.id==R.id.iv_left->{
                finish()
            }
        }
    }

    override fun layoutId(): Int = R.layout.activity_messagenotification
    override fun initData() {
    }

    override fun initView() {
        StatusBarUtil.darkMode(this)
        StatusBarUtil.setPaddingSmart(this,cl_bar)
        iv_left.visibility=View.VISIBLE
        iv_left.setColorFilter(Color.BLACK)
        tv_title.setTextColor(Color.BLACK)
        cl_bar.setBackgroundColor(Color.WHITE)
        tv_title.text="消息通知"
        iv_left.setOnClickListener(this)
    }

    override fun start() {
    }
}