package com.yang.study;


import android.content.Intent;
import android.view.View;

import com.yang.base.base.BaseActivity;
import com.yang.baseapp.R;

public class LevelMenuActivity extends BaseActivity implements View.OnClickListener {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_menu2;
    }

    @Override
    protected void findViews() {
        findViewById(R.id.tv_service).setOnClickListener(this);
        findViewById(R.id.tv_broadCast).setOnClickListener(this);

    }

    @Override
    protected void init() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_service:
                startActivity(new Intent(this,ServiceActivity.class));
                break;
            case R.id.tv_broadCast:
                startActivity(new Intent(this,BroadCastActivity.class));
                break;
        }
    }
}