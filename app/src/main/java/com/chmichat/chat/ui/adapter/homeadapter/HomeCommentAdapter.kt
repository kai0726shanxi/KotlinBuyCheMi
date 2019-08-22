package com.chmichat.chat.ui.adapter.homeadapter

import android.content.Context
import com.chmichat.chat.R
import com.chmichat.chat.view.recyclerview.ViewHolder
import com.chmichat.chat.view.recyclerview.adapter.CommonAdapter

/**
 * @Author 20342
 * @Date 2019/8/21 10:44
 */
class HomeCommentAdapter(context: Context,list: ArrayList<String>):CommonAdapter<String>(context,list, R.layout.item_home_comment) {
    override fun bindData(holder: ViewHolder, data: String, position: Int) {


    }
}