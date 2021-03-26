package com.yang.mvp.aboutus;


import android.annotation.SuppressLint;

import com.yang.base.base.mvp.BasePresenter;
import com.yang.mvp.pai.InterceptorConsumer;
import com.yang.mvp.pai.RxScheduler;

import io.reactivex.functions.Consumer;
import okhttp3.ResponseBody;

public class AboutUsPresenter extends BasePresenter<AboutUsContract.Model,AboutUsContract.View> implements AboutUsContract.Presenter {

    @Override
    protected AboutUsContract.Model createModel() {
        return new AboutUsModel();
    }


    @SuppressLint("CheckResult")
    @Override
    public void loadData() {
        if (!isViewAttached()) {
            return;
        }
        model.loadData("keji","4c52313fc9247e5b4176aed5ddd56ad7")
                .compose(RxScheduler.<ResponseBody>Flo_io_main())
                .subscribe(new InterceptorConsumer<ResponseBody>() {
                    @Override
                    public void onSuccess(ResponseBody body) throws Exception {
                        mView.get().onSuccess(body.string());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.get().onError(throwable);
                    }
                });
    }


}
