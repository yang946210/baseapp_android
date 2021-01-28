package com.yang.base.adapter;


/***
 * @desc 每项点击事件
 * @author yang
 */
public interface OnBaseItemClickListener<T> {

    /***
     * 每项点击事件
     * @param holder view容器
     * @param position 数据位置
     * @param itemData 数据内容
     */
    void onItemViewClick(BaseViewHolder holder, int position, T itemData);


}
