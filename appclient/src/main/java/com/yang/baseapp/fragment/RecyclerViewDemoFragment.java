package com.yang.baseapp.fragment;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JsonArray;
import com.alibaba.fastjson.JSONObject;
import com.yang.baseapp.R;
import com.yang.base.adapter.BaseViewHolder;
import com.yang.base.adapter.OnBaseItemChildrenClickListener;
import com.yang.base.adapter.OnBaseItemClickListener;
import com.yang.base.adapter.OnBaseItemLongClickListener;
import com.yang.baseapp.adapter.RecyclerBeanAdapter;
import com.yang.baseapp.adapter.RecyclerJsonAdapter;
import com.yang.base.base.BaseFragment;
import com.yang.baseapp.bean.RecyclerBean;
import com.yang.base.util.BaseToastHelper;


import java.util.ArrayList;
import java.util.List;

/***
 * @desc recyclerDemo
 * @author yangguoq
 */

public class RecyclerViewDemoFragment extends BaseFragment {

    private RecyclerView rv_bean,rv_json;

    private RecyclerBeanAdapter beanAdapter;

    private RecyclerJsonAdapter jsonAdapter;

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_recycler_demo;
    }

    @Override
    protected void findViews() {
        rv_bean=findViewById(R.id.rv_bean);
        rv_json=findViewById(R.id.rv_json);
    }

    @Override
    protected void init() {
        beanAdapter=new RecyclerBeanAdapter(mContext);
        jsonAdapter=new RecyclerJsonAdapter(mContext);
        rv_bean.setLayoutManager(new LinearLayoutManager(mContext));
        rv_json.setLayoutManager(new LinearLayoutManager(mContext));
        rv_bean.setAdapter(beanAdapter);
        rv_json.setAdapter(jsonAdapter);
        beanAdapter.setOnItemChildrenClickListener(new OnBaseItemChildrenClickListener<RecyclerBean>() {
            @Override
            public void onItemChildViewClick(BaseViewHolder holder, int position, RecyclerBean itemData, View clickView) {
                BaseToastHelper.showToast("点击了Children:"+itemData.getName());
            }
        });

        beanAdapter.setOnItemClickListener(new OnBaseItemClickListener<RecyclerBean>() {
            @Override
            public void onItemViewClick(BaseViewHolder holder, int position, RecyclerBean itemData) {
                BaseToastHelper.showToast("点击了Item:"+itemData.getName());
            }
        });

        beanAdapter.setOnItemLongClickListener(new OnBaseItemLongClickListener<RecyclerBean>() {
            @Override
            public void onItemViewLongClick(BaseViewHolder holder, int position, RecyclerBean itemData) {
                BaseToastHelper.showToast("长按了Item:"+itemData.getName());
            }
        });

        jsonAdapter.setOnItemChildrenClickListener(new OnBaseItemChildrenClickListener<JSONObject>() {
            @Override
            public void onItemChildViewClick(BaseViewHolder holder, int position, JSONObject itemData, View clickView) {
                BaseToastHelper.showToast("点击了Children:"+itemData.getString("name"));
            }
        });

        jsonAdapter.setOnItemClickListener(new OnBaseItemClickListener<JSONObject>() {
            @Override
            public void onItemViewClick(BaseViewHolder holder, int position, JSONObject itemData) {
                BaseToastHelper.showToast("点击了item:"+itemData.getString("name"));
            }
        });

        jsonAdapter.setOnItemLongClickListener(new OnBaseItemLongClickListener<JSONObject>() {
            @Override
            public void onItemViewLongClick(BaseViewHolder holder, int position, JSONObject itemData) {
                BaseToastHelper.showToast("长按了item:"+itemData.getString("name"));
            }
        });
        addDate();
    }

    private void addDate(){
        JsonArray array=new JsonArray();
        List<RecyclerBean> list=new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            JSONObject object=new JSONObject();
            object.put("name","nameJson"+i);
            RecyclerBean bean=new RecyclerBean();
            bean.setName("nameBean"+i);
            bean.setInfo("info"+i);
            array.add(object);
            list.add(bean);
        }
        beanAdapter.setData(list);
        jsonAdapter.setData(array);
    }
}
