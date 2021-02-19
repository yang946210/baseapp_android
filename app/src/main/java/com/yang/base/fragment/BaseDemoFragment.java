package com.yang.base.fragment;

import android.Manifest;
import android.content.Intent;
import android.view.View;

import com.yang.base.BaseTestActivity;
import com.yang.base.R;
import com.yang.base.base.BaseFragment;
import com.yang.base.base.PermissionListener;
import com.yang.base.util.BaseToastHelper;

import java.util.List;

/***
 * @desc 基类fragment功能展示
 * @author yangguoq
 */

public class BaseDemoFragment extends BaseFragment implements View.OnClickListener {

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_base_demo;
    }

    @Override
    protected void findViews() {
        findViewById(R.id.tv_intoActivity).setOnClickListener(this);
        findViewById(R.id.tv_getPermission).setOnClickListener(this);
    }

    @Override
    protected void init() {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_intoActivity:
                startActivity(new Intent(mContext, BaseTestActivity.class));
                break;
            case R.id.tv_getPermission:
                getPermission();
                break;
        }
    }

    /**
     * 权限demo
     */
    private void getPermission(){
        String[] permissions = {
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE};
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
