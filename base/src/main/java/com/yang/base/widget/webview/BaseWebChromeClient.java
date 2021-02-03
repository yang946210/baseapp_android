package com.yang.base.widget.webview;

import android.util.Log;
import android.webkit.ConsoleMessage;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

/***
 * @desc WebViewClient
 * @author yangguoq
 */

public class BaseWebChromeClient extends WebChromeClient {


    @Override
    public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
        Log.i("======onJsAlert",result.toString());
        return super.onJsAlert(view, url, message, result);
    }

    @Override
    public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
        Log.i("======onJsPrompt",result.toString()+"===="+message);
        return super.onJsPrompt(view, url, message, defaultValue, result);
    }

    @Override
    public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
        Log.i("======onJsConfirm",result.toString()+"===="+message);
        return super.onJsConfirm(view, url, message, result);
    }

    @Override
    public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
        Log.i("======onConsoleMessage",consoleMessage.toString());
        return super.onConsoleMessage(consoleMessage);
    }
}
