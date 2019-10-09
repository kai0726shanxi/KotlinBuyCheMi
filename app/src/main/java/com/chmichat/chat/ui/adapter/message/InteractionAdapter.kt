package com.chmichat.chat.ui.adapter.message

import android.content.Context
import android.view.View
import com.chmichat.chat.R
import com.chmichat.chat.api.UrlConstant
import com.chmichat.chat.bean.InteractListMessageEntity
import com.chmichat.chat.glide.GlideApp
import com.chmichat.chat.view.recyclerview.ViewHolder
import com.chmichat.chat.view.recyclerview.adapter.CommonAdapter

/**
 * 互动
 * @Author 20342
 * @Date 2019/8/21 15:47
 */
class InteractionAdapter(context: Context, list: ArrayList<InteractListMessageEntity>) : CommonAdapter<InteractListMessageEntity>(context, list, R.layout.item_manage_interaction) {

    private var mOnTagItemClick: ((tag:InteractListMessageEntity) -> Unit)? = null

    fun setOnTagItemClickListener(onTagItemClickListener:(tag:InteractListMessageEntity) -> Unit) {
        this.mOnTagItemClick = onTagItemClickListener
    }
    /**
     * 追加数据
     */
    fun setDataAll(categoryList: ArrayList<InteractListMessageEntity>) {
        this.mData.addAll(categoryList)
        notifyDataSetChanged()
    }

    /**
     * 设置新数据
     */
    fun setDataNew(categoryList: ArrayList<InteractListMessageEntity>) {
        this.mData.clear()
        this.mData.addAll(categoryList)
        notifyDataSetChanged()
    }

    override fun bindData(holder: ViewHolder, data: InteractListMessageEntity, position: Int) {

        GlideApp.with(mContext)
                .load(UrlConstant.BASE_URL_IMAGE + data.sendUserId + ".png")
                .placeholder(R.mipmap.head_ic)
                .into(holder.getView(R.id.iv_head))
        data.sendUserName?.let { holder.setText(R.id.tv_name, it) }
        //消息类型1赞，2踩，3收藏，4评论帖子，5回复评论
        when(data?.msgType){
            1->{holder.setText(R.id.tv_content, "赞了你的作品")}
            2->{holder.setText(R.id.tv_content, "踩了你的作品")}
            3->{holder.setText(R.id.tv_content, "收藏了你的作品")}
            4->{holder.setText(R.id.tv_content, "评论了你的作品")}
            5->{holder.setText(R.id.tv_content, "回复了你的作品")}
            else->{
                holder.setText(R.id.tv_content, "")
            }
        }
       // data.sendContent?.let { holder.setText(R.id.tv_content, it) }
        GlideApp.with(mContext)
                .load(data.contentCover)
                .placeholder(R.mipmap.moren_icon)
                .into(holder.getView(R.id.iv_content))
        holder.setText(R.id.tv_time,data?.createTime.toString())

        holder.setOnItemClickListener(listener = View.OnClickListener {
            mOnTagItemClick?.invoke(data)

        })
    }
}