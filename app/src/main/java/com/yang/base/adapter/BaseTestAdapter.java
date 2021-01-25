package com.yang.base.adapter;

import android.content.Context;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yang.base.R;

/***
 * @desc
 * @time 2020/11/12
 * @author yangguoq
 */

public class BaseTestAdapter extends BaseJSONRecyclerViewAdapter {

    public BaseTestAdapter(Context context, JSONArray data) {
        super(context, data);
    }

    @Override
    public int getItemLayoutResourceId(int position, JSONObject itemData) {
        if ((position%2)==0){
            return R.layout.item_recyclerview;
        }else {
            return R.layout.item_recyclerview1;
        }
    }

    @Override
    public void onItemViewBind(BaseViewHolder holder, int position, JSONObject itemData) {
        addItemChildViewClickListener(holder,holder.getView(R.id.tv_name),position);
    }
}
