package com.yang.base;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yang.base.adapter.OnBaseItemClickListener;
import com.yang.base.adapter.BaseViewHolder;
import com.yang.base.adapter.MenuAdapter;
import com.yang.base.base.BaseActivity;
import com.yang.base.bean.MenuBean;
import com.yang.base.fragment.BaseDemoFragment;
import com.yang.base.fragment.DialogDemoFragment;
import com.yang.base.fragment.RecyclerViewDemoFragment;
import com.yang.base.fragment.SdkAndAppDemoFragment;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends BaseActivity implements OnBaseItemClickListener<MenuBean> {

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
    }

    private void addDate(){
        SdkAndAppDemoFragment sdkAndAppDemoFragment;
        menuBeans.add(new MenuBean("SDK,App",sdkAndAppDemoFragment=new SdkAndAppDemoFragment()));
        menuBeans.add(new MenuBean("Activity",new BaseDemoFragment()));
        menuBeans.add(new MenuBean("recyclerView",new RecyclerViewDemoFragment()));
        menuBeans.add(new MenuBean("dialogUtil",new DialogDemoFragment()));
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
}
