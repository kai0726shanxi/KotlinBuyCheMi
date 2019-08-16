package com.chmichat.chat.ui.adapter.homeadapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.TextView
import com.chmichat.chat.R
import com.chmichat.chat.R.id.tv_name
import com.chmichat.chat.view.recyclerview.ViewHolder
import com.chmichat.chat.view.recyclerview.adapter.CommonAdapter

/**
 * @Author 20342
 * @Date 2019/8/8 9:46
 * 论坛动态的适配器
 */
class HomeDynamicAdapter(context: Context, data: ArrayList<String>) : CommonAdapter<String>(context, data, R.layout.item_home_dynamic) {


    override fun bindData(holder: ViewHolder, data: String, position: Int) {

        Log.e("item>>",""+position)
        holder.getView<TextView>(R.id.tv_name).text="阁楼上的猫$position"
    }


}