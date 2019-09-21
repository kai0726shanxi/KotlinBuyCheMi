package com.chmichat.chat.ui.adapter.homeadapter

import android.content.Context
import android.view.View
import com.chmichat.chat.R
import com.chmichat.chat.api.UrlConstant
import com.chmichat.chat.bean.CommentListEntity
import com.chmichat.chat.getTime4String
import com.chmichat.chat.glide.GlideApp
import com.chmichat.chat.view.recyclerview.ViewHolder
import com.chmichat.chat.view.recyclerview.adapter.CommonAdapter

/**
 * @Author 20342
 * @Date 2019/8/21 10:44
 */
class HomeCommentAdapter(context: Context,list: ArrayList<CommentListEntity>):CommonAdapter<CommentListEntity>(context,list, R.layout.item_home_comment) {
    private var mOnTitletemClick: ((tag: CommentListEntity) -> Unit)? = null

    fun setOnTitleItemClickListener(onTitleItemClickListener: (tag: CommentListEntity) -> Unit) {
        this.mOnTitletemClick = onTitleItemClickListener
    }
    fun addDataNew(dataList: ArrayList<CommentListEntity>) {
        this.mData.clear()
        this.mData.addAll(dataList)
        notifyDataSetChanged()
    }
    fun addDataAll(dataList: ArrayList<CommentListEntity>) {
        this.mData.addAll(dataList)
        notifyDataSetChanged()
    }
    override fun bindData(holder: ViewHolder, data: CommentListEntity, position: Int) {
         if (data.level=="1"){
             GlideApp.with(mContext)
                     .load(UrlConstant.BASE_URL_IMAGE+data.sendUserId+".png")
             data.sendUserName?.let { holder.setText(R.id.tv_name, it) }
             data.comment?.let { holder.setText(R.id.tv_content, it) }

         }else{
             GlideApp.with(mContext)
                     .load(UrlConstant.BASE_URL_IMAGE+data.sendUserId+".png")
             data.sendUserName?.let { holder.setText(R.id.tv_name, it+" 回复 "+data.receiveUserName) }
             data.comment?.let { holder.setText(R.id.tv_content, it) }
         }
        data.createTime?.let { mContext.getTime4String(it) }?.let { holder.setText(R.id.tv_time, it) }



        holder.setOnItemClickListener(View.OnClickListener { mOnTitletemClick?.invoke(data) })
    }
}