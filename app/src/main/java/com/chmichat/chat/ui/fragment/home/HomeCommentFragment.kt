package com.chmichat.chat.ui.fragment.home

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.inputmethod.EditorInfo
import com.chmichat.chat.R
import com.chmichat.chat.base.BaseFragment
import com.chmichat.chat.bean.CommentListEntity
import com.chmichat.chat.mvp.contract.home.CommentListContract
import com.chmichat.chat.mvp.presenter.home.CommentListPresenter
import com.chmichat.chat.showToast
import com.chmichat.chat.ui.adapter.homeadapter.HomeCommentAdapter
import kotlinx.android.synthetic.main.fragment_home_comment.*

/**
 * 长视频播放里面的评论
 * @Author 20342
 * @Date 2019/8/21 9:57
 */
class HomeCommentFragment : BaseFragment(), CommentListContract.View {


    private var mlist = ArrayList<CommentListEntity>()
    private val mPresenter: CommentListPresenter by lazy { CommentListPresenter() }
    private var mId: Int? = 0
    private var map = HashMap<String, String>()
    private var page: Int = 0
    private var mTotalPage: Int? = 0
    private var isCommentUser:Boolean=false
    private var mCommentListEntity:CommentListEntity?=null

    var mHomeCommentAdapter: HomeCommentAdapter? = null

    companion object {
        fun getInstance(id: Int?): HomeCommentFragment {
            val fragment = HomeCommentFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.mId = id
            return fragment
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_home_comment

    override fun initView() {
        mPresenter.attachView(this)
        mHomeCommentAdapter = activity?.let { HomeCommentAdapter(it, mlist) }
        mHomeCommentAdapter?.setOnTitleItemClickListener {
            mCommentListEntity=it
            isCommentUser=true
            et_content.requestFocus()
            activity?.let { it1 -> openKeyBord(et_content, it1) }
        }
        recycle_view.adapter = mHomeCommentAdapter
        recycle_view.layoutManager = LinearLayoutManager(activity)

        et_content.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEND) {
                setsendData()

            }
            false
        }

        refreshLayout.setOnRefreshListener { refreshLayout ->
            //下拉刷新
            page = 1
            setpushdata()
            refreshLayout.finishRefresh()
        }
        refreshLayout.setOnLoadMoreListener { refreshLayout ->
            //加载更多
            page++
            if (page < mTotalPage!!) {
                setpushdata()
                refreshLayout.finishLoadMore()

            } else {
                refreshLayout.finishLoadMore(1000, true, true)

            }

        }
    }

    private fun setpushdata() {

        map.clear()
        map["postId"] = mId.toString()
        map["pageSize"] = "10"
        map["pageNum"] = page.toString()
        mPresenter.getCommentList(map)
    }

    private fun setsendData(){
        map.clear()
        map["postId"]=mId.toString()
        if (isCommentUser){
            if (mCommentListEntity!=null){
                map["receiveUserId"]=mCommentListEntity?.receiveUserId.toString()
                map["commentId"]=mCommentListEntity?.id.toString()
            }
        }
        map["comment"]=et_content.text.toString()
        mPresenter.getPushComment(map)

    }

    override fun lazyLoad() {
        setpushdata()
    }

    override fun onCommentlist(data: ArrayList<CommentListEntity>?, totalpage: Int?) {
        mTotalPage = totalpage
        if (data != null) {

            if (page == 1) {
                mHomeCommentAdapter?.addDataNew(data)
            } else {
                mHomeCommentAdapter?.addDataAll(data)
            }
        } else {

        }


    }

    override fun onPushComment(data: String?) {
        showToast("发送成功，待审核")

        if (isCommentUser){
            isCommentUser=false
            mCommentListEntity=null
        }
         et_content.setText("")
    }

    override fun showError(emg: String, code: Int) {
      showToast(emg)
    }

    override fun showLoading() {
    }

    override fun dismissLoading() {

    }
}