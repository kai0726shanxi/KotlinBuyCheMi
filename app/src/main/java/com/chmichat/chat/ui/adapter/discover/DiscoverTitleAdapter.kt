package com.chmichat.chat.ui.adapter.discover

import android.content.Context
import android.graphics.Typeface
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.TextView
import com.chmichat.chat.R
import com.chmichat.chat.view.recyclerview.ViewHolder
import com.chmichat.chat.view.recyclerview.adapter.CommonAdapter

/**
 * 发现顶部的title
 * @Author 20342
 *
 * @Date 2019/8/8 13:59
 */
class DiscoverTitleAdapter(context: Context, data: ArrayList<String>) : CommonAdapter<String>(context, data, R.layout.item_discover_title) {

    var mposition: Int = 0

    private var mOnTitletemClick: ((tag: String,i:Int) -> Unit)? = null

    fun setOnTitleItemClickListener(onTitleItemClickListener: (tag: String,i:Int) -> Unit) {
        this.mOnTitletemClick = onTitleItemClickListener
    }

    override fun bindData(holder: ViewHolder, data: String, position: Int) {

        val tv = holder.getView<TextView>(R.id.tv_title)
        tv.text = data
        if (position == mposition) {
            tv.textSize = 22f
            tv.setTextColor(ContextCompat.getColor(mContext, R.color.black))
            holder.setViewVisibility(R.id.iv_point,View.VISIBLE)
            tv.setTypeface(Typeface.DEFAULT_BOLD, Typeface.BOLD)

        } else {
            tv.textSize = 14f
            tv.setTextColor(ContextCompat.getColor(mContext, R.color.hometopright))
            holder.setViewVisibility(R.id.iv_point,View.INVISIBLE)
            tv.setTypeface(Typeface.DEFAULT, Typeface.NORMAL)


        }
        holder.setOnItemClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                mOnTitletemClick?.invoke(data,position)
            }

        }
        )
    }
}


