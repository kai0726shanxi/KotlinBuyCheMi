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
class DiscoverContentAdapter(context: Context, data: ArrayList<String>) : CommonAdapter<String>(context, data, object : MultipleType<String> {
    override fun getLayoutId(item: String, position: Int): Int {
        return when {
            position == 0 ->
                R.layout.item_discover_content_big
            else ->
                R.layout.item_discover_content_small
        }
    }
}) {
    override fun bindData(holder: ViewHolder, data: String, position: Int) {
        if (position==3){
            holder.setViewVisibility(R.id.img_content,View.GONE)
        }

    }

}