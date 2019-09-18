package com.chmichat.chat.ui.activity.add

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.view.View
import com.chmichat.chat.Constants
import com.chmichat.chat.R
import com.chmichat.chat.base.BaseActivity
import com.chmichat.chat.bean.SaveImageTextEntity
import com.chmichat.chat.utils.SpUtil
import com.chmichat.chat.utils.StatusBarUtil
import com.luck.picture.lib.PictureSelector
import com.luck.picture.lib.config.PictureConfig
import com.luck.picture.lib.config.PictureMimeType
import kotlinx.android.synthetic.main.activity_home_add.*

/**
 * 首页添加
 * @Author 20342
 * @Date 2019/8/12 14:43
 */
class HomeAddActivity : BaseActivity(), View.OnClickListener {

   private var imageTextEntity:SaveImageTextEntity?=null
    override fun layoutId(): Int {

        return R.layout.activity_home_add
    }

    override fun initData() {

    }

    override fun initView() {
        StatusBarUtil.darkMode(this)
        tv_add_imgtxt.setOnClickListener(this)
        tv_add_longvideo.setOnClickListener(this)
        tv_add_video.setOnClickListener(this)
        tv_add_tie.setOnClickListener(this)
        iv_delete.setOnClickListener(this)
    }

    override fun start() {
    }


    override fun onClick(v: View?) {
        when {
            v?.id == R.id.tv_add_imgtxt -> {
                //图文
                imageTextEntity=SpUtil.getObject(this,Constants.SAVEIMGTXT)
                if (imageTextEntity!=null){
                    val intent = Intent(this, ReleaseImageTextActivity::class.java)
                    intent.putExtra(Constants.SAVEIMGTXT,imageTextEntity)
                    startActivity(intent)
                    finish()

                }else{
                    openpicture()

                }
            }
            v?.id == R.id.tv_add_tie -> {
                //帖子
                  startActivity(Intent(this,MyReleasePostActivity::class.java))
                finish()

            }
            v?.id == R.id.tv_add_video -> {
                //小视频
                openvideo()

            }
            v?.id == R.id.tv_add_longvideo -> {
                //长视频
            }
            v?.id == R.id.iv_delete -> {
                //删除
                finish()

            }


        }
    }

    private fun openvideo() {
        //打开小视频
        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofVideo())
                .compress(true)// 是否压缩 true or
                .videoQuality(0)// 视频录制质量  0 or 1 int
                .recordVideoSecond(15)//视频秒数录制 默认60s int
                .videoMaxSecond(16)// 显示多少秒以内的视频or音频也可适用 int
                .forResult(Constants.CHOOSE_VIDEO)

    }

    private fun openpicture() {
        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofImage())
                .enableCrop(true)// 是否裁剪 true or false
                .compress(true)// 是否压缩 true or
                .showCropFrame(true)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false   true or false
                .showCropGrid(false)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false    true or false
                .selectionMode(PictureConfig.SINGLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
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

                Constants.CHOOSE_VIDEO -> {
                    val selectList = PictureSelector.obtainMultipleResult(data)
                    Log.e("测试》》", selectList[0].path+">>"+selectList[0].duration)

                    val intent = Intent(this, ReleaseVideoActivity::class.java)
                    intent.putExtra(Constants.VIDEOURL, selectList[0].path)
                    intent.putExtra(Constants.DURATION, selectList[0].duration)

                    startActivity(intent)
                    finish()
                }
            }
        }
    }
}