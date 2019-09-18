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
class HomeCommentFragment : BaseFragment(),CommentListContract.View {


    private var mlist = arrayListOf("1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1")
    private val mPresenter:CommentListPresenter by lazy { CommentListPresenter() }
    private var mId:Int?=0
    private var map=HashMap<String,String>()

    var mHomeCommentAdapter: HomeCommentAdapter? = null

    companion object {
        fun getInstance(id:Int?): HomeCommentFragment {
            val fragment = HomeCommentFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.mId=id
            return fragment
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_home_comment

    override fun initView() {
        mPresenter.attachView(this)
        mHomeCommentAdapter = activity?.let { HomeCommentAdapter(it, mlist) }
        recycle_view.adapter = mHomeCommentAdapter
        recycle_view.layoutManager = LinearLayoutManager(activity)

        et_content.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEND) {
                showToast("搜索")
            }
            false
        }
    }

    override fun lazyLoad() {

        map["postId"]=mId.toString()
        mPresenter.getCommentList(map)
    }
    override fun onCommentlist(data: ArrayList<CommentListEntity>?, totalpage: Int?) {
    }

    override fun onPushComment(data: String?) {
    }

    override fun showError(emg: String, code: Int) {
    }

    override fun showLoading() {
    }

    override fun dismissLoading() {
    }
}