package com.chmichat.chat.ui.adapter.add

import android.content.Context
import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.TextView
import com.chmichat.chat.R
import com.chmichat.chat.view.recyclerview.ViewHolder
import com.chmichat.chat.view.recyclerview.adapter.CommonAdapter

/**
 * @Author 20342
 * 发布小视频分类
 * @Date 2019/8/17 18:01
 */
class ReleaseVideoAdapter(context: Context, list: ArrayList<String>) : CommonAdapter<String>(context, list, R.layout.item_video_classify) {

    var btnPosition = -1
    private var mOnTagItemClick: ((tag: String,pos:Int) -> Unit)? = null

    fun setOnTagItemClickListener(onTagItemClickListener: (tag: String,pos:Int) -> Unit) {
        this.mOnTagItemClick = onTagItemClickListener
    }

    override fun bindData(holder: ViewHolder, data: String, position: Int) {
        var tvcontent = holder.getView<TextView>(R.id.tv_content)
        if (position == btnPosition) {
            tvcontent.setBackgroundResource(R.drawable.btn_display_down)
            tvcontent.setTextColor(Color.WHITE)
        } else {
            tvcontent.setBackgroundResource(R.drawable.btn_display_nomal)
            tvcontent.setTextColor(ContextCompat.getColor(mContext,R.color.current_time_text))

        }
        tvcontent.text = "测试$position"
        holder.setOnItemClickListener(View.OnClickListener { mOnTagItemClick?.invoke("111",position) }

        )

    }


}