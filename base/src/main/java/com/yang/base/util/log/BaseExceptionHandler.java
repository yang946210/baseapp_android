package com.yang.base.util.log;

import androidx.annotation.NonNull;

import com.yang.base.BaseSdk;
import com.yang.base.util.BaseCommHelper;
import com.yang.base.util.BaseTimeHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Date;

/***
 * @desc 全局异常处理
 * @author yangguoq
 */

public class BaseExceptionHandler implements Thread.UncaughtExceptionHandler {


    @Override
    public void uncaughtException(@NonNull Thread t, @NonNull Throwable e) {
        saveCrashInfoToFile(e);
    }

    /**
     * 保存错误信息到文件中
     * @param ex
     */
    private void saveCrashInfoToFile(Throwable ex) {
        FileOutputStream fileOutputStream=null;
        PrintWriter printWriter=null;
        try {
            Writer writer = new StringWriter();
            printWriter = new PrintWriter(writer);
            printWriter.append("Debug:"+ BaseSdk.getInstance().isDebug()+"\n");
            printWriter.append("AppName:"+ BaseCommHelper.getAppName(BaseSdk.getInstance().getContext())+"\n");
            printWriter.append("AppVersion:"+BaseCommHelper.getAppVersion(BaseSdk.getInstance().getContext())+"\n");
            printWriter.append("ProcessName:"+BaseCommHelper.getProcessName(0)+"\n");
            printWriter.append("Date:"+ BaseTimeHelper.getTime(new Date(System.currentTimeMillis()),"yyyy-MM-dd HH:mm:ss")+"\n");
            ex.printStackTrace(printWriter);
            Throwable exCause = ex.getCause();
            while (exCause != null) {
                exCause.printStackTrace(printWriter);
                exCause =exCause.getCause();
            }
            //文件名称
            String fileName = "crash-" + BaseTimeHelper.getTime(new Date(System.currentTimeMillis()),"yyyy-MM-dd")+ ".log";
            //文件存储位置
            File fl = BaseSdk.getInstance().getContext().getExternalFilesDir("log");

            fileOutputStream = new FileOutputStream(new File(fl,fileName));
            fileOutputStream.write(writer.toString().getBytes());
            fileOutputStream.close();
        } catch (Exception e) {}
        finally {
            BaseCommHelper.closeStream(fileOutputStream);
            BaseCommHelper.closeStream(printWriter);
        }
    }

    /***
     * 获取异常信息
     * @param throwable 异常
     * @return 异常信息
     */
    public static String getThrowableMessage(Throwable throwable) {
        PrintWriter pw = null;
        try {
            Writer writer = new StringWriter();
            pw = new PrintWriter(writer);
            throwable.printStackTrace(pw);
            return writer.toString();
        } catch (Exception e) {
            return "";
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
    }
}
