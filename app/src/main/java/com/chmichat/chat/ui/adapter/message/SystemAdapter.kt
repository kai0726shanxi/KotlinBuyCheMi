package com.chmichat.chat.ui.adapter.message

import android.content.Context
import android.view.View
import android.widget.TextView
import com.chmichat.chat.R
import com.chmichat.chat.view.recyclerview.ViewHolder
import com.chmichat.chat.view.recyclerview.adapter.CommonAdapter

/**系统
 * @Author 20342
 * @Date 2019/8/21 15:47
 */
class SystemAdapter(context: Context,list: ArrayList<String>):CommonAdapter<String>(context,list, R.layout.item_manage_system) {
    override fun bindData(holder: ViewHolder, data: String, position: Int) {
        var  tvtime=holder.getView<TextView>(R.id.tv_time)
        if(position%3==0){
            tvtime.visibility=View.GONE
        }else{
            tvtime.visibility=View.VISIBLE

        }
    }
}