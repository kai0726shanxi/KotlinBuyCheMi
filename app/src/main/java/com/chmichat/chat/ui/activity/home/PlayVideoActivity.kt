package com.chmichat.chat.ui.activity.home

import android.support.v7.widget.OrientationHelper
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.chmichat.chat.R
import com.chmichat.chat.base.BaseActivity
import com.chmichat.chat.glide.GlideApp
import com.chmichat.chat.showToast
import com.chmichat.chat.ui.adapter.homeadapter.PlayVideoAdapter
import com.chmichat.chat.view.layoutmanagergroup.viewpager.OnViewPagerListener
import com.chmichat.chat.view.layoutmanagergroup.viewpager.ViewPagerLayoutManager
import kotlinx.android.synthetic.main.activity_playvideo.*
import com.chmichat.chat.utils.StatusBarUtil
import com.chmichat.chat.view.CircleImageView
import com.chmichat.chat.view.VideoViewController
import com.dueeeke.videoplayer.player.VideoView


/**
 * @Author 20342
 * @Date 2019/8/17 20:35
 */

class PlayVideoActivity : BaseActivity(), View.OnClickListener {

    private var mPlayVideoAdapter: PlayVideoAdapter? = null
    private var mLayoutManager: ViewPagerLayoutManager? = null
    private var isplaying: Boolean? = null
    private var mVideoView: VideoView? = null
    private var mTikTokController: VideoViewController? = null
    private var mCurrentPosition: Int = 0
    private var mlist = arrayListOf("1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1")
    override fun layoutId(): Int {

        return R.layout.activity_playvideo
    }

    override fun initData() {
    }

    override fun initView() {
        StatusBarUtil.darkMode(this)
        mVideoView = VideoView(this)
        mVideoView!!.setLooping(true)
        mTikTokController = VideoViewController(this)
        mVideoView!!.setVideoController(mTikTokController)
        mPlayVideoAdapter = PlayVideoAdapter(this, mlist)
        recycle_view.adapter = mPlayVideoAdapter
        mLayoutManager = ViewPagerLayoutManager(this, OrientationHelper.VERTICAL)
        recycle_view.layoutManager = mLayoutManager
        initListener()

    }

    private fun initListener() {

        mLayoutManager!!.setOnViewPagerListener(object : OnViewPagerListener {


            override fun onInitComplete() {
                //自动播放第一条
                startPlay(0)
            }

            override fun onPageRelease(isNext: Boolean, position: Int) {
                if (mCurrentPosition == position) {
                    mVideoView!!.release()
                }

            }


            override fun onPageSelected(position: Int, isBottom: Boolean) {


                if (mCurrentPosition == position) return
                startPlay(position)
                mCurrentPosition = position

            }


        })

    }


    override fun start() {
    }

    private fun startPlay(position: Int) {
        val itemView = recycle_view.getChildAt(0)
        val frameLayout = itemView.findViewById<FrameLayout>(R.id.container)
        val mcollect = itemView.findViewById<TextView>(R.id.tv_collect)
        val mcomment = itemView.findViewById<TextView>(R.id.tv_comment)
        val mlike = itemView.findViewById<TextView>(R.id.tv_lick)
        val mhead = itemView.findViewById<CircleImageView>(R.id.iv_head)
        val mivshare = itemView.findViewById<ImageView>(R.id.iv_share)
        val mivleft = itemView.findViewById<ImageView>(R.id.iv_left)
        mcollect.setOnClickListener(this)
        mcomment.setOnClickListener(this)
        mlike.setOnClickListener(this)
        mhead.setOnClickListener(this)
        mivshare.setOnClickListener(this)
        mivleft.setOnClickListener(this)



        GlideApp.with(this)
                .load("https://p1.pstatp.com/large/4bea0014e31708ecb03e.jpeg")
                .placeholder(R.mipmap.moren_icon)
                .into(mTikTokController!!.thumb!!)
        val parent = mVideoView!!.parent
        if (parent is FrameLayout) {
            parent.removeView(mVideoView)
        }
        frameLayout.addView(mVideoView)
        if (position % 2 == 0) {
            mVideoView!!.setUrl("https://aweme.snssdk.com/aweme/v1/play/?video_id=374e166692ee4ebfae030ceae117a9d0&line=0&ratio=720p&media_type=4&vr_type=0")

        } else {
            mVideoView!!.setUrl("http://vfx.mtime.cn/Video/2019/02/04/mp4/190204084208765161.mp4")

        }
        if (mVideoView!!.videoSize[0] < mVideoView!!.videoSize[1]) {
            mVideoView!!.setScreenScale(VideoView.KEEP_SCREEN_ON)

        } else {
            mVideoView!!.setScreenScale(VideoView.SCREEN_SCALE_CENTER_CROP)

        }
        mVideoView!!.start()

    }


    override fun onDestroy() {
        mVideoView!!.release()
        super.onDestroy()

    }

    override fun onResume() {
        if (mVideoView != null) {
            mVideoView!!.resume()

        }

        super.onResume()
    }

    override fun onPause() {
        if (mVideoView != null) {
            mVideoView!!.pause()

        }

        super.onPause()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_collect -> {
                showToast("收藏")

            }
            R.id.tv_comment -> {
                showToast("评论")

            }
            R.id.tv_lick -> {
                showToast("喜欢")

            }
            R.id.iv_head ->
                showToast("头像")

            R.id.iv_share -> {
           showToast("分享")
            }
            R.id.iv_left -> {
            finish()
            }

        }
    }

}
