package com.yang.baseapp;


import android.Manifest;
import android.view.View;

import com.yang.base.base.BaseActivity;
import com.yang.base.base.PermissionListener;
import com.yang.base.util.BaseToastHelper;

import java.util.List;

public class BaseTestActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void beforeSetView() {
        super.beforeSetView();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_base_test;
    }

    @Override
    protected void findViews() {
        findViewById(R.id.tv_getPermission).setOnClickListener(this);
    }

    @Override
    protected void init() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_getPermission:
                getPermission();
                break;
        }
    }


    /**
     * 权限demo
     */
    private void getPermission(){
        String[] permissions = {Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.READ_CONTACTS,
                Manifest.permission.READ_CALL_LOG};
        requestRuntimePermission(permissions, new PermissionListener() {
            @Override
            public void success() {
                BaseToastHelper.showToast("全部权限获取成功");
            }

            @Override
            public void fail(List<String> failList) {
                String s="";
                for(String next:failList){
                    s=s+next; }
                BaseToastHelper.showToast("全部权限获取失败，一下权限未通过"+s);
            }
        });
    }
}