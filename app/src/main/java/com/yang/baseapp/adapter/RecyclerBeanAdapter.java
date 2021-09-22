package com.yang.baseapp.adapter;

import android.content.Context;
import android.widget.TextView;

import com.yang.base.adapter.BaseObjectRecyclerViewAdapter;
import com.yang.base.adapter.BaseViewHolder;
import com.yang.baseapp.R;
import com.yang.baseapp.bean.RecyclerBean;

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
        TextView tv_name=holder.getView(R.id.tv_name);
        tv_name.setText(itemData.getName());
        addItemChildViewClickListener(holder,tv_name,position);
    }
}
