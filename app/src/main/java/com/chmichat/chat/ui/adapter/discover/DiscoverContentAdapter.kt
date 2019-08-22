package com.chmichat.chat.ui.adapter.discover

import android.content.Context
import android.view.View
import com.chmichat.chat.R
import com.chmichat.chat.view.recyclerview.MultipleType
import com.chmichat.chat.view.recyclerview.ViewHolder
import com.chmichat.chat.view.recyclerview.adapter.CommonAdapter

/**
 * @Author 20342
 * @Date 2019/8/8 15:37
 */
class DiscoverContentAdapter(context: Context, data: ArrayList<String>,p:String) : CommonAdapter<String>(context, data, object : MultipleType<String> {

    override fun getLayoutId(item: String, position: Int): Int {
        return when {
            p == "0"->
                R.layout.item_discover_content_small
            p == "1" ->
                R.layout.item_discover_content_big
            p == "2" ->
                R.layout.item_mevideo_layout
            p == "3"->
                R.layout.item_melongvideo_layout
            else -> {
                R.layout.item_discover_content_small
            }
        }
    }
}) {
    override fun bindData(holder: ViewHolder, data: String, position: Int) {

      holder.setOnItemClickListener(listener = View.OnClickListener {

      })
    }

}