package com.chmichat.chat.ui.activity.me

import android.graphics.Color
import android.view.View
import com.chmichat.chat.R
import com.chmichat.chat.base.BaseActivity
import com.chmichat.chat.utils.StatusBarUtil
import kotlinx.android.synthetic.main.title_bar_layout.*

/**
 * @Author 20342
 * 关于我们
 * @Date 2019/8/14 11:33
 */
class AboutUsActivity : BaseActivity(), View.OnClickListener {


    override fun layoutId(): Int = R.layout.activity_me_about

    override fun initData() {
    }

    override fun initView() {
        StatusBarUtil.darkMode(this)
        StatusBarUtil.setPaddingSmart(this, cl_bar)
        iv_left.visibility = View.VISIBLE
        iv_left.setColorFilter(Color.BLACK)
        tv_title.setTextColor(Color.BLACK)
        cl_bar.setBackgroundColor(Color.WHITE)
        iv_left.setOnClickListener(this)
    }

    override fun start() {
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.iv_left->{
                finish()
            }
        }

    }
}