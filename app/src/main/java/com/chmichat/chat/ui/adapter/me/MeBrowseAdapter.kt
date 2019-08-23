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
 * 最近浏览的适配器
 * @Author 20342
 * @Date 2019/8/8 18:06
 */
class MeBrowseAdapter(context: Context,list: ArrayList<String>):CommonAdapter<String>(context,list, R.layout.item_me_browse_layout){
    override fun bindData(holder: ViewHolder, data: String, position: Int) {
        holder.setOnItemClickListener(listener = View.OnClickListener {

            mContext.startActivity(Intent(mContext as Activity,AllDynamicActivity::class.java))
        })


    }

}