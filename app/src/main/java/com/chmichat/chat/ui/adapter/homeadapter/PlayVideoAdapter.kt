package com.chmichat.chat.ui.adapter.homeadapter

import android.content.Context
import android.view.View
import android.widget.ImageView
import com.chmichat.chat.R
import com.chmichat.chat.glide.GlideApp
import com.chmichat.chat.showToast
import com.chmichat.chat.view.recyclerview.ViewHolder
import com.chmichat.chat.view.recyclerview.adapter.CommonAdapter


/**
 * @Author 20342
 * @Date 2019/8/17 20:48
 */
class PlayVideoAdapter(context: Context,list: ArrayList<String>):CommonAdapter<String>(context,list, R.layout.item_play_video) {

    override fun bindData(holder: ViewHolder, data: String, position: Int) {
        var image=holder.getView<ImageView>(R.id.thumb)
        GlideApp.with(mContext)
                .load("https://p1.pstatp.com/large/4bea0014e31708ecb03e.jpeg")
                .placeholder(R.mipmap.moren_icon)
                .into(image)
    }
}