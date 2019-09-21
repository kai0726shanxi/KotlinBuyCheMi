package com.chmichat.chat.ui.adapter.me

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import com.chmichat.chat.R
import com.chmichat.chat.bean.PostListEntity
import com.chmichat.chat.ui.activity.home.AllDynamicActivity
import com.chmichat.chat.view.recyclerview.ViewHolder
import com.chmichat.chat.view.recyclerview.adapter.CommonAdapter

/**
 * 我的界面论坛适配器
 * @Author 20342
 * @Date 2019/8/9 11:35
 */
class MeForumAdapter(context: Context, data:ArrayList<PostListEntity>):CommonAdapter<PostListEntity>(context,data, R.layout.item_meforum_layout){

    fun addDataNew(dataList: ArrayList<PostListEntity>) {
        this.mData.clear()
        this.mData.addAll(dataList)
        notifyDataSetChanged()
    }
    fun addDataAll(dataList: ArrayList<PostListEntity>) {
        this.mData.addAll(dataList)
        notifyDataSetChanged()
    }
    override fun bindData(holder: ViewHolder, data: PostListEntity, position: Int) {
        holder.setOnItemClickListener(View.OnClickListener {
            val intent = Intent(mContext as Activity, AllDynamicActivity::class.java)
            mContext.startActivity(intent)
        })
    }
}