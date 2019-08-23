package com.chmichat.chat.ui.adapter.homeadapter

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
 * 搜索界面(历史和热门)
 * @Date 2019/8/17 18:01
 */
class SearchForumAdapter(context: Context, list: ArrayList<String>) : CommonAdapter<String>(context, list, R.layout.item_video_classify) {

    var btnPosition = -1
    private var mOnTagItemClick: ((tag: String,pos:Int) -> Unit)? = null

    fun setOnTagItemClickListener(onTagItemClickListener: (tag: String,pos:Int) -> Unit) {
        this.mOnTagItemClick = onTagItemClickListener
    }

    override fun bindData(holder: ViewHolder, data: String, position: Int) {
        var tvcontent = holder.getView<TextView>(R.id.tv_content)
               tvcontent.setBackgroundResource(R.drawable.btn_search_forum)

    }


}