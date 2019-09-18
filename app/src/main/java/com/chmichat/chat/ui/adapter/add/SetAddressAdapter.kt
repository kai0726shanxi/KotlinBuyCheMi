package com.chmichat.chat.ui.adapter.add

import android.content.Context
import android.view.View
import com.chmichat.chat.R
import com.chmichat.chat.bean.ForumListEntity
import com.chmichat.chat.view.recyclerview.ViewHolder
import com.chmichat.chat.view.recyclerview.adapter.CommonAdapter

/**
 * 设置地址的适配器
 * @Author 20342
 * @Date 2019/8/15 17:07
 */
class SetAddressAdapter(context: Context, data:ArrayList<String>):CommonAdapter<String>(context,data, R.layout.item_choseforum_layout) {


    private var mOnTagItemClick: ((tag: String) -> Unit)? = null

    fun setOnTagItemClickListener(onTagItemClickListener:(tag: String) -> Unit) {
        this.mOnTagItemClick = onTagItemClickListener
    }
    override fun bindData(holder: ViewHolder, data: String, position: Int) {
      holder.setText(R.id.tv_name,data)
        holder.setOnItemClickListener(listener = View.OnClickListener {
            mOnTagItemClick?.invoke(data)

        })
    }
}