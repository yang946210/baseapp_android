package com.yang.show.text;


import com.yang.base.util.BaseToastHelper;

public abstract class TestAbs implements TestInterface {

    void Test03(){
        BaseToastHelper.showToast("text03");
        text02();
    };

    abstract void text04();
}
