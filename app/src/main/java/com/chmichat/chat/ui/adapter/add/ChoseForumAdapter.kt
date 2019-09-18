package com.chmichat.chat.ui.adapter.add

import android.content.Context
import android.view.View
import com.chmichat.chat.R
import com.chmichat.chat.bean.ForumListEntity
import com.chmichat.chat.view.recyclerview.ViewHolder
import com.chmichat.chat.view.recyclerview.adapter.CommonAdapter

/**
 * 选择论坛的适配器
 * @Author 20342
 * @Date 2019/8/15 17:07
 */
class ChoseForumAdapter(context: Context, data: ArrayList<ForumListEntity>) : CommonAdapter<ForumListEntity>(context, data, R.layout.item_choseforum_layout) {

    fun addData(dataList: ArrayList<ForumListEntity>) {
        this.mData.addAll(dataList)
        notifyDataSetChanged()
    }
    private var mOnTagItemClick: ((tag:ForumListEntity) -> Unit)? = null

    fun setOnTagItemClickListener(onTagItemClickListener:(tag:ForumListEntity) -> Unit) {
        this.mOnTagItemClick = onTagItemClickListener
    }
    override fun bindData(holder: ViewHolder, data: ForumListEntity, position: Int) {

        data?.sectionName?.let { holder.setText(R.id.tv_name, it) }

        holder.setOnItemClickListener(listener = View.OnClickListener {
            mOnTagItemClick?.invoke(data)

        })
    }
}