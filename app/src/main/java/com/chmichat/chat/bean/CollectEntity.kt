package com.chmichat.chat.bean

import java.io.Serializable

/**
 * 收藏
 * Created by 20342 on 2019-09-10
 */
data class CollectEntity(var id: Int,
                         var userId: Int,
                         var collectionType: Int,
                         var collectionId: Int,
                         var sectionIcon: String?,
                         var sectionName: String?,
                         var postTotal: Int,
                         var postTitle: String?,
                         var collectionNum: Int,
                         var commentsNum: Int,
                         var praiseNum: Int,
                         var readingNum: Int,
                         var shareNum: Int,
                         var treadNum: Int):Serializable