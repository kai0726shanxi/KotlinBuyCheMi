package com.chmichat.chat.ui.adapter.homeadapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import com.chmichat.chat.Constants
import com.chmichat.chat.R
import com.chmichat.chat.bean.PostListEntity
import com.chmichat.chat.glide.GlideApp
import com.chmichat.chat.ui.activity.home.PostDetailsActivity
import com.chmichat.chat.view.recyclerview.ViewHolder
import com.chmichat.chat.view.recyclerview.adapter.CommonAdapter

/**
 * 活动专题的适配器
 * @Author 20342
 * @Date 2019/8/17 15:51
 */
class ActivityProjectAdapter(context: Context, data:ArrayList<PostListEntity>):CommonAdapter<PostListEntity> (context,data, R.layout.item_activity_project){



    fun addDataNew(dataList: java.util.ArrayList<PostListEntity>) {
        this.mData.clear()
        this.mData.addAll(dataList)
        notifyDataSetChanged()
    }

    fun addDataAll(dataList: java.util.ArrayList<PostListEntity>) {
        this.mData.clear()
        this.mData.addAll(dataList)
        notifyDataSetChanged()
    }
    override fun bindData(holder: ViewHolder, data: PostListEntity, position: Int) {
        GlideApp.with(mContext)
                .load(data.firstCover)
                .placeholder(R.mipmap.moren_icon)
                .into(holder.getView(R.id.iv_content))
        data.postTitle?.let { holder.setText(R.id.tv_tite, it) }

        holder.setOnItemClickListener(listener = View.OnClickListener {
            val intent = Intent(mContext as Activity, PostDetailsActivity::class.java)
            intent.putExtra(Constants.KEYNAME, data.id)
            mContext.startActivity(intent)

        })
    }
}