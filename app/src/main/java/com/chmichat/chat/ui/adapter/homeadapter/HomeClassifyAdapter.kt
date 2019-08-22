package com.chmichat.chat.ui.adapter.homeadapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.chmichat.chat.R
import com.chmichat.chat.ui.activity.home.AllDynamicActivity
import com.chmichat.chat.ui.activity.home.PlayVideoActivity
import com.chmichat.chat.view.recyclerview.ViewHolder
import com.chmichat.chat.view.recyclerview.adapter.CommonAdapter

/***
 * 首页论坛顶部
 */
class HomeClassifyAdapter(context: Context, data: ArrayList<String>)
    : CommonAdapter<String>(context, data, -1) {
    private var mTextface: Typeface?=null


    /**
     * 添加更多数据
     */
    fun addItemData(itemList: ArrayList<String>) {
        this.mData.addAll(itemList)
        notifyDataSetChanged()
    }





    /**
     * 绑定布局
     */
    override fun bindData(holder: ViewHolder, data: String, position: Int) {
        val tv=holder.getView<TextView>(R.id.tv_name)
           tv.typeface=mTextface
        tv.text="进口评论$position"
        holder.setOnItemClickListener(object :View.OnClickListener{
            override fun onClick(p0: View?) {
                val intent = Intent(mContext as Activity, AllDynamicActivity::class.java)
                mContext.startActivity(intent)
            }

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
