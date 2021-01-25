package com.yang.base.base;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.yang.base.BaseSdk;
import com.yang.base.util.BasePermissionHelper;

import java.util.List;

/***
 * @desc Activity基类
 * @time 2020/11/20
 * @author yangguoq
 */

public abstract class BaseActivity extends AppCompatActivity {

    /**
     * 权限请求码
     */
    private static final int activityRequest=101;

    /**
     * 权限请求回调
     */
    private PermissionListener permissionListener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        beforeSetView();
        setContentView(getLayoutId());
        findViews();
        init();
    }

    /**
     * 在初始化渲染之前调用，设置横竖屏等使用
     */
    protected void beforeSetView(){
        if (BaseSdk.getInstance().getScreenType()!=null){
            setRequestedOrientation(BaseSdk.getInstance().getScreenType());
        }
    }

    /***
     * 获取fragment的布局文件
     */
    protected abstract int getLayoutId();

    /**
     * 处理布局相关
     */
    protected abstract void findViews();

    /**
     * 初始化
     */
    protected abstract void init();

    /**
     * 权限请求
     * @param permissions  权限
     * @param permissionListener  回调
     */
    public void requestRuntimePermission(String[] permissions, PermissionListener permissionListener) {
        this.permissionListener=permissionListener;
        BasePermissionHelper.requestPermissions(this,permissions, activityRequest);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==activityRequest){
            try {
                List<String> fileList=BasePermissionHelper.checkPermissions(this,permissions);
                if (fileList.size()==0){
                    permissionListener.success();
                }else {
                    permissionListener.fail(fileList);
                }
            }catch (Throwable e){
                e.printStackTrace();
            }
        }
    }
}
