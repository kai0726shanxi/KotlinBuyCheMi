package com.chmichat.chat.ui.activity.add

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.support.v4.widget.DrawerLayout
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Gravity
import android.view.View
import com.chmichat.chat.*
import com.chmichat.chat.base.BaseActivity
import com.chmichat.chat.mvp.contract.add.ReleaseVideoContract
import com.chmichat.chat.mvp.presenter.add.ReleaseVideoPresenter
import com.chmichat.chat.ui.adapter.add.ReleaseVideoAdapter
import com.chmichat.chat.utils.SpUtil
import com.chmichat.chat.utils.StatusBarUtil
import com.google.android.flexbox.FlexboxLayoutManager
import kotlinx.android.synthetic.main.activity_release_video.*
import kotlinx.android.synthetic.main.title_bar_layout.*
import com.google.android.flexbox.JustifyContent
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.qiniu.android.storage.UploadManager
import java.io.File


/**
 *
 * @Author 20342
 * 发布小视频
 * @Date 2019/8/16 15:50
 */
class ReleaseVideoActivity : BaseActivity(),ReleaseVideoContract.View, View.OnClickListener {


    private val mPresenter:ReleaseVideoPresenter by lazy { ReleaseVideoPresenter() }
    private val mlist = arrayListOf("1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1")
    var limit = 60
    //  用来记录输入字符的时候光标的位置
    var cursor = 0
    // 用来标注输入某一内容之前的编辑框中的内容的长度
    var before_length: Int = 0
    private var mProject: ReleaseVideoAdapter? = null//产品
    private var mArt: ReleaseVideoAdapter? = null//技术
    private var mCommon: ReleaseVideoAdapter? = null//常识
    private var isProject:Boolean=false
    private var isArt:Boolean=false
    private var isCommon:Boolean=false
    private var forumname:String?=""
    private var forumid:String?=""
    private var address:String?=""
    private var map=HashMap<String,String?>()
    private var uploadManager:UploadManager?=null
    var mRecycle:ReleaseVideoActivity?=null
    private var islooking:Int=0


    override fun layoutId(): Int {
        return R.layout.activity_release_video
    }

    private var videopath: String? = ""
    private var duration: Long? = 0
    private var mImageBitmap: Bitmap? = null

    override fun initData() {
        videopath = intent.getStringExtra(Constants.VIDEOURL)
        duration = intent.getLongExtra(Constants.DURATION, 0)
        islooking=SpUtil.getInt(this,Constants.ISVISIBLE,0)
    }

