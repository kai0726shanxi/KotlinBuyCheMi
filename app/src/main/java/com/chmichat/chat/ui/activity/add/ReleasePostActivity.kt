package com.chmichat.chat.ui.activity.add

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.graphics.Rect
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.chmichat.chat.Constants
import com.chmichat.chat.R
import com.chmichat.chat.base.BaseActivity
import com.chmichat.chat.bean.ChannelBean
import com.chmichat.chat.getStatusBarHeight
import com.chmichat.chat.showToast
import com.chmichat.chat.ui.adapter.add.ReleaseFostAdapter
import com.chmichat.chat.utils.StatusBarUtil
import com.dueeeke.videoplayer.util.PlayerUtils.dp2px
import com.luck.picture.lib.PictureSelector
import com.luck.picture.lib.config.PictureConfig
import com.luck.picture.lib.config.PictureMimeType
import kotlinx.android.synthetic.main.activity_release_post.*
import kotlinx.android.synthetic.main.title_bar_layout.*

/**
 * 发帖
 * @Author 20342
 * @Date 2019/8/23 16:41
 */
class ReleasePostActivity : BaseActivity(), View.OnClickListener {


    private var list = ArrayList<ChannelBean>()
    private var timpList=ArrayList<ChannelBean>()
    private var mReleaseFostAdapter: ReleaseFostAdapter? = null
    private var forumname: String?=""
    private var forumid: String?=""
    private var address: String?=""
    private var limit = 30
    //  用来记录输入字符的时候光标的位置
    private var cursor = 0
    // 用来标注输入某一内容之前的编辑框中的内容的长度
    private var before_length: Int = 0
    override fun layoutId(): Int {
        return R.layout.activity_release_post
    }

    override fun initData() {
    }

    override fun initView() {
        mReleaseFostAdapter = ReleaseFostAdapter(this, list)

        window.decorView.viewTreeObserver.addOnGlobalLayoutListener {
           // mImgKeyboard.setImageResource(if (KeyboardUtils.isSoftShowing(this)) R.mipmap.jianpan_down_ico else R.mipmap.jianpan_ico)
            // 除了软键盘以外的可见区域
            val rect = Rect()
            window.decorView.getWindowVisibleDisplayFrame(rect)                // 计算出剩余高度：  除了状态栏高度、topBar高度、bottomBar高度、键盘高度的剩余高度
            val invisibleHeight = rect.bottom - getStatusBarHeight(this) - dp2px(this, 44F) - dp2px(this, 44F)                 // 计算出所点击的图片描述的EditText距离RecyclerView顶部的距离
            val etDescView = mRecyclePost.layoutManager.findViewByPosition(mReleaseFostAdapter!!.etFocusPosition)
            if (etDescView != null) {
                val focusViewTop = etDescView!!.top
                val itemHeight = etDescView!!.height
                val differ = focusViewTop + itemHeight - invisibleHeight
                if (differ > 0) {                        // 让RecyclerView滚动差的那点距离
                    mRecyclePost.scrollBy(0, differ)
                }
            }
        }

        StatusBarUtil.darkMode(this)
        StatusBarUtil.setPaddingSmart(this, cl_bar)
        iv_left.setOnClickListener(this)
        iv_icon.setOnClickListener(this)
        iv_pic.setOnClickListener(this)
        iv_address.setOnClickListener(this)
        iv_classify_new.setOnClickListener(this)
        tv_lun_content.setOnClickListener(this)
        tv_address_content.setOnClickListener(this)
        iv_left.visibility = View.VISIBLE
        iv_left.setColorFilter(Color.BLACK)
        tv_title.text = "发帖"
        tv_right.visibility = View.VISIBLE
        tv_right.text = "发布"
        tv_right.setTextColor(ContextCompat.getColor(this, R.color.displaynomal))

        list.add(ChannelBean("0", "", "", false))

        mRecyclePost.adapter = mReleaseFostAdapter
        mRecyclePost.layoutManager = linearLayouManager
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

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iv_left ->
                finish()
            R.id.iv_icon -> {
                //图标

            }
            R.id.iv_pic -> {

                showToast("相册")
                //照片
                openpicture()
            }

            R.id.iv_classify-> {
                //分类
                var  intent =Intent(this,ChoseForumActivity::class.java)
                intent.putExtra(Constants.KEYTYPE,"2")
                startActivityForResult(intent,Constants.CHOSEFORUM)
            }
            R.id.iv_address -> {
                //地址
                startActivityForResult(Intent(this, SetAddressActivity::class.java), Constants.SETADDRESS)

            }
            R.id.tv_address_content->{
                tv_address_content.text=""
                address=""
                tv_address_content.visibility=View.INVISIBLE

            }

            R.id.tv_lun_content->{
                tv_lun_content.text=""
                forumid=""
                forumname=""
                tv_lun_content.visibility=View.INVISIBLE
            }
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
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true  注意：音视频除外
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true  注意：音视频除外
                    // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的

                    /*  val intent = Intent(this, ReleaseImageTextActivity::class.java)
                      intent.putExtra(Constants.IMGAEURL, selectList[0].compressPath)
                      startActivity(intent)
                      finish()*/

                    if (selectList != null) {
                        timpList.clear()
                        for (mitem in selectList) {
                            if (!mitem.compressPath.isNullOrEmpty()) {
                                timpList.add(ChannelBean("1", mitem.compressPath, "", true))
                                timpList.add(ChannelBean("0", "", "", false))
                            } else {
                                showToast("添加失败，稍后重试")
                            }


                        }

                        mReleaseFostAdapter!!.setData(timpList)

                    }

                }
                Constants.CHOSEFORUM -> {
                    forumname= data?.getStringExtra(Constants.KEYNAME)
                    forumid= data?.getStringExtra(Constants.KEYID)
                    if (!forumname.isNullOrEmpty()){
                        tv_lun_content.visibility=View.VISIBLE
                        tv_lun_content.text = data?.getStringExtra(Constants.KEYNAME)

                    }
                }
                Constants.SETADDRESS->{
                    address=data?.getStringExtra(Constants.KEYNAME)
                    if (!address.isNullOrEmpty()){
                       tv_address_content.visibility=View.VISIBLE
                        tv_address_content.text=address

                    }
                }

            }
        }
    }

    private val linearLayouManager by lazy {
        LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

    }

}