package com.yang;


import android.content.Intent;
import android.view.View;

import com.alibaba.fastjson.JSONObject;
import com.yang.base.base.BaseActivity;
import com.yang.base.util.log.Logger;
import com.yang.mvp.R;
import com.yang.mvp.aboutus.AboutUsActivity;


public class ModelIndexActivity extends BaseActivity {

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

    public void mvpTest(View view){
        JSONObject object=new JSONObject();
        object.put("name","zhangsan");
        object.put("age","12");
        JSONObject info=new JSONObject();
        info.put("address","兰家勾18号");
        info.put("student","没有");
        object.put("info",info);

        Logger.json(object.toJSONString());
        Logger.d("logger=====d");
        Logger.e("12212");

        startActivity(new Intent(this, AboutUsActivity.class));
    }

    public void Rxjava(View view){
        startActivity(new Intent(this, RxAndroidActivity.class));
    }

    public void Retrofit(View view){
        startActivity(new Intent(this, RetrofitActivity.class));
    }
}