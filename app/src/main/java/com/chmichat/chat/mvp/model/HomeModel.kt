package com.chmichat.chat.mvp.model

import com.chmichat.chat.dispatchDefault
import com.chmichat.chat.mvp.model.bean.BannerBean
import com.chmichat.chat.mvp.model.bean.HomeBean
import com.chmichat.chat.net.BaseResponse
import com.chmichat.chat.net.RetrofitManager
import com.chmichat.chat.rx.scheduler.SchedulerUtils
import io.reactivex.Observable

/**
 * Created by xuhao on 2017/11/21.
 * desc: 首页精选 model
 */

class HomeModel{

    /**
     * 获取首页 Banner 数据
     */
    fun requestHomeData(num:Int):Observable<HomeBean>{
        return RetrofitManager.service.getFirstHomeData(num)
                .compose(SchedulerUtils.ioToMain())
    }

    /**
     * 加载更多
     */
    fun loadMoreData(url:String):Observable<HomeBean>{

        return RetrofitManager.service.getMoreHomeData(url)
                .compose(SchedulerUtils.ioToMain())
    }

    /**
     * 测试接口
     */
   fun  myTestUrl(map:Map<String,String>):Observable<BaseResponse<List<BannerBean>>>{


        return RetrofitManager.service.getHomeBanner(map)
                .dispatchDefault()
                .compose(SchedulerUtils.ioToMain())


    }


}
