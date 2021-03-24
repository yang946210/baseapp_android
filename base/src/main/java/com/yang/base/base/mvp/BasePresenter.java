package com.yang.base.base.mvp;



import java.lang.ref.WeakReference;


public abstract class BasePresenter<M extends BaseModel,V extends BaseView>{


    /**
     * view
     */
    protected WeakReference<V> mView;

    /***
     * model
     */
    protected M model;


    public void attachView(V view) {
        mView =new WeakReference<>(view);
        model=createModel();
    }

    /**
     * 释放view索引
     */
    public void detachView() {
        if (mView != null) {
            mView.clear();
            mView = null;
        }
    }

    /**
     * View是否绑定
     *
     * @return
     */
    public boolean isViewAttached() {
        return mView != null;
    }

    /**
     * 创建model
     * @return
     */
    protected abstract M createModel();


    /**
     * 获取view层索引
     * @return
     */
    public V getView() {
        return mView.get();
    }
}
