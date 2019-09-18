package com.chmichat.chat.ui.activity.add

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import com.chmichat.chat.Constants
import com.chmichat.chat.R
import com.chmichat.chat.base.BaseActivity
import com.chmichat.chat.bean.InvitationInfo
import com.chmichat.chat.mvp.contract.add.ReleasePostContract
import com.chmichat.chat.mvp.presenter.add.ReleasePresenter
import com.chmichat.chat.showToast
import com.chmichat.chat.ui.adapter.add.MyReleaseFostAdapter
import com.chmichat.chat.utils.itemtouch.DefaultItemTouchHelpCallback
import com.chmichat.chat.utils.itemtouch.DefaultItemTouchHelper
import com.google.gson.Gson
import com.luck.picture.lib.PictureSelector
import com.luck.picture.lib.config.PictureConfig
import com.luck.picture.lib.config.PictureMimeType
import com.qiniu.android.utils.StringUtils
import kotlinx.android.synthetic.main.activity_release_post_new.*
import kotlinx.android.synthetic.main.title_bar_layout.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap


/**
 * @Author 20342
 * @Date 2019/8/27 14:03
 */
class MyReleasePostActivity : BaseActivity(), ReleasePostContract.View, View.OnClickListener, View.OnTouchListener {


    /**
     * 数据源
     */
    private val mPresenter: ReleasePresenter by lazy {
        ReleasePresenter()
    }
    private var userInfoList: MutableList<InvitationInfo>? = null
    private var userImage = ArrayList<InvitationInfo>()
    private var ImageUrl: MutableList<String?>? = ArrayList()
    private var mMyReleaseFostAdapter: MyReleaseFostAdapter? = null

    /**
     * 滑动拖拽的帮助类
     */
    private var itemTouchHelper: DefaultItemTouchHelper? = null

    private var isMoveing = false
    private var mSrcPosition: Int = 0
    private var mTargetPosition: Int = 0
    /**
     * 选中的item位置
     */
    private var mSelectPostion = 0
    private var forumname: String? = ""
    private var forumid: String? = ""
    private var address: String? = ""
    private var limit = 30
    //  用来记录输入字符的时候光标的位置
    private var cursor = 0
    // 用来标注输入某一内容之前的编辑框中的内容的长度
    private var before_length: Int = 0
    private var imgurlpostion: Int = 0
    private var strcontent:String?=""

    private var strbuf: StringBuffer = StringBuffer()
    private var map = HashMap<String, String?>()
    private var strimg:String?=""

    override fun layoutId(): Int {

        return R.layout.activity_release_post_new
    }

    override fun initData() {
    }

