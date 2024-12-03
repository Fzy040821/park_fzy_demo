package com.bw.kf.park_fzy_kotlin.webView

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import com.tencent.smtt.sdk.WebView
import com.tencent.smtt.sdk.WebViewClient


class X5WebView(context: Context , attributeSet: AttributeSet): WebView(context , attributeSet) {
    private val webViewClient = object : WebViewClient(){
        override fun shouldOverrideUrlLoading(
            view: WebView?,
            url: String?
        ): Boolean {

            if(view!=null && url!=null){
                view.loadUrl(url)
            }

            return true
        }
    }

    init{
        setWebViewClient(webViewClient)
        initWebViewClient()
        view.isClickable = true
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebViewClient(){
        val webViewSetting = settings
        webViewSetting.javaScriptEnabled = true
    }
}