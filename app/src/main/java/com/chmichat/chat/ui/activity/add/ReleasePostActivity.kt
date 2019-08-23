package com.chmichat.chat.ui.activity.add

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.View
import com.chmichat.chat.Constants
import com.chmichat.chat.R
import com.chmichat.chat.base.BaseActivity
import com.chmichat.chat.utils.StatusBarUtil
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

    override fun layoutId(): Int {
        return R.layout.activity_release_post
    }

    override fun initData() {
    }

    override fun initView() {
        StatusBarUtil.darkMode(this)
        StatusBarUtil.setPaddingSmart(this, cl_bar)
        iv_left.setOnClickListener(this)
        iv_icon.setOnClickListener(this)
        iv_pic.setOnClickListener(this)
        iv_address.setOnClickListener(this)
        iv_classify.setOnClickListener(this)

        iv_left.visibility = View.VISIBLE
        iv_left.setColorFilter(Color.BLACK)
        tv_title.text = "发帖"
        tv_right.visibility = View.VISIBLE
        tv_right.text = "发布"
        tv_right.setTextColor(ContextCompat.getColor(this, R.color.displaynomal))

    }

    override fun start() {
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iv_left -> finish()
            R.id.iv_icon -> {
                //图标

            }
            R.id.iv_pic -> {
                //照片
                openpicture()
            }

            R.id.iv_classify -> {
                //分类
            }
            R.id.iv_address -> {
                //地址
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

                    val intent = Intent(this, ReleaseImageTextActivity::class.java)
                    intent.putExtra(Constants.IMGAEURL, selectList[0].compressPath)
                    startActivity(intent)
                    finish()

                }


            }
        }
    }

}