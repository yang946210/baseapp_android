package com.yang.base.base;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.yang.base.util.BasePermissionHelper;

import java.util.ArrayList;
import java.util.List;

/***
 * @desc fragment基类
 * @time 2020/11/20
 * @author yangguoq
 */

public abstract class BaseFragment extends Fragment {

    /**
     * 权限请求码
     */
    private static final int fragmentRequest =100;

    /**
     * 主布局
     */
    protected View view;

    /**
     * 全局context
     */
    protected Context mContext;

    /**
     * 权限请求回调
     */
    private PermissionListener permissionListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext=context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(getFragmentLayoutId(),null);
        findViews();
        init();
        return view;
    }

    public <T extends View> T findViewById(int resId){
        return view.findViewById(resId);
    }

    /***
     * 获取fragment的布局文件
     */
    protected abstract int getFragmentLayoutId();

    /**
     * 处理布局
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
        this.requestPermissions(permissions, fragmentRequest);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==fragmentRequest){
           try {
               List<String> fileList=new ArrayList<>();
               for(String p:permissions){
                   if (!BasePermissionHelper.hasPermission(getActivity(),p)){
                       fileList.add(p);
                   }
               }
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


    @Override
    public void onDestroy() {
        mContext=null;
        super.onDestroy();
    }
}
