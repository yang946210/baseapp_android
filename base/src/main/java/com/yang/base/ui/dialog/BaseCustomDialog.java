package com.yang.base.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.yang.base.R;

/***
 * @desc 一个公共的dialog展示框。
 * @time 2021/1/25
 * @author yangguoq
 */

public class BaseCustomDialog extends Dialog{

    /**
     * 标题控件
     */
    private TextView tv_title;

    /**
     * 文本控件
     */
    private TextView tv_message;

    /**
     * 确认取消按钮
     */
    private TextView tv_confirm, tv_cancel;

    /**
     * 标题内容
     */
    private String title;

    /**
     * 文本内容
     */
    private String message;

    /**
     * 确定取消文本的显示内容
     */
    private String confirm, cancel;

    /**
     * 点击监听
     */
    private OnDialogClickListener listener;

    /**
     * 布局
     */
    private View view;

    public BaseCustomDialog(Context context) {
        super(context, R.style.base_custom_dialog_style);
        setCanceledOnTouchOutside(false);
        view= LayoutInflater.from(context).inflate(R.layout.dialog_custom,null);
    }

    public BaseCustomDialog(Context context,String message,OnDialogClickListener listener) {
        this(context);
        this.listener=listener;
        this.message = message;
    }

    public BaseCustomDialog(Context context,String title,String message,OnDialogClickListener listener) {
        this(context,message,listener);
        this.title = title;
    }

    public BaseCustomDialog(Context context,String title,String message,String cancel,String confirm,OnDialogClickListener listener) {
        this(context,title,message,listener);
        this.confirm=confirm;
        this.cancel=cancel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(view);
        initView();
        init();
    }

    /**
     * 初始化界面控件
     */
    private void initView() {
        tv_confirm = findViewById(R.id.tv_confirm);
        tv_cancel =  findViewById(R.id.tv_cancel);
        tv_title = findViewById(R.id.tv_title);
        tv_message =  findViewById(R.id.tv_message);
        tv_message.setText(message);
        tv_title.setText(title);
        tv_title.setVisibility(TextUtils.isEmpty(title)?View.GONE:View.VISIBLE);
        if (!TextUtils.isEmpty(confirm)){
            tv_confirm.setText(confirm);
        }
        if (!TextUtils.isEmpty(cancel)){
            tv_message.setText(cancel);
        }
    }

    private void init(){
        tv_confirm.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (listener!=null){
                    listener.onDialogClick(true,v);
                }
                dismiss();
            }
        });
        tv_cancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (listener!=null){
                    listener.onDialogClick(false,v);
                }
                dismiss();
            }
        });
    }

    public BaseCustomDialog setOnDialogClickListener(OnDialogClickListener listener){
        this.listener=listener;
        return  this;
    }

    /**
     * 设置title
     * @param title  title
     * @return
     */
    public BaseCustomDialog setTitle(String title) {
        this.title = title;
        return this;
    }


    /**
     * 设置message
     * @param message
     */
    public BaseCustomDialog setMessage(String message) {
        this.message = message;
        return this;
    }

    /**
     * 设置确认按钮文字
     * @param confirm
     */
    public BaseCustomDialog setConfirm(String confirm) {
        this.confirm = confirm;
        return this;
    }


    /**
     * 设置取消按钮文字
     * @param cancel
     */
    public BaseCustomDialog setCancel(String cancel) {
        this.cancel = cancel;
        return this;
    }

    /**
     * 设置取消按钮文字
     * @param clickText
     */
    public BaseCustomDialog setSingleClick(String clickText) {
        this.cancel = clickText;
        view.findViewById(R.id.tv_confirm).setVisibility(View.GONE);
        return this;
    }

    /**
     * 获取title控件
     * @return
     */
    public TextView getTitleView(){
        return tv_title;
    }

    /**
     * 获取message控件
     * @return
     */
    public TextView getMessageView(){
        return tv_message;
    }

    /**
     * 获取确认按钮控件
     * @return
     */
    public TextView getConfirmView(){
        return tv_confirm;
    }

    /**
     * 获取取消按钮控件
     * @return
     */
    public TextView getCancelView(){
        return  tv_cancel;
    }
}

