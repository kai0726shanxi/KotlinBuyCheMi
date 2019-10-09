package com.chmichat.chat.ui.activity.home

import android.graphics.drawable.Drawable
import android.support.v7.widget.OrientationHelper
import android.text.Html
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.chmichat.chat.Constants
import com.chmichat.chat.R
import com.chmichat.chat.base.BaseActivity
import com.chmichat.chat.bean.PostListEntity
import com.chmichat.chat.glide.GlideApp
import com.chmichat.chat.mvp.contract.home.PlayVideoContract
import com.chmichat.chat.mvp.presenter.home.PlayvideoPresenter
import com.chmichat.chat.showToast
import com.chmichat.chat.ui.adapter.homeadapter.PlayVideoAdapter
import com.chmichat.chat.ui.dialog.VideoCommentDialog
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

class PlayVideoActivity : BaseActivity(), PlayVideoContract.View, View.OnClickListener {


    private var mPlayVideoAdapter: PlayVideoAdapter? = null
    private var mLayoutManager: ViewPagerLayoutManager? = null
    private var isplaying: Boolean = false
    private var mVideoView: VideoView? = null
    private var mTikTokController: VideoViewController? = null
    private var mCurrentPosition: Int = 0
    private var mivPlay: ImageView? = null
    private var mPlayPosition: Int = 0
    private var mlist = ArrayList<PostListEntity>()
    private var mcollect: TextView? = null
    private var mlike: TextView? = null
    private var mPostListEntity: PostListEntity? = null
    private var map = HashMap<String, String>()
    private var mType: String? = ""
    private var drawablezan: Drawable? = null
    private var drawablezancheck: Drawable? = null

    private var drawablecang: Drawable? = null
    private var drawablecangcheck: Drawable? = null
    private var misCollection: Boolean = false//从收藏进来，本地标记是否收藏
    private val mPresenter: PlayvideoPresenter by lazy { PlayvideoPresenter() }
    override fun layoutId(): Int {

        return R.layout.activity_playvideo
    }

    override fun initData() {
        mPlayPosition = intent.getIntExtra(Constants.PLAYPOSITION, 0)
        mlist = intent.getSerializableExtra(Constants.PLAYLIST) as ArrayList<PostListEntity>
        mType = intent.getStringExtra(Constants.KEYTYPE)
    }

    override fun initView() {
        mPresenter.attachView(this)
        StatusBarUtil.darkMode(this)
        drawablezan = resources.getDrawable(R.mipmap.like_video_ic)
        drawablezancheck = resources.getDrawable(R.mipmap.like_video_red_ic)
        drawablecang = resources.getDrawable(R.mipmap.collect_video_ic)
        drawablecangcheck = resources.getDrawable(R.mipmap.collect_video_red_ic)
        mVideoView = VideoView(this)
        mVideoView?.setLooping(true)
        mTikTokController = VideoViewController(this)
        mVideoView?.setVideoController(mTikTokController)
        mPlayVideoAdapter = PlayVideoAdapter(this, mlist)
        recycle_view.adapter = mPlayVideoAdapter
        mLayoutManager = ViewPagerLayoutManager(this, OrientationHelper.VERTICAL)

        recycle_view.layoutManager = mLayoutManager
        recycle_view.scrollToPosition(mPlayPosition)
        initListener()

    }

