package com.yang.mvp.aboutus;


import com.yang.mvp.api.RetrofitClient;

import io.reactivex.Flowable;
import okhttp3.ResponseBody;

public class AboutUsAboutModel implements AboutUsContract.AboutModel {
    @Override
    public Flowable<ResponseBody> loadData(String type, String key) {
        return RetrofitClient.getInstance().getApi().loadUs(type,key);
    }
}
