package com.chmichat.chat.ui.adapter.homeadapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import com.chmichat.chat.R
import com.chmichat.chat.ui.activity.home.AllDynamicActivity
import com.chmichat.chat.view.recyclerview.ViewHolder
import com.chmichat.chat.view.recyclerview.adapter.CommonAdapter

/**
 * 视频专区热门评论
 * @Author 20342
 * @Date 2019/8/20 9:19
 */
class VideoForumImgaeAdapter(context: Context,list: ArrayList<String>):CommonAdapter<String>(context,list, R.layout.item_homevideo_forum_image) {
    override fun bindData(holder: ViewHolder, data: String, position: Int) {
        holder.setOnItemClickListener(View.OnClickListener {
            val intent = Intent(mContext as Activity, AllDynamicActivity::class.java)
            mContext.startActivity(intent)
        })

    }
}