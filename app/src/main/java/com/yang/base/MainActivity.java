package com.yang.base;

import android.content.Intent;
import android.view.View;
import com.yang.base.base.BaseActivity;
import com.yang.mvp.MvpActivity;

/**
 * @desc 主页
 * @author yanggq
 */


public class MainActivity extends BaseActivity {

    @Override
    protected void beforeSetView() {
        super.beforeSetView();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void findViews() {
    }

    @Override
    protected void init(){
    }

    public void baseUtil(View view){
        startActivity(new Intent(this,MenuActivity.class));
    }

    public void mvp(View view){
        startActivity(new Intent(this, MvpActivity.class));
    }

}
