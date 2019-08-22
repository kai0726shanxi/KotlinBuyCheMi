package com.chmichat.chat.ui.adapter.message

import android.content.Context
import com.chmichat.chat.R
import com.chmichat.chat.view.recyclerview.ViewHolder
import com.chmichat.chat.view.recyclerview.adapter.CommonAdapter

/**
 * 互动
 * @Author 20342
 * @Date 2019/8/21 15:47
 */
class InteractionAdapter(context: Context, list: ArrayList<String>):CommonAdapter<String>(context,list, R.layout.item_manage_interaction) {
    override fun bindData(holder: ViewHolder, data: String, position: Int) {

    }
}