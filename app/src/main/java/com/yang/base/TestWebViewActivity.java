package com.yang.base;


import com.yang.base.base.BaseActivity;
import com.yang.base.widget.webview.BaseWebView;

/**
 * @desc 主页
 * @author yanggq
 */


public class TestWebViewActivity extends BaseActivity {

    private BaseWebView bwv_base;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_test_web;
    }

    @Override
    protected void findViews() {
        bwv_base=findViewById(R.id.bwv_base);
    }

    @Override
    protected void init(){
        bwv_base.getVebView().loadUrl("file:///android_asset/show.html");
    }


}
