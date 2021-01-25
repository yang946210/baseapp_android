package com.yang.base.fragment;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yang.base.adapter.BaseAdapterItemChildrenClickListener;
import com.yang.base.adapter.BaseAdapterItemClickListener;
import com.yang.base.adapter.BaseViewHolder;
import com.yang.base.base.BaseFragment;
import com.yang.base.R;
import com.yang.base.adapter.BaseTestAdapter;


/***
 * @desc
 * @time 2020/11/10
 * @author yang
 */

public class BaseTestFragment extends BaseFragment implements View.OnClickListener, BaseAdapterItemClickListener<JSONObject>, BaseAdapterItemChildrenClickListener<JSONObject> {

    private RecyclerView rv_content;

    private BaseTestAdapter adapter;

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_basetest;
    }

    @Override
    protected void findViews() {
        rv_content=view.findViewById(R.id.rv_content);
        view.findViewById(R.id.bt_toast).setOnClickListener(this);
    }

    @Override
    protected void init(){
        rv_content.setLayoutManager(new LinearLayoutManager(mContext));
        JSONArray array=new JSONArray();
        for (int i = 0; i < 10; i++) {
            array.add(new JSONObject());
        }
        adapter=new BaseTestAdapter(mContext,array);
        rv_content.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
        adapter.setOnItemChildrenClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_toast:
                Intent intent1=new Intent();
                getContext().sendBroadcast(intent1);
                break;
        }
    }

    @Override
    public void onItemViewClick(BaseViewHolder holder, int position, JSONObject itemData) {
        Log.i("=====","=======item click"+position);
    }


    @Override
    public void onItemChildViewClick(BaseViewHolder holder, int position, JSONObject itemData, View clickView) {
        Log.i("=====","=======item children click"+position+"====="+clickView.getId()+"======"+holder.getView(R.id.tv_name).getId());

    }
}
