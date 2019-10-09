package com.chmichat.chat.ui.adapter.homeadapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import com.chmichat.chat.Constants
import com.chmichat.chat.R
import com.chmichat.chat.bean.PostListEntity
import com.chmichat.chat.getTime4String
import com.chmichat.chat.ui.activity.home.PostDetailsActivity
import com.chmichat.chat.view.recyclerview.ViewHolder
import com.chmichat.chat.view.recyclerview.adapter.CommonAdapter
import java.util.*

/**
 * @Author 20342
 * 热门帖子
 * @Date 2019/8/7 18:32
 */
class HomePopularAdapter(context: Context, data: ArrayList<PostListEntity>):CommonAdapter<PostListEntity>(context, data, R.layout.item_home_ipopular){


    fun addData(dataList: ArrayList<PostListEntity>) {
        this.mData.clear()
        this.mData.addAll(dataList)
        notifyDataSetChanged()
    }
    override fun bindData(holder: ViewHolder, data: PostListEntity, position: Int) {
        data.postTitle?.let { holder.setText(R.id.tv_title, it) }
        if (!data.sectionName.isNullOrEmpty()){

            data.createTime?.let { holder.setText(R.id.tv_time, data.sectionName+"  "+mContext.getTime4String(it) ) }

        }else{
            data.createTime?.let { holder.setText(R.id.tv_time, mContext.getTime4String(it) )}

        }
        holder.setText(R.id.tv_read,data.postStatisticsData?.readingNum.toString())
        holder.setOnItemClickListener(listener = View.OnClickListener {
            val intent = Intent(mContext as Activity, PostDetailsActivity::class.java)
            intent.putExtra(Constants.KEYNAME, data.id)
            mContext.startActivity(intent)

        })
    }

}