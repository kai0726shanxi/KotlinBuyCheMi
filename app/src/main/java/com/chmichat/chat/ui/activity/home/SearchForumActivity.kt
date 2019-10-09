package com.chmichat.chat.ui.activity.home

import android.content.Intent
import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.view.View
import android.view.inputmethod.EditorInfo
import com.chmichat.chat.Constants
import com.chmichat.chat.R
import com.chmichat.chat.base.BaseActivity
import com.chmichat.chat.bean.PostListEntity
import com.chmichat.chat.bean.SearchGoodsHotBean
import com.chmichat.chat.mvp.contract.home.SearchForumCintract
import com.chmichat.chat.mvp.presenter.LoginPresenter
import com.chmichat.chat.mvp.presenter.home.SearchForumPresenter
import com.chmichat.chat.showToast
import com.chmichat.chat.ui.adapter.homeadapter.SearchForumAdapter
import com.chmichat.chat.ui.adapter.homeadapter.SearchForumHistoryAdapter
import com.chmichat.chat.utils.SpUtil
import com.chmichat.chat.utils.StatusBarUtil
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import kotlinx.android.synthetic.main.activity_search_forum.*
import kotlinx.android.synthetic.main.title_bar_layout.*

/**搜索论坛的界面
 * @Author 20342
 * @Date 2019/8/23 9:25
 */
class SearchForumActivity : BaseActivity(), SearchForumCintract.View, View.OnClickListener {


    private var mlist = ArrayList<SearchGoodsHotBean>()
    private var mHistoryList = ArrayList<String>()
    private var mSearchhistory: SearchForumHistoryAdapter? = null
    private var mSearchhot: SearchForumAdapter? = null
    private val mPresenter by lazy { SearchForumPresenter() }

    private val map = HashMap<String, String>()
    override fun layoutId(): Int {

        return R.layout.activity_search_forum
    }

    override fun initData() {

    }

    override fun initView() {
        mPresenter.attachView(this)
        StatusBarUtil.darkMode(this)
        StatusBarUtil.setPaddingSmart(this, cl_bar)
        mSearchhistory = SearchForumHistoryAdapter(this, mHistoryList)

        mSearchhistory?.setOnTagItemClickListener { tag, _ ->
            var intent = Intent(this, SearchResultActivity::class.java)
            intent.putExtra(Constants.KEYNAME, tag)
            startActivity(intent)
        }
        mSearchhot = SearchForumAdapter(this, mlist)
        mSearchhot?.setOnTagItemClickListener { tag, _ ->
            if (mHistoryList != null) {
                mHistoryList.add(tag.hotName)
            } else {
                mHistoryList = ArrayList()
                mHistoryList.add(tag.hotName)
            }
            SpUtil.setDataList(this, Constants.HISTORYSEARCH, mHistoryList)
            var intent = Intent(this, SearchResultActivity::class.java)
            intent.putExtra(Constants.KEYNAME, tag.hotName)
            startActivity(intent)
        }
        recycle_history.adapter = mSearchhistory
        recycle_history.layoutManager = setFlexboxLayout()
        recycle_hot.adapter = mSearchhot
        recycle_hot.layoutManager = setFlexboxLayout()

        iv_left.setOnClickListener(this)
        tv_right.setOnClickListener(this)
        iv_delete.setOnClickListener(this)
        iv_left.visibility = View.VISIBLE
        iv_left.setColorFilter(Color.BLACK)
        tv_right.visibility = View.VISIBLE
        tv_right.text = "搜索"
        tv_right.setTextColor(ContextCompat.getColor(this, R.color.black_ivory))
        tv_title.visibility = View.GONE
        et_search_input.visibility = View.VISIBLE
        et_search_input.hint = "搜索你感兴趣的内容"
        et_search_input.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                setData()

            }
            false
        }
    }

    private fun setData() {

        if (mHistoryList != null) {
            if (et_search_input.text.toString() != "") {
                mHistoryList.add(et_search_input.text.toString())

            }
        } else {
            mHistoryList = ArrayList()
            if (et_search_input.text.toString() != "") {
                mHistoryList.add(et_search_input.text.toString())

            }
        }
        SpUtil.setDataList(this, Constants.HISTORYSEARCH, mHistoryList)
        var intent = Intent(this, SearchResultActivity::class.java)
        intent.putExtra(Constants.KEYNAME, et_search_input.text.toString())
        startActivity(intent)


    }

    override fun start() {
        map.clear()
        map["type"] = "3"
        mPresenter.getSearchHots(map)
    }

    override fun onStart() {
        super.onStart()
        mHistoryList = SpUtil.getDataList(this, Constants.HISTORYSEARCH)
        if (mHistoryList != null) {
            mHistoryList = SpUtil.removeDuplicteUsers(mHistoryList)
            mSearchhistory?.addDataNew(mHistoryList)
        }

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iv_left -> {
                finish()
            }
            R.id.tv_right -> {
                setData()
            }
            R.id.iv_delete -> {
                SpUtil.cleardata(this, Constants.HISTORYSEARCH)
                if (mHistoryList != null) {
                    mHistoryList.clear()
                    mSearchhistory?.addDataNew(mHistoryList)
                }
            }
        }
    }

    private fun setFlexboxLayout(): FlexboxLayoutManager? {
        val layoutManager = FlexboxLayoutManager(this)
        //flexDirection 属性决定主轴的方向（即项目的排列方向）。类似 LinearLayout 的 vertical 和 horizontal。
        layoutManager.flexDirection = FlexDirection.ROW//主轴为水平方向，起点在左端。
        //flexWrap 默认情况下 Flex 跟 LinearLayout 一样，都是不带换行排列的，但是flexWrap属性可以支持换行排列。
        layoutManager.flexWrap = FlexWrap.WRAP//按正常方向换行
        //justifyContent 属性定义了项目在主轴上的对齐方式。
        layoutManager.justifyContent = JustifyContent.FLEX_START//交叉轴的起点对齐。
        if (layoutManager != null) {
            return layoutManager
        }
        return null
    }


    override fun onSearchHots(date: ArrayList<SearchGoodsHotBean>?) {
        if (date != null) {
            mSearchhot?.addDataNew(date)
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