package com.chmichat.chat.ui.adapter.me

import android.content.Context
import com.chmichat.chat.R
import com.chmichat.chat.bean.CollectEntity
import com.chmichat.chat.view.recyclerview.MultipleType
import com.chmichat.chat.view.recyclerview.ViewHolder
import com.chmichat.chat.view.recyclerview.adapter.CommonAdapter

/**
 * @Author 20342
 * @Date 2019/9/12 16:35
 */
class MyCollectAdapter(context: Context, data:ArrayList<CollectEntity>):CommonAdapter<CollectEntity>(context,data,object : MultipleType<CollectEntity> {



    override fun getLayoutId(item: CollectEntity, position: Int): Int {
        return when(item.collectionType){
             1->{
                 R.layout.item_meforum_layout

             }
            2->{
                R.layout.item_medynamic_layout

            }

            3->{
                R.layout.item_mevideo_layout

            }
            4->{
                R.layout.item_melongvideo_layout

            }

            else->{
                R.layout.item_meforum_layout

            }

        }
    }
}) {



    fun addDataNew(dataList: ArrayList<CollectEntity>) {
        this.mData.clear()
        this.mData.addAll(dataList)
        notifyDataSetChanged()
    }
    fun addDataAll(dataList: ArrayList<CollectEntity>) {
        this.mData.addAll(dataList)
        notifyDataSetChanged()
    }
    override fun bindData(holder: ViewHolder, data: CollectEntity, position: Int) {


    }
}