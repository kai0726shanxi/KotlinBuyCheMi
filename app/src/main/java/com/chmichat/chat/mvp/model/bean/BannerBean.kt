package com.chmichat.chat.mvp.model.bean

/**
 * Created by 20342 on 2019-08-01
 */
data class BannerBean(var id: Int,
                      var bannerTitle: String,
                      var bannerImg: String,
                      var bannerPcImg: String,
                      var bannerType: Int,
                      var bannerState: Int,
                      var sort: Int,
                      var isDelete: Int,
                      var createTime: String,
                      var informationSite: String,
                      var goodsId: Int,
                      var language: Int,
                      var source: Int,
                      var businessType: Int)