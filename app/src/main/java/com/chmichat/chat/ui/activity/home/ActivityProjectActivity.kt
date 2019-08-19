package com.chmichat.chat.ui.activity.home

import android.graphics.Color
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.chmichat.chat.R
import com.chmichat.chat.base.BaseActivity
import com.chmichat.chat.ui.adapter.homeadapter.ActivityProjectAdapter
import com.chmichat.chat.utils.StatusBarUtil
import kotlinx.android.synthetic.main.activity_activity_project.*
import kotlinx.android.synthetic.main.title_bar_layout.*

/**
 * @Author 20342
 * @Date 2019/8/17 15:47
 */
class ActivityProjectActivity : BaseActivity(), View.OnClickListener {
    private var mAvtivityProjectAdapter: ActivityProjectAdapter? = null
    private var mlist = arrayListOf("1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1")


    override fun layoutId(): Int {
        return R.layout.activity_activity_project
    }

    override fun initData() {
        iv_left.visibility = View.VISIBLE
        tv_title.text = "活动专题"
        iv_left.setColorFilter(Color.BLACK)
        iv_left.setOnClickListener(this)
    }

    override fun initView() {
        StatusBarUtil.darkMode(this)
        StatusBarUtil.setPaddingSmart(this,cl_bar)
        mAvtivityProjectAdapter = ActivityProjectAdapter(this, mlist)
        recycle_view.adapter = mAvtivityProjectAdapter
        recycle_view.layoutManager = LayoutManager
    }

    override fun start() {
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.iv_left->{
                finish()
            }
        }
    }

    private val LayoutManager by lazy {
        LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

    }
}