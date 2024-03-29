package com.chmichat.chat.ui.adapter.homeadapter

import android.content.Context
import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.TextView
import com.chmichat.chat.R
import com.chmichat.chat.bean.PostListEntity
import com.chmichat.chat.bean.SearchGoodsHotBean
import com.chmichat.chat.view.recyclerview.ViewHolder
import com.chmichat.chat.view.recyclerview.adapter.CommonAdapter

/**
 * @Author 20342
 * 搜索界面(热门)
 * @Date 2019/8/17 18:01
 */
class SearchForumAdapter(context: Context, list: ArrayList<SearchGoodsHotBean>) : CommonAdapter<SearchGoodsHotBean>(context, list, R.layout.item_video_classify) {


    fun addDataNew(dataList: java.util.ArrayList<SearchGoodsHotBean>) {
        this.mData.clear()
        this.mData.addAll(dataList)
        notifyDataSetChanged()
    }

    fun addDataAll(dataList: java.util.ArrayList<SearchGoodsHotBean>) {
        this.mData.clear()
        this.mData.addAll(dataList)
        notifyDataSetChanged()
    }

    private var mOnTagItemClick: ((tag: SearchGoodsHotBean, pos: Int) -> Unit)? = null

    fun setOnTagItemClickListener(onTagItemClickListener: (tag: SearchGoodsHotBean, pos: Int) -> Unit) {
        this.mOnTagItemClick = onTagItemClickListener
    }

    override fun bindData(holder: ViewHolder, data: SearchGoodsHotBean, position: Int) {
        var tvcontent = holder.getView<TextView>(R.id.tv_content)
     //   tvcontent.setBackgroundResource(R.drawable.btn_search_forum)
        tvcontent.text=data.hotName
        holder.setOnItemClickListener(listener = View.OnClickListener {
            mOnTagItemClick?.invoke(data,position)

        })
    }


}