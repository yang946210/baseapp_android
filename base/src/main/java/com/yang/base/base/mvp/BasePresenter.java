package com.yang.base.base.mvp;



import java.lang.ref.WeakReference;


public abstract class BasePresenter<M,V>{


    /**
     * view
     */
    protected WeakReference<V> view;

    /***
     * model
     */
    protected M model;


    public void attachView(V view) {
        this.view =new WeakReference<>(view);
        model=createModel();
    }

    /**
     * 释放view索引
     */
    public void detachView() {
        if (view != null) {
            view.clear();
            view = null;
        }
    }

    /**
     * View是否绑定
     *
     * @return
     */
    public boolean isViewAttached() {
        return view != null;
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
        return view.get();
    }
}
