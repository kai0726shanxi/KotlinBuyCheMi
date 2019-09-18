package com.chmichat.chat.ui.adapter.message

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.TextView
import com.chmichat.chat.R
import com.chmichat.chat.ui.activity.manage.NotificationActivity
import com.chmichat.chat.view.recyclerview.ViewHolder
import com.chmichat.chat.view.recyclerview.adapter.CommonAdapter

/**
 * @Author 20342
 * @Date 2019/8/19 15:50
 */
class MessageListAdapter(context: Context, list: ArrayList<String>) : CommonAdapter<String>(context, list, R.layout.item_message_list) {


    /**
     * 添加更多数据
     */
    fun addItemData(itemList: ArrayList<String>) {
        this.mData.addAll(itemList)
        notifyDataSetChanged()
    }
    override fun bindData(holder: ViewHolder, data: String, position: Int) {
        holder.getView<TextView>(R.id.tv_title).text = data

        holder.setOnItemClickListener(listener = View.OnClickListener {
            var intent = Intent(mContext as Activity, NotificationActivity::class.java)
            intent.putExtra("position", position)
            mContext.startActivity(intent)


        })
    }
}