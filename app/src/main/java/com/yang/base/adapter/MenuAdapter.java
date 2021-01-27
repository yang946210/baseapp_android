package com.yang.base.adapter;

import android.content.Context;
import android.widget.TextView;

import com.yang.base.R;
import com.yang.base.bean.MenuBean;


/***
 * @desc 菜单适配器
 * @author yangguoq
 */

public class MenuAdapter extends BaseObjectRecyclerViewAdapter<MenuBean> {


    public MenuAdapter(Context context) {
        super(context);
    }

    @Override
    public int getItemLayoutResourceId(int position, MenuBean itemData) {
        return R.layout.item_recyclerview;
    }

    @Override
    public void onItemViewBind(BaseViewHolder holder, int position, MenuBean itemData) {
        TextView tv_name= holder.getView(R.id.tv_name);
        if (position%2==0){
            tv_name.setBackgroundColor(context.getResources().getColor(R.color.color_97B2EE));
        }else {
            tv_name.setBackgroundColor(context.getResources().getColor(R.color.color_BECCE0));
        }
        tv_name.setText(itemData.getName());
    }
}
