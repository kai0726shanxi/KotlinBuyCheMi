package com.chmichat.chat.ui.adapter.message

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.TextView
import com.chmichat.chat.R
import com.chmichat.chat.bean.LastMessageEntivity
import com.chmichat.chat.ui.activity.manage.NotificationActivity
import com.chmichat.chat.view.recyclerview.ViewHolder
import com.chmichat.chat.view.recyclerview.adapter.CommonAdapter

/**
 * @Author 20342
 * @Date 2019/8/19 15:50
 */
class MessageListAdapter(context: Context, list: ArrayList<LastMessageEntivity>) : CommonAdapter<LastMessageEntivity>(context, list, R.layout.item_message_list) {


    fun setDataNew(categoryList: ArrayList<LastMessageEntivity>) {
        this.mData.addAll(categoryList)
        notifyDataSetChanged()
    }

    override fun bindData(holder: ViewHolder, data: LastMessageEntivity, position: Int) {

        if (position == 0) {
            holder.setText(R.id.tv_title, "系统消息")
            data?.systemVo?.createTime?.let { holder.setText(R.id.tv_time, it) }
            data?.systemVo?.noticeTitle?.let { holder.setText(R.id.tv_content, it) }
            if (data?.systemVo?.whetherReadNum != null&&data?.systemVo?.whetherReadNum!=0) {
                holder.setText(R.id.tv_num, data?.systemVo?.whetherReadNum.toString())
                holder.setViewVisibility(R.id.tv_num, View.VISIBLE)
            } else {
                holder.setViewVisibility(R.id.tv_num, View.INVISIBLE)

            }
        } else {
            holder.setText(R.id.tv_title, "互动消息")
            data?.interactiveVo?.createTime?.let { holder.setText(R.id.tv_time, it) }
            data?.interactiveVo?.sendContent?.let { holder.setText(R.id.tv_content, it) }
            if (data?.interactiveVo?.unreadNum != null&&data?.interactiveVo?.unreadNum !=0) {
                holder.setText(R.id.tv_num, data?.interactiveVo?.unreadNum.toString())
                holder.setViewVisibility(R.id.tv_num, View.VISIBLE)
            } else {
                holder.setViewVisibility(R.id.tv_num, View.INVISIBLE)

            }
        }



        holder.setOnItemClickListener(listener = View.OnClickListener {
            var intent = Intent(mContext as Activity, NotificationActivity::class.java)
            intent.putExtra("position", position)
            mContext.startActivity(intent)


        })
    }
}