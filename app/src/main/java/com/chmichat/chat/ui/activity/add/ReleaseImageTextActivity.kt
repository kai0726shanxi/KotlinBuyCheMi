package com.chmichat.chat.ui.activity.add

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.chmichat.chat.App
import com.chmichat.chat.Constants
import com.chmichat.chat.R
import com.chmichat.chat.base.BaseActivity
import com.chmichat.chat.bean.SaveImageTextEntity
import com.chmichat.chat.glide.GlideApp
import com.chmichat.chat.mvp.contract.add.ImageTextContract
import com.chmichat.chat.mvp.presenter.add.ImageTextPresenter
import com.chmichat.chat.showToast
import com.chmichat.chat.ui.dialog.ChosepictureDialog
import com.chmichat.chat.ui.dialog.SaveEditDialog
import com.chmichat.chat.utils.SpUtil
import com.chmichat.chat.utils.StatusBarUtil
import kotlinx.android.synthetic.main.activity_release_imagetext.*
import kotlinx.android.synthetic.main.title_bar_layout.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File


/**发布图文
 * @Author 20342
 * @Date 2019/8/15 14:21
 */
class ReleaseImageTextActivity : BaseActivity(), ImageTextContract.View, View.OnClickListener {


    private var imgagepath: String? = ""
    private var ImageUrl: String? = ""
    private var limit = 300
    //  用来记录输入字符的时候光标的位置
    private var cursor = 0
    // 用来标注输入某一内容之前的编辑框中的内容的长度
    private var before_length: Int = 0
    private var forumname:String?=""
    private var forumid:String?=""
    private var address:String?=""
    private var map=HashMap<String,String?>()
    private var strbuf: StringBuffer = StringBuffer()
    private var imageentity:SaveImageTextEntity?=null
    private val mPresenter by lazy { ImageTextPresenter() }
    override fun layoutId(): Int {

        return R.layout.activity_release_imagetext
    }

    override fun initData() {
        imgagepath = intent.getStringExtra(Constants.IMGAEURL)
        imageentity= intent.getSerializableExtra(Constants.SAVEIMGTXT) as SaveImageTextEntity?
    }

    override fun initView() {
        mPresenter.attachView(this)
        StatusBarUtil.darkMode(this)
        StatusBarUtil.setPaddingSmart(this, cl_bar)
        iv_left.visibility = View.VISIBLE
        iv_left.setColorFilter(Color.BLACK)
        tv_title.text = "发布图文"
        tv_right.visibility = View.VISIBLE
        tv_right.text = "发布"
        tv_right.setTextColor(ContextCompat.getColor(this, R.color.displaynomal))
        iv_left.setOnClickListener(this)
        tv_lun_content.setOnClickListener(this)
        tv_address_content.setOnClickListener(this)
        tv_right.setOnClickListener(this)
        if (imageentity!=null){
           GlideApp.with(this)
                   .load(imageentity?.imageurl)
                   .into(show_img)
            tv_name.setText(imageentity?.title)
            edit_content.setText(imageentity?.tvcontent)
            tv_lun_content.text = imageentity?.forumname
            tv_address_content.text = imageentity?.address
            forumid=imageentity?.forumid
            ImageUrl=imageentity?.imageurl

        }else{
            GlideApp.with(this)
                    .load(imgagepath)
                    .error(R.mipmap.moren_icon)
                    .into(show_img) }
        edit_content.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val after_length = s!!.length
                if (after_length > limit) {
                    val d_value = after_length - limit
                    val d_num = after_length - before_length
                    val st = cursor + (d_num - d_value)
                    val en = cursor + d_num
                    val s_new = s.delete(st, en)
                    edit_content.setText(s_new.toString())
                    edit_content.setSelection(st)
                }

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                before_length = s!!.length

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                cursor = start
                show_num.text = "${s!!.length}/" + limit

            }


        })

        meditdialog.setBtnDataLinsenter(object : SaveEditDialog.BtnDataLinsenter {
            override fun btndata(str: String) {
               when(str){
                   "1"->{
                      finish()
                   }

                   "2"->{
                       imageentity=SaveImageTextEntity(tv_name.text.toString(),edit_content.text.toString(),ImageUrl,forumname,address,forumid)
                       SpUtil.putObject(this@ReleaseImageTextActivity,Constants.SAVEIMGTXT,imageentity)

                       finish()
                   }

               }

            }
        })
    }
    private val meditdialog: SaveEditDialog by lazy {
        SaveEditDialog(this)
    }
    override fun start() {

        if (!imgagepath.isNullOrEmpty()) {
            val file = File(imgagepath)
            val builder = MultipartBody.Builder().setType(MultipartBody.FORM)
            builder.addFormDataPart("files", file.name, RequestBody.create(MediaType.parse("image/jpeg"), file))
            val requestBody = builder.build()
            mPresenter.getImageUrl(requestBody)
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iv_left -> {
                //
                meditdialog.show()
            }
            R.id.tv_lun_content -> {
                var  intent =Intent(this,ChoseForumActivity::class.java)
                intent.putExtra(Constants.KEYTYPE,"1")
                startActivityForResult(intent,Constants.CHOSEFORUM)
            }
            R.id.tv_address_content -> {
                startActivityForResult(Intent(this, SetAddressActivity::class.java), Constants.SETADDRESS)

            }
            R.id.tv_right->{
                strbuf.append("<img src=\"$ImageUrl\" style=\"width: 100%;max-width: 100%;\">")
                strbuf.append("<div style=\"max-width: 100%;\">" + edit_content.text.toString() + "</div>")
               map.clear()
               map["postTitle"]=tv_name.text.toString()
               map["content"]=strbuf.toString()
               map["sectionId"]=forumid
               map["position"]=tv_address_content.text.toString()
               map["firstCover"]=ImageUrl
               map["type"]="1"
               mPresenter.getPushImageText(map)

            }
        }

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                Constants.CHOSEFORUM -> {
                    forumname= data?.getStringExtra(Constants.KEYNAME)
                    forumid= data?.getStringExtra(Constants.KEYID)
                    tv_lun_content.text = data?.getStringExtra(Constants.KEYNAME)
                }
             Constants.SETADDRESS->{
                 address=data?.getStringExtra(Constants.KEYNAME)
                 tv_address_content.text=address
             }
            }
        }

    }

    override fun onImageUrl(data: ArrayList<String?>?) {
        if (data != null && data.size > 0) {
            ImageUrl = data[0]
        }
    }

    override fun onPushImagetext(str: String?) {

        showToast("发布成功")
        SpUtil.cleardata(this,Constants.SAVEIMGTXT)
        finish()
    }

    override fun showError(errorMsg: String, errorCode: Int) {
        showToast(errorMsg)
    }

    override fun showLoading() {
    }

    override fun dismissLoading() {
    }
}