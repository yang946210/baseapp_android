package com.yang.base.ui.webview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

/***
 * @desc 一个封装好的webView
 * @author yang
 */

class BaseWebView extends LinearLayout {

    public BaseWebView(Context context) {
        super(context);
    }

    public BaseWebView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseWebView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public BaseWebView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
