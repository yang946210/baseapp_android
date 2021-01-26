package com.yang.base.fragment;

import android.view.View;
import android.widget.TextView;

import com.yang.base.base.BaseFragment;
import com.yang.base.ui.dialog.BaseLoadingDialogHelper;
import com.yang.base.util.BaseHandlerHelper;
import com.yang.base.R;

/***
 * @desc
 * @time 2020/11/10
 * @author yang
 */

public class PlugTestFragment extends BaseFragment implements View.OnClickListener {

    TextView bt_toast1,bt_toast0;

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_baseplug;
    }

    @Override
    protected void findViews() {
        bt_toast0=findViewById(R.id.bt_toast0);
        bt_toast1=findViewById(R.id.bt_toast1);
        bt_toast0.setOnClickListener(this);
        bt_toast1.setOnClickListener(this);
    }

    @Override
    protected void init() {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_toast0:
                BaseLoadingDialogHelper.showLoadingDialog(getContext());
                BaseHandlerHelper.getInstance().getHandler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getActivity().finish();
                    }
                },2000);
                break;
            case R.id.bt_toast1:
                BaseLoadingDialogHelper.dismissLoadingDialog();
                break;
        }
    }
}
