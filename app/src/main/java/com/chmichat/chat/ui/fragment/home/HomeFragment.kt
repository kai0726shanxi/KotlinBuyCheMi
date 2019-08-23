package com.chmichat.chat.ui.fragment.home


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.FragmentTransaction
import android.support.v4.content.ContextCompat
import android.view.View
import com.chmichat.chat.App
import com.chmichat.chat.R
import com.chmichat.chat.base.BaseFragment
import com.chmichat.chat.showToast
import com.chmichat.chat.ui.activity.home.SearchForumActivity
import com.chmichat.chat.utils.StatusBarUtil
import kotlinx.android.synthetic.main.fragment_home_layout.*

/**
 * @Author 20342
 * @Date 2019/8/5 9:37
 */
class HomeFragment : BaseFragment(), View.OnClickListener {
    private var homebbs: HomeBBSFragment? = null
    private var homevideo: HomeVidoFragment? = null

    override fun getLayoutId(): Int = R.layout.fragment_home_layout


    //懒加载
    override fun lazyLoad() {
    }


    companion object {
        fun getInstance(): HomeFragment {
            val fragment = HomeFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            return fragment
        }
    }


    override fun initView() {
        //状态栏透明和间距处理
        activity?.let { StatusBarUtil.darkMode(it) }
        activity?.let { StatusBarUtil.setPaddingSmart(it, cl_bar) }
        tv_left_title.setOnClickListener(this)
        tv_right_title.setOnClickListener(this)
        iv_search.setOnClickListener(this)
        onClick(tv_left_title)


    }

    override fun onClick(v: View?) {
        val transaction = childFragmentManager.beginTransaction()
        hideFragments(transaction)
        when {

            v?.id == R.id.tv_left_title -> {
                tv_left_title.textSize = 22F
                tv_right_title.textSize = 14F
                tv_right_title.setTextColor(ContextCompat.getColor(App.context, R.color.hometopright))
                tv_left_title.setTextColor(ContextCompat.getColor(App.context, R.color.black))
                line_left.visibility = View.VISIBLE
                line_right.visibility = View.INVISIBLE
                homebbs?.let {
                    transaction.show(it)
                } ?: HomeBBSFragment.getInstance().let {
                    homebbs = it
                    transaction.add(R.id.fl_containe, it, "bbs")
                }
            }
            v?.id == R.id.tv_right_title -> {

                tv_right_title.textSize = 22F
                tv_left_title.textSize = 14F
                tv_right_title.setTextColor(ContextCompat.getColor(App.context, R.color.black))
                tv_left_title.setTextColor(ContextCompat.getColor(App.context, R.color.hometopright))
                line_right.visibility = View.VISIBLE
                line_left.visibility = View.INVISIBLE
                homevideo?.let {
                    transaction.show(it)
                } ?: HomeVidoFragment.getInstance().let {
                    homevideo = it
                    transaction.add(R.id.fl_containe, it, "video")
                }


            }
              v?.id==R.id.iv_search->{
                 startActivity(Intent(activity,SearchForumActivity::class.java))
            }

        }
        if(v?.id!=R.id.iv_search){
            transaction.commitAllowingStateLoss()

        }

    }

    private fun hideFragments(transaction: FragmentTransaction) {
        homebbs?.let { transaction.hide(it) }
        homevideo?.let { transaction.hide(it) }

    }

}


