package com.chmichat.chat

//  ┏┓　　　┏┓
//┏┛┻━━━┛┻┓
//┃　　　　　　　┃
//┃　　　━　　　┃
//┃　┳┛　┗┳　┃
//┃　　　　　　　┃
//┃　　　┻　　　┃
//┃　　　　　　　┃
//┗━┓　　　┏━┛
//    ┃　　　┃   神兽保佑
//    ┃　　　┃   代码无BUG！
//    ┃　　　┗━━━┓
//    ┃　　　　　　　┣┓
//    ┃　　　　　　　┏┛
//    ┗┓┓┏━┳┓┏┛
//      ┃┫┫　┃┫┫
//      ┗┻┛　┗┻┛
/**
 * Created by xuhao on 2017/11/27.
 * desc: 常量
 */
class Constants private constructor() {

    companion object {

        val BUNDLE_VIDEO_DATA = "video_data"
        val BUNDLE_CATEGORY_DATA = "category_data"

        //腾讯 Bugly APP id
        val BUGLY_APPID = "176aad0d9e"
        val KEYTYPE="keytype"


        //sp 存储的文件名
        val FILE_WATCH_HISTORY_NAME = "watch_history_file"   //观看记录

        val FILE_COLLECTION_NAME = "collection_file"    //收藏视屏缓存的文件名
        val HISTORYSEARCH="historysearch"//搜索历史记录
        val KEYNAME="key_name"//传值的名称
        val KEYID="key_id"//传值的id
        val KEYADDRESS="keyaddress"//传地址
        val ANMATIONSHOWTIME=350L //动画执行的时间
        val IMGAEURL="image_url" //图片路径
        val VIDEOURL="video_url" //视频路径
        val DURATION="duration" //视频时长
        val USERBEAN="userbean"//用户信息
        val SAVEIMGTXT="saveimagetext" //保存图文
        val PLAYPOSITION="playposition"//播放的视频
        val PLAYLIST="playlist"//播放的集合
        val USERBEANTOKEN="userbeantoken"//用户信息
        val CHOSEFORUM=1000 //选择论坛
        val SETADDRESS=1001 //设置地址
        val CHOOSE_VIDEO = 1002//小视频回调
        val SETISVISIVLE=1003//设置是否可见
        val ISVISIBLE="isvisible"
    }
}