    private fun initListener() {

        mLayoutManager?.setOnViewPagerListener(object : OnViewPagerListener {


            override fun onInitComplete() {
                //自动播放第一条
                startPlay(mPlayPosition)
            }

            override fun onPageRelease(isNext: Boolean, position: Int) {
                if (mCurrentPosition == position) {
                    if (isplaying) {
                        isplaying = !isplaying
                        if (mivPlay != null) {
                            mivPlay?.visibility = View.GONE
                        }

                    }
                    mVideoView?.release()
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
        mPostListEntity = mlist[position]
        val itemView = recycle_view.getChildAt(0)
        val frameLayout = itemView.findViewById<FrameLayout>(R.id.container)
        mcollect = itemView.findViewById<TextView>(R.id.tv_collect)
        val mcomment = itemView.findViewById<TextView>(R.id.tv_comment)
        mlike = itemView.findViewById<TextView>(R.id.tv_lick)
        val mhead = itemView.findViewById<CircleImageView>(R.id.iv_head)
        val mivshare = itemView.findViewById<ImageView>(R.id.iv_share)
        val mivleft = itemView.findViewById<ImageView>(R.id.iv_left)
        val viewBg = itemView.findViewById<View>(R.id.view_bg)
        val maddress = itemView.findViewById<TextView>(R.id.tv_address)
        val mName = itemView.findViewById<TextView>(R.id.tv_name)
        val mcontent = itemView.findViewById<TextView>(R.id.tv_content)

        if (mType == "me") {
            mcollect?.setCompoundDrawablesWithIntrinsicBounds(null, drawablecangcheck, null, null)
           if (mPostListEntity?.praise!=null&& mPostListEntity?.praise==true){
               mlike?.setCompoundDrawablesWithIntrinsicBounds( null,drawablezancheck, null, null)
           }
            mlike?.text = mPostListEntity?.praiseNum.toString()
            mcomment?.text = mPostListEntity?.collectionNum.toString()

        } else {
            mlike?.text = mPostListEntity?.postStatisticsData?.praiseNum.toString()
            mcomment?.text = mPostListEntity?.postStatisticsData?.commentsNum.toString()
            if (mPostListEntity?.userPostOperation != null) {

                if (mPostListEntity?.userPostOperation?.collection==true){
                    mcollect?.setCompoundDrawablesWithIntrinsicBounds(null, drawablecangcheck, null, null)

                }else{
                    mcollect?.setCompoundDrawablesWithIntrinsicBounds(null, drawablecang, null, null)

                }

                if (mPostListEntity?.userPostOperation?.praise==true) {
                    mlike?.setCompoundDrawablesWithIntrinsicBounds( null,drawablezancheck, null, null)
                } else {
                    mlike?.setCompoundDrawablesWithIntrinsicBounds( null,drawablezan, null, null)
                }
            }
        }

        mivPlay = itemView.findViewById<ImageView>(R.id.iv_play)
        mcollect?.setOnClickListener(this)
        mcomment.setOnClickListener(this)
        mlike?.setOnClickListener(this)
        mhead.setOnClickListener(this)
        mivshare.setOnClickListener(this)
        mivleft.setOnClickListener(this)
        viewBg.setOnClickListener(this)


        maddress.text = mlist[position].position
        mName.text = mlist[position].userName
        mcontent.text = Html.fromHtml(mlist[position].postTitle)


        mTikTokController?.thumb?.let {
            GlideApp.with(this)
                .load(mlist[position].firstCover)
                .placeholder(R.mipmap.moren_icon)
                .into(it)
        }
        val parent = mVideoView?.parent
        if (parent is FrameLayout) {
            parent.removeView(mVideoView)
        }
        frameLayout.addView(mVideoView)

        mVideoView?.setUrl(mlist[position].videoUrl)
        /*  if (mVideoView?.videoSize!![0] < mVideoView!!.videoSize[1]) {
              mVideoView?.setScreenScale(VideoView.KEEP_SCREEN_ON)

          } else {
              mVideoView?.setScreenScale(VideoView.SCREEN_SCALE_CENTER_CROP)

          }*/
        mVideoView?.setScreenScale(VideoView.KEEP_SCREEN_ON)

        mVideoView?.start()


    }


    override fun onDestroy() {
        mVideoView?.release()
        super.onDestroy()

    }

    override fun onResume() {
        if (mVideoView != null) {
            if (isplaying) {
                isplaying = !isplaying
                mivPlay?.visibility = View.GONE
            }
            mVideoView?.resume()

        }

        super.onResume()
    }

    override fun onPause() {
        if (mVideoView != null) {
            mVideoView?.pause()

        }

        super.onPause()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_collect -> {
                map.clear()
                if (mType == "me") {
                    map["postId"] = mPostListEntity?.collectionId.toString()

                    if (!misCollection) {
                        //取消
                        mPresenter.getConcelCollectPostData(map)

                    } else {
                        //收藏
                        mPresenter.getCollectPostData(map)

                    }

                } else {
                    map["postId"] = mPostListEntity?.id.toString()


                    if (mPostListEntity?.userPostOperation?.collection==true) {
                        //取消收藏
                        mPresenter.getConcelCollectPostData(map)

                    } else {
                        //收藏
                        mPresenter.getCollectPostData(map)
                    }
                }


            }
            R.id.tv_comment -> {
                map.clear()
                mVideoDialog.setId(mPostListEntity?.id, mPostListEntity?.postStatisticsData?.commentsNum)
                mVideoDialog.show()
            }
            R.id.tv_lick -> {
                map.clear()
                if (mType == "me") {
                    map.clear()
                    map["postId"] = mPostListEntity?.collectionId.toString()

                    if (mPostListEntity?.praise!=null&&mPostListEntity?.praise==true) {
                        //取消
                        mPresenter.getCancelPraisePost(map)

                    } else {
                        //点赞
                        mPresenter.getPraisePost(map)

                    }
                } else {
                    map["postId"] = mPostListEntity?.id.toString()


                    if (mPostListEntity?.userPostOperation?.praise==true) {
                        //取消点赞
                        mPresenter.getCancelPraisePost(map)

                    } else {
                        //点赞
                        mPresenter.getPraisePost(map)

                    }
                }

            }
            R.id.iv_head ->

                showToast("头像")

            R.id.iv_share -> {
                map.clear()

                showToast("分享")
            }
            R.id.iv_left -> {
                finish()
            }

            R.id.view_bg -> {


                if (!isplaying) {
                    if (!mVideoView?.isPlaying!!) {
                        return
                    }
                    isplaying = !isplaying

                    if (mivPlay != null) {
                        mivPlay?.visibility = View.VISIBLE
                    }
                    mVideoView?.pause()
                } else {

                    isplaying = !isplaying
                    if (mivPlay != null) {
                        mivPlay?.visibility = View.GONE
                    }
                    mVideoView?.resume()
                }
            }

        }
    }


    private val mVideoDialog: VideoCommentDialog by lazy {
        VideoCommentDialog(this)
    }

    override fun onCollectPost(date: String?) {
        mPostListEntity?.userPostOperation?.collection = true
        misCollection=false

        //收藏
        mcollect?.setCompoundDrawablesWithIntrinsicBounds(null, drawablecangcheck, null, null)

    }

    override fun onCancelCollectPost(date: String?) {
        mPostListEntity?.userPostOperation?.collection = false
        misCollection=true

        //取消收藏
        mcollect?.setCompoundDrawablesWithIntrinsicBounds(null, drawablecang, null, null)

    }

    override fun showError(errmsg: String, code: Int) {
        showToast(errmsg)
    }

    override fun showLoading() {
    }

    override fun dismissLoading() {
    }

    override fun onpraisePost(date: String?) {
        mPostListEntity?.userPostOperation?.praise = true
        mPostListEntity?.praise=true

        //点赞
        mlike?.setCompoundDrawablesWithIntrinsicBounds(null, drawablezancheck, null, null)


    }

    override fun onCancelPraisePost(date: String?) {
        //取消点赞
        mPostListEntity?.userPostOperation?.praise = false
        mPostListEntity?.praise=false
        mlike?.setCompoundDrawablesWithIntrinsicBounds( null, drawablezan,null, null)


    }
}
