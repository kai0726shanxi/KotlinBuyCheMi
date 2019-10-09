package com.chmichat.chat.ui.adapter.homeadapter

import android.content.Context
import android.view.View
import com.chmichat.chat.R
import com.chmichat.chat.bean.PostListEntity
import com.chmichat.chat.glide.GlideApp
import com.chmichat.chat.view.recyclerview.MultipleType
import com.chmichat.chat.view.recyclerview.ViewHolder
import com.chmichat.chat.view.recyclerview.adapter.CommonAdapter

/**
 * 相关推荐的适配器
 * @Author 20342
 * @Date 2019/9/20 10:29
 */
class PostRecommendAdapter(context: Context, list: ArrayList<PostListEntity>) : CommonAdapter<PostListEntity>(context, list, object : MultipleType<PostListEntity> {

    override fun getLayoutId(item: PostListEntity, position: Int): Int {
        return when (position) {
            0 ->
                R.layout.item_post_recommend_big
            else ->
                R.layout.item_post_recommend_small

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
        data.postTitle?.let { holder.setText(R.id.tv_title, it) }
        holder.setText(R.id.tv_name,data.sectionName+" "+data.createTime)
          if (data.postCoverList!=null&& data.postCoverList!!.isNotEmpty()){
              holder.setViewVisibility(R.id.iv_bg,View.VISIBLE)

              GlideApp.with(mContext)
                      .load(data.postCoverList!![0].coverImg)
                      .into(holder.getView(R.id.iv_bg))

          }else{
              holder.setViewVisibility(R.id.iv_bg,View.GONE)
          }


    }

}