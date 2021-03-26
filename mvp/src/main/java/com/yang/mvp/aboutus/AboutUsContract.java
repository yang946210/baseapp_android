package com.yang.mvp.aboutus;


import com.yang.base.base.mvp.BaseView;

import io.reactivex.Flowable;
import okhttp3.ResponseBody;

public interface AboutUsContract {
    interface Model {
        Flowable<ResponseBody> loadData(String type,String key);
    }

    interface View extends BaseView {
        void onSuccess(String body);
    }

    interface Presenter {
        void loadData();
    }
}
