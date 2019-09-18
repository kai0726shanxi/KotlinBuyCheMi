package com.chmichat.chat.ui.adapter.add

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextSwitcher
import com.chmichat.chat.R
import com.chmichat.chat.bean.ChannelBean
import com.chmichat.chat.glide.GlideApp
import com.chmichat.chat.view.recyclerview.MultipleType
import com.chmichat.chat.view.recyclerview.ViewHolder
import com.chmichat.chat.view.recyclerview.adapter.CommonAdapter
import android.text.Editable
import android.text.TextWatcher
import android.support.design.widget.CoordinatorLayout.Behavior.setTag





/**帖子
 * @Author 20342
 * @Date 2019/8/22 16:57
 */
class ReleaseFostAdapter(context: Context, list: ArrayList<ChannelBean>) : CommonAdapter<ChannelBean>(context, list, object : MultipleType<ChannelBean> {


    override fun getLayoutId(item: ChannelBean, position: Int): Int {
        return when {
            item.type == "1" ->
                R.layout.item_releasefost_image
            else -> {
                R.layout.item_releasefost_edit
            }
        }
    }
}) {


    /**
     * 设置新数据
     */
    fun setData(categoryList: ArrayList<ChannelBean>) {
        this.mData.addAll(categoryList)
        notifyDataSetChanged()
    }

    var etFocusPosition:Int = 0

    public lateinit var bgitem: ConstraintLayout

    override fun bindData(holder: ViewHolder, data: ChannelBean, position: Int) {
      holder.setIsRecyclable(false)

      bgitem= holder.getView<ConstraintLayout>(R.id.cl_bg)
        if (data.type == "1") {
            GlideApp.with(mContext)
                    .load(data.imgUrl)
                    .placeholder(R.mipmap.moren_icon)
                    .into(holder.getView(R.id.iv_content))

        } else {
            var editText=holder.getView<EditText>(R.id.et_post)

            editText.hint = "请输入内容"
            editText.setText(data.etContent)
            val watcher = object : TextWatcher {
                override fun beforeTextChanged(sequence: CharSequence, i: Int, i1: Int, i2: Int) {

                }

                override fun onTextChanged(sequence: CharSequence, i: Int, i1: Int, i2: Int) {

                }

                override fun afterTextChanged(editable: Editable) {
                    //通过接口回调将数据传递到Activity中,修改list里的bean

                  if((editText.text.toString()).isNullOrEmpty()){
                      data.etContent=""
                  }else{
                      data.etContent=editText.text.toString()

                  }


                }
            }

            editText.addTextChangedListener(watcher)
            editText.tag = watcher
            editText.onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
                if (hasFocus) {//获得焦点
                    etFocusPosition = position
                } else {//失去焦点

                }
            }
        }

    }

}