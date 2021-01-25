package com.yang.base.fragment;

import android.content.Intent;
import android.view.View;

import com.yang.base.base.BaseFragment;
import com.yang.base.MenuActivity;
import com.yang.base.R;


/***
 * @desc
 * @time 2020/11/27
 * @author yangguoq
 */

public class SingleFragment extends BaseFragment implements View.OnClickListener {
    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_sigle;
    }

    @Override
    protected void findViews() {
        view.findViewById(R.id.tv_threadTest).setOnClickListener(this);
    }

    @Override
    protected void init() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_threadTest:
            threadTest();
            break;
        }
    }

    /**
     * 线程泄漏测试
     */
    private void threadTest(){
        startActivity(new Intent(getActivity(), MenuActivity.class));

    }



    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
