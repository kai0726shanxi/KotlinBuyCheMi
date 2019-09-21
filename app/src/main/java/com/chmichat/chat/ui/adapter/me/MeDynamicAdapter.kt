package com.chmichat.chat.ui.adapter.me

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
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
 * 动态适配器
 * @Author 20342
 * @Date 2019/8/9 11:35
 */
class MeDynamicAdapter(context: Context, data: ArrayList<PostListEntity>) : CommonAdapter<PostListEntity>(context, data, object : MultipleType<PostListEntity> {


    override fun getLayoutId(item: PostListEntity, position: Int): Int {
        return when (item.type) {
            3 -> {
                R.layout.item_mevideo_layout

            }
            4 -> {
                R.layout.item_melongvideo_layout

            }

            else -> {
                R.layout.item_medynamic_layout

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
        Log.e(">>startPlay>",position.toString())

        when(data.type){
            3->{
             if (data.firstCover!=null){
                 GlideApp.with(mContext)
                         .load(data.firstCover)
                         .placeholder(R.mipmap.moren_icon)
                         .into(holder.getView(R.id.iv_video))
             }
            if (data.userId!=null){
                GlideApp.with(mContext)
                        .load(UrlConstant.BASE_URL_IMAGE+data.userId+".png")
                        .placeholder(R.mipmap.head_ic)
                        .into(holder.getView(R.id.iv_avater))
            }

                data.userName?.let { holder.setText(R.id.tv_name, it) }
                data.postTitle?.let { holder.setText(R.id.tv_title, it) }
                holder.setText(R.id.tv_zan,data.postStatisticsData?.praiseNum.toString())
                data.position?.let { holder.setText(R.id.tv_address, it) }
                holder.setOnItemClickListener(listener = View.OnClickListener {
                    val intent = Intent(mContext as Activity, PlayVideoActivity::class.java)
                    intent.putExtra(Constants.PLAYPOSITION,position)
                    intent.putExtra(Constants.PLAYLIST,mData)

                    mContext.startActivity(intent)
                })
                holder.setOnItemClickListener(listener = View.OnClickListener {
                    val intent = Intent(mContext as Activity, PlayVideoActivity::class.java)
                    intent.putExtra(Constants.PLAYPOSITION, position)
                    intent.putExtra(Constants.PLAYLIST, mData)

                    mContext.startActivity(intent)
                })
            }
            4->{
                if (data.firstCover!=null){
                    GlideApp.with(mContext)
                            .load(data.firstCover)
                            .placeholder(R.mipmap.moren_icon)
                            .into(holder.getView(R.id.iv_video))
                }
                if (data.userId!=null){
                    GlideApp.with(mContext)
                            .load(UrlConstant.BASE_URL_IMAGE+data.userId+".png")
                            .placeholder(R.mipmap.head_ic)
                            .into(holder.getView(R.id.iv_avater))
                }

                data.userName?.let { holder.setText(R.id.tv_name, it) }
                data.postTitle?.let { holder.setText(R.id.tv_title, it) }
                data.createTime?.let { mContext.getTime4String(it) }?.let { holder.setText(R.id.tv_time, it) }
                holder.setText(R.id.tv_share,data.postStatisticsData?.shareNum.toString())
                holder.setText(R.id.tv_comment,data.postStatisticsData?.commentsNum.toString())
                holder.setText(R.id.tv_zan,data.postStatisticsData?.praiseNum.toString())
                holder.setOnItemClickListener(listener = View.OnClickListener {
                    val intent = Intent(mContext as Activity, ReleaseLongVideoActivity::class.java)
                    intent.putExtra(Constants.PLAYLIST,mData[position])

                    mContext.startActivity(intent)
                })
            }
            else->{
                data.createTime?.let { mContext.getTime4String(it) }?.let { holder.setText(R.id.tv_time, it) }
                data.postTitle?.let { holder.setText(R.id.tv_title, it) }
                data.sectionName?.let { holder.setText(R.id.tv_source, "来自  $it") }
                holder.setText(R.id.tv_comment,data.postStatisticsData?.commentsNum.toString())
                holder.setText(R.id.tv_zan,data.postStatisticsData?.praiseNum.toString())
                holder.setOnItemClickListener(listener = View.OnClickListener {
                    val intent = Intent(mContext as Activity, PostDetailsActivity::class.java)
                    intent.putExtra(Constants.KEYNAME, data.id)
                    mContext.startActivity(intent)

                })
            }
        }




    }
}