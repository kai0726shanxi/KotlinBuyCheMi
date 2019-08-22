package com.chmichat.chat.ui.adapter.homeadapter

import android.content.Context
import com.chmichat.chat.R
import com.chmichat.chat.view.CircleImageView
import com.chmichat.chat.view.recyclerview.ViewHolder
import com.chmichat.chat.view.recyclerview.adapter.CommonAdapter

/**
 * 视频专区热门评论头像
 * @Author 20342
 * @Date 2019/8/20 9:19
 */
class VideoForumHeadAdapter(context: Context, list: ArrayList<String>):CommonAdapter<String>(context,list, R.layout.item_homevideo_forum_head) {
    override fun bindData(holder: ViewHolder, data: String, position: Int) {
      var  image=holder.getView<CircleImageView>(R.id.iv_head)


    }
}