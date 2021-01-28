package com.yang.base;


import android.os.Message;
import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yang.base.adapter.OnBaseItemClickListener;
import com.yang.base.adapter.BaseViewHolder;
import com.yang.base.adapter.MenuAdapter;
import com.yang.base.base.BaseActivity;
import com.yang.base.base.BaseConfig;
import com.yang.base.bean.MenuBean;
import com.yang.base.fragment.BaseDemoFragment;
import com.yang.base.fragment.CommonUtilDemoFragment;
import com.yang.base.fragment.CryptoDemoFragment;
import com.yang.base.fragment.DialogDemoFragment;
import com.yang.base.fragment.HandlerDemoFragment;
import com.yang.base.fragment.RecyclerViewDemoFragment;
import com.yang.base.fragment.SdkAndAppDemoFragment;
import com.yang.base.util.BaseHandlerHelper;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends BaseActivity implements OnBaseItemClickListener<MenuBean>, BaseHandlerHelper.HandlerListener {

    private RecyclerView rv_menu;

    private MenuAdapter adapter;

    private List<MenuBean> menuBeans=new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_menu;
    }

    @Override
    protected void findViews() {
        rv_menu=findViewById(R.id.rv_menu);
    }

    @Override
    protected void init() {
        adapter=new MenuAdapter(this);
        rv_menu.setLayoutManager(new LinearLayoutManager(this));
        rv_menu.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
        addDate();
        BaseHandlerHelper.getInstance().addHandleListener(this);
    }

    private void addDate(){
        SdkAndAppDemoFragment sdkAndAppDemoFragment;
        menuBeans.add(new MenuBean("baseSdk",sdkAndAppDemoFragment=new SdkAndAppDemoFragment()));
        menuBeans.add(new MenuBean("Activity",new BaseDemoFragment()));
        menuBeans.add(new MenuBean("recyclerView",new RecyclerViewDemoFragment()));
        menuBeans.add(new MenuBean("handler/thread",new HandlerDemoFragment()));
        menuBeans.add(new MenuBean("dialogUtil",new DialogDemoFragment()));
        menuBeans.add(new MenuBean("commonHelper",new CommonUtilDemoFragment()));
        menuBeans.add(new MenuBean("加解密",new CryptoDemoFragment()));

        adapter.setData(menuBeans);
        replaceFragment(sdkAndAppDemoFragment);
    }




    @Override
    public void onItemViewClick(BaseViewHolder holder, int position, MenuBean itemData) {
        replaceFragment(itemData.getFragment());
    }

    /**
     * 替换fragment展示
     * @param fragment
     */
    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();   // 开启一个事务
        transaction.replace(R.id.ft_show, fragment);
        transaction.commit();
    }

    @Override
    public void handleMessage(Message msg, boolean mainThread) {
        if (msg.what== BaseConfig.message_test2){
            Log.i("====","========activity也收到了======");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BaseHandlerHelper.getInstance().removeHandleListener(this);
    }
}
