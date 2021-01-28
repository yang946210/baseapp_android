package com.yang.base.adapter;

import android.content.Context;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.yang.base.R;

/***
 * @desc 测试JsonAdapter
 * @author yangguoq
 */

public class RecyclerJsonAdapter extends BaseJSONRecyclerViewAdapter {

    public RecyclerJsonAdapter(Context context) {
        super(context);
    }

    @Override
    public int getItemLayoutResourceId(int position, JSONObject itemData) {
        return R.layout.item_recycler_json;
    }

    @Override
    public void onItemViewBind(BaseViewHolder holder, int position, JSONObject itemData) {
        TextView tv_name=(TextView)holder.getView(R.id.tv_name);
        tv_name.setText(itemData.getString("name"));
        addItemChildViewClickListener(holder,tv_name,position);
    }
}
