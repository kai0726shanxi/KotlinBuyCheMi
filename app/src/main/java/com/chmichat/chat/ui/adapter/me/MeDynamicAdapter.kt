package com.chmichat.chat.ui.adapter.me

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import com.chmichat.chat.R
import com.chmichat.chat.ui.activity.home.AllDynamicActivity
import com.chmichat.chat.view.recyclerview.ViewHolder
import com.chmichat.chat.view.recyclerview.adapter.CommonAdapter

/**
 * 动态适配器
 * @Author 20342
 * @Date 2019/8/9 11:35
 */
class MeDynamicAdapter(context: Context,data:ArrayList<String>):CommonAdapter<String>(context,data, R.layout.item_medynamic_layout){
    override fun bindData(holder: ViewHolder, data: String, position: Int) {


    }
}