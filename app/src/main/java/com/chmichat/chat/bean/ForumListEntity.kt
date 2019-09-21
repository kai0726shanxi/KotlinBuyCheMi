package com.chmichat.chat.bean

import java.io.Serializable

/**
 * Created by 20342 on 2019-09-10
 */
data class ForumListEntity(var id:Int,var sectionName:String?,var sectionIcon:String?,var total:Int?,var sort:String?,var isCollection :String?,var reading_num:String?="0"):Serializable