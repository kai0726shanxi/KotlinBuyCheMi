package com.chmichat.chat.ui.adapter.message

import android.content.Context
import com.chmichat.chat.R
import com.chmichat.chat.view.recyclerview.ViewHolder
import com.chmichat.chat.view.recyclerview.adapter.CommonAdapter

/**
 * @Author 20342
 * @Date 2019/8/19 15:50
 */
class MessageListAdapter(context: Context,list: ArrayList<String>):CommonAdapter<String>(context,list, R.layout.item_message_list) {
    override fun bindData(holder: ViewHolder, data: String, position: Int) {


    }
}