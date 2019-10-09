package com.chmichat.chat.ui.fragment.message


import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.chmichat.chat.R
import com.chmichat.chat.base.BaseFragment
import com.chmichat.chat.bean.InteractListMessageEntity
import com.chmichat.chat.bean.LastMessageEntivity
import com.chmichat.chat.bean.PostListEntity
import com.chmichat.chat.bean.SystemListMessageEntity
import com.chmichat.chat.mvp.contract.message.MessageListContract
import com.chmichat.chat.mvp.presenter.message.MessageListPresenter
import com.chmichat.chat.ui.adapter.message.MessageListAdapter
import com.chmichat.chat.utils.StatusBarUtil
import kotlinx.android.synthetic.main.fragment_order_layout.*
import kotlinx.android.synthetic.main.title_bar_layout.*

/**
 * @Author 20342
 * @Date 2019/8/5 11:41
 */
class MessageListFragment : BaseFragment(), MessageListContract.View, View.OnClickListener {
    override fun onFindPost(data: PostListEntity?) {

    }

    private val mPresenter by lazy { MessageListPresenter() }
    private var mMessageListAdapter: MessageListAdapter? = null
    var mlist = ArrayList<LastMessageEntivity>()
    var list = ArrayList<String>()

    companion object {
        fun getInstance(): MessageListFragment {
            val fragment = MessageListFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_order_layout

    override fun initView() {
        //状态栏透明和间距处理
        mPresenter.attachView(this)

        mMessageListAdapter = activity?.let { MessageListAdapter(it, mlist) }
        activity?.let { StatusBarUtil.darkMode(it) }
        activity?.let { StatusBarUtil.setPaddingSmart(it, cl_bar) }
        recycle_view.adapter = mMessageListAdapter
        recycle_view.layoutManager = LinearLayoutManager(activity)
        tv_title.text = "通知"

    }

    override fun onClick(v: View?) {
        when (v?.id) {

        }
    }

    override fun lazyLoad() {
        mPresenter.getLastData()
    }

    override fun showError(errorMsg: String, errorCode: Int) {
        mlist.clear()
        mlist.add(LastMessageEntivity(null, null))
        mMessageListAdapter?.setDataNew(mlist)
        ShowErrorMes(errorMsg, errorCode)
    }

    override fun onLastData(data: LastMessageEntivity?) {
        //最后一条消息
        if (data != null) {
            mlist.clear()
            mlist.add(data)
            // mlist.add(data)
        }
        mMessageListAdapter?.setDataNew(mlist)

    }

    override fun onSystemMessageList(data: ArrayList<SystemListMessageEntity>?, total: Int?) {
        //
    }

    override fun onInteractMessageList(data: ArrayList<InteractListMessageEntity>?, total: Int?) {
        //
    }

    override fun onSystemDetails(data: SystemListMessageEntity?) {
    }

    override fun onSetread(data: String?) {
    }

    override fun showLoading() {
    }

    override fun dismissLoading() {
    }
}