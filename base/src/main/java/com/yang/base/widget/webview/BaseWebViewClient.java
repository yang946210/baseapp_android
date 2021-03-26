package com.yang.base.widget.webview;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.util.Log;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

/***
 * @desc WebViewClient
 * @author yangguoq
 */

public class BaseWebViewClient extends WebViewClient {

    /**
     * webView容器
     */
    private BaseWebView webGroup;

    public BaseWebViewClient(BaseWebView webGroup){
        this.webGroup=webGroup;
    }

    /**
     * 开始加载网页
     */
    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        Log.i("======onPageStarted", "====url"+url);
        super.onPageStarted(view, url, favicon);
    }

    /**
     * 网页加载完成
     */
    @Override
    public void onPageFinished(WebView view, String url) {
        Log.i("======onPageFinished", "====url"+url);
        super.onPageFinished(view, url);
    }

    /**
     *拦截url跳转
     */
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        Log.i("======shouldOverrideUrl", "====request"+request.getUrl());
        return super.shouldOverrideUrlLoading(view, request);
    }

    /**
     * 加载错误回调
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
        Log.i("======onReceivedError", "====request"+request.getUrl()+"==error"+error.getDescription());
        webGroup.showError(error.getDescription().toString());
        super.onReceivedError(view, request, error);
    }

    /**
     * https错误回调
     */
    @Override
    public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
        Log.i("======onSslError", "====error"+error.getUrl());
        super.onReceivedSslError(view, handler, error);
    }

    /**
     * 资源请求回调
     * @param view
     * @param request
     * @return
     */
    @Nullable
    @Override
    public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
        Log.i("======interceptRequest", "====request"+request.getUrl());
        return super.shouldInterceptRequest(view, request);
    }
}
