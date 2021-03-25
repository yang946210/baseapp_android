package com.yang.mvp.aboutus;

import android.view.View;
import android.widget.Toast;

import com.yang.base.base.mvp.BaseMvpActivity;
import com.yang.mvp.R;


public class AboutUsActivity extends BaseMvpActivity<AboutUsPresenter> implements AboutUsContract.View {

    public void getData(View view){
        presenter.loadData();
    }

    @Override
    public int getLayoutId(){
        return R.layout.activity_about_us;
    }


    @Override
    protected AboutUsPresenter createPresenter() {
        return new AboutUsPresenter();
    }

    @Override
    protected void findViews() {

    }

    @Override
    public void init(){

    }

    @Override
    public void onSuccess(String body) {
        Toast.makeText(this,body,Toast.LENGTH_LONG).show();
    }


    @Override
    public void onShowLoading() {

    }

    @Override
    public void onHideLoading() {

    }

    @Override
    public void onError(Throwable throwable) {

    }



}
