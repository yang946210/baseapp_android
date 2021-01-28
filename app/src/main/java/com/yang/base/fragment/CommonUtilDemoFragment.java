package com.yang.base.fragment;

import android.widget.TextView;

import com.yang.base.BaseSdk;
import com.yang.base.R;
import com.yang.base.base.BaseFragment;
import com.yang.base.ui.dialog.BaseCustomDialog;
import com.yang.base.util.BaseCommHelper;

/***
 * @desc 一些公共方法
 * @author yangguoq
 */

public class CommonUtilDemoFragment extends BaseFragment {

    private TextView tv_nameAgent,tv_ProcessName,tv_AppVersion,tv_isInstallAPK,
            tv_VersionCode,tv_AppName,tv_package;

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_common_demo;
    }

    @Override
    protected void findViews() {
        tv_nameAgent=findViewById(R.id.tv_nameAgent);
        tv_ProcessName=findViewById(R.id.tv_ProcessName);
        tv_AppVersion=findViewById(R.id.tv_AppVersion);
        tv_isInstallAPK=findViewById(R.id.tv_isInstallAPK);
        tv_VersionCode=findViewById(R.id.tv_VersionCode);
        tv_AppName=findViewById(R.id.tv_AppName);
        tv_package=findViewById(R.id.tv_package);
    }

    @Override
    protected void init() {
        tv_nameAgent.setText("当前agent："+ BaseCommHelper.getUserAgent());
        tv_ProcessName.setText("当前进程名："+ BaseCommHelper.getProcessName(0));
        tv_AppVersion.setText("当前appVersion："+ BaseCommHelper.getAppVersion(BaseSdk.getInstance().getContext()));
        tv_package.setText("当前包名："+BaseCommHelper.getPackageName(BaseSdk.getInstance().getContext()));
        tv_isInstallAPK.setText("com.yang.base包名是否安装："+ BaseCommHelper.isInstallAPK(BaseSdk.getInstance().getContext(),"com.yang.base"));
        tv_VersionCode.setText("当前VersionCode："+ BaseCommHelper.getAppVersionCode(BaseSdk.getInstance().getContext()));
        tv_AppName.setText("当前appName："+ BaseCommHelper.getAppName(BaseSdk.getInstance().getContext()));
    }
}
