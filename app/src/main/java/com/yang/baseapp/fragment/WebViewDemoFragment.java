package com.yang.baseapp.fragment;

import android.content.Intent;
import android.view.View;

import com.yang.baseapp.R;
import com.yang.baseapp.TestWebViewActivity;
import com.yang.base.base.BaseFragment;

/***
 * @desc webViewDemo
 * @author yangguoq
 */

public class WebViewDemoFragment extends BaseFragment implements View.OnClickListener {

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_webview_demo;
    }

    @Override
    protected void findViews() {
        findViewById(R.id.tv_startWeb).setOnClickListener(this);
    }

    @Override
    protected void init() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_startWeb:
                startActivity(new Intent(mContext, TestWebViewActivity.class));
                break;
        }
    }
}
