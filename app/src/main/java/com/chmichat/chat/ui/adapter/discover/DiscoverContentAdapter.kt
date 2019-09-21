package com.chmichat.chat.ui.adapter.discover

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.text.Html
import android.view.View
import android.widget.TextView
import com.chmichat.chat.Constants
import com.chmichat.chat.R
import com.chmichat.chat.api.UrlConstant
import com.chmichat.chat.bean.PostListEntity
import com.chmichat.chat.getTime4String
import com.chmichat.chat.glide.GlideApp
import com.chmichat.chat.ui.activity.add.ReleaseLongVideoActivity
import com.chmichat.chat.ui.activity.home.PlayVideoActivity
import com.chmichat.chat.ui.activity.home.PostDetailsActivity
import com.chmichat.chat.view.recyclerview.MultipleType
import com.chmichat.chat.view.recyclerview.ViewHolder
import com.chmichat.chat.view.recyclerview.adapter.CommonAdapter

/**
 * @Author 20342
 * @Date 2019/8/8 15:37
 */
class DiscoverContentAdapter(context: Context, data: ArrayList<PostListEntity>, p: String) : CommonAdapter<PostListEntity>(context, data, object : MultipleType<PostListEntity> {

    override fun getLayoutId(item: PostListEntity, position: Int): Int {
        return when (item.type) {
            1 ->
                R.layout.item_discover_content_small
            2 ->
                R.layout.item_discover_content_big
            3 ->
                R.layout.item_mevideo_layout
            4 ->
                R.layout.item_melongvideo_layout
            else -> {
                R.layout.item_discover_content_small
            }
        }
    }
}) {


    fun addDataNew(dataList: ArrayList<PostListEntity>) {
        this.mData.clear()
        this.mData.addAll(dataList)
        notifyDataSetChanged()
    }

    fun addDataAll(dataList: ArrayList<PostListEntity>) {
        this.mData.addAll(dataList)
        notifyDataSetChanged()
    }

    override fun bindData(holder: ViewHolder, data: PostListEntity, position: Int) {
        when (data.type) {
            1 -> {
                data.postTitle?.let { holder.setText(R.id.tv_title, it) }
                GlideApp.with(mContext)
                        .load(UrlConstant.BASE_URL_IMAGE + data.userId + ".png")
                        .placeholder(R.mipmap.head_ic)
                        .into(holder.getView(R.id.iv_head))
                data.userName?.let { holder.setText(R.id.tv_name, it) }
                data.desc?.let { holder.setText(R.id.tv_content, it) }
                data.createTime?.let { mContext.getTime4String(it) }?.let { holder.setText(R.id.tv_time, it) }
                holder.setText(R.id.tv_discuss, data.postStatisticsData?.commentsNum.toString())
                holder.setText(R.id.tv_zan, data.postStatisticsData?.praiseNum.toString())
                holder.setText(R.id.tv_cai, data.postStatisticsData?.treadNum.toString())
                holder.setText(R.id.tv_share, data.postStatisticsData?.shareNum.toString())
                holder.setOnItemClickListener(listener = View.OnClickListener {
                    val intent = Intent(mContext as Activity, PostDetailsActivity::class.java)
                    intent.putExtra(Constants.KEYNAME, data.id)
                    mContext.startActivity(intent)

                })
            }
            2 -> {

                GlideApp.with(mContext)
                        .load(UrlConstant.BASE_URL_IMAGE + data.userId + ".png")
                        .placeholder(R.mipmap.head_ic)
                        .into(holder.getView(R.id.iv_head))
                holder.getView<TextView>(R.id.tv_name).text = data.userName
                data.desc?.let { holder.setText(R.id.tv_content, it) }
                holder.setText(R.id.tv_discuss, data.postStatisticsData?.commentsNum.toString())
                holder.setText(R.id.tv_zan, data.postStatisticsData?.praiseNum.toString())
                holder.setText(R.id.tv_cai, data.postStatisticsData?.treadNum.toString())
                holder.setText(R.id.tv_share, data.postStatisticsData?.shareNum.toString())
                data.createTime?.let { holder.setText(R.id.tv_time, mContext.getTime4String(it)) }
                data.postTitle?.let { holder.setText(R.id.tv_title, it) }
                if (data.postCoverList != null && data.postCoverList?.size!! > 0) {
                    holder.setViewVisibility(R.id.cl_content_img, View.VISIBLE)
                    if (data.postCoverList?.size!! < 3) {
                        holder.setViewVisibility(R.id.iv_content, View.VISIBLE)
                        holder.setViewVisibility(R.id.cl_show_img, View.GONE)
                        GlideApp.with(mContext)
                                .load(data.postCoverList?.get(0)?.coverImg)
                                .placeholder(R.mipmap.moren_icon)
                                .into(holder.getView(R.id.iv_content))

                    } else {
                        holder.setViewVisibility(R.id.iv_content, View.GONE)
                        holder.setViewVisibility(R.id.cl_show_img, View.VISIBLE)
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


                } else {
                    holder.setViewVisibility(R.id.cl_content_img, View.GONE)

                }
                holder.setOnItemClickListener(listener = View.OnClickListener {
                    val intent = Intent(mContext as Activity, PostDetailsActivity::class.java)
                    intent.putExtra(Constants.KEYNAME, data.id)

                    mContext.startActivity(intent)
                })

            }
            3 -> {
                GlideApp.with(mContext)
                        .load(data.firstCover)
                        .placeholder(R.mipmap.moren_icon)
                        .into(holder.getView(R.id.iv_video))
                data.position?.let { holder.setText(R.id.tv_address, it) }
                data.postTitle?.let { holder.setText(R.id.tv_title, it) }
                GlideApp.with(mContext)
                        .load(UrlConstant.BASE_URL_IMAGE + data.userId + ".png")
                        .placeholder(R.mipmap.head_ic)
                        .into(holder.getView(R.id.iv_avater))
                data.userName?.let { holder.setText(R.id.tv_name, it) }
                holder.setText(R.id.tv_zan, data.postStatisticsData?.praiseNum.toString())
                holder.setOnItemClickListener(listener = View.OnClickListener {
                    val intent = Intent(mContext as Activity, PlayVideoActivity::class.java)
                    intent.putExtra(Constants.PLAYPOSITION, position)
                    intent.putExtra(Constants.PLAYLIST, mData)

                    mContext.startActivity(intent)
                })
            }
            4 -> {
                GlideApp.with(mContext)
                        .load(UrlConstant.BASE_URL_IMAGE + data.userId + ".png")
                        .placeholder(R.mipmap.head_ic)
                        .into(holder.getView(R.id.iv_avater))
                data.userName?.let { holder.setText(R.id.tv_name, it) }
                data.createTime?.let { mContext.getTime4String(it) }?.let { holder.setText(R.id.tv_time, it) }
                GlideApp.with(mContext)
                        .load(data.firstCover)
                        .placeholder(R.mipmap.moren_icon)
                        .into(holder.getView(R.id.iv_video))
                holder.setText(R.id.tv_num, data.postStatisticsData?.readingNum.toString() + "次观看")
                data.postTitle?.let { holder.setText(R.id.tv_title, it) }
                holder.setText(R.id.tv_share, data.postStatisticsData?.shareNum.toString())
                holder.setText(R.id.tv_comment, data.postStatisticsData?.commentsNum.toString())
                holder.setText(R.id.tv_zan, data.postStatisticsData?.praiseNum.toString())
                holder.setOnItemClickListener(listener = View.OnClickListener {
                    val intent = Intent(mContext as Activity, ReleaseLongVideoActivity::class.java)
                    intent.putExtra(Constants.PLAYLIST, mData[position])

                    mContext.startActivity(intent)
                })
            }

        }


    }

}