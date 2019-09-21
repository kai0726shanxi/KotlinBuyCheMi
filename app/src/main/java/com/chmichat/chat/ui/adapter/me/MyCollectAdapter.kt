package com.chmichat.chat.ui.adapter.me

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import com.chmichat.chat.Constants
import com.chmichat.chat.R
import com.chmichat.chat.api.UrlConstant
import com.chmichat.chat.bean.PostListEntity
import com.chmichat.chat.getTime4String
import com.chmichat.chat.glide.GlideApp
import com.chmichat.chat.ui.activity.home.AllDynamicActivity
import com.chmichat.chat.ui.activity.home.PlayVideoActivity
import com.chmichat.chat.view.recyclerview.MultipleType
import com.chmichat.chat.view.recyclerview.ViewHolder
import com.chmichat.chat.view.recyclerview.adapter.CommonAdapter

/**
 * @Author 20342
 * @Date 2019/9/12 16:35
 */
class MyCollectAdapter(context: Context, data: ArrayList<PostListEntity>) : CommonAdapter<PostListEntity>(context, data, object : MultipleType<PostListEntity> {


    override fun getLayoutId(item: PostListEntity, position: Int): Int {
        return when (item.collectionType) {
            1 -> {
                R.layout.item_meforum_layout

            }
            2 -> {
                R.layout.item_medynamic_layout

            }

            3 -> {
                R.layout.item_mevideo_layout

            }
            4 -> {
                R.layout.item_melongvideo_layout

            }

            else -> {
                R.layout.item_meforum_layout

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
        when (data.collectionType) {
            1 -> {
                GlideApp.with(mContext)
                        .load(data.sectionIcon)
                        .placeholder(R.mipmap.moren_icon)
                        .into(holder.getView(R.id.iv_head))
                data.sectionName?.let { holder.setText(R.id.tv_tag, it) }
                holder.setText(R.id.tv_num, "阅读 ${data.readingNum} / 帖子 ${data.postTotal}")
                holder.setOnItemClickListener(View.OnClickListener {
                    val intent = Intent(mContext as Activity, AllDynamicActivity::class.java)
                    intent.putExtra(Constants.KEYNAME,mData[position].collectionId)
                    mContext.startActivity(intent)
                })
            }
            2 -> {

                data.postCreateTime?.let { mContext.getTime4String(it) }?.let { holder.setText(R.id.tv_time, it) }
                data.postTitle?.let { holder.setText(R.id.tv_title, it) }
                 holder.setText(R.id.tv_source,"来自  "+data.sectionName)
                holder.setText(R.id.tv_comment,data.commentsNum.toString())
                holder.setText(R.id.tv_zan,data.praiseNum.toString())

            }
            3 -> {
                GlideApp.with(mContext)
                        .load(data.postCover)
                        .placeholder(R.mipmap.moren_icon)
                        .into(holder.getView(R.id.iv_video))
                data.position?.let { holder.setText(R.id.tv_address, it) }
                data.postTitle?.let { holder.setText(R.id.tv_title, it) }
                GlideApp.with(mContext)
                        .load(UrlConstant.BASE_URL_IMAGE + data.authorId + ".png")
                        .placeholder(R.mipmap.moren_icon)
                        .into(holder.getView(R.id.iv_avater))
                data.author?.let { holder.setText(R.id.tv_name, it) }
                holder.setText(R.id.tv_zan, data.praiseNum.toString())

                holder.setOnItemClickListener(listener = View.OnClickListener {
                    val intent = Intent(mContext as Activity, PlayVideoActivity::class.java)
                    intent.putExtra(Constants.PLAYPOSITION,position)
                    intent.putExtra(Constants.PLAYLIST,mData)
                    intent.putExtra(Constants.KEYTYPE,"me")

                    mContext.startActivity(intent)
                })

            }

            4 -> {
                GlideApp.with(mContext)
                        .load(UrlConstant.BASE_URL_IMAGE + data.authorId + ".png")
                        .placeholder(R.mipmap.head_ic)
                        .into(holder.getView(R.id.iv_avater))
                data.author?.let { holder.setText(R.id.tv_name, it) }
                data.postCreateTime?.let { mContext.getTime4String(it) }?.let { holder.setText(R.id.tv_time, it) }
                GlideApp.with(mContext)
                        .load(data.postCover)
                        .placeholder(R.mipmap.moren_icon)
                        .into(holder.getView(R.id.iv_video))
                holder.setText(R.id.tv_num, data.readingNum.toString())
                data.postTitle?.let { holder.setText(R.id.tv_title, it) }
                holder.setText(R.id.tv_share, data.shareNum.toString())
                holder.setText(R.id.tv_comment, data.commentsNum.toString())
                holder.setText(R.id.tv_zan,data.praiseNum.toString())
            }


        }

    }
}