package com.chmichat.chat.ui.adapter.homeadapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.TextView
import com.chmichat.chat.R
import com.chmichat.chat.R.id.tv_name
import com.chmichat.chat.api.UrlConstant
import com.chmichat.chat.bean.PostListEntity
import com.chmichat.chat.getTime4String
import com.chmichat.chat.glide.GlideApp
import com.chmichat.chat.view.recyclerview.ViewHolder
import com.chmichat.chat.view.recyclerview.adapter.CommonAdapter

/**
 * @Author 20342
 * @Date 2019/8/8 9:46
 * 论坛动态的适配器
 */
class HomeDynamicAdapter(context: Context, data: ArrayList<PostListEntity>) : CommonAdapter<PostListEntity>(context, data, R.layout.item_home_dynamic) {

    fun addDataNew(dataList: java.util.ArrayList<PostListEntity>) {
        this.mData.clear()
        this.mData.addAll(dataList)
        notifyDataSetChanged()
    }
    fun addDataAll(dataList: java.util.ArrayList<PostListEntity>) {
        this.mData.addAll(dataList)
        notifyDataSetChanged()
    }
    override fun bindData(holder: ViewHolder, data: PostListEntity, position: Int) {
        GlideApp.with(mContext)
                .load(UrlConstant.BASE_URL_IMAGE+data.userId+".png")
                .placeholder(R.mipmap.head_ic)
                .into(holder.getView(R.id.iv_head))
        holder.getView<TextView>(R.id.tv_name).text=data.userName
        data.sectionName?.let { holder.setText(R.id.tv_type, it) }
        holder.setText(R.id.tv_discuss,data.postStatisticsData?.commentsNum.toString())
        holder.setText(R.id.tv_zan,data.postStatisticsData?.praiseNum.toString())
        holder.setText(R.id.tv_cai,data.postStatisticsData?.treadNum.toString())
        holder.setText(R.id.tv_share,data.postStatisticsData?.shareNum.toString())
        data.createTime?.let { holder.setText(R.id.tv_time, mContext.getTime4String(it)) }
        data.postTitle?.let { holder.setText(R.id.tv_title, it) }
        if (data.postCoverList!=null&& data.postCoverList?.size!! >0){
            holder.setViewVisibility(R.id.cl_content_img, View.VISIBLE)
            if (data.postCoverList?.size!!<3){
                holder.setViewVisibility(R.id.iv_content,View.VISIBLE)
                holder.setViewVisibility(R.id.cl_show_img,View.GONE)
                GlideApp.with(mContext)
                        .load(data.postCoverList?.get(0)?.coverImg)
                        .placeholder(R.mipmap.moren_icon)
                        .into(holder.getView(R.id.iv_content))

            }else{
                holder.setViewVisibility(R.id.iv_content,View.GONE)
                holder.setViewVisibility(R.id.cl_show_img,View.VISIBLE)
                GlideApp.with(mContext)
                        .load(data.postCoverList?.get(0)?.coverImg)
                        .placeholder(R.mipmap.moren_icon)
                        .into(holder.getView(R.id.iv_one))
                GlideApp.with(mContext)
                        .load(data.postCoverList?.get(1)?.coverImg)
                        .placeholder(R.mipmap.moren_icon)
                        .into(holder.getView(R.id.iv_two))
                GlideApp.with(mContext)
                        .load(data.postCoverList?.get(2)?.coverImg)
                        .placeholder(R.mipmap.moren_icon)
                        .into(holder.getView(R.id.iv_three))
            }



        }else{
            holder.setViewVisibility(R.id.cl_content_img, View.GONE)

        }

    }


}