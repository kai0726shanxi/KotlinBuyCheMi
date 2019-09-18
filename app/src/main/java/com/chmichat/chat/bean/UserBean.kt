package com.chmichat.chat.bean

import java.io.Serializable

/**
 * Created by 20342 on 2019-09-09
 */
data class UserBean(var id: Int,
                    var userAccount: String?,
                    var userMobile: String?,
                    var mobileAreaCode: Int,
                    var nickname: String?,
                    var userGender: Int,
                    var accessToken: String?,
                    var rongcloudToken: String?,
                    var rongCloudId: String?,
                    var jpushId: String?,
                    var position: String?,
                    var companyName: String?,
                    var oldPsw: String?,
                    var userType: Int,
                    var appId: Int,
                    var postsNum: Int,
                    var praiseTotal: Int,
                    var introduction: String?,
                    var birthDate: String?,
                    var isLock: Int?,
                    var createTime: String?,
                    var lastLoginTime: String?,
                    var permissionsVo: PermissionsVo?) : Serializable {
    data class PermissionsVo(var id: Int?,
                             var userId: Int?,
                             var posting: Boolean?,
                             var comments: Boolean?,
                             var praise: Boolean?,
                             var updateUserId: Int?,
                             var updateTime: String?,
                             var report: Boolean?) : Serializable
}