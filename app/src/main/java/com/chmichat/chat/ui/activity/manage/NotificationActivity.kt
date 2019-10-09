package com.chmichat.chat.ui.activity.manage

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.chmichat.chat.Constants
import com.chmichat.chat.R
import com.chmichat.chat.base.BaseActivity
import com.chmichat.chat.bean.InteractListMessageEntity
import com.chmichat.chat.bean.LastMessageEntivity
import com.chmichat.chat.bean.PostListEntity
import com.chmichat.chat.bean.SystemListMessageEntity
import com.chmichat.chat.mvp.contract.message.MessageListContract
import com.chmichat.chat.mvp.presenter.message.MessageListPresenter
import com.chmichat.chat.ui.activity.add.ReleaseLongVideoActivity
import com.chmichat.chat.ui.activity.home.PlayVideoActivity
import com.chmichat.chat.ui.activity.home.PostDetailsActivity
import com.chmichat.chat.ui.adapter.message.InteractionAdapter
import com.chmichat.chat.ui.adapter.message.SystemAdapter
import com.chmichat.chat.utils.StatusBarUtil
import kotlinx.android.synthetic.main.activity_notification_layout.*
import kotlinx.android.synthetic.main.title_bar_layout.*

/**
 * @Author 20342
 * @Date 2019/8/21 15:35
 */
class NotificationActivity : BaseActivity(), MessageListContract.View, View.OnClickListener {


    private var map = HashMap<String, String>()
    private var page: Int = 1
    private var mTotalPage: Int? = 0
    var mPosition = 0
    var mSystemlist = ArrayList<SystemListMessageEntity>()
    var mlist = ArrayList<InteractListMessageEntity>()
    var mSystemAdapter: SystemAdapter? = null
    var minteractionAdapter: InteractionAdapter? = null
    private val mPresenter by lazy { MessageListPresenter() }
    override fun layoutId(): Int {

        return R.layout.activity_notification_layout
    }

    override fun initData() {
        mPosition = intent.getIntExtra("position", 0)
    }

    override fun initView() {
        mPresenter.attachView(this)
        iv_left.visibility = View.VISIBLE
        iv_left.setColorFilter(Color.BLACK)
        iv_left.setOnClickListener(this)
        StatusBarUtil.darkMode(this)
        StatusBarUtil.setPaddingSmart(this, cl_bar)
        when (mPosition) {
            1 -> {
                tv_title.text = "互动消息"
                minteractionAdapter = InteractionAdapter(this, mlist)
                minteractionAdapter?.setOnTagItemClickListener {
                    if (it?.msgType == 5) {
                        mPresenter.getFindPost(it?.interactiveId.toString())
                    } else {
                        mPresenter.getpostDetails(it?.interactiveId.toString())
                    }

                }
                mRecyclerView.adapter = minteractionAdapter
                mRecyclerView.layoutManager = LinearLayoutManager(this)
            }
            else -> {
                tv_title.text = "系统消息"
                mSystemAdapter = SystemAdapter(this, mSystemlist)
                mSystemAdapter?.setOnTagItemClickListener {
                    mPresenter.getSystemDetails(it?.id.toString())
                }
                mRecyclerView.adapter = mSystemAdapter
                mRecyclerView.layoutManager = LinearLayoutManager(this)
            }
        }


        refreshLayout.setOnRefreshListener { refreshLayout ->
            //下拉刷新
            page = 1
            setpushData()
            refreshLayout.finishRefresh()
        }
        refreshLayout.setOnLoadMoreListener { refreshLayout ->
            //加载更多
            page++
            if (page < mTotalPage!!) {
                setpushData()
                refreshLayout.finishLoadMore()

            } else {
                refreshLayout.finishLoadMore(1000, true, true)

            }

        }

    }

    private fun setpushData() {
        map.clear()
        map["pageSize"] = "10"
        map["pageNum"] = page.toString()
        if (mPosition == 1) {
            mPresenter.getInteractMessageList(map)
        } else {
            mPresenter.getSystemMessageList(map)

        }
    }

    override fun start() {
        setpushData()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iv_left -> finish()
        }
    }

    override fun showError(errorMsg: String, errorCode: Int) {
        ShowErrorMes(errorMsg, errorCode)
    }

    override fun onLastData(data: LastMessageEntivity?) {

    }

    override fun onSystemMessageList(data: ArrayList<SystemListMessageEntity>?, total: Int?) {
        mTotalPage = total
        if (data != null) {

            if (page == 1) {
                mSystemAdapter?.setDataNew(data)
            } else {
                mSystemAdapter?.setDataAll(data)

            }
        }

    }

    override fun onInteractMessageList(data: ArrayList<InteractListMessageEntity>?, total: Int?) {
        mTotalPage = total
        if (data != null) {

            if (page == 1) {
                minteractionAdapter?.setDataNew(data)
            } else {
                minteractionAdapter?.setDataAll(data)

            }
        }
    }

    override fun onSystemDetails(data: SystemListMessageEntity?) {
        var intent=Intent(this,SystemDetailsActivity::class.java)
        intent.putExtra(Constants.KEYNAME,data)
        startActivity(intent)
    }

    override fun onSetread(data: String?) {
    }

    override fun showLoading() {
    }

    override fun dismissLoading() {
    }

    override fun onFindPost(data: PostListEntity?) {
//帖子详情
//帖子内容类型:1:图文;2:帖子;3:小视频;4:VLOG;
        when (data?.type) {
            1 -> {
                val intent = Intent(this, PostDetailsActivity::class.java)
                intent.putExtra(Constants.KEYTYPE, data)
                startActivity(intent)

            }
            2 -> {
                val intent = Intent(this, PostDetailsActivity::class.java)
                intent.putExtra(Constants.KEYTYPE, data)
                startActivity(intent)
            }
            3 -> {
                var list = ArrayList<PostListEntity>()
                list.add(data)
                val intent = Intent(this, PlayVideoActivity::class.java)
                intent.putExtra(Constants.PLAYPOSITION, 0)
                intent.putExtra(Constants.PLAYLIST, list)
                startActivity(intent)
            }
            4 -> {
                val intent = Intent(this, ReleaseLongVideoActivity::class.java)
                intent.putExtra(Constants.PLAYLIST, data)
                startActivity(intent)
            }

        }

    }
}