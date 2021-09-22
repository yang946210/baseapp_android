package com.yang.baseapp;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.view.View;

import com.yang.base.BaseSdk;
import com.yang.base.base.BaseActivity;
import com.yang.base.base.PermissionListener;
import com.yang.base.util.BaseToastHelper;
import com.yang.ModelIndexActivity;
import com.yang.base.util.log.Logger;
import com.yang.baseapp.util.JsonUtil;
import com.yang.study.LevelMenuActivity;
import com.yang.study.ServiceActivity;
import com.yang.study.ServiceAbout;

import java.util.List;

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
        getPermission();
        Logger.d(BaseSdk.getInstance().getRootPath());
        getJson();

    }

    /**
     * 权限demo
     */
    private void getPermission(){
        String[] permissions =new String[]{"android.permission.CALL_PHONE"};

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

    public void baseUtil(View view){
        startActivity(new Intent(this,MenuActivity.class));
    }

    public void mvp(View view){
        startActivity(new Intent(this, ModelIndexActivity.class));
    }

    public void Level(View view){
        startActivity(new Intent(this, LevelMenuActivity.class));
    }



    private void getJson(){
        JsonUtil.getJson();
    }

}
