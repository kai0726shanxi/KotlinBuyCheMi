package com.chmichat.chat.bean

import java.io.Serializable

/**
 * 所有帖子的model
 * Created by 20342 on 2019-09-12
 */
data class PostListEntity(var id: Int,
                          var userId: Int,
                          var userType: Int,
                          var sectionName: String?,
                          var userName: String?,
                          var author: String?,
                          var sectionId: Int,
                          var content: String?,
                          var desc:String?,
                          var postTitle: String?,
                          var firstCover: String?,
                          var type: Int,
                          var videoUrl: String?,
                          var state: Int,
                          var visible: Int,
                          var position: String?,
                          var openComment: Int,
                          var createTime: String?,
                          var isDelete: Int,
                          var isHot: Int,
                          var isEssence: Int,
                          var isTop: Int,
                          var postStatisticsData: PostStatisticsData?=PostStatisticsData(),
                          var userPostOperation:UserPostOperation?=UserPostOperation(false,false),
                          var collectionType: Int,
                          var collectionId: Int,
                          var sectionIcon: String?,
                          var postTotal: Int,
                          var collectionNum: Int,
                          var commentsNum: Int,
                          var praiseNum: Int,
                          var praise:Boolean?,
                          var readingNum: Int,
                          var shareNum: Int,
                          var treadNum: Int,
                          var postContentDesc:String?,
                          var authorId:Int,
                          var authorType:Int,
                          var postType:Int,
                          var postCreateTime:String?,
                          var postCover:String?,
                          var postCoverList: List<ImageUrlData>?):Serializable {
    data class PostStatisticsData(var postId: Int=0,
                                  var readingNum: Int=0,
                                  var praiseNum: Int?=0,
                                  var commentsNum: Int=0,
                                  var collectionNum: Int=0,
                                  var shareNum: Int=0,
                                  var treadNum: Int?=0,
                                  var realReadingNum: Int=0,
                                  var realPraiseNum: Int=0,
                                  var realCollectionNum: Int=0,
                                  var realShareNum: Int=0,
                                  var realTreadNum: Int=0):Serializable

    data class ImageUrlData(var id:Int,var coverImg:String?):Serializable
    data class UserPostOperation(var praise:Boolean?,var collection:Boolean?):Serializable
}