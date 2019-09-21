package com.chmichat.chat.ui.dialog

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import com.chmichat.chat.R
import com.chmichat.chat.ui.adapter.ShareDialogAdapter

/**
 * 分享界面
 * @Author 20342
 * @Date 2019/8/14 15:54
 */
class ShareDialog : Dialog,View.OnClickListener {


    private var mOnclick: BtnDataLinsenter? = null
    private var mList= arrayListOf(context.getString(R.string.share_wx),context.getString(R.string.share_friend),"QQ",context.getString(R.string.share_weibo),context.getString(R.string.share_copy),context.getString(R.string.share_QR),context.getString(R.string.share_report))

    private var madapter:ShareDialogAdapter ?=null


    constructor(context: Context) : super(context, R.style.DialogTheme) {
        initView(context)
    }

    protected constructor(context: Context, cancelable: Boolean, cancelListener: DialogInterface.OnCancelListener?) : super(context, cancelable, cancelListener) {
        initView(context)
    }


    private var convertView: View? = null

    private var recycleshare: RecyclerView?=null

    private fun initView(mcontext: Context) {

        convertView = LayoutInflater.from(mcontext).inflate(R.layout.layout_dialog_share, null, false)
        recycleshare=convertView?.findViewById<RecyclerView>(R.id.recycle_share)
        madapter= ShareDialogAdapter(mcontext,mList)
        recycleshare?.adapter=madapter
        recycleshare?.layoutManager=GridLayoutManager(mcontext,4)


    }
    override fun onClick(v: View?) {
        dismiss()
        when(v?.id){
            R.id.rb1-> mOnclick!!.btndata("男")
            R.id.rb2-> mOnclick!!.btndata("女")
            R.id.rb3-> mOnclick!!.btndata("保密")

        }

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(convertView)
        setCancelable(true)
        setCanceledOnTouchOutside(true)
        val win = window
        val lp = win!!.attributes
        lp.gravity = Gravity.BOTTOM
        lp.width = WindowManager.LayoutParams.MATCH_PARENT
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT
        win.attributes = lp
        win.setWindowAnimations(R.style.share_animation)

    }

    fun setBtnDataLinsenter(onclick: BtnDataLinsenter) {
        mOnclick = onclick
    }

    interface BtnDataLinsenter {
        fun btndata(str: String)
    }

}

