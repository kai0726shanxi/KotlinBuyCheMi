package com.chmichat.chat.ui.activity.add

import android.support.v4.app.Fragment
import android.util.Log
import android.view.View
import com.chmichat.chat.R
import com.chmichat.chat.base.BaseActivity
import com.chmichat.chat.base.BaseFragmentAdapter
import com.chmichat.chat.ui.fragment.home.HomeCommentFragment
import com.chmichat.chat.ui.fragment.home.HomeIntroductionFragment
import com.dueeeke.videocontroller.StandardVideoController
import com.dueeeke.videoplayer.listener.OnVideoViewStateChangeListener
import com.dueeeke.videoplayer.player.VideoView
import com.dueeeke.videoplayer.util.L
import kotlinx.android.synthetic.main.activity_release_long_video.*

/**
 * 长视频的播放界面
 * @Author 20342
 * @Date 2019/8/20 17:05
 */
class ReleaseLongVideoActivity : BaseActivity(), View.OnClickListener {


    private val mTitles = arrayListOf("简介", "评论")
    private var mFragments = arrayListOf<Fragment>(HomeIntroductionFragment.getInstance(), HomeCommentFragment.getInstance())
    override fun layoutId(): Int {

        return R.layout.activity_release_long_video
    }

    override fun initData() {
    }

    override fun initView() {
        mViewPager.adapter = BaseFragmentAdapter(supportFragmentManager, mFragments, mTitles)

        tab_layout.setViewPager(mViewPager)
        tab_layout.showMsg(1, 22)
        iv_left.setOnClickListener(this)
        iv_playm.setOnClickListener(this)
        val controller = StandardVideoController(this)
        controller.setTitle("测试标题")
        player.setVideoController(controller)
        player.replay(false)
        player.setUrl("http://vfx.mtime.cn/Video/2019/03/12/mp4/190312143927981075.mp4")
        //   player.setUrl("http://ivi.bupt.edu.cn/hls/cctv6hd.m3u8")
        player.setOnVideoViewStateChangeListener(object : OnVideoViewStateChangeListener {
            override fun onPlayStateChanged(playState: Int) {
                Log.e("demo>>", playState.toString() + "")

                when (playState) {
                    VideoView.STATE_IDLE -> {
                    }
                    VideoView.STATE_PREPARING -> {
                    }
                    VideoView.STATE_PREPARED -> {
                        //需在此时获取视频宽高
                        val videoSize = player.videoSize
                        L.d("视频宽：" + videoSize[0])
                        L.d("视频高：" + videoSize[1])
                    }
                    VideoView.STATE_PLAYING -> {
                        runOnUiThread {
                            iv_playm.visibility = View.GONE

                        }
                    }
                    VideoView.STATE_PAUSED -> {
                        runOnUiThread {
                            iv_playm.visibility = View.VISIBLE

                        }
                    }
                    VideoView.STATE_BUFFERING -> {
                    }
                    VideoView.STATE_BUFFERED -> {
                    }
                    VideoView.STATE_PLAYBACK_COMPLETED -> {
                        //播放完的回调
                        runOnUiThread {
                        }
                    }
                    VideoView.STATE_ERROR -> {

                    }
                }
            }

            override fun onPlayerStateChanged(playerState: Int) {
                when (playerState) {
                    VideoView.PLAYER_NORMAL//小屏
                    -> {
                    }
                    VideoView.PLAYER_FULL_SCREEN//全屏
                    -> {
                    }
                }
            }

        }
        )
        player.start()


    }

    override fun start() {
    }

    override fun onDestroy() {

        super.onDestroy()
        if (player != null) {
            player.release()
        }
    }

    override fun onResume() {

        super.onResume()
        if (player != null) {
            player.resume()
        }
    }

    override fun onPause() {

        super.onPause()
        if (player != null) {
            player.pause()
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iv_left -> {
                finish()
            }
            R.id.iv_playm -> {
                player.resume()
            }
        }
    }
}