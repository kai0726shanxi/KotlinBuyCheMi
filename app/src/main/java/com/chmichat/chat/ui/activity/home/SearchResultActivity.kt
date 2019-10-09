package com.chmichat.chat.ui.activity.home

import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.view.inputmethod.EditorInfo
import com.chmichat.chat.Constants
import com.chmichat.chat.R
import com.chmichat.chat.base.BaseActivity
import com.chmichat.chat.bean.PostListEntity
import com.chmichat.chat.mvp.contract.home.SearchResultContract
import com.chmichat.chat.mvp.presenter.home.SearchResultPresenter
import com.chmichat.chat.showToast
import com.chmichat.chat.ui.adapter.homeadapter.SearchResultAdapter
import com.chmichat.chat.utils.StatusBarUtil
import kotlinx.android.synthetic.main.activity_search_result.*
import kotlinx.android.synthetic.main.title_bar_layout.*

/**
 * 搜索结果界面
 * @Author 20342
 * @Date 2019/10/8 11:21
 */
class SearchResultActivity : BaseActivity(),SearchResultContract.View, View.OnClickListener {


    private var mList=ArrayList<PostListEntity>()
    private var mTotal=0
    private var page:Int=1
    private var map=HashMap<String,String>()
    private var mSearchResultAdapter: SearchResultAdapter? = null
    private var mName:String=""
    private val mPresenter by lazy { SearchResultPresenter() }
    override fun layoutId(): Int {
        return R.layout.activity_search_result
    }

    override fun initData() {
        mName=intent.getStringExtra(Constants.KEYNAME)
    }

    override fun initView() {
        mPresenter.attachView(this)
        StatusBarUtil.darkMode(this)
        StatusBarUtil.setPaddingSmart(this, cl_bar)
        iv_left.setOnClickListener(this)
        tv_right.setOnClickListener(this)
        iv_left.visibility = View.VISIBLE
        iv_left.setColorFilter(Color.BLACK)
        tv_right.visibility = View.VISIBLE
        tv_right.text = "搜索"
        tv_right.setTextColor(ContextCompat.getColor(this, R.color.black_ivory))
        tv_title.visibility = View.GONE
        et_search_input.visibility = View.VISIBLE
        et_search_input.hint = "搜索你感兴趣的内容"
        mSearchResultAdapter= SearchResultAdapter(this,mList)
        recyclerView.adapter=mSearchResultAdapter
        recyclerView.layoutManager=LinearLayoutManager(this)
        et_search_input.setText(mName)
        et_search_input.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {

                page=1
                mName=et_search_input.text.toString()
                setPushData()
            }
            false
        }
        refreshLayout.setOnRefreshListener { refreshLayout ->
            //下拉刷新
            page = 1
            setPushData()
            refreshLayout.finishRefresh()
        }
        refreshLayout.setOnLoadMoreListener { refreshLayout ->
            //加载更多
            page++
            if (page < mTotal!!) {
                setPushData()
                refreshLayout.finishLoadMore()

            } else {
                refreshLayout.finishLoadMore(1000, true, true)

            }

        }
    }


    override fun start() {
        setPushData()
    }

    private fun setPushData() {
        map.clear()
        map["pageSize"] = "10"
        map["pageNum"] = page.toString()
        map["postTitle"]=mName
        mPresenter.getSearchDatas(map)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iv_left -> finish()

            R.id.tv_right->{
                page=1
                mName=et_search_input.text.toString()
                setPushData()
            }
        }
    }

    override fun onSearchPosts(date: ArrayList<PostListEntity>?, total: Int?) {
        if (date!=null){
            if (page==1){
                mSearchResultAdapter?.addDataNew(date)
            }else{
                mSearchResultAdapter?.addDataAll(date)
            }
        }else{
            if (page==1){
                mSearchResultAdapter?.addDataNew(mList)

            }

        }
    }

    override fun showError(errmsg: String, code: Int) {

        showToast(errmsg)
    }

    override fun showLoading() {
    }

    override fun dismissLoading() {
    }
}