package com.chmichat.chat.ui.adapter.message

import android.content.Context
import android.view.View
import android.widget.TextView
import com.chmichat.chat.R
import com.chmichat.chat.bean.SystemListMessageEntity
import com.chmichat.chat.glide.GlideApp
import com.chmichat.chat.view.recyclerview.ViewHolder
import com.chmichat.chat.view.recyclerview.adapter.CommonAdapter

/**系统
 * @Author 20342
 * @Date 2019/8/21 15:47
 */
class SystemAdapter(context: Context,list: ArrayList<SystemListMessageEntity>):CommonAdapter<SystemListMessageEntity>(context,list, R.layout.item_manage_system) {

    private var mOnTagItemClick: ((tag:SystemListMessageEntity) -> Unit)? = null

    fun setOnTagItemClickListener(onTagItemClickListener:(tag:SystemListMessageEntity) -> Unit) {
        this.mOnTagItemClick = onTagItemClickListener
    }
    /**
     * 追加数据
     */
    fun setDataAll(categoryList: ArrayList<SystemListMessageEntity>) {
        this.mData.addAll(categoryList)
        notifyDataSetChanged()
    }
    /**
     * 设置新数据
     */
    fun setDataNew(categoryList: ArrayList<SystemListMessageEntity>) {
        this.mData.clear()
        this.mData.addAll(categoryList)
        notifyDataSetChanged()
    }


    override fun bindData(holder: ViewHolder, data: SystemListMessageEntity, position: Int) {
        var  tvtime=holder.getView<TextView>(R.id.tv_time)
        if(position%3==0){
            tvtime.visibility=View.GONE
        }else{
            tvtime.visibility=View.VISIBLE

        }

        data.noticeTitle?.let { holder.setText(R.id.tv_title, it) }
        data.contentDesc?.let { holder.setText(R.id.tv_content, it) }
        if (data.cover!=""){
         GlideApp.with(mContext)
                 .load(data.cover)
                 .placeholder(R.mipmap.moren_icon)
                 .into(holder.getView(R.id.iv_content))
        }

        holder.setOnItemClickListener(listener = View.OnClickListener {
            mOnTagItemClick?.invoke(data)

        })
    }
}