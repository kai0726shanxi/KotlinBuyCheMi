package com.chmichat.chat.ui.fragment.home

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.chmichat.chat.R
import com.chmichat.chat.api.UrlConstant
import com.chmichat.chat.base.BaseFragment
import com.chmichat.chat.bean.PostListEntity
import com.chmichat.chat.glide.GlideApp
import com.chmichat.chat.mvp.contract.home.PostLongVideoContract
import com.chmichat.chat.mvp.presenter.home.PostLongVideoPresenter
import com.chmichat.chat.showToast
import com.chmichat.chat.ui.activity.add.ReleaseLongVideoActivity
import com.chmichat.chat.ui.adapter.homeadapter.HomeIntrudictionAdapter
import com.chmichat.chat.utils.StatusBarUtil
import kotlinx.android.synthetic.main.fragment_home_introduction.*

/**长视频简介
 * @Author 20342
 * @Date 2019/8/21 9:18
 */
class HomeIntroductionFragment : BaseFragment(), PostLongVideoContract.View, View.OnClickListener {

    private val mPresenter by lazy { PostLongVideoPresenter() }
    private var mlist = ArrayList<PostListEntity>()
    private var mHomeIntrudictionAdapter: HomeIntrudictionAdapter? = null
    private var mPostListEntity: PostListEntity? = null
    private var map = HashMap<String, String>()
    private var mactivity: ReleaseLongVideoActivity? = null
    private var drawablezan: Drawable? = null
    private var drawablezancheck: Drawable? = null
    private var drawablecang: Drawable? = null
    private var drawablecangcheck: Drawable? = null
    private var mType:String?=""


