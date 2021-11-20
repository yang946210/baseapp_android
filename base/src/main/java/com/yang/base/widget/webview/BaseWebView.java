package com.yang.base.widget.webview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.yang.base.BaseSdk;
import com.yang.base.R;
import java.lang.ref.WeakReference;


/***
 * @desc 一个封装好的webView
 * @author yang
 */

public class BaseWebView extends LinearLayout {

    /**
     * webView
     */
    private WebView wv_base;

    /**
     * 加载失败布局
     */
    private TextView  tv_loadFail;


    /**
     * 自定义ChromeClient
     */
    private BaseWebChromeClient webChromeClient;

    /**
     * 自定义WebViewClient
     */
    private BaseWebViewClient webViewClient;

    /**
     * context
     */
    private WeakReference<Context> weakReference;


    /**
     * 初始化
     * @param context
     */
    public BaseWebView(Context context){
        super(context);
        initView(context);
        initWebView();
    }


    public BaseWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
        initWebView();
    }


    /**
     * 初始化布局
     * @param context
     */
    private void initView(Context context){
        LayoutInflater.from(context).inflate(R.layout.base_webview,this);
        weakReference=new WeakReference<>(context);
        wv_base=findViewById(R.id.wv_base);
        tv_loadFail=findViewById(R.id.tv_loadFail);
    }

    /**
     * 初始化webview
     */
    @SuppressLint("SetJavaScriptEnabled")
    private void initWebView(){
        wv_base.setWebViewClient(webViewClient=new BaseWebViewClient(this));
        wv_base.setWebChromeClient(webChromeClient=new BaseWebChromeClient());
        wv_base.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        //调试模式开关
        if (BaseSdk.getInstance().getCanDebugWebView()||BaseSdk.getInstance().isDebug()) {
            wv_base.setWebContentsDebuggingEnabled(true);
        }
        WebSettings setting = wv_base.getSettings();
        setting.setJavaScriptEnabled(true);
        setting.setDomStorageEnabled(true);
        setting.setLoadWithOverviewMode(true);
        setting.setUseWideViewPort(true);
        setting.setSupportMultipleWindows(false);
        setting.setGeolocationEnabled(true);
        setting.setSaveFormData(false);
        setting.setDatabaseEnabled(true);
    }

    /**
     * 获取webView
     * @return
     */
    public WebView getVebView(){
        return wv_base;
    }


    /**
     * 加载失败展示页面
     */
    public void showError(){
        tv_loadFail.setVisibility(VISIBLE);
        wv_base.setVisibility(GONE);
    }

    /**
     * 加载失败展示页面
     * @param errorInfo 错误描述
     */
    public void showError(String errorInfo){
        showError();
        tv_loadFail.setText(errorInfo);
    }
}
