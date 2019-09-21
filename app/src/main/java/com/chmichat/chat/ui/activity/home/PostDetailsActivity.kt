package com.chmichat.chat.ui.activity.home

import android.graphics.Color
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.view.inputmethod.EditorInfo
import com.chmichat.chat.Constants
import com.chmichat.chat.R
import com.chmichat.chat.api.UrlConstant
import com.chmichat.chat.base.BaseActivity
import com.chmichat.chat.bean.CommentListEntity
import com.chmichat.chat.bean.PostListEntity
import com.chmichat.chat.glide.GlideApp
import com.chmichat.chat.mvp.contract.home.PostDetailsContract
import com.chmichat.chat.mvp.presenter.home.PostDetailsPresenter
import com.chmichat.chat.showToast
import com.chmichat.chat.ui.adapter.homeadapter.PostDetailsCommentAdapter
import com.chmichat.chat.ui.adapter.homeadapter.PostRecommendAdapter
import com.chmichat.chat.ui.dialog.ShareDialog
import com.chmichat.chat.utils.StatusBarUtil
import kotlinx.android.synthetic.main.activity_post_details.*
import kotlinx.android.synthetic.main.title_bar_layout.*

/**
 *
 * 帖子详情
 * @Author 20342
 * @Date 2019/9/20 9:01
 */
class PostDetailsActivity : BaseActivity(), PostDetailsContract.View, View.OnClickListener {
    private val mPresenter: PostDetailsPresenter by lazy { PostDetailsPresenter() }
    private var mlist = arrayListOf("1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1")
    private var mCommentlist = ArrayList<CommentListEntity>()
    private var mPostDetailsCommentAdapter: PostDetailsCommentAdapter? = null
    private var mPostRecommendAdapter: PostRecommendAdapter? = null
    private var mCommentListEntity: CommentListEntity? = null
    private var map = HashMap<String, String>()
    private var mId: Int? = 0
    private var page: Int = 1
    private var mTotalPage: Int? = 0
    private var isCommentUser: Boolean = false
    private var commentmap = HashMap<String, String>()
    private var head = "<style>\n" + "  \n" + "img{\n" + " max-width:100%;\n" + " height:auto;\n" + "}\n" + "  \n" + "</style>"

    private val mDialog:ShareDialog by lazy { ShareDialog(this) }
    override fun layoutId(): Int {
        return R.layout.activity_post_details
    }

    override fun initData() {
        mId = intent.getIntExtra(Constants.KEYNAME, 0)
    }

    override fun initView() {
        mPresenter.attachView(this)
        StatusBarUtil.darkMode(this)
        StatusBarUtil.setPaddingSmart(this, cl_bar)
        iv_left.visibility = View.VISIBLE
        iv_left.setColorFilter(Color.BLACK)
        iv_right.visibility = View.VISIBLE
        iv_right.setImageResource(R.mipmap.postdetails_more)
        tv_title.text = ""
        tv_content.isHorizontalScrollBarEnabled = false;
        iv_left.setOnClickListener(this)
        iv_right.setOnClickListener(this)
        tv_share.setOnClickListener(this)
        recycle_recommend.isNestedScrollingEnabled = false
        recycle_comment.isNestedScrollingEnabled = false
        mPostRecommendAdapter = PostRecommendAdapter(this, mlist)
        recycle_recommend.adapter = mPostRecommendAdapter
        recycle_recommend.layoutManager = LinearLayoutManager(this)
        mPostDetailsCommentAdapter = PostDetailsCommentAdapter(this, mCommentlist)
        mPostDetailsCommentAdapter?.setOnTitleItemClickListener {
            mCommentListEntity=it
            isCommentUser=true
            et_content.requestFocus()
            openKeyBord(et_content,this)
        }
        recycle_comment.adapter = mPostDetailsCommentAdapter
        recycle_comment.layoutManager = LinearLayoutManager(this)

        et_content.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEND) {
                setsendData()

            }
            false
        }
        refreshLayout.setOnLoadMoreListener { refreshLayout ->
            //加载更多
            page++
            if (page < mTotalPage!!) {
                setPushComment()
                refreshLayout.finishLoadMore()

            } else {
                refreshLayout.finishLoadMore(1000, true, true)

            }
        }

    }

    private fun setsendData() {
        map.clear()
        map["postId"] = mId.toString()
        if (isCommentUser) {
            if (mCommentListEntity != null) {
                map["receiveUserId"] = mCommentListEntity?.receiveUserId.toString()
                map["commentId"] = mCommentListEntity?.id.toString()
            }
        }
        map["comment"] = et_content.text.toString()
        mPresenter.getSendComment(map)

    }

    override fun start() {

        mPresenter.getPostDetails(mId.toString())
        //   mPresenter.getPostRecommendList(map)
        setPushComment()
    }

    private fun setPushComment() {
        commentmap.clear()
        commentmap["pageSize"] = "10"
        commentmap["pageNum"] = page.toString()
        commentmap["postId"] = mId.toString()
        mPresenter.getPostCommentList(commentmap)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iv_left -> {
                finish()

            }

            R.id.iv_right -> {


            }
            R.id.tv_share->{
                mDialog.show()
            }
        }

    }


    override fun onPostDetails(data: PostListEntity?) {

        setViewData(data)


    }

    private fun setViewData(data: PostListEntity?) {
        tv_mtitle.text = data?.postTitle
        GlideApp.with(this)
                .load(UrlConstant.BASE_URL_IMAGE + data?.userId + ".png")
                .placeholder(R.mipmap.head_ic)
                .into(iv_avater)
        tv_name.text = data?.author
        tv_readnum.text = "阅读 " + data?.postStatisticsData?.readingNum + " | " + data?.createTime
        tv_zan.text = data?.postStatisticsData?.praiseNum.toString()
        tv_cai.text = data?.postStatisticsData?.treadNum.toString()
        tv_content.loadDataWithBaseURL(UrlConstant.BASE_URL, head + data?.content, "text/html", "UTF-8", "about:blank")


    }

    override fun onPostRecommendList(data: ArrayList<PostListEntity>?) {
    }

    override fun onPostCommentList(data: ArrayList<CommentListEntity>?, totalpage: Int?) {
        mTotalPage = totalpage
        if (data != null) {
            if (page == 1) {
                mPostDetailsCommentAdapter?.addDataNew(data)
            } else {
                mPostDetailsCommentAdapter?.addDataAll(data)

            }

        } else {

        }
    }

    override fun onPostSendComment(data: String?) {
        if (isCommentUser){
            isCommentUser=false
            mCommentListEntity=null
        }
        showToast("发送成功，待审核")
        et_content.setText("")

    }

    override fun showError(errormsg: String, code: Int) {
        showToast(errormsg)
    }

    override fun showLoading() {
    }

    override fun dismissLoading() {
    }
}