package com.yang.base.util;

import androidx.annotation.NonNull;

import com.yang.base.BaseSdk;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Date;

/***
 * @desc 全局异常处理
 * @time 2020/11/17
 * @author yangguoq
 */

public class BaseCrashHelper implements Thread.UncaughtExceptionHandler {


    private BaseCrashHelper(){}

    private static class BaseCrashHolder{
        private static final BaseCrashHelper instance=new BaseCrashHelper();
    }

    public static BaseCrashHelper getInstance(){
        return BaseCrashHolder.instance;
    }

    public void init(){
        Thread.setDefaultUncaughtExceptionHandler(getInstance());
    }


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
            printWriter.append("Debug:"+BaseSdk.getInstance().isDebug()+"\n");
            printWriter.append("AppName:"+BaseCommHelper.getAppName(BaseSdk.getInstance().getContext())+"\n");
            printWriter.append("AppVersion:"+BaseCommHelper.getAppVersion(BaseSdk.getInstance().getContext())+"\n");
            printWriter.append("ProcessName:"+BaseCommHelper.getProcessName(0)+"\n");
            printWriter.append("Date:"+BaseTimeHelper.getTime(new Date(System.currentTimeMillis()),"yyyy-MM-dd HH:mm:ss")+"\n");
            ex.printStackTrace(printWriter);
            Throwable exCause = ex.getCause();
            while (exCause != null) {
                exCause.printStackTrace(printWriter);
                exCause =exCause.getCause();
            }
            //文件名称
            String fileName = "crash" + System.currentTimeMillis() +BaseCommHelper.getRandomString(10)+ ".log";
            //文件存储位置
            File fl = BaseSdk.getInstance().getContext().getExternalFilesDir("crash");

            fileOutputStream = new FileOutputStream(new File(fl,fileName));
            fileOutputStream.write(writer.toString().getBytes());
            fileOutputStream.close();
        } catch (Exception e) {}
        finally {
            BaseCommHelper.closeStream(fileOutputStream);
            BaseCommHelper.closeStream(printWriter);
        }


    }
}
