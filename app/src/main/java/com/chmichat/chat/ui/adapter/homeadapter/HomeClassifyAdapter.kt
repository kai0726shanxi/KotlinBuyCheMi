package com.chmichat.chat.ui.adapter.homeadapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.chmichat.chat.Constants
import com.chmichat.chat.R
import com.chmichat.chat.bean.ForumListEntity
import com.chmichat.chat.glide.GlideApp
import com.chmichat.chat.ui.activity.home.AllDynamicActivity
import com.chmichat.chat.view.CircleImageView
import com.chmichat.chat.view.recyclerview.ViewHolder
import com.chmichat.chat.view.recyclerview.adapter.CommonAdapter

/***
 * 首页论坛顶部
 */
class HomeClassifyAdapter(context: Context, data: ArrayList<ForumListEntity>)
    : CommonAdapter<ForumListEntity>(context, data, -1) {
    private var mTextface: Typeface?=null


    /**
     * 添加更多数据
     */
    fun addItemData(itemList: ArrayList<ForumListEntity>) {
        this.mData.clear()
        this.mData.addAll(itemList)
        notifyDataSetChanged()
    }





    /**
     * 绑定布局
     */
    override fun bindData(holder: ViewHolder, data: ForumListEntity, position: Int) {
        val tv=holder.getView<TextView>(R.id.tv_name)
        var ivhead=holder.getView<CircleImageView>(R.id.iv_bg)
        var tvnum=holder.getView<TextView>(R.id.tv_num)
        GlideApp.with(mContext)
                .load(data.sectionIcon)
                .placeholder(R.mipmap.head_ic)
                .into(ivhead)
        tvnum.text= data.total.toString()

        tv.typeface=mTextface
        tv.text=data.sectionName
        holder.setOnItemClickListener(View.OnClickListener {
            val intent = Intent(mContext as Activity, AllDynamicActivity::class.java)
            intent.putExtra(Constants.KEYNAME,mData[position])
            mContext.startActivity(intent)
        })


    }

    /**
     *  创建布局
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       return ViewHolder(inflaterView(R.layout.item_home_classify, parent))
    }


    /**
     * 加载布局
     */
    private fun inflaterView(mLayoutId: Int, parent: ViewGroup): View {
        //创建view
        val view = mInflater?.inflate(mLayoutId, parent, false)
        return view ?: View(parent.context)
    }

   fun setTextStyle(mt:Typeface){
       mTextface=mt
   }



}
