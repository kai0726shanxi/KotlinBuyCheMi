package com.chmichat.chat.ui.adapter.homeadapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.chmichat.chat.Constants
import com.chmichat.chat.R
import com.chmichat.chat.bean.PostListEntity
import com.chmichat.chat.glide.GlideApp
import com.chmichat.chat.glide.GlideRoundTransform
import com.chmichat.chat.ui.activity.home.ActivityProjectActivity
import com.chmichat.chat.view.recyclerview.ViewHolder
import com.chmichat.chat.view.recyclerview.adapter.CommonAdapter

/**
 * @Author 20342
 * @Date 2019/8/7 9:54
 */
class HomeImageTextAdapter(context: Context, data: ArrayList<PostListEntity>): CommonAdapter<PostListEntity>(context, data, R.layout.item_home_imgetext) {


    fun addDataNew(dataList: java.util.ArrayList<PostListEntity>) {
        this.mData.clear()
        this.mData.addAll(dataList)
        notifyDataSetChanged()
    }
    override fun bindData(holder: ViewHolder, data: PostListEntity, position: Int) {
        var ivbg=holder.getView<ImageView>(R.id.iv_bg)
        var tvtag=holder.getView<TextView>(R.id.tv_tag)
        GlideApp.with(mContext)
                .load(data.firstCover)
                .placeholder(R.mipmap.moren_icon)
                .transition(DrawableTransitionOptions().crossFade())
                .into(ivbg)
        data.sectionName?.let { holder.setText(R.id.tv_name, it) }

       //"isHot": 0,是否热门:0:否;1:是; <number>
        //"isEssence": 0,是否精华:0:否;1:是; <number>
        //"isTop": 0,是否置顶:0:否;1:是; <number>
        when {
            position%3==0 -> {
                tvtag.setBackgroundResource(R.drawable.bg_radius_small_green)
            }
            position%3==1 -> {
                tvtag.setBackgroundResource(R.drawable.bg_radius_small_blue)

            }
            position%3==2 -> {
                tvtag.setBackgroundResource(R.drawable.bg_radius_small_yellow)

            }
        }

        tvtag.visibility=View.VISIBLE
        when {

            data.isHot==1 -> {
            tvtag.text="热门"
            }
            data.isEssence==1 -> {
                tvtag.text="精华"

            }
            data.isTop==1 -> {
                tvtag.text="置顶"

            }
            else -> tvtag.visibility=View.GONE
        }


        holder.setOnItemClickListener(View.OnClickListener {
            val intent = Intent(mContext as Activity, ActivityProjectActivity::class.java)
            intent.putExtra(Constants.KEYNAME,mData[position])
            mContext.startActivity(intent)
        })
    }
}