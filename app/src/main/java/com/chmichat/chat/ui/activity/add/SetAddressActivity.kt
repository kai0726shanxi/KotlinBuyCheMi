package com.chmichat.chat.ui.activity.add

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.chmichat.chat.Constants
import com.chmichat.chat.R
import com.chmichat.chat.base.BaseActivity
import com.chmichat.chat.showToast
import com.chmichat.chat.ui.adapter.add.SetAddressAdapter
import com.chmichat.chat.utils.SpUtil
import com.chmichat.chat.utils.StatusBarUtil
import kotlinx.android.synthetic.main.activity_set_address.*
import kotlinx.android.synthetic.main.title_bar_layout.*

/**
 * 设置地址
 * @Author 20342
 * @Date 2019/8/15 17:00
 */
class SetAddressActivity : BaseActivity(), View.OnClickListener {


    private var mSetAddressAdapter: SetAddressAdapter? = null
    private var mlist = ArrayList<String>()
    override fun layoutId(): Int {

        return R.layout.activity_set_address
    }

    override fun initData() {
        mlist=SpUtil.getDataList(this,Constants.KEYADDRESS)
        if (mlist!=null){
            mlist=SpUtil.removeDuplicteUsers(mlist)

        }

    }

    override fun initView() {
        StatusBarUtil.darkMode(this)
        StatusBarUtil.setPaddingSmart(this,cl_bar)
        iv_left.visibility = View.VISIBLE
        iv_left.setColorFilter(Color.BLACK)
        iv_left.setOnClickListener(this)
        tv_affirm.setOnClickListener(this)
        tv_title.text = "选择位置"
        mSetAddressAdapter = SetAddressAdapter(this, mlist)
        recycle_view.adapter = mSetAddressAdapter
        recycle_view.layoutManager = LinearLayoutManager(this)
        mSetAddressAdapter?.setOnTagItemClickListener{

            var intent = Intent()
            intent.putExtra(Constants.KEYNAME, it)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }


    }

    override fun start() {
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iv_left -> {
                finish()
            }
            R.id.tv_affirm->{
                if (!edit_search.text.toString().isNullOrEmpty()){
                    if (mlist!=null){
                        mlist.add(edit_search.text.toString())
                    }else{
                        mlist= ArrayList()
                        mlist.add(edit_search.text.toString())
                    }
                    SpUtil.setDataList(this,Constants.KEYADDRESS,mlist)
                    var intent = Intent()
                    intent.putExtra(Constants.KEYNAME, edit_search.text.toString())
                    setResult(Activity.RESULT_OK, intent)
                    finish()
                }else{
                    showToast("请输入地址")
                }




            }
        }
    }
}