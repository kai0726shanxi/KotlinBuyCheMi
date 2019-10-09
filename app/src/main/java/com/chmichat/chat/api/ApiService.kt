package com.chmichat.chat.api

import com.chmichat.chat.bean.*
import com.chmichat.chat.net.BaseResponse
import io.reactivex.Observable
import okhttp3.RequestBody
import retrofit2.http.*

/**
 * Created by 203442 2019/9/2
 * Api 接口
 */

interface ApiService {

    /**
     * 开关详情
     */
    @GET("bbs/user/push/switch/details")
    fun getSwitchDetails(): Observable<BaseResponse<NotificationManagerEntity>>

    /**
     *切换踩
     */
    @POST("bbs/user/push/switch/tread")
    fun getSwitchTread(@Query("action") id: Boolean): Observable<BaseResponse<Boolean>>

    /**
     *切换评论
     */
    @POST("bbs/user/push/switch/comments")
    fun getSwitchComments(@Query("action") id: Boolean): Observable<BaseResponse<Boolean>>

    /**
     *切换点赞
     */
    @POST("bbs/user/push/switch/praise")
    fun getSwitchPraise(@Query("action") id: Boolean): Observable<BaseResponse<Boolean>>

    /**
     * 手机登录
     */
    @FormUrlEncoded
    @POST("accountAuth/user/login/mobileLanding")
    fun loginMember(@FieldMap parmer: Map<String, String>): Observable<BaseResponse<UserBean>>

    /**
     * 邮箱登录
     *
     * @param parmer
     * @return
     */
    @FormUrlEncoded
    @POST("accountAuth/user/login/emailLanding")
    fun getemailLogin(@FieldMap parmer: Map<String, String>): Observable<BaseResponse<UserBean>>


    /**
     * 短信验证码注册
     * @param parmer
     * @return
     */

    @FormUrlEncoded
    @POST("accountAuth/user/register/mobileRegister")
    fun registerMember(@FieldMap parmer: Map<String, String>): Observable<BaseResponse<UserBean>>

    /**
     * 邮箱注册
     * @param parmer
     * @return
     */

    @FormUrlEncoded
    @POST("accountAuth/user/register/emailRegister")
    fun registerEmail(@FieldMap parmer: Map<String, String>): Observable<BaseResponse<UserBean>>


    /**
     * 发送验证码
     *
     *
     */

    @FormUrlEncoded
    @POST("accountAuth/user/register/sendRegisterCode")
    fun sendCode(@FieldMap parmer: Map<String, String>): Observable<BaseResponse<String>>

    //获取邮箱验证码
    @GET("accountAuth/user/register/registerEmailCode")
    fun getsendEmailCode(@Query("email") emailnum: String): Observable<BaseResponse<String>>


    /**
     * 忘记密码
     *
     * @param parmer
     * @return
     */
    @FormUrlEncoded
    @POST("accountAuth/user/register/forgetPsw")
    fun ForgetPassword(@FieldMap parmer: Map<String, String>): Observable<BaseResponse<String>>


    /**
     * 忘记密码(邮箱)
     *
     * @param parmer
     * @return
     */
    @FormUrlEncoded
    @POST("accountAuth/user/register/email/forgetPsw")
    fun ForgetPasswordEmail(@FieldMap parmer: Map<String, String>): Observable<BaseResponse<String>>

    /**
     * 用户最近浏览模块
     *
     * @param parmer
     * @return
     */
    @FormUrlEncoded
    @POST("bbs/user/plate/browse")
    fun getRecentBrowse(@FieldMap parmer: Map<String, String>): Observable<BaseResponse<ArrayList<RecentBrowseEntity>>>
    /**
     * 编辑用户信息
     *
     * @param parmer
     * @return
     */
    @FormUrlEncoded
    @POST("bbs/user/editor")
    fun getEditUserInfo(@FieldMap parmer: Map<String, String>): Observable<BaseResponse<String?>>



    /**
     * 收藏列表
     * @param parmer
     * @return
     */
    @FormUrlEncoded

    @POST("bbs/user/collection/page")
    fun getCollectionList( @FieldMap parmer: Map<String, String>): Observable<BaseResponse<ArrayList<PostListEntity>>>
    /**
     * 帖子详情
     * @param parmer
     * @return
     */
    @GET("bbs/forum/post/findByPostId")
    fun getPostDtetails( @Query("postId") mid:String ): Observable<BaseResponse<PostListEntity>>

    /**
     * 上传图片
     * @param parmer
     * @return
     */
    @POST("file/post/upload/img/array")
    fun getPushImg( @Body body:RequestBody): Observable<BaseResponse<ArrayList<String?>>>

