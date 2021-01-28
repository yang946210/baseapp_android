package com.yang.base.adapter;

import android.view.View;

/***
 * @desc 每项点击事件
 * @author yang
 */
public interface OnBaseItemChildrenClickListener<T> {

    /***
     * 每项子view点击事件
     * @param holder view容器
     * @param position 数据位置
     * @param itemData 数据内容
     * @param clickView 点击view
     */
    void onItemChildViewClick(BaseViewHolder holder, int position, T itemData, View clickView);
}
