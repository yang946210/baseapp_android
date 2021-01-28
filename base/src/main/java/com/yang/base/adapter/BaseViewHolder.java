package com.yang.base.adapter;

import android.util.SparseArray;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/***
 * @desc viewHolder
 * @author yang
 */
public class BaseViewHolder extends RecyclerView.ViewHolder {

    /***
     * 记录view容器
     */
    protected SparseArray<View> views;
    /**
     * item 的顶级view
     */
    protected View itemView;
    /***
     * view类型,自定义类型，方便同一个listview/recyclerView使用不同类型的item 布局
     */
    protected int viewType;
    /***
     * item 的位置标识
     */
    protected int itemPosition = 0;

    /***
     * 构造函数
     * @param itemView 父级view
     * @param viewType 自定类型，方便同一个listview/recyclerView使用不同类型的item 布局
     */
    public BaseViewHolder(View itemView, int viewType) {
        super(itemView);
        this.itemView = itemView;
        this.viewType = viewType;
        views = new SparseArray<>();
    }


    /***
     * 通过viewId获取控件
     * @param viewId
     * @param <T>
     * @return
     */
    public <T extends View> T getView(int viewId) {
        View view = views.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            views.put(viewId, view);
        }
        return (T) view;
    }

    /***
     * 获取item位置ID
     * @return
     */
    public int getItemPosition() {
        return itemPosition;
    }

    /***
     * 设置item位置ID
     * @param itemPosition
     */
    public void setItemPosition(int itemPosition) {
        this.itemPosition = itemPosition;
    }

    /***
     * 获取itemView
     * @return
     */
    public View getItemView() {
        return itemView;
    }

    /***
     * 获取类型
     * @return
     */
    public int getViewType() {
        return viewType;
    }

    @Override
    protected void finalize() throws Throwable {
        views.clear();
        super.finalize();
    }
}
