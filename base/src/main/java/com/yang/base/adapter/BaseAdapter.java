package com.yang.base.adapter;

import android.view.View;

/***
 * @desc 适配器接口
 * @author yang
 */
public interface BaseAdapter<T> {


    /***
     * 添加数据
     * @param item 数据内容
     */
    void addData(T item);

    /***
     * 异常数据
     * @param position 数据位置
     */
    void removeData(int position);

    /***
     * 获取数据
     * @param position 数据位置
     * @return
     */
    T getData(int position);


    /***
     * 获取item布局文件ID
     * @param position 数据位置
     * @param itemData 数据
     * @return
     */
    int getItemLayoutResourceId(int position, T itemData);

    /***
     * 绑定数据
     * @param holder view容器
     * @param position 数据位置
     * @param itemData 数据内容
     */
    void onItemViewBind(BaseViewHolder holder, int position, T itemData);


    /***
     * 设置点击事件监听器
     * @param listener 监听器
     */
    void setOnItemClickListener(OnBaseItemClickListener<T> listener);


    /**
     * item中的某个布局点击事件监听求
     */
    void setOnItemChildrenClickListener(OnBaseItemChildrenClickListener<T> listener);
    /***
     * 设置长按监听
     * @param listener
     */
    void setOnItemLongClickListener(OnBaseItemLongClickListener<T> listener);

    /***
     * 设置子view点击事件
     * @param holder  view容器
     * @param childViewId  子viewID
     * @param position 位置
     */
    void addItemChildViewClickListener(BaseViewHolder holder, int childViewId, int position);

    /***
     * 设置子view点击事件
     * @param holder  view容器
     * @param childView  子view
     * @param position 位置
     */
    void addItemChildViewClickListener(BaseViewHolder holder, View childView, int position);
}
