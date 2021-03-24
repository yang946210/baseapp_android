package com.yang.base.base.mvp;


import android.os.Bundle;

import androidx.annotation.Nullable;

import com.yang.base.base.BaseFragment;

public abstract class BaseMvpFragment<P extends BasePresenter>  extends BaseFragment implements BaseView {

    protected P presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        presenter=createPresenter();
        presenter.attachView(this);
        super.onCreate(savedInstanceState);
    }

    protected abstract P createPresenter();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (presenter != null) {
            presenter.detachView();
        }
        super.onDestroyView();
    }

}
