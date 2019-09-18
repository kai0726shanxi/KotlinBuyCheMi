package com.chmichat.chat.ui.adapter.homeadapter

import android.content.Context
import android.text.Html
import android.view.View
import android.widget.ImageView
import com.chmichat.chat.R
import com.chmichat.chat.bean.PostListEntity
import com.chmichat.chat.view.recyclerview.MultipleType
import com.chmichat.chat.view.recyclerview.ViewHolder
import com.chmichat.chat.view.recyclerview.adapter.CommonAdapter

/**总动态
 * @Author 20342
 * @Date 2019/8/22 16:57
 */
class AllDynamicAdapter(context: Context,list: ArrayList<PostListEntity>) : CommonAdapter<PostListEntity>(context, list, object : MultipleType<PostListEntity> {

    override fun getLayoutId(item: PostListEntity, position: Int): Int {
        return when {
            position == 0->
                R.layout.item_discover_content_big
            position == 1 ->
                R.layout.item_discover_content_small
            else -> {
                R.layout.item_discover_content_big
            }
        }
    }
}) {
    override fun bindData(holder: ViewHolder, data: PostListEntity, position: Int) {
         var ivcontent=holder.getView<ImageView>(R.id.img_content)

        if (position%3==0){
            ivcontent.visibility=View.GONE
        }else{
            ivcontent.visibility=View.VISIBLE

        }

    }

}