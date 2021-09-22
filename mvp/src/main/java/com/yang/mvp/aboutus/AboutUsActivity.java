package com.yang.mvp.aboutus;

import android.view.View;

import com.yang.base.base.mvp.BaseMvpActivity;
import com.yang.base.util.BaseToastHelper;
import com.yang.base.widget.dialog.BaseLoadingDialogHelper;
import com.yang.mvp.R;


public class AboutUsActivity extends BaseMvpActivity<AboutUsPresenter> implements AboutUsContract.View {



    @Override
    public int getLayoutId(){
        return R.layout.activity_about_us;
    }

    @Override
    protected void findViews() {

    }

    @Override
    public void init(){

    }

    @Override
    protected AboutUsPresenter createPresenter() {
        return new AboutUsPresenter();
    }

    /**
     * 点击事件
     * @param view
     */
    public void getData(View view){
        presenter.loadData();
    }


    @Override
    public void onStartLoad() {
        BaseLoadingDialogHelper.showLoadingDialog(AboutUsActivity.this);
    }

    @Override
    public void onSuccess(String body) {
        BaseLoadingDialogHelper.dismissLoadingDialog();
        BaseToastHelper.showToast(body);
    }

    @Override
    public void onError(Throwable throwable) {
        BaseLoadingDialogHelper.dismissLoadingDialog();
    }

    @Override
    public void onNext() {

    }
}
