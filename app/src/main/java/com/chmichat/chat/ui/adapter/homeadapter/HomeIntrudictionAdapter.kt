package com.chmichat.chat.ui.adapter.homeadapter

import android.content.Context
import android.view.View
import com.chmichat.chat.R
import com.chmichat.chat.bean.PostListEntity
import com.chmichat.chat.glide.GlideApp
import com.chmichat.chat.view.recyclerview.ViewHolder
import com.chmichat.chat.view.recyclerview.adapter.CommonAdapter

/**
 * 长视频简介
 * @Author 20342
 * @Date 2019/8/21 10:31
 */
class HomeIntrudictionAdapter(context: Context, list: ArrayList<PostListEntity>) : CommonAdapter<PostListEntity>(context, list, R.layout.item_home_intrudiction) {
    private var mOnTitletemClick: ((tag: PostListEntity) -> Unit)? = null

    fun setOnTitleItemClickListener(onTitleItemClickListener: (tag: PostListEntity) -> Unit) {
        this.mOnTitletemClick = onTitleItemClickListener
    }

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

        GlideApp.with(mContext)
                .load(data.firstCover)
                .placeholder(R.mipmap.test_video)
                .into(holder.getView(R.id.iv_logo))
        data.postTitle?.let { holder.setText(R.id.tv_name, it) }
        holder.setText(R.id.tv_num, "如约而至 "+data.postStatisticsData?.readingNum.toString() + "次光看")
        holder.setOnItemClickListener(View.OnClickListener { mOnTitletemClick?.invoke(data) })

    }
}