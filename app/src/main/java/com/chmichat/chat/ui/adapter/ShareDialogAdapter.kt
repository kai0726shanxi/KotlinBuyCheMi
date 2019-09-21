package com.chmichat.chat.ui.adapter

import android.content.Context
import com.chmichat.chat.R
import com.chmichat.chat.glide.GlideApp
import com.chmichat.chat.view.recyclerview.ViewHolder
import com.chmichat.chat.view.recyclerview.adapter.CommonAdapter

/**
 * @Author 20342
 * @Date 2019/9/20 17:15
 */
class ShareDialogAdapter(context: Context, list: ArrayList<String>) : CommonAdapter<String>(context, list, R.layout.item_sharedialog_layout) {
    override fun bindData(holder: ViewHolder, data: String, position: Int) {
        when (data) {
            mContext.getString(R.string.share_wx) -> {
                GlideApp.with(mContext)
                        .load(R.mipmap.share_weixin_ic)
                        .into(holder.getView(R.id.iv_avater))
                holder.setText(R.id.tv_name, mContext.getString(R.string.share_wx))
            }
            mContext.getString(R.string.share_friend) -> {
                GlideApp.with(mContext)
                        .load(R.mipmap.share_weixin_friend_ic)
                        .into(holder.getView(R.id.iv_avater))
                holder.setText(R.id.tv_name, mContext.getString(R.string.share_friend))
            }
            "QQ" -> {
                GlideApp.with(mContext)
                        .load(R.mipmap.share_qq_ic)
                        .into(holder.getView(R.id.iv_avater))
                holder.setText(R.id.tv_name, "QQ")
            }
            mContext.getString(R.string.share_weibo) -> {
                GlideApp.with(mContext)
                        .load(R.mipmap.share_weibo_ic)
                        .into(holder.getView(R.id.iv_avater))
                holder.setText(R.id.tv_name, mContext.getString(R.string.share_weibo))
            }
            mContext.getString(R.string.share_copy) -> {
                GlideApp.with(mContext)
                        .load(R.mipmap.share_copy_ic)
                        .into(holder.getView(R.id.iv_avater))
                holder.setText(R.id.tv_name, mContext.getString(R.string.share_copy))
            }
            mContext.getString(R.string.share_QR) -> {
                GlideApp.with(mContext)
                        .load(R.mipmap.share_qr_code_ic)
                        .into(holder.getView(R.id.iv_avater))
                holder.setText(R.id.tv_name, mContext.getString(R.string.share_QR))
            }
            mContext.getString(R.string.share_report) -> {
                GlideApp.with(mContext)
                        .load(R.mipmap.share_report_ic)
                        .into(holder.getView(R.id.iv_avater))
                holder.setText(R.id.tv_name, mContext.getString(R.string.share_report))
            }


        }

    }
}