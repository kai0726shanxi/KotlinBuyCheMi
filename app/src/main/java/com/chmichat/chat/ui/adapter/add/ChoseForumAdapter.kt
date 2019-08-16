package com.chmichat.chat.ui.adapter.add

import android.content.Context
import com.chmichat.chat.R
import com.chmichat.chat.view.recyclerview.ViewHolder
import com.chmichat.chat.view.recyclerview.adapter.CommonAdapter

/**
 * 选择论坛的适配器
 * @Author 20342
 * @Date 2019/8/15 17:07
 */
class ChoseForumAdapter(context: Context,data:ArrayList<String>):CommonAdapter<String>(context,data, R.layout.item_choseforum_layout) {
    override fun bindData(holder: ViewHolder, data: String, position: Int) {


    }
}