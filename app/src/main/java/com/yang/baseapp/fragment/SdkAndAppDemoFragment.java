package com.yang.baseapp.fragment;

import android.widget.TextView;

import com.yang.base.BaseSdk;
import com.yang.baseapp.R;
import com.yang.base.base.BaseFragment;

/***
 * @desc demo
 * @author yangguoq
 */

public class SdkAndAppDemoFragment extends BaseFragment {

    private TextView tv_debug,tv_rootPath,tv_type;

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_sdk_demo;
    }

    @Override
    protected void findViews() {
        tv_debug=findViewById(R.id.tv_debug);
        tv_rootPath=findViewById(R.id.tv_rootPath);
        tv_type=findViewById(R.id.tv_type);
    }

    @Override
    protected void init() {
        boolean debug=BaseSdk.getInstance().isDebug();
        String rootPath=BaseSdk.getInstance().getRootPath().getAbsolutePath();
        int type=BaseSdk.getInstance().getScreenType();

        tv_debug.setText("当前debug状态："+debug);
        tv_rootPath.setText("当前存储根目录："+rootPath);
        tv_type.setText("当前设置的全局横竖屏状态码："+type);
    }
}
