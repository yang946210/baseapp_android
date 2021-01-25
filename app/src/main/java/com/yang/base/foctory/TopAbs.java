package com.yang.base.foctory;

/***
 * @desc
 * @time 2020/12/24
 * @author yangguoq
 */

public abstract class TopAbs implements TopInterface {

    public void startGet(){
        onCreate();
    }

    @Override
    public void onGetData(String data) {

    }
}
