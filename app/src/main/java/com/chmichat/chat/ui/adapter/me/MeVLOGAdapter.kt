package com.chmichat.chat.ui.adapter.me

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import com.chmichat.chat.R
import com.chmichat.chat.bean.CollectEntity
import com.chmichat.chat.ui.activity.add.ReleaseLongVideoActivity
import com.chmichat.chat.view.recyclerview.ViewHolder
import com.chmichat.chat.view.recyclerview.adapter.CommonAdapter

/**
 * 长视频适配器
 * @Author 20342
 * @Date 2019/8/9 11:35
 */
class MeVLOGAdapter(context: Context, data:ArrayList<CollectEntity>):CommonAdapter<CollectEntity>(context,data, R.layout.item_melongvideo_layout){

    fun addDataNew(dataList: ArrayList<CollectEntity>) {
        this.mData.clear()
        this.mData.addAll(dataList)
        notifyDataSetChanged()
    }
    fun addDataAll(dataList: ArrayList<CollectEntity>) {
        this.mData.addAll(dataList)
        notifyDataSetChanged()
    }

    override fun bindData(holder: ViewHolder, data: CollectEntity, position: Int) {

        holder.setOnItemClickListener(listener = View.OnClickListener {
            mContext.startActivity(Intent(mContext as Activity,ReleaseLongVideoActivity::class.java))
        })
    }
}