package com.chmichat.chat.ui.adapter.me

import android.content.Context
import com.chmichat.chat.R
import com.chmichat.chat.view.recyclerview.ViewHolder
import com.chmichat.chat.view.recyclerview.adapter.CommonAdapter

/**
 * 我的界面论坛适配器
 * @Author 20342
 * @Date 2019/8/9 11:35
 */
class MeForumAdapter(context: Context, data:ArrayList<String>):CommonAdapter<String>(context,data, R.layout.item_meforum_layout){
    override fun bindData(holder: ViewHolder, data: String, position: Int) {
    }
}