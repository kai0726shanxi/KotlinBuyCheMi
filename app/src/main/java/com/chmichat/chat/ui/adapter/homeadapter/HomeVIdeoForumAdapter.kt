package com.chmichat.chat.ui.adapter.homeadapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.chmichat.chat.R
import com.chmichat.chat.ui.activity.home.AllDynamicActivity
import com.chmichat.chat.view.recyclerview.ViewHolder
import com.chmichat.chat.view.recyclerview.adapter.CommonAdapter

/**
 * @Author 20342
 * @Date 2019/8/19 18:28
 */
class HomeVIdeoForumAdapter(context: Context, list: ArrayList<String>):CommonAdapter<String>(context,list, R.layout.item_homevideo_forum) {

      var list= arrayListOf("1","1","1")
    override fun bindData(holder: ViewHolder, data: String, position: Int) {
     var recycleimgae=holder.getView<RecyclerView>(R.id.recycle_content)
     var recyclerhead=holder.getView<RecyclerView>(R.id.recycle_vavtar)
        recycleimgae.adapter=VideoForumImgaeAdapter(mContext as Activity,list)
        recycleimgae.layoutManager=GridLayoutManager(mContext as Activity,3)
        recycleimgae.isClickable=false

        recyclerhead.adapter=VideoForumHeadAdapter(mContext as Activity,list)
        recyclerhead.layoutManager= LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false)

        holder.setOnItemClickListener(View.OnClickListener {
            val intent = Intent(mContext as Activity, AllDynamicActivity::class.java)
            mContext.startActivity(intent)
        })

    }
}