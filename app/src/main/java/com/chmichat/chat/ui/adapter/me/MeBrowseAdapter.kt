package com.chmichat.chat.ui.adapter.me

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import com.chmichat.chat.R
import com.chmichat.chat.bean.RecentBrowseEntity
import com.chmichat.chat.ui.activity.home.AllDynamicActivity
import com.chmichat.chat.view.recyclerview.ViewHolder
import com.chmichat.chat.view.recyclerview.adapter.CommonAdapter

/**
 * 最近浏览的适配器
 * @Author 20342
 * @Date 2019/8/8 18:06
 */
class MeBrowseAdapter(context: Context,list: ArrayList<RecentBrowseEntity>):CommonAdapter<RecentBrowseEntity>(context,list, R.layout.item_me_browse_layout){


    fun addData(dataList: ArrayList<RecentBrowseEntity>) {
        this.mData.clear()
        this.mData.addAll(dataList)
        notifyDataSetChanged()
    }
    override fun bindData(holder: ViewHolder, data: RecentBrowseEntity, position: Int) {

        data?.sectionName?.let { holder.setText(R.id.tv_name, it) }
        data?.browseNum?.let { holder.setText(R.id.tv_num, it.toString()+"条帖子") }
        holder.setOnItemClickListener(listener = View.OnClickListener {
            mContext.startActivity(Intent(mContext as Activity,AllDynamicActivity::class.java))
        })


    }

}