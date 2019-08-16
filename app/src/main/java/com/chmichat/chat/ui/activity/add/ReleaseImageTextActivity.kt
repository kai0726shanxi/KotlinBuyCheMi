package com.chmichat.chat.ui.activity.add

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.chmichat.chat.Constants
import com.chmichat.chat.R
import com.chmichat.chat.base.BaseActivity
import com.chmichat.chat.glide.GlideApp
import com.chmichat.chat.utils.StatusBarUtil
import kotlinx.android.synthetic.main.activity_release_imagetext.*
import kotlinx.android.synthetic.main.title_bar_layout.*









/**发布图文
 * @Author 20342
 * @Date 2019/8/15 14:21
 */
class ReleaseImageTextActivity : BaseActivity(), View.OnClickListener {
    private var imgagepath: String? = ""
    var limit = 200
    //  用来记录输入字符的时候光标的位置
    var cursor = 0
    // 用来标注输入某一内容之前的编辑框中的内容的长度
    var before_length: Int = 0
    override fun layoutId(): Int {

        return R.layout.activity_release_imagetext
    }

    override fun initData() {
        imgagepath = intent.getStringExtra(Constants.IMGAEURL)
    }

    override fun initView() {
        StatusBarUtil.darkMode(this)
        StatusBarUtil.setPaddingSmart(this, cl_bar)
        iv_left.visibility = View.VISIBLE
        iv_left.setColorFilter(Color.BLACK)
        tv_title.text = "发布图文"
        tv_right.visibility = View.VISIBLE
        tv_right.text = "发布"
        tv_right.setTextColor(ContextCompat.getColor(this, R.color.displaynomal))
        iv_left.setOnClickListener(this)
        tv_lun_content.setOnClickListener(this)
        tv_address_content.setOnClickListener(this)
        GlideApp.with(this)
                .load(imgagepath)
                .error(R.mipmap.moren_icon)
                .into(show_img)
        edit_content.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val after_length = s!!.length
                if (after_length >limit) {
                    val d_value = after_length - limit
                    val d_num = after_length - before_length
                    val st = cursor + (d_num - d_value)
                    val en = cursor + d_num
                    val s_new = s.delete(st, en)
                    edit_content.setText(s_new.toString())
                    edit_content.setSelection(st)
                }

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                before_length = s!!.length

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                cursor = start
                show_num.text="${s!!.length}/"+limit

            }


        })
    }

    override fun start() {
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iv_left -> {
                finish()
            }
            R.id.tv_lun_content->{

                startActivityForResult(Intent(this,ChoseForumActivity::class.java),Constants.CHOSEFORUM)
            }
            R.id.tv_address_content->{
                startActivityForResult(Intent(this,SetAddressActivity::class.java),Constants.SETADDRESS)

            }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                Constants.CHOSEFORUM->{
                 tv_lun_content.text=data!!.getStringExtra(Constants.KEYNAME)
                }

            }
        }

    }
}