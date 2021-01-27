package com.yang.base.bean;

import androidx.fragment.app.Fragment;

/***
 * @desc menu实体
 * @time 2021/1/27
 * @author yangguoq
 */

public class MenuBean {

    /**
     * 名字
     */
    private String name;

    /**
     * fragment
     */
    private Fragment fragment;


    public MenuBean(String name,Fragment fragment){
        this.name=name;
        this.fragment=fragment;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
