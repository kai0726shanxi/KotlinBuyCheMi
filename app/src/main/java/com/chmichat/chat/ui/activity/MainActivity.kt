package com.chmichat.chat.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.FragmentTransaction
import android.view.KeyEvent
import android.view.View
import android.widget.ImageView
import com.flyco.tablayout.listener.CustomTabEntity
import com.chmichat.chat.R
import com.chmichat.chat.base.BaseActivity
import com.chmichat.chat.showToast
import com.chmichat.chat.ui.activity.add.HomeAddActivity
import com.chmichat.chat.ui.fragment.home.HomeFragment
import com.chmichat.chat.ui.fragment.mesetting.MeSettingFragment
import com.chmichat.chat.ui.fragment.discover.DiscoverFragment
import com.chmichat.chat.ui.fragment.message.MessageListFragment
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


/**
 * @author Jake.Ho
 * created: 2017/10/25
 * desc:
 */


class MainActivity : BaseActivity() {
    private var iv: ImageView? = null

    private val mTitles = arrayOf("首页", "发现", "", "通知", "我的")

    // 未被选中的图标
    private val mIconUnSelectIds = intArrayOf(R.mipmap.icon_home_normal, R.mipmap.icon_news_normal, R.mipmap.head_icon, R.mipmap.icon_order_normal, R.mipmap.icon_me_normal)
    // 被选中的图标
    private val mIconSelectIds = intArrayOf(R.mipmap.icon_home_down, R.mipmap.icon_news_down, R.mipmap.head_icon, R.mipmap.icon_order_down, R.mipmap.icon_me_down)

    private val mTabEntities = ArrayList<CustomTabEntity>()

    private var mHomeFragment: HomeFragment? = null
    private var mNewsInformationFragment: DiscoverFragment? = null
    private var mOrderListFragment: MessageListFragment? = null
    private var mMeSettingFragment: MeSettingFragment? = null


    //默认为0
    private var mIndex = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            mIndex = savedInstanceState.getInt("currTabIndex")
        }
        super.onCreate(savedInstanceState)
        initTab()
        switchFragment(mIndex)

    }

    override fun layoutId(): Int {
        return R.layout.activity_main
    }


    //初始化底部菜单
    private fun initTab() {
        /*(0 until mTitles.size)
                .mapTo(mTabEntities)
                { TabEntity(mTitles[it], mIconSelectIds[it], mIconUnSelectIds[it]) }
        //为Tab赋值
        tab_layout.setTabData(mTabEntities)

        tab_layout.setOnTabSelectListener(object : OnTabSelectListener {
            override fun onTabSelect(position: Int) {
                //切换Fragment
                switchFragment(position)
            }

            override fun onTabReselect(position: Int) {

            }
        })*/

        tab_layout!!.setOnItemSelectedListener { _, _, position ->
            when (position) {
                0 -> switchFragment(0)
                1 -> switchFragment(1)
                3 -> switchFragment(3)
                4 -> switchFragment(4)
            }

        }
        btn_add.setOnClickListener {

            startActivity(Intent(this, HomeAddActivity::class.java))
             overridePendingTransition(R.anim.anim_in, R.anim.anim_out)
        }
    }

    /**
     * 切换Fragment
     * @param position 下标
     */
    private fun switchFragment(position: Int) {
        val transaction = supportFragmentManager.beginTransaction()
        hideFragments(transaction)
        when (position) {
            0 // 首页
            -> mHomeFragment?.let {
                transaction.show(it)
            } ?: HomeFragment.getInstance().let {
                mHomeFragment = it
                transaction.add(R.id.fl_container, it, "home")
            }
            1  //资讯
            -> mNewsInformationFragment?.let {
                transaction.show(it)
            } ?: DiscoverFragment.getInstance().let {
                mNewsInformationFragment = it
                transaction.add(R.id.fl_container, it, "news")
            }

            3 //订单
            -> mOrderListFragment?.let {
                transaction.show(it)
            } ?: MessageListFragment.getInstance().let {
                mOrderListFragment = it
                transaction.add(R.id.fl_container, it, "order")
            }
            4 //我的界面
            -> mMeSettingFragment?.let {

                transaction.show(it)
            } ?: MeSettingFragment.getInstance().let {
                mMeSettingFragment = it
                transaction.add(R.id.fl_container, it, "mine")
            }

            else -> {

            }
        }

        mIndex = position
        transaction.commitAllowingStateLoss()
    }

    /**
     * 隐藏所有的Fragment
     * @param transaction transaction
     */
    private fun hideFragments(transaction: FragmentTransaction) {
        mHomeFragment?.let { transaction.hide(it) }
        mNewsInformationFragment?.let { transaction.hide(it) }
        mOrderListFragment?.let { transaction.hide(it) }
        mMeSettingFragment?.let { transaction.hide(it) }
    }

    @SuppressLint("MissingSuperCall")
    override fun onSaveInstanceState(outState: Bundle) {
        //记录fragment的位置,防止崩溃 activity被系统回收时，fragment错乱
        if (tab_layout != null) {
            outState.putInt("currTabIndex", mIndex)
        }
    }

    override fun initView() {

    }

    override fun initData() {

    }

    override fun start() {

    }

    private var mExitTime: Long = 0

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis().minus(mExitTime) <= 2000) {
                finish()
            } else {
                mExitTime = System.currentTimeMillis()
                showToast("再按一次退出程序")

            }
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    fun View.setHeight(height: Int) {
        val params = layoutParams
        params.height = height
        layoutParams = params
    }


    fun View.setWith(width: Int) {
        val params = layoutParams
        params.width = height
        layoutParams = params
    }

}
