package com.chmichat.chat.ui.adapter.homeadapter

import android.content.Context
import com.chmichat.chat.R
import com.chmichat.chat.view.recyclerview.MultipleType
import com.chmichat.chat.view.recyclerview.ViewHolder
import com.chmichat.chat.view.recyclerview.adapter.CommonAdapter

/**
 * 相关推荐的适配器
 * @Author 20342
 * @Date 2019/9/20 10:29
 */
class PostRecommendAdapter(context: Context, list: ArrayList<String>) : CommonAdapter<String>(context, list, object : MultipleType<String> {

    override fun getLayoutId(item: String, position: Int): Int {
        return when (position) {
            1 ->
                R.layout.item_post_recommend_big
            else ->
                R.layout.item_post_recommend_small

        }
    }
}) {


    fun addDataNew(dataList: ArrayList<String>) {
        this.mData.clear()
        this.mData.addAll(dataList)
        notifyDataSetChanged()
    }

    fun addDataAll(dataList: ArrayList<String>) {
        this.mData.addAll(dataList)
        notifyDataSetChanged()
    }

    override fun bindData(holder: ViewHolder, data: String, position: Int) {


    }

}