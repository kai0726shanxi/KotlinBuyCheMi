package com.chmichat.chat.ui.activity.me

import android.app.Activity
import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.view.View
import com.bigkoo.pickerview.builder.TimePickerBuilder
import com.bigkoo.pickerview.listener.OnTimeSelectListener
import com.bigkoo.pickerview.view.TimePickerView
import com.chmichat.chat.R
import com.chmichat.chat.base.BaseActivity
import com.chmichat.chat.hintKbTwo
import com.chmichat.chat.ui.dialog.ChoseSexDialog
import com.chmichat.chat.ui.dialog.ChosepictureDialog
import com.chmichat.chat.utils.StatusBarUtil
import com.luck.picture.lib.PictureSelector
import com.luck.picture.lib.config.PictureConfig
import com.luck.picture.lib.config.PictureMimeType
import kotlinx.android.synthetic.main.activity_uesr_information.*
import kotlinx.android.synthetic.main.title_bar_layout.*
import java.text.SimpleDateFormat
import java.util.*
import android.content.Intent
import android.util.Log
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.chmichat.chat.glide.GlideApp


/**
 * 编辑用户信息
 * @Author 20342
 * @Date 2019/8/14 12:07
 */
class UserInformationActivity : BaseActivity(), View.OnClickListener {


    override fun layoutId(): Int {
        return R.layout.activity_uesr_information
    }

    override fun initData() {
    }

    private var pvTime: TimePickerView? = null

    override fun initView() {
        StatusBarUtil.darkMode(this)
        StatusBarUtil.setPaddingSmart(this, cl_bar)
        iv_left.visibility = View.VISIBLE
        iv_left.setColorFilter(Color.BLACK)
        tv_title.setTextColor(Color.BLACK)
        tv_title.text="编辑个人资料"
        tv_right.visibility=View.VISIBLE
        tv_right.setTextColor(ContextCompat.getColor(this,R.color.displaynomal))
        tv_right.text="保存"
        cl_bar.setBackgroundColor(Color.WHITE)
        iv_left.setOnClickListener(this)
        tv_age_content.setOnClickListener(this)
        tv_sex_content.setOnClickListener(this)
        iv_head.setOnClickListener(this)
        mChoseDialog.setBtnDataLinsenter(object :ChoseSexDialog.BtnDataLinsenter{
            override fun btndata(str: String) {
             tv_sex_content.text=str
            }
        })
        mChosepicDialog.setBtnDataLinsenter(object :ChosepictureDialog.BtnDataLinsenter{
            override fun btndata(str: String) {
               if (str.isNullOrEmpty()){
                   return
               }
                if (str == "1"){
                    //打开相机
                    opencamera()
                }else if (str=="2") {
                    //打开相册
                    openpicture()

                }
            }
        })


        pvTime = TimePickerBuilder(this, OnTimeSelectListener { date, _ ->
           tv_age_content.text=simpleDateFormat.format(date)
        }).setDecorView(
                window.decorView.findViewById(android.R.id.content))
                .setCancelText("取消")//取消按钮文字
                .setSubmitText("确认")//确认按钮文字
                .setTitleSize(20)//标题文字大小
                .setOutSideCancelable(false)//点击屏幕，点在控件外部范围时，是否取消显示
                .isCyclic(true)//是否循环滚动
                .setSubmitColor(ContextCompat.getColor(this, R.color.displaynomal))//确定按钮文字颜色
                .setCancelColor(ContextCompat.getColor(this, R.color.medynamic))//取消按钮文字颜色
                .setTextColorCenter(ContextCompat.getColor(this, R.color.displaynomal))
                .setBgColor(Color.WHITE)//滚轮背景颜色 Night mode
                .setTitleBgColor(Color.WHITE)
                .isCenterLabel(true) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .build()
    }

    override fun start() {

    }

 private fun opencamera(){
     PictureSelector.create(this)
             .openCamera(PictureMimeType.ofImage())
             .enableCrop(true)// 是否裁剪 true or false
             .compress(true)// 是否压缩 true or
             .showCropFrame(true)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false   true or false
             .showCropGrid(false)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false    true or false
             .forResult(PictureConfig.CHOOSE_REQUEST) }
 private fun  openpicture(){
     PictureSelector.create(this)
             .openGallery(PictureMimeType.ofImage())
             .isCamera(false)// 是否显示拍照按钮 true or false
             .enableCrop(true)// 是否裁剪 true or false
             .compress(true)// 是否压缩 true or
             .showCropFrame(true)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false   true or false
             .showCropGrid(false)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false    true or false
             .forResult(PictureConfig.CHOOSE_REQUEST)

 }
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iv_left -> {
                finish()
            }
            R.id.tv_age_content -> {
                hintKbTwo(this)
               pvTime!!.show()
               /* PictureSelector.create(this)
                        .openCamera(PictureMimeType.ofVideo())
                        .recordVideoSecond(15)
                        .videoQuality(0)// 视频录制质量 0 or 1 int
                        .compress(true)// 是否压缩 true or false
                        .forResult(PictureConfig.CHOOSE_REQUEST)*/
            }
            R.id.tv_sex_content -> {
                hintKbTwo(this)

                mChoseDialog.show()
            }
            R.id.iv_head->{
                hintKbTwo(this)

                if(!mChosepicDialog.isShowing){
                    mChosepicDialog.show()

                }
            }

        }
    }

    private val mChoseDialog: ChoseSexDialog by lazy {
        ChoseSexDialog(this)
    }
    private val mChosepicDialog: ChosepictureDialog by lazy {
        ChosepictureDialog(this)
    }

    private val simpleDateFormat by lazy {
        SimpleDateFormat("yyyy-MM-dd", Locale.CHINA)
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
                    Log.e("测试》》",selectList[0].compressPath)
                    GlideApp.with(this)
                            .load(selectList[0].compressPath)
                            .circleCrop()
                            .into(iv_head)

                }
            }
        }
    }

}