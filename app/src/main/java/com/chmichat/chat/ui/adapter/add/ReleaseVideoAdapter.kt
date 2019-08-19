package com.chmichat.chat.ui.adapter.add

import android.content.Context
import android.widget.TextView
import com.chmichat.chat.R
import com.chmichat.chat.view.recyclerview.ViewHolder
import com.chmichat.chat.view.recyclerview.adapter.CommonAdapter

/**
 * @Author 20342
 * 发布小视频分类
 * @Date 2019/8/17 18:01
 */
class ReleaseVideoAdapter(context: Context,list: ArrayList<String>):CommonAdapter<String>(context,list, R.layout.item_video_classify){
    override fun bindData(holder: ViewHolder, data: String, position: Int) {
         holder.getView<TextView>(R.id.tv_content).text="测试$position"
    }
}