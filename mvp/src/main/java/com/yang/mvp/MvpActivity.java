package com.yang.mvp;


import android.content.Intent;
import android.view.View;

import com.yang.base.base.BaseActivity;


public class MvpActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mvp;
    }

    @Override
    protected void findViews() {

    }

    @Override
    protected void init() {

    }

    public void Rxjava(View view){
        startActivity(new Intent(this,RxAndroidActivity.class));
    }

    public void Retrofit(View view){
        startActivity(new Intent(this,RetrofitActivity.class));
    }
}