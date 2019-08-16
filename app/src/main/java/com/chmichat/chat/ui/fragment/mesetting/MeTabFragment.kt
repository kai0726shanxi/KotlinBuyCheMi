package com.chmichat.chat.ui.fragment.mesetting

import android.graphics.Rect
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.chmichat.chat.R
import com.chmichat.chat.base.BaseFragment
import com.chmichat.chat.showToast
import com.chmichat.chat.ui.adapter.me.MeDynamicAdapter
import com.chmichat.chat.ui.adapter.me.MeForumAdapter
import com.chmichat.chat.ui.adapter.me.MeVLOGAdapter
import com.chmichat.chat.ui.adapter.me.MeVideoAdapter
import com.chmichat.chat.utils.DisplayManager
import kotlinx.android.synthetic.main.fragment_metab_layout.*

/**
 * 我的界面底部分类
 * @Author 20342
 * @Date 2019/8/9 10:21
 */
class MeTabFragment : BaseFragment() {
    private val mlist = arrayListOf("1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1")

    private var mTitle: String? = null
    private var mMeDynamicAdapter: MeDynamicAdapter? = null
    private var mMeVideoAdapter: MeVideoAdapter? = null
    private var mMeVLOGAdapter: MeVLOGAdapter? = null
    private var smMeForumAdapter: MeForumAdapter? = null
    private var smMeDynamicAdapter: MeDynamicAdapter? = null
    private var smMeVideoAdapter: MeVideoAdapter? = null
    private var smMeVLOGAdapter: MeVLOGAdapter? = null
    private var checkPosition: Int = 0

    companion object {
        fun getInstance(title: String): MeTabFragment {
            val fragment = MeTabFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.mTitle = title
            return fragment
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_metab_layout

    override fun initView() {

        if (!mTitle.isNullOrEmpty()) {
         //   mRecyclerView.isNestedScrollingEnabled = false
            ShowView(mTitle!!)
        }
        multipleStatusView.isEnabled=false

        refreshLayout.setOnLoadMoreListener {
             showToast("加载更多")
        }


    }

    override fun lazyLoad() {

    }

    private fun ShowView(s: String) {
        when {
            s == "0" -> {
                rg.visibility = View.GONE
                mMeDynamicAdapter = activity?.let { MeDynamicAdapter(it, mlist) }
                mRecyclerView.adapter = mMeDynamicAdapter
                mRecyclerView.layoutManager = LinearLayoutManager(activity)

            }
            s == "1" -> {
                rg.visibility = View.GONE
                mMeVideoAdapter = activity?.let { MeVideoAdapter(it, mlist) }
                mRecyclerView.adapter = mMeVideoAdapter
                mRecyclerView.layoutManager = GridLayoutManager(activity, 2)
                mRecyclerView.addItemDecoration(object : RecyclerView.ItemDecoration() {
                    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State?) {
                        val position = parent.getChildPosition(view)
                        val offset = DisplayManager.dip2px(5f)!!

                        outRect.set(if (position % 2 == 0) 10 else offset, offset,
                                if (position % 2 == 0) offset else 10, offset)
                    }

                })
            }
            s == "2" -> {
                rg.visibility = View.GONE
                mMeVLOGAdapter = activity?.let { MeVLOGAdapter(it, mlist) }
                mRecyclerView.adapter = mMeVLOGAdapter
                mRecyclerView.layoutManager = LinearLayoutManager(activity)

            }
            s == "3" -> {

                rg.visibility = View.VISIBLE
                setcollectData(checkPosition)
                rg.setOnCheckedChangeListener { _, checkedId ->
                    when (checkedId) {

                        R.id.rbimg -> {
                            checkPosition = 0
                            setcollectData(checkPosition)
                        }
                        R.id.rbtie -> {
                            checkPosition = 1

                            setcollectData(checkPosition)

                        }
                        R.id.rbvideo -> {
                            checkPosition = 2

                            setcollectData(checkPosition)

                        }
                        R.id.rblongvideo -> {
                            checkPosition = 3

                            setcollectData(checkPosition)

                        }


                    }


                }
            }


        }

    }

    private fun setcollectData(position: Int) {
        when (position) {
            0 -> {
                smMeForumAdapter = activity?.let { MeForumAdapter(it, mlist) }
                mRecyclerView.adapter = smMeForumAdapter
                mRecyclerView.layoutManager = LinearLayoutManager(activity)
            }
            1 -> {
                smMeDynamicAdapter = activity?.let { MeDynamicAdapter(it, mlist) }
                mRecyclerView.adapter = smMeDynamicAdapter
                mRecyclerView.layoutManager = LinearLayoutManager(activity)
            }
            2 -> {
                smMeVideoAdapter = activity?.let { MeVideoAdapter(it, mlist) }
                mRecyclerView.adapter = smMeVideoAdapter
                mRecyclerView.layoutManager = GridLayoutManager(activity, 2)
                mRecyclerView.addItemDecoration(object : RecyclerView.ItemDecoration() {
                    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State?) {
                        val position = parent.getChildPosition(view)
                        val offset = DisplayManager.dip2px(5f)!!

                        outRect.set(if (position % 2 == 0) 10 else offset, offset,
                                if (position % 2 == 0) offset else 10, offset)
                    }

                })
            }
            3 -> {
                smMeVLOGAdapter = activity?.let { MeVLOGAdapter(it, mlist) }
                mRecyclerView.adapter = smMeVLOGAdapter
                mRecyclerView.layoutManager = LinearLayoutManager(activity)

            }


        }
    }


}