    /**
     * 图文上传
     *
     * @param parmer
     * @return
     */
    @FormUrlEncoded
    @POST("bbs/forum/post/addImg")
    fun getImageText(@FieldMap parmer: Map<String, String?>): Observable<BaseResponse<String?>>
    /**
     *帖子上传
     *
     * @param parmer
     * @return
     */
    @FormUrlEncoded
    @POST("bbs/forum/post/addPost")
    fun getReleasePost(@FieldMap parmer: Map<String, String?>): Observable<BaseResponse<String?>>
    /**
     *视频上传
     *
     * @param parmer
     * @return
     */
    @FormUrlEncoded
    @POST("bbs/forum/post/addVideo")
    fun getReleaseVideo(@FieldMap parmer: Map<String, String?>): Observable<BaseResponse<String?>>

    /**
     *获取七牛token
     *
     * @param parmer
     * @return
     */
    @FormUrlEncoded
    @POST("file/qiniu/auth/getToken")
    fun getgetToken(@FieldMap parmer: Map<String, String?>): Observable<BaseResponse<String?>>
    /**
     *论坛列表
     * @param parmer
     * @return
     */
    @FormUrlEncoded
    @POST("bbs/forum/section/getPage")
    fun getSectionlist(@FieldMap parmer: Map<String, String?>): Observable<BaseResponse<ArrayList<ForumListEntity>>>
    /**
     *论坛详情
     * @param parmer
     * @return
     */
    @GET("bbs/forum/section/findBySectionId")
    fun getSectionDetails(@Query("id") id:String): Observable<BaseResponse<ForumListEntity>>

    /**
     *帖子列表
     * @param parmer
     * @return
     */
    @FormUrlEncoded
    @POST("bbs/forum/post/page")
    fun getpostlist(@FieldMap parmer: Map<String, String>): Observable<BaseResponse<ArrayList<PostListEntity>>>

    /**
     *评论列表
     * @param parmer
     * @return
     */
    @FormUrlEncoded
    @POST("bbs/post/comment/page")
    fun getcommentlist(@FieldMap parmer: Map<String, String>): Observable<BaseResponse<ArrayList<CommentListEntity>>>
    /**
     *发送评论
     * @param parmer
     * @return
     */
    @FormUrlEncoded
    @POST("bbs/post/comment/send")
    fun getPushcomment(@FieldMap parmer: Map<String, String>): Observable<BaseResponse<String>>

    /**
     *我的帖子列表
     * @param parmer
     * @return
     */
    @FormUrlEncoded
    @POST("bbs/forum/post/my/page")
    fun getMypostlist(@FieldMap parmer: Map<String, String>): Observable<BaseResponse<ArrayList<PostListEntity>>>

    /**
     *收藏板块
     * @param parmer
     * @return
     */
    @FormUrlEncoded
    @POST("bbs/user/collection/section")
    fun getCollectionPlate(@FieldMap parmer: Map<String, String>): Observable<BaseResponse<String>>
    /**
     *收藏帖子
     * @param parmer
     * @return
     */
    @FormUrlEncoded
    @POST("bbs/user/collection/post")
    fun getCollectionPost(@FieldMap parmer: Map<String, String>): Observable<BaseResponse<String>>
    /**
     *取消收藏帖子
     * @param parmer
     * @return
     */
    @FormUrlEncoded
    @POST("bbs/user/collection/cancelPost")
    fun getCancelCollectionPost(@FieldMap parmer: Map<String, String>): Observable<BaseResponse<String>>

    /**
     *赞帖子
     * @param parmer
     * @return
     */
    @FormUrlEncoded
    @POST("bbs/user/action/praise")
    fun getpraisePost(@FieldMap parmer: Map<String, String>): Observable<BaseResponse<String>>

    /**
     *赞帖子
     * @param parmer
     * @return
     */
    @FormUrlEncoded
    @POST("bbs/user/action/tread")
    fun gettreadPost(@FieldMap parmer: Map<String, String>): Observable<BaseResponse<String>>
    /**
     *取消赞或者踩
     * @param parmer
     * @return
     */
    @FormUrlEncoded
    @POST("bbs/user/action/cancel")
    fun getCancelpraisePost(@FieldMap parmer: Map<String, String>): Observable<BaseResponse<String>>

    /**
     *取消收藏板块
     * @param parmer
     * @return
     */
    @FormUrlEncoded
    @POST("bbs/user/collection/cancelSection")
    fun getCancleCollectionPlate(@FieldMap parmer: Map<String, String>): Observable<BaseResponse<String>>

    /**
     *推荐帖子板块
     * @param parmer
     * @return
     */
    @FormUrlEncoded
    @POST("bbs/forum/post/related/suggestion")
    fun getPostRelatedList(@FieldMap parmer: Map<String, String>): Observable<BaseResponse<ArrayList<PostListEntity>>>


    /**
     * 热门推荐(搜索)
     *
     * @param parmer
     * @return
     */
    @FormUrlEncoded
    @POST("api/goods/goodsHot/getPage")
    fun getGoodsHots(@FieldMap parmer: Map<String, String>): Observable<BaseResponse<ArrayList<SearchGoodsHotBean>>>

    /**
     * 查询个人信息
     */
    @GET("bbs/user/this")
    fun getMeUserInfo(): Observable<BaseResponse<UserBean>>

}