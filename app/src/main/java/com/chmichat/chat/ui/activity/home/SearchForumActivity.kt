package com.chmichat.chat.ui.activity.home

import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.view.View
import com.chmichat.chat.R
import com.chmichat.chat.base.BaseActivity
import com.chmichat.chat.ui.adapter.homeadapter.SearchForumAdapter
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
class SearchForumActivity : BaseActivity() ,View.OnClickListener{
 private var mlist= arrayListOf("1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1")
 private var  mSearchhistory: SearchForumAdapter?=null
 private var  mSearchhot: SearchForumAdapter?=null
    override fun layoutId(): Int {

        return R.layout.activity_search_forum
    }

    override fun initData() {

    }

    override fun initView() {
        StatusBarUtil.darkMode(this)
        StatusBarUtil.setPaddingSmart(this, cl_bar)
        mSearchhistory= SearchForumAdapter(this,mlist)
        mSearchhot = SearchForumAdapter(this,mlist)
        recycle_history.adapter=mSearchhistory
        recycle_history.layoutManager=setFlexboxLayout()
        recycle_hot.adapter=mSearchhot
        recycle_hot.layoutManager=setFlexboxLayout()

        iv_left.setOnClickListener(this)
        iv_left.visibility=View.VISIBLE
        iv_left.setColorFilter(Color.BLACK)
        tv_right.visibility=View.VISIBLE
        tv_right.text="搜索"
        tv_right.setTextColor(ContextCompat.getColor(this,R.color.black_ivory))
        tv_title.visibility=View.GONE
        et_search_input.visibility=View.VISIBLE
        et_search_input.hint="搜索你感兴趣的内容"

     }

    override fun start() {
    }

    override fun onClick(v: View?) {
   when(v?.id){
       R.id.iv_left->{
           finish()
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
        if (layoutManager!= null){
            return layoutManager
        }
        return null
    }
}