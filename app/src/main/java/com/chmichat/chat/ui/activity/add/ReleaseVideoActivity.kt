package com.chmichat.chat.ui.activity.add

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.support.v4.widget.DrawerLayout
import android.text.Editable
import android.text.TextWatcher
import android.view.Gravity
import android.view.View
import com.chmichat.chat.*
import com.chmichat.chat.base.BaseActivity
import com.chmichat.chat.ui.adapter.add.ReleaseVideoAdapter
import com.chmichat.chat.utils.StatusBarUtil
import com.google.android.flexbox.FlexboxLayoutManager
import kotlinx.android.synthetic.main.activity_release_video.*
import kotlinx.android.synthetic.main.title_bar_layout.*
import com.google.android.flexbox.JustifyContent
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap


/**
 *
 * @Author 20342
 * 发布小视频
 * @Date 2019/8/16 15:50
 */
class ReleaseVideoActivity : BaseActivity(), View.OnClickListener {
    private val mlist = arrayListOf("1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1")
    var limit = 60
    //  用来记录输入字符的时候光标的位置
    var cursor = 0
    // 用来标注输入某一内容之前的编辑框中的内容的长度
    var before_length: Int = 0
    private var mProject: ReleaseVideoAdapter? = null//产品
    private var mArt: ReleaseVideoAdapter? = null//技术
    private var mCommon: ReleaseVideoAdapter? = null//常识
    var mRecycle:ReleaseVideoActivity?=null

    override fun layoutId(): Int {
        return R.layout.activity_release_video
    }

    private var videopath: String? = ""
    private var duration: Long? = 0
    private var mImageBitmap: Bitmap? = null

    override fun initData() {
        videopath = intent.getStringExtra(Constants.VIDEOURL)
        duration = intent.getLongExtra(Constants.DURATION, 0)

    }

    override fun initView() {

        StatusBarUtil.darkMode(this)
        StatusBarUtil.setPaddingSmart(this, cl_bar)
        tv_classify_content.setOnClickListener(this)
        tv_address_content.setOnClickListener(this)
        iv_left.setOnClickListener(this)
        iv_left.visibility = View.VISIBLE
        iv_left.setColorFilter(Color.BLACK)
        tv_title.text = "发布"
        tv_time.text = durationFormat(duration!! / 1000)
        try {
            mImageBitmap = GetVieoImage(videopath)

        } catch (e: Exception) {

        }
        if (mImageBitmap != null) {
            show_img.setImageBitmap(mImageBitmap)
        } else {
            showToast("获取的图片为空")
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
        recycle_project.adapter = mProject
        recycle_project.layoutManager=setFlexboxLayout()


    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iv_left->finish()
            R.id.tv_classify_content -> {
                mDrawerLayout.openDrawer(Gravity.END)
            }
            R.id.tv_address_content -> {
                startActivityForResult(Intent(this, SetAddressActivity::class.java), Constants.SETADDRESS)

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
}