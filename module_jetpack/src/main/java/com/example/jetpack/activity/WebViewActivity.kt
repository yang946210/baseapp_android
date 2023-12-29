package com.example.jetpack.activity

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.webkit.*
import com.blankj.utilcode.util.ToastUtils
import com.example.lib_jetpack.databinding.ActivityWebViewBinding
import com.yang.ktbase.base.BaseActivity
import com.yang.ktbase.base.BaseViewModel
import com.yang.ktbase.extorutil.logD

class WebViewActivity : BaseActivity<BaseViewModel, ActivityWebViewBinding>() {

    @SuppressLint("JavascriptInterface", "SetJavaScriptEnabled")
    override fun initView(savedInstanceState: Bundle?) {

        mViewBind.apply {

            vwMain.apply {
                loadUrl("file:///android_asset/show.html")
                webViewClient = MyWebViewClient()
                webChromeClient = MyWebChromeClient()
                addJavascriptInterface(JavaScriptInterface(),"native")
                settings.apply {
                    javaScriptEnabled = true
                }
            }

            btNativeClick1.setOnClickListener {
                vwMain.apply {
                    if ( Build.VERSION.SDK_INT>= Build.VERSION_CODES.KITKAT)
                        evaluateJavascript("javascript:androidClick()") { it.logD() }
                    else
                        loadUrl("javascript:test()")
                }
            }

            btNativeClick2.setOnClickListener {
                vwMain.apply {
                    if ( Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT)
                        evaluateJavascript("javascript:test()") { it.logD() }
                    else
                        loadUrl("javascript:test()")
                }
            }
        }
    }
}


class MyWebViewClient : WebViewClient()

class MyWebChromeClient : WebChromeClient() {
    override fun onJsPrompt(
        view: WebView?,
        url: String?,
        message: String?,
        defaultValue: String?,
        result: JsPromptResult?
    ): Boolean {
        ToastUtils.showLong("native log :$url $message")
        return true
    }

    override fun onJsAlert(
        view: WebView?,
        url: String?,
        message: String?,
        result: JsResult?
    ): Boolean {
        return super.onJsAlert(view, url, message, result)
    }

}

class JavaScriptInterface{

    @JavascriptInterface
    fun nativeLog(msg:String){
        ToastUtils.showLong(msg)
    }
}