package com.chmichat.chat.ui.adapter.homeadapter

import android.content.Context
import com.chmichat.chat.R
import com.chmichat.chat.view.recyclerview.ViewHolder
import com.chmichat.chat.view.recyclerview.adapter.CommonAdapter

/**
 * @Author 20342
 * 热门帖子
 * @Date 2019/8/7 18:32
 */
class HomePopularAdapter(context: Context, data: ArrayList<String>):CommonAdapter<String>(context, data, R.layout.item_home_ipopular){
    override fun bindData(holder: ViewHolder, data: String, position: Int) {

    }

}