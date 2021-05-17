package com.yang.mvp.aboutus;


import android.annotation.SuppressLint;

import com.yang.base.base.mvp.BasePresenter;
import com.yang.mvp.pai.InterceptorConsumer;
import com.yang.mvp.pai.RxScheduler;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
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
                .compose(RxScheduler.Flo_io_main())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                    }}, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                    }});
    }


}
