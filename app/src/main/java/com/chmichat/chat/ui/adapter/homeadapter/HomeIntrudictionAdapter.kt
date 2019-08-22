package com.chmichat.chat.ui.adapter.homeadapter

import android.content.Context
import com.chmichat.chat.R
import com.chmichat.chat.view.recyclerview.ViewHolder
import com.chmichat.chat.view.recyclerview.adapter.CommonAdapter

/**
 * 长视频简介
 * @Author 20342
 * @Date 2019/8/21 10:31
 */
class HomeIntrudictionAdapter(context: Context,list: ArrayList<String>):CommonAdapter<String>(context,list, R.layout.item_home_intrudiction) {
    override fun bindData(holder: ViewHolder, data: String, position: Int) {


    }
}