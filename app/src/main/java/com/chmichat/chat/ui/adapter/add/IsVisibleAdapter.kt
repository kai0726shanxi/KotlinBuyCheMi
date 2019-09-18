package com.chmichat.chat.ui.adapter.add

import android.content.Context
import android.view.View
import com.chmichat.chat.Constants
import com.chmichat.chat.R
import com.chmichat.chat.bean.ForumListEntity
import com.chmichat.chat.bean.IsVisibleEntity
import com.chmichat.chat.utils.SpUtil
import com.chmichat.chat.view.recyclerview.ViewHolder
import com.chmichat.chat.view.recyclerview.adapter.CommonAdapter

/**
 * 是否可见的适配器
 * @Author 20342
 * @Date 2019/9/15 17:07
 */
class IsVisibleAdapter(context: Context, data: ArrayList<IsVisibleEntity>) : CommonAdapter<IsVisibleEntity>(context, data, R.layout.item_visible_layout) {

    var mPosition:Int=SpUtil.getInt(context,Constants.ISVISIBLE,0)
    private var mOnTagItemClick: ((tag:IsVisibleEntity,i:Int) -> Unit)? = null

    fun setOnTagItemClickListener(onTagItemClickListener:(data:IsVisibleEntity,i:Int) -> Unit) {
        this.mOnTagItemClick = onTagItemClickListener
    }
    override fun bindData(holder: ViewHolder, data: IsVisibleEntity, position: Int) {
        data.title?.let { holder.setText(R.id.tv_title, it) }
        data.content?.let { holder.setText(R.id.tv_content, it) }
        if (mPosition==position){
            holder.setViewVisibility(R.id.iv_chose,View.VISIBLE)
        }else{
            holder.setViewVisibility(R.id.iv_chose,View.GONE)

        }
        holder.setOnItemClickListener(listener = View.OnClickListener {
            mOnTagItemClick?.invoke(data,position)

        })
    }
}