package com.yang.base.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.yang.base.R;
import com.yang.base.util.BaseThreadHelper;

import java.lang.ref.WeakReference;

/***
 * @desc 加载等待框
 * @author yangguoq
 */

public class BaseLoadingDialogHelper{

    /**
     * 弹框
     */
    private static WeakReference<Dialog> loadingDialog;

    /**
     * 上次弹出对应的context
     */
    private static WeakReference<Context> lastContext;

    /**
     * 展示等待框
     * @param context
     * @param msg  内容
     */
    public static void showLoadingDialog(final Context context, final String msg) {
        BaseThreadHelper.getInstance().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                BaseLoadingDialogHelper._showLoadingDialog(context, msg);
            }
        });
    }


    /**
     * 展示等待框
     * @param context
     */
    public static void showLoadingDialog(final Context context) {
        showLoadingDialog(context,null);
    }

    private static void _showLoadingDialog(Context context, String msg) {
        try {
            if (lastContext!=null&&context==lastContext.get()&&loadingDialog!=null&&loadingDialog.get()!=null&&loadingDialog.get().isShowing()){
                return;
            }
            loadingDialog=new WeakReference<>(new Dialog(context, R.style.base_loading_dialog_style));
            lastContext=new WeakReference<>(context);
            Dialog dialog=loadingDialog.get();
            View view = LayoutInflater.from(lastContext.get()).inflate(R.layout.base_dialog_loading, null);
            dialog.setContentView(view);
            dialog.show();
            TextView tv_showMessage = dialog.findViewById(R.id.tv_showMessage);
            if (msg == null) {
                tv_showMessage.setText("加载中...");
            } else {
                tv_showMessage.setText(msg);
            }
            dialog.show();
            dialog.setCancelable(false);
            Window dialogWindow = dialog.getWindow();
            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            dialogWindow.setGravity(17);
            lp.width = ViewGroup.LayoutParams.MATCH_PARENT;
            lp.height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialogWindow.setAttributes(lp);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * 关闭等待框
     */
    public static void dismissLoadingDialog() {
        BaseThreadHelper.getInstance().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                BaseLoadingDialogHelper._dismissLoadingDialog();
            }
        });
    }

    private static void _dismissLoadingDialog() {
        if (loadingDialog != null&& loadingDialog.get()!=null && loadingDialog.get().isShowing()) {
            try {
                loadingDialog.get().dismiss();
            } catch (Exception e) {
            }
        }
        loadingDialog.clear();
    }
}