    override fun initView() {
        mPresenter.attachView(this)
        StatusBarUtil.darkMode(this)
        StatusBarUtil.setPaddingSmart(this, cl_bar)
        tv_classify_content.setOnClickListener(this)
        tv_address_content.setOnClickListener(this)
        tv_isvisible_content.setOnClickListener(this)
        iv_left.setOnClickListener(this)
        tv_cancel.setOnClickListener(this)
        tv_affirm.setOnClickListener(this)
        tv_right.setOnClickListener(this)
        iv_left.visibility = View.VISIBLE
        iv_left.setColorFilter(Color.BLACK)
        tv_title.text = "发布视频"
        tv_right.visibility = View.VISIBLE
        tv_right.text = "发布"
        tv_right.setTextColor(ContextCompat.getColor(this, R.color.displaynomal))
        tv_time.text = durationFormat(duration!! / 1000)
        uploadManager= UploadManager()
        try {
            mImageBitmap = GetVieoImage(videopath)

        } catch (e: Exception) {

        }
        if (mImageBitmap != null) {
            show_img.setImageBitmap(mImageBitmap)
        } else {
            showToast("获取的图片为空")
        }

        if (islooking==0){
            tv_isvisible_content.text="公开"

        }else{
            tv_isvisible_content.text="私密"
        }
        //监听
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        mDrawerLayout.addDrawerListener(object : DrawerLayout.DrawerListener {

            override fun onDrawerClosed(drawerView: View) {
                //关闭
                //禁止手势滑动
                mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            }

            override fun onDrawerOpened(drawerView: View) {
                //打开
                mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);


            }

            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                //滑动中


            }

            override fun onDrawerStateChanged(newState: Int) {
                //状态改变

            }

        })

        edit_content.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val after_length = s!!.length
                if (after_length > limit) {
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
                show_num.text = "${s!!.length}/" + limit

            }


        })

        mProject = ReleaseVideoAdapter(this, mlist)
        mProject!!.setOnTagItemClickListener { tag, pos ->
            if (!isProject){
                isProject=true
            }

            if (isArt){
                mArt!!.btnPosition=-1
                mArt!!.notifyDataSetChanged()
            }
            if (isCommon){
                mCommon!!.btnPosition=-1
                mCommon!!.notifyDataSetChanged()
            }

            mProject!!.btnPosition=pos
            mProject!!.notifyDataSetChanged()
        }
        mArt= ReleaseVideoAdapter(this,mlist)
        mArt!!.setOnTagItemClickListener { tag, pos ->
            if (!isArt){
                isArt=true
            }
            if (isCommon){
                mCommon!!.btnPosition=-1
                mCommon!!.notifyDataSetChanged()
            }
            if (isProject){
                mProject!!.btnPosition=-1
                mProject!!.notifyDataSetChanged()
            }
            mArt!!.btnPosition=pos
            mArt!!.notifyDataSetChanged()
        }

        mCommon= ReleaseVideoAdapter(this,mlist)
        mCommon!!.setOnTagItemClickListener { tag, pos ->
            if (!isCommon){
                isCommon=true
            }

            mCommon!!.btnPosition=pos
            mCommon!!.notifyDataSetChanged()

        if (isProject){
            mProject!!.btnPosition=-1
            mProject!!.notifyDataSetChanged()
        }
        if (isArt){
            mArt!!.btnPosition=-1
            mArt!!.notifyDataSetChanged()
        }
        }
        recycle_project.adapter = mProject
        recycle_project.layoutManager=setFlexboxLayout()
        recycle_art.adapter=mArt
        recycle_art.layoutManager=setFlexboxLayout()

        recycle_common.adapter=mCommon
        recycle_common.layoutManager=setFlexboxLayout()



    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iv_left->finish()
            R.id.tv_classify_content -> {

                var  intent =Intent(this,ChoseForumActivity::class.java)
                intent.putExtra(Constants.KEYTYPE,"3")
                startActivityForResult(intent,Constants.CHOSEFORUM)
                //mDrawerLayout.openDrawer(Gravity.END)
            }
            R.id.tv_address_content -> {
                startActivityForResult(Intent(this, SetAddressActivity::class.java), Constants.SETADDRESS)

            }
            R.id.tv_isvisible_content->{
                startActivityForResult(Intent(this, IsVisibleActivity::class.java), Constants.SETISVISIVLE)

            }
            R.id.tv_cancel->{
                mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
                mDrawerLayout.closeDrawer(Gravity.END)

            }
            R.id.tv_affirm->{
                mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
                mDrawerLayout.closeDrawer(Gravity.END)

            }
            R.id.tv_right->{
                mPresenter.getQiNiuToken(map)

            }
        }
    }

    override fun start() {
    }

    private fun setFlexboxLayout(): FlexboxLayoutManager? {
        val layoutManager = FlexboxLayoutManager(this)
        //flexDirection 属性决定主轴的方向（即项目的排列方向）。类似 LinearLayout 的 vertical 和 horizontal。
        layoutManager.flexDirection = FlexDirection.ROW//主轴为水平方向，起点在左端。
        //flexWrap 默认情况下 Flex 跟 LinearLayout 一样，都是不带换行排列的，但是flexWrap属性可以支持换行排列。
        layoutManager.flexWrap = FlexWrap.WRAP//按正常方向换行
        //justifyContent 属性定义了项目在主轴上的对齐方式。
        layoutManager.justifyContent = JustifyContent.FLEX_START//交叉轴的起点对齐。
        if (layoutManager!= null){
            return layoutManager
        }
        return null
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                Constants.CHOSEFORUM -> {
                    forumname= data?.getStringExtra(Constants.KEYNAME)
                    forumid= data?.getStringExtra(Constants.KEYID)
                    tv_classify_content.text = data?.getStringExtra(Constants.KEYNAME)
                }
                Constants.SETADDRESS->{
                    address=data?.getStringExtra(Constants.KEYNAME)
                    tv_address_content.text=address
                }
                Constants.SETISVISIVLE->{
                    tv_isvisible_content.text=data?.getStringExtra(Constants.KEYNAME)
                    islooking=SpUtil.getInt(this,Constants.ISVISIBLE,0)

                }
            }
        }

    }

    override fun onImageUrl(str: ArrayList<String?>?) {
    }

    override fun onPushreleaseViedo(str: String?) {
        showToast("上传成功")
        finish()
    }

    override fun onQiNiuToken(str: String?) {


        if (!str.isNullOrEmpty()){

            uploadManager?.put(File(videopath), null, str,
                    { key, info, res ->
                        //res包含hash、key等信息，具体字段取决于上传策略的设置
                        if (info.isOK) {

                            setpushVideo(res["key"] as String?)


                        Log.e("qiniu", "Upload Success???"+res["key"])
                        } else {
                            //如果失败，这里可以把info信息上报自己的服务器，便于后面分析上传错误原因
                        }
                    }, null)
        }else{
            showToast("服务器繁忙，稍后重试~")
        }

    }

    private fun setpushVideo(str: String?) {
      map.clear()
      map["sectionId"]=forumid
      map["postTitle"]=edit_content.text.toString()
      map["type"]="3"
      map["videoUrl"]=str
        if (islooking==0){
            map["visible"]="1"

        }else{
            map["visible"]="2"

        }
      map["position"]=address
      map["openComment"]="1"
      mPresenter.getPushReleaseVideo(map)

    }

    override fun showError(errorMsg: String, errorCode: Int) {
    }

    override fun showLoading() {
    }

    override fun dismissLoading() {
    }
}