    companion object {
        fun getInstance(data: PostListEntity?,type:String?): HomeIntroductionFragment {
            val fragment = HomeIntroductionFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.mPostListEntity = data
            fragment.mType=type
            return fragment
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_home_introduction

    override fun initView() {
        mPresenter.attachView(this)
        tv_collect.setOnClickListener(this)
        tv_zan.setOnClickListener(this)
        tv_cache.setOnClickListener(this)
        drawablezan = resources.getDrawable(R.mipmap.jian_zan_ic)
        drawablezancheck = resources.getDrawable(R.mipmap.jian_zan_red_ic)
        drawablecang = resources.getDrawable(R.mipmap.jian_cang_ic)
        drawablecangcheck = resources.getDrawable(R.mipmap.jian_cang_red_ic)
        mactivity = activity as ReleaseLongVideoActivity?
        mHomeIntrudictionAdapter = activity?.let { HomeIntrudictionAdapter(it, mlist) }

        mHomeIntrudictionAdapter?.setOnTitleItemClickListener {

            mPostListEntity = it
            mactivity?.setvideourl(it)

            setUserData(it)
            if (mType!="me") {
                map["id"] = it?.id.toString()
            } else {
                map["id"] = it?.collectionId.toString()
            }
            mPresenter.getPostRecommendList(map)
        }
        mRecyclerView.isNestedScrollingEnabled = false
        mRecyclerView.adapter = mHomeIntrudictionAdapter
        mRecyclerView.layoutManager = LinearLayoutManager(activity)
        setUserData(mPostListEntity)

    }

    private fun setUserData(it: PostListEntity?) {
        GlideApp.with(this)
                .load(UrlConstant.BASE_URL_IMAGE + it?.userId + ".png")
                .placeholder(R.mipmap.head_ic)
                .into(iv_head)
        tv_name.text = it?.userName
        tv_content.text = it?.postTitle
        if (mType!="me"){
            tv_time.text = it?.createTime + "  " + it?.postStatisticsData?.readingNum + "次观看"
            tv_zan.text = it?.postStatisticsData?.praiseNum.toString()
            tv_comment.text = it?.postStatisticsData?.commentsNum.toString()
            if (it?.userPostOperation?.praise == true) {

                tv_zan.setCompoundDrawablesWithIntrinsicBounds(null, drawablezancheck, null, null)

            } else {
                tv_zan.setCompoundDrawablesWithIntrinsicBounds(null, drawablezan, null, null)

            }


            if (it?.userPostOperation?.collection == true) {
                tv_collect.setCompoundDrawablesWithIntrinsicBounds(null, drawablecangcheck, null, null)

            } else {
                tv_collect.setCompoundDrawablesWithIntrinsicBounds(null, drawablecang, null, null)

            }

            if (it?.userPostOperation?.collection == true) {
                tv_collect.setCompoundDrawablesWithIntrinsicBounds(null, drawablecangcheck, null, null)

            } else {
                tv_collect.setCompoundDrawablesWithIntrinsicBounds(null, drawablecang, null, null)

            }
        }else{
            tv_time.text = it?.createTime + "  " + it?.readingNum + "次观看"
            tv_zan.text = it?.praiseNum.toString()
            tv_comment.text = it?.commentsNum.toString()
            if (it?.praise == true) {

                tv_zan.setCompoundDrawablesWithIntrinsicBounds(null, drawablezancheck, null, null)

            } else {
                tv_zan.setCompoundDrawablesWithIntrinsicBounds(null, drawablezan, null, null)

            }

            tv_collect.setCompoundDrawablesWithIntrinsicBounds(null, drawablecangcheck, null, null)


        }



    }

    override fun lazyLoad() {
        if (mType!="me") {
            map["id"] = mPostListEntity?.id.toString()
        } else {
            map["id"] = mPostListEntity?.collectionId.toString()
        }
        mPresenter.getPostRecommendList(map)
    }


    override fun onPostDetails(data: PostListEntity?) {
        //详情
    }

    override fun onPostRecommendList(data: ArrayList<PostListEntity>?) {
        //相关推荐
        if (data != null) {
            mHomeIntrudictionAdapter?.addDataNew(data)
        }
    }

    override fun showError(errormsg: String, code: Int) {
        showToast(errormsg)
    }

    override fun showLoading() {
    }

    override fun dismissLoading() {
    }

    override fun onCollectPost(date: String?) {
        //收藏
        if (mPostListEntity?.userPostOperation == null) {
            mPostListEntity?.userPostOperation = PostListEntity.UserPostOperation(false, true)

        } else {
            mPostListEntity?.userPostOperation?.collection = true

        }

        tv_collect.setCompoundDrawablesWithIntrinsicBounds(null, drawablecangcheck, null, null)
        showToast("收藏成功")
    }

    override fun onCancelCollectPost(date: String?) {
        //取消收藏
        showToast("已取消")
        tv_collect.setCompoundDrawablesWithIntrinsicBounds(null, drawablecang, null, null)

        if (mPostListEntity?.userPostOperation == null) {
            mPostListEntity?.userPostOperation = PostListEntity.UserPostOperation(false, false)

        } else {
            mPostListEntity?.userPostOperation?.collection = false

        }
    }

    override fun onpraisePost(date: String?) {
        //点赞
        tv_zan.setCompoundDrawablesWithIntrinsicBounds(null, drawablezancheck, null, null)

        if (mPostListEntity?.postStatisticsData != null) {
            mPostListEntity?.postStatisticsData?.praiseNum = mPostListEntity?.postStatisticsData?.praiseNum?.plus(1)!!
            tv_zan.text = mPostListEntity?.postStatisticsData?.praiseNum.toString()
        } else {
            tv_zan.text = "1"
        }



        if (mPostListEntity?.userPostOperation == null) {
            mPostListEntity?.userPostOperation = PostListEntity.UserPostOperation(true, false)

        } else {
            mPostListEntity?.userPostOperation?.praise = true

        }
        showToast("点赞成功")

    }

    override fun onCancelPraisePost(date: String?) {
        //取消点赞
        tv_zan.setCompoundDrawablesWithIntrinsicBounds(null, drawablezan, null, null)

        if (mPostListEntity?.postStatisticsData != null) {
            mPostListEntity?.postStatisticsData?.praiseNum = mPostListEntity?.postStatisticsData?.praiseNum?.minus(1)!!
            tv_zan.text = mPostListEntity?.postStatisticsData?.praiseNum.toString()
        } else {
            tv_zan.text = "0"
        }
        if (mPostListEntity?.userPostOperation == null) {
            mPostListEntity?.userPostOperation = PostListEntity.UserPostOperation(false, false)

        } else {
            mPostListEntity?.userPostOperation?.praise = false

        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_collect -> {

                if (mType=="me"){
                    map["postId"] = mPostListEntity?.collectionId.toString()

                }else{
                    map["postId"] = mPostListEntity?.id.toString()

                }


                if (mPostListEntity?.userPostOperation?.collection == true) {
                    //取消收藏
                    mPresenter.getConcelCollectPostData(map)

                } else {
                    //收藏
                    mPresenter.getCollectPostData(map)
                }
            }
            R.id.tv_zan -> {
                if (mType!="me"){
                    map["postId"] = mPostListEntity?.id.toString()

                }else{
                    map["postId"] = mPostListEntity?.collectionId.toString()

                }


                if (mPostListEntity?.userPostOperation?.praise == true) {
                    //取消点赞
                    mPresenter.getCancelPraisePost(map)

                } else {
                    //点赞
                    mPresenter.getPraisePost(map)

                }
            }
            R.id.tv_cache -> {
                showToast("点击下载")
            }
        }
    }

}