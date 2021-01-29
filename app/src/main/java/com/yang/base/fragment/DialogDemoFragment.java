package com.yang.base.fragment;

import android.view.View;

import com.yang.base.R;
import com.yang.base.base.BaseFragment;
import com.yang.base.widget.dialog.BaseCustomDialog;
import com.yang.base.widget.dialog.BaseLoadingDialogHelper;
import com.yang.base.widget.dialog.OnDialogClickListener;
import com.yang.base.util.BaseHandlerHelper;
import com.yang.base.util.BaseToastHelper;

/***
 * @desc dialog相关功能
 * @author yangguoq
 */

public class DialogDemoFragment extends BaseFragment implements View.OnClickListener {

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_dialog_demo;
    }

    @Override
    protected void findViews() {
        findViewById(R.id.tv_dialog1).setOnClickListener(this);
        findViewById(R.id.tv_dialog2).setOnClickListener(this);
        findViewById(R.id.tv_dialog3).setOnClickListener(this);
        findViewById(R.id.tv_dialog4).setOnClickListener(this);
    }

    @Override
    protected void init() {}

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_dialog1:
                BaseCustomDialog dialog1=new BaseCustomDialog(mContext, "message1", new OnDialogClickListener() {
                    @Override
                    public void onDialogClick(boolean isConfirm, View view) {
                        BaseToastHelper.showToast("点击了"+(isConfirm?"确认1":"取消1"));
                    }
                });
                dialog1.show();
                break;
            case R.id.tv_dialog2:
                BaseCustomDialog dialog2=new BaseCustomDialog(mContext);
                dialog2.setTitle("title2").setMessage("message2").setConfirm("确定不").setOnDialogClickListener(new OnDialogClickListener() {
                    @Override
                    public void onDialogClick(boolean isConfirm, View view) {
                        BaseToastHelper.showToast("点击了"+(isConfirm?"确认2":"取消2"));
                    }
                });
                dialog2.show();
                break;
            case R.id.tv_dialog3:
                BaseCustomDialog dialog3=new BaseCustomDialog(mContext);
                dialog3.setTitle("title3").setMessage("message3").setSingleClick("想想").setOnDialogClickListener(new OnDialogClickListener() {
                    @Override
                    public void onDialogClick(boolean isConfirm, View view) {
                        BaseToastHelper.showToast("点击了"+(isConfirm?"确认3":"取消3"));
                    }
                });
                dialog3.show();
                break;
            case R.id.tv_dialog4:
                BaseLoadingDialogHelper.showLoadingDialog(mContext,"加载中");
                BaseHandlerHelper.getInstance().getHandler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        BaseLoadingDialogHelper.dismissLoadingDialog();
                    }
                },2000);
                break;
        }
    }
}
