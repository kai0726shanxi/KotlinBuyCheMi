package com.chmichat.chat.ui.activity.manage

import android.graphics.Color
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.chmichat.chat.R
import com.chmichat.chat.base.BaseActivity
import com.chmichat.chat.ui.adapter.message.InteractionAdapter
import com.chmichat.chat.ui.adapter.message.SystemAdapter
import com.chmichat.chat.utils.StatusBarUtil
import kotlinx.android.synthetic.main.activity_notification_layout.*
import kotlinx.android.synthetic.main.title_bar_layout.*

/**
 * @Author 20342
 * @Date 2019/8/21 15:35
 */
class NotificationActivity: BaseActivity() ,View.OnClickListener{

    var mPosition=0
    var mlist= arrayListOf("1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1")
    var mSystemAdapter:SystemAdapter?=null
    var minteractionAdapter:InteractionAdapter?=null
    override fun layoutId(): Int {

        return R.layout.activity_notification_layout
    }

    override fun initData() {
        mPosition=intent.getIntExtra("position",0)
    }

    override fun initView() {
        iv_left.visibility=View.VISIBLE
        iv_left.setColorFilter(Color.BLACK)
        iv_left.setOnClickListener(this)
        StatusBarUtil.darkMode(this)
        StatusBarUtil.setPaddingSmart(this,cl_bar)
        when (mPosition) {
            1 -> {
            tv_title.text="互动消息"
              minteractionAdapter= InteractionAdapter(this,mlist)
                mRecyclerView.adapter=minteractionAdapter
                mRecyclerView.layoutManager=LinearLayoutManager(this)
            }
            2 -> {
                tv_title.text="活动消息"
                mSystemAdapter= SystemAdapter(this,mlist)
                mRecyclerView.adapter=mSystemAdapter
                mRecyclerView.layoutManager=LinearLayoutManager(this)
            }
            else -> {
                tv_title.text="系统消息"
                mSystemAdapter= SystemAdapter(this,mlist)
                mRecyclerView.adapter=mSystemAdapter
                mRecyclerView.layoutManager=LinearLayoutManager(this)
            }
        }

    }

    override fun start() {
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.iv_left->finish()
        }
    }

}