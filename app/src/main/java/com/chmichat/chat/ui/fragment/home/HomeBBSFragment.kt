package com.chmichat.chat.ui.fragment.home

import android.content.Context
import android.graphics.Typeface
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.ImageView
import com.bigkoo.convenientbanner.ConvenientBanner
import com.bigkoo.convenientbanner.holder.Holder
import com.bumptech.glide.Glide
import com.chmichat.chat.App
import com.chmichat.chat.R
import com.chmichat.chat.base.BaseFragment
import com.chmichat.chat.ui.adapter.homeadapter.HomeClassifyAdapter
import com.chmichat.chat.ui.adapter.homeadapter.HomeDynamicAdapter
import com.chmichat.chat.ui.adapter.homeadapter.HomeImageTextAdapter
import com.chmichat.chat.ui.adapter.homeadapter.HomePopularAdapter
import kotlinx.android.synthetic.main.fragment_home_bbs.*

/**
 * @Author 20342
 * @Date 2019/8/7 10:35
 */
class HomeBBSFragment : BaseFragment() {


    private var mHomeClassifyAdapter: HomeClassifyAdapter? = null
    private var mImageTextAdapter: HomeImageTextAdapter? = null
    private var mHomePopularAdapter: HomePopularAdapter? = null
    private var mHomeDynamicAdapter:HomeDynamicAdapter?=null
    private lateinit var mBanner: ConvenientBanner<String>
    private val mlist = arrayListOf("1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1")
    private var mTextTypeface: Typeface? = null


    companion object {
        fun getInstance(): HomeBBSFragment {
            val fragment = HomeBBSFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_home_bbs

    override fun initView() {

        // Adapter
        mHomeClassifyAdapter = activity?.let { HomeClassifyAdapter(it, mlist) }
        mImageTextAdapter = activity?.let { HomeImageTextAdapter(it, mlist) }
        mHomePopularAdapter = activity?.let { HomePopularAdapter(it, mlist) }
        mHomeDynamicAdapter=activity?.let { HomeDynamicAdapter(it,mlist) }
        mHomeClassifyAdapter!!.setTextStyle(mTextTypeface!!)

        recycle_popular.isNestedScrollingEnabled = false
        recycle_bbs.isNestedScrollingEnabled=false

        recycle_view.adapter = mHomeClassifyAdapter
        recycle_view.layoutManager = linearLayoutManager
        recycle_view.itemAnimator = DefaultItemAnimator()
        recycle_imageandtext.adapter = mImageTextAdapter
        recycle_imageandtext.layoutManager = imlinearLayoutManager
        recycle_popular.adapter = mHomePopularAdapter
        recycle_popular.layoutManager = plinearLayoutManager
        recycle_bbs.adapter = mHomeDynamicAdapter
        recycle_bbs.layoutManager = LinearLayoutManager(activity )

        mBanner = home_banner as ConvenientBanner<String>
        mBanner.setPages({ NetworkImageHolderView() }, mlist)
                .startTurning(2000)
    }

    init {
        mTextTypeface = Typeface.createFromAsset(App.context.assets, "fonts/FZjianti.ttf")

    }

    override fun lazyLoad() {
    }

    private val linearLayoutManager by lazy {
        LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

    }
    private val imlinearLayoutManager by lazy {
        LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

    }
    private val plinearLayoutManager by lazy {
        LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

    }

    class NetworkImageHolderView : Holder<String> {
        private var imageView: ImageView? = null

        override fun createView(context: Context): View {
            //你可以通过layout文件来创建，也可以像我一样用代码创建，不一定是Image，任何控件都可以进行翻页

            imageView = ImageView(context)
            imageView!!.scaleType = ImageView.ScaleType.FIT_XY
            return imageView as ImageView
        }

        override fun UpdateUI(context: Context, position: Int, data: String) {
            Glide.with(context).load("https://img-blh.buychemi.com/20190321/2217102976504300546.png")
                    .into(imageView!!)


        }
    }
}