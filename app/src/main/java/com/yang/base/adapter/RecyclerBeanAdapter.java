package com.yang.base.adapter;

import android.content.Context;
import android.widget.TextView;

import com.yang.base.R;
import com.yang.base.bean.RecyclerBean;

/***
 * @desc 测试实体beanAdapter
 * @author yangguoq
 */

public class RecyclerBeanAdapter extends BaseObjectRecyclerViewAdapter<RecyclerBean> {

    public RecyclerBeanAdapter(Context context) {
        super(context);
    }

    @Override
    public int getItemLayoutResourceId(int position, RecyclerBean itemData) {
        return R.layout.item_recycler_bean;
    }

    @Override
    public void onItemViewBind(BaseViewHolder holder, int position, RecyclerBean itemData) {
        TextView tv_name=(TextView)holder.getView(R.id.tv_name);
        tv_name.setText(itemData.getName());
        addItemChildViewClickListener(holder,tv_name,position);
    }
}
