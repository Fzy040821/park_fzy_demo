package com.bw.kf.park_fzy_kotlin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.JavascriptInterface
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.SPUtils
import com.bw.kf.common.net.Const
import com.bw.kf.park_fzy_kotlin.news.NewsActivity
import com.bw.kf.park_fzy_kotlin.sign.SignActivity
import com.bw.kf.park_fzy_kotlin.webView.WebViewJavaScriptFunction
import com.tencent.smtt.sdk.WebView

/**
 * 实现x5浏览器:
 * JavaScript和Android交互
 * @author: FZY
 * @Date: 2024/12/2
 */
class MainActivity : AppCompatActivity() {

    private lateinit var url: String
    private lateinit var listener: MainWebViewJavaScriptFunction
    private lateinit var main_X5_WebView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //TODO 自定义X5WebView
//        url = SPUtils.getInstance().getString(Const.HOME)
        url = "http://10.161.9.80:7035/index.html"
        Log.i("TAGA", "onCreate: WebViewUrl: $url")
        initWebView()

    }

    private fun initWebView() {

        listener = MainWebViewJavaScriptFunction()

        main_X5_WebView = findViewById(R.id.main_X5View)
        main_X5_WebView.loadUrl(url)
        main_X5_WebView.view.overScrollMode = View.OVER_SCROLL_ALWAYS
        main_X5_WebView.addJavascriptInterface(listener , "androidinfo")
    }

    fun start(clazz: Class<*>){
        val intent = Intent(this , clazz)
        startActivity(intent)
    }

    inner class MainWebViewJavaScriptFunction: WebViewJavaScriptFunction{
        override fun onJSFunctionCalled(tag: String) {
        }

        @JavascriptInterface
        fun androidsign(id: String){
            Log.i("TAGA", "androidsign: $id 签到")
            start(SignActivity::class.java)
        }

        @JavascriptInterface
        fun androidaddculture(id: String){//文化编辑
            Log.i("TAGA", "androidaddculture: $id")
            start(NewsActivity::class.java)
        }

        //注册要监听的js事件
        @JavascriptInterface
        fun androidparking(id: String?) {//车位申请
            Log.i("TAGA", "androidparking: $id")
        }

        @JavascriptInterface
        fun androidapply(id: String?) {//我的申请
            Log.i("TAGA", "androidapply: $id")
        }

        @JavascriptInterface
        fun androidvisit(id: String?) {//来访预约
            Log.i("TAGA", "androidvisit: $id")

//            start(VisitorActivity::class.java)
        }

        @JavascriptInterface
        fun androidpatrol(id: String?) {//巡更管理
            Log.i("TAGA", "androidpatrol: $id")
//            start(PatrolActivity::class.java)
        }

        @JavascriptInterface
        fun androidrepair(id: String?) {//维修管理
            Log.i("TAGA", "androidrepair: $id")
//            start(RepairActivity::class.java)
        }

        @JavascriptInterface
        fun androidaddrepair(id: String?) {//添加维修
            Log.i("TAGA", "androidaddrepair: $id")
//            start(AddRepairActivity::class.java)
        }

        @JavascriptInterface
        fun androidaddnotice(id: String?) {//第一个公告管理
            Log.i("TAGA", "androidaddnotice: $id")
            //发公告
//            start(AddNoticeActivity::class.java)
        }

        @JavascriptInterface
        fun androidculture(id: String?) {//文化管理
            Log.i("TAGA", "androidculture: $id")

        }

        @JavascriptInterface
        fun androidnotice(id: String?) {//第二个公告管理
            Log.i("TAGA", "androidnotice: $id")
            //公告管理
//            start(NoticeActivity::class.java)
        }

        @JavascriptInterface
        fun androidattendance(id: String?) {//考勤管理
            Log.i("TAGA", "androidattendance: $id")
        }

        @JavascriptInterface
        fun androidproperty(id: String?) {//物业审核
            Log.i("TAGA", "androidproperty: $id")
        }

        @JavascriptInterface
        fun androidcheckculture(id: String?) {//文化审核
            Log.i("TAGA", "androidcheckculture: $id")

        }

        @JavascriptInterface
        fun androidpeople(id: String?) {//人员管理
            Log.i("TAGA", "androidpeople: $id")
//            start(DepartmentActivity::class.java)
        }

        @JavascriptInterface
        fun androidnews(id: String?) {
            Log.i("TAGA", "androidnews: $id")
        }

        @JavascriptInterface
        fun androidnoticelist(id: String?) {
            Log.i("TAGA", "androidnoticelist: $id")
        }

    }
}