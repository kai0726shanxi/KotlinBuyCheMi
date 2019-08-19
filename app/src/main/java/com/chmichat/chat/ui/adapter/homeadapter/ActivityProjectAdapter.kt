package com.chmichat.chat.ui.adapter.homeadapter

import android.content.Context
import com.chmichat.chat.R
import com.chmichat.chat.view.recyclerview.ViewHolder
import com.chmichat.chat.view.recyclerview.adapter.CommonAdapter

/**
 * 活动专题的适配器
 * @Author 20342
 * @Date 2019/8/17 15:51
 */
class ActivityProjectAdapter(context: Context, data:ArrayList<String>):CommonAdapter<String> (context,data, R.layout.item_activity_project){
    override fun bindData(holder: ViewHolder, data: String, position: Int) {

    }
}