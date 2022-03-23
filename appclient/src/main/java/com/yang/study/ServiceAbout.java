package com.yang.study;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import androidx.annotation.Nullable;
import com.yang.base.util.log.Logger;
import java.util.Timer;
import java.util.TimerTask;

/***
 * @desc
 * @time 2021/9/8
 * @author yangguoq
 */

public class ServiceAbout extends Service {

    private LocalBind bind= new LocalBind();

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.simple(" service onCreate");
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        try{
            Timer timer=new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Logger.simple(" service onStartCommand"+System.currentTimeMillis());
                }
            },0,2000);
        }catch (Throwable e){
            e.printStackTrace();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Logger.simple(" service onBind");
        return bind;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Logger.simple(" service onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Logger.simple(" service onDestroy");
        super.onDestroy();
    }

    public static class LocalBind extends Binder{

    }
}
