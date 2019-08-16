package com.chmichat.chat.ui.dialog

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.RadioButton
import android.widget.RadioGroup
import com.chmichat.chat.R

/**
 * 选择是否是拍照还是相册
 * @Author 20342
 * @Date 2019/8/14 15:54
 */
class ChosepictureDialog : Dialog, View.OnClickListener {


    private var mRadioGroup: RadioGroup? = null
    private var mRadioBundle1: RadioButton? = null
    private var mRadioBundle2: RadioButton? = null
    private var mRadioBundle3: RadioButton? = null
    private var mOnclick: BtnDataLinsenter? = null


    constructor(context: Context) : super(context, R.style.DialogTheme) {
        initView(context)
    }

    protected constructor(context: Context, cancelable: Boolean, cancelListener: DialogInterface.OnCancelListener?) : super(context, cancelable, cancelListener) {
        initView(context)
    }


    private var convertView: View? = null

    private fun initView(mcontext: Context) {
        convertView = LayoutInflater.from(mcontext).inflate(R.layout.layout_dialog_chosepicture, null, false)
        mRadioBundle1 = convertView!!.findViewById(R.id.rb_1)
        mRadioBundle2 = convertView!!.findViewById(R.id.rb_2)
        mRadioBundle3 = convertView!!.findViewById(R.id.rb_3)
        mRadioGroup = convertView!!.findViewById(R.id.radio_grop)

        mRadioBundle1!!.setOnClickListener(this)
        mRadioBundle2!!.setOnClickListener(this)
        mRadioBundle3!!.setOnClickListener(this)


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

    override fun onClick(v: View?) {
        dismiss()
        when (v?.id) {
            R.id.rb_1 -> {
                mOnclick!!.btndata("1")

            }
            R.id.rb_2 -> {
                mOnclick!!.btndata("2")

            }

        }

    }

}

