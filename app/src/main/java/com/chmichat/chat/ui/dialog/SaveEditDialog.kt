package com.chmichat.chat.ui.dialog

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import com.chmichat.chat.R

/**
 * 保存编辑的内容
 * @Author 20342
 * @Date 2019/8/14 15:54
 */
class SaveEditDialog : Dialog, View.OnClickListener {
    private var mOnclick: BtnDataLinsenter? = null
    private var tvleft: TextView? = null
    private var tvright: TextView? = null


    constructor(context: Context) : super(context, R.style.DialogTheme) {
        initView(context)
    }

    protected constructor(context: Context, cancelable: Boolean, cancelListener: DialogInterface.OnCancelListener?) : super(context, cancelable, cancelListener) {
        initView(context)
    }


    private var convertView: View? = null

    private fun initView(mcontext: Context) {
        convertView = LayoutInflater.from(mcontext).inflate(R.layout.layout_dialog_editcontent, null, false)

        tvleft = convertView?.findViewById(R.id.tv_left)
        tvright = convertView?.findViewById(R.id.tv_right)

        tvleft?.setOnClickListener(this)
        tvright?.setOnClickListener(this)


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(convertView)
        setCancelable(true)
        setCanceledOnTouchOutside(true)
        val win = window
        val lp = win!!.attributes
        lp.gravity = Gravity.CENTER
        lp.width = WindowManager.LayoutParams.MATCH_PARENT
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT
        win.attributes = lp
        //win.setWindowAnimations(R.style.share_animation)
    }


    fun setBtnDataLinsenter(onclick: BtnDataLinsenter) {
        mOnclick = onclick
    }

    interface BtnDataLinsenter {
        fun btndata(str: String)
    }

    override fun onClick(v: View?) {
        dismiss()
        when (v?.id) {
            R.id.tv_left -> {
                mOnclick!!.btndata("1")

            }
            R.id.tv_right -> {
                mOnclick!!.btndata("2")

            }

        }

    }

}

