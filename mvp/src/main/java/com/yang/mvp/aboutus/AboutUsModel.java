package com.yang.mvp.aboutus;


import com.yang.mvp.pai.RetrofitClient;

import io.reactivex.Flowable;
import okhttp3.ResponseBody;

public class AboutUsModel implements AboutUsContract.Model {
    @Override
    public Flowable<ResponseBody> loadData(String type, String key) {
        return RetrofitClient.getInstance().getApi().loadUs(type,key);
    }
}