    override fun initView() {
        mPresenter.attachView(this)
        iv_left.visibility = View.VISIBLE
        iv_left.setColorFilter(Color.BLACK)
        tv_title.text = "发帖"
        tv_right.visibility = View.VISIBLE
        tv_right.text = "发布"
        tv_right.setTextColor(ContextCompat.getColor(this, R.color.displaynomal))
        iv_pic.setOnClickListener(this)
        tv_right.setOnClickListener(this)
        iv_left.setOnClickListener(this)
        iv_address.setOnClickListener(this)
        iv_classify.setOnClickListener(this)
        tv_lun_content.setOnClickListener(this)
        tv_address_content.setOnClickListener(this)
        recyclerView!!.itemAnimator = DefaultItemAnimator()

        // 必须要设置一个布局管理器
        val linearLayoutManager = LinearLayoutManager(this)

        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView!!.layoutManager = linearLayoutManager

        mMyReleaseFostAdapter = MyReleaseFostAdapter(userInfoList, this, recyclerView)
        mMyReleaseFostAdapter!!.setOnItemClickListener(onItemClickListener)
        recyclerView!!.adapter = mMyReleaseFostAdapter
        recyclerView!!.setOnTouchListener(this)
        // 模拟数据
        userInfoList = ArrayList()
        userInfoList!!.add(InvitationInfo("", "", 1))
        mMyReleaseFostAdapter!!.notifyDataSetChanged(userInfoList)


        // 把ItemTouchHelper和itemTouchHelper绑定
        itemTouchHelper = DefaultItemTouchHelper(onItemTouchCallbackListener)
        itemTouchHelper!!.attachToRecyclerView(recyclerView)

        mMyReleaseFostAdapter!!.setItemTouchHelper(itemTouchHelper)

        itemTouchHelper!!.setDragEnable(false)
        itemTouchHelper!!.setSwipeEnable(false)
        et_title.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val after_length = s!!.length
                if (after_length > limit) {
                    val d_value = after_length - limit
                    val d_num = after_length - before_length
                    val st = cursor + (d_num - d_value)
                    val en = cursor + d_num
                    val s_new = s.delete(st, en)
                    et_title.setText(s_new.toString())
                    et_title.setSelection(st)
                }

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                before_length = s!!.length

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                cursor = start

            }


        })
    }

    override fun start() {

    }

    private val onItemTouchCallbackListener = object : DefaultItemTouchHelpCallback.OnItemTouchCallbackListener {
        override fun onSwiped(adapterPosition: Int) {
            if (userInfoList != null) {
                userInfoList!!.removeAt(adapterPosition)
                mMyReleaseFostAdapter!!.notifyItemRemoved(adapterPosition)
            }
        }

        @Synchronized
        override fun onMove(srcPosition: Int, targetPosition: Int): Boolean {
            mSrcPosition = srcPosition
            mTargetPosition = targetPosition
            if (userInfoList != null) {
                // 更换数据源中的数据Item的位置
                Collections.swap(userInfoList, srcPosition, targetPosition)

                // 更新UI中的Item的位置，主要是给用户看到交互效果
                mMyReleaseFostAdapter!!.notifyItemMoved(srcPosition, targetPosition)
                mSelectPostion = targetPosition
                isMoveing = true
                return true
            }
            return false
        }
    }

    /**
     * RecyclerView的Item点击监听
     */
    private val onItemClickListener = MyReleaseFostAdapter.OnItemClickListener { view, position -> Toast.makeText(this, "第" + position + "被点击", Toast.LENGTH_SHORT).show() }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iv_left -> finish()
            R.id.tv_right -> {
                if (userInfoList!=null&& userInfoList?.size!! >0){
                    for (index in userInfoList!!) {
                        if (index.isPic == 0) {
                            userImage.add(index)
                        }
                    }

                    setpushImg()
                }else{
                    showToast("请输入内容")
                }

            }
            R.id.iv_icon -> {
                //图标

            }
            R.id.iv_pic -> {
                //照片
                openpicture()
            }


            R.id.iv_classify -> {
                //分类
                startActivityForResult(Intent(this, ChoseForumActivity::class.java), Constants.CHOSEFORUM)

            }
            R.id.iv_address -> {
                //地址
                startActivityForResult(Intent(this, SetAddressActivity::class.java), Constants.SETADDRESS)

            }
            R.id.tv_address_content -> {
                tv_address_content.text = ""
                address = ""
                tv_address_content.visibility = View.INVISIBLE

            }

            R.id.tv_lun_content -> {
                tv_lun_content.text = ""
                forumid = ""
                forumname = ""
                tv_lun_content.visibility = View.INVISIBLE
            }
        }
    }

    private fun setpushImg() {
        if (userImage != null && userImage.size > 0) {
            val builder = MultipartBody.Builder().setType(MultipartBody.FORM)
            for (item in userImage) {
                val file = File(item.image)
                builder.addFormDataPart("files", file.name, RequestBody.create(MediaType.parse("image/jpeg"), file))
            }
            val requestBody = builder.build()
            mPresenter.getImageUrl(requestBody)

        } else {
            for (index in userInfoList!!) {

                if (!index.text.isNullOrEmpty()) {
                    strbuf.append("<div style=\"max-width: 100%;\">" + index.text + "</div>")

                }


            }
            strcontent=strbuf.toString()
             setPushpost()


        }

    }

    private fun setPushpost() {
     //发布帖子
        map.clear()
        map["sectionId"]=forumid
        map["content"]=strcontent
        map["postTitle"]=et_title.text.toString()
        map["type"]="2"
        map["position"]=address
        if (ImageUrl!=null&&ImageUrl?.size!!>0){
            map["firstCover"]=ImageUrl?.get(0)
            strimg=Gson().toJson(ImageUrl, ImageUrl!!::class.java)
            map["postCovers"]= strimg?.length?.minus(2)?.let { strimg?.substring(2, it) }

        }

        map["openComment"]="0"
        mPresenter.getPushPost(map)
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_MOVE -> {
            }
            MotionEvent.ACTION_UP -> {
                notifyItemView()
                mMyReleaseFostAdapter!!.setHeight(700, null)
            }
        }
        return false
    }


    private fun notifyItemView() {
        if (!isMoveing)
            return
        try {
            if (userInfoList!![0].isPic != 1)
            //第一行
                userInfoList!!.add(0, InvitationInfo("", "", 1))
            if (userInfoList!![userInfoList!!.size - 1].isPic != 1)
            //最后一行
                userInfoList!!.add(InvitationInfo("", "", 1))
            run {
                var i = 0
                while (i < userInfoList!!.size) {
                    if (i > 0 && userInfoList!![i].isPic == 0 && userInfoList!![i].isPic == userInfoList!![i - 1].isPic) {
                        userInfoList!!.add(i, InvitationInfo("", "", 1))
                        mSelectPostion = if (mSelectPostion < i) mSelectPostion else mSelectPostion++
                        i++
                    }
                    i++
                }
            }

            var i = 0
            while (i < userInfoList!!.size) {
                if (userInfoList!![i].isPic == 1 && i > 0 && userInfoList!![i].isPic == userInfoList!![i - 1].isPic)
                    if (TextUtils.isEmpty(userInfoList!![i].text)) {
                        userInfoList!!.removeAt(i)
                        mSelectPostion = if (mSelectPostion < i) mSelectPostion else mSelectPostion--
                        i--
                    } else if (TextUtils.isEmpty(userInfoList!![i - 1].text)) {
                        userInfoList!!.removeAt(i - 1)
                        mSelectPostion = if (mSelectPostion < i - 1) mSelectPostion else mSelectPostion--
                        i--
                    }
                i++
            }
        } catch (e: IndexOutOfBoundsException) {
            e.printStackTrace()
        } finally {
            mMyReleaseFostAdapter!!.notifyDataSetChanged()
            recyclerView!!.smoothScrollToPosition(mSelectPostion)
            isMoveing = false
        }
    }


    private fun openpicture() {
        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofImage())
                .enableCrop(true)// 是否裁剪 true or false
                .compress(true)// 是否压缩 true or
                .maxSelectNum(6)
                .showCropFrame(true)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false   true or false
                .showCropGrid(false)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false    true or false
                .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                .forResult(PictureConfig.CHOOSE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                PictureConfig.CHOOSE_REQUEST -> {
                    // 图片、视频、音频选择结果回调
                    val selectList = PictureSelector.obtainMultipleResult(data)
                    /**
                     *   val file = File(imgagepath)
                    val builder = MultipartBody.Builder().setType(MultipartBody.FORM)
                    builder.addFormDataPart("files", file.name, RequestBody.create(MediaType.parse("image/jpeg"), file))
                    val requestBody = builder.build()
                    mPresenter.getImageUrl(requestBody)
                     */

                    if (selectList != null) {
                        for (mitem in selectList) {
                            if (!mitem.compressPath.isNullOrEmpty()) {
                                userInfoList!!.add(InvitationInfo("", mitem.compressPath, 0))
                                userInfoList!!.add(InvitationInfo("", "", 1))
                            } else {
                                showToast("添加失败，稍后重试")
                            }

                        }


                        mMyReleaseFostAdapter!!.notifyDataSetChanged(userInfoList)


                    }

                }
                Constants.CHOSEFORUM -> {
                    forumname = data?.getStringExtra(Constants.KEYNAME)
                    forumid = data?.getStringExtra(Constants.KEYID)
                    if (!forumname.isNullOrEmpty()) {
                        tv_lun_content.visibility = View.VISIBLE
                        tv_lun_content.text = data?.getStringExtra(Constants.KEYNAME)

                    }
                }
                Constants.SETADDRESS -> {
                    address = data?.getStringExtra(Constants.KEYNAME)
                    if (!address.isNullOrEmpty()) {
                        tv_address_content.visibility = View.VISIBLE
                        tv_address_content.text = address

                    }
                }

            }
        }
    }

    override fun onImageUrl(data: ArrayList<String?>?) {
        //图片集合
        ImageUrl = data
        if (ImageUrl != null) {

            for (index in userInfoList!!) {
                if (index.isPic == 0) {
                    strbuf.append("< img src=\"" + ImageUrl?.get(imgurlpostion) + "\" style=\"width: 100%;max-width: 100%;\">")
                    imgurlpostion++
                } else {
                    if (!index.text.isNullOrEmpty()) {
                        strbuf.append("<div style=\"max-width: 100%;\">" + index.text + "</div>")

                    }
                }
            }
            strcontent=strbuf.toString()
            setPushpost()

        }

    }

    override fun onPushImagetext(str: String?) {
        showToast("发布成功")
        finish()
    }

    override fun showError(errorMsg: String, errorCode: Int) {
        showToast(errorMsg)
    }

    override fun showLoading() {
    }

    override fun dismissLoading() {
    }

}