package com.yang.mvp.aboutus;


import android.annotation.SuppressLint;

import com.yang.base.base.mvp.BasePresenter;
import com.yang.mvp.api.InterceptorConsumer;
import com.yang.mvp.api.RetrofitClient;
import com.yang.mvp.api.RxScheduler;

import io.reactivex.functions.Consumer;
import okhttp3.ResponseBody;

public class AboutUsPresenter extends BasePresenter<AboutUsContract.AboutModel,AboutUsContract.View> implements AboutUsContract.Presenter {

    @Override
    protected AboutUsContract.AboutModel createModel() {
        return new AboutUsAboutModel();
    }


    @SuppressLint("CheckResult")
    @Override
    public void loadData() {
        if (!isViewAttached()) {
            return;
        }
        model.loadData("keji","4c52313fc9247e5b4176aed5ddd56ad7")
                .compose(RxScheduler.Flo_io_main())
                .subscribe(new InterceptorConsumer<ResponseBody>() {
                    @Override
                    public void onSuccess(ResponseBody responseBody) throws Exception {
                        view.get().onSuccess(responseBody.string());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                }});
    }


}
