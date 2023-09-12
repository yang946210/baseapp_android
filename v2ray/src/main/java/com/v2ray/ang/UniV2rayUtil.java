package com.v2ray.ang;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.VpnService;
import android.text.TextUtils;
import android.util.Log;
import com.google.gson.Gson;
import com.tencent.mmkv.MMKV;
import com.v2ray.ang.dto.ServerConfig;
import com.v2ray.ang.dto.ServersCache;
import com.v2ray.ang.dto.SubscriptionItem;
import com.v2ray.ang.service.V2RayServiceManager;
import com.v2ray.ang.util.AngConfigManager;
import com.v2ray.ang.util.MessageUtil;
import com.v2ray.ang.util.MmkvManager;
import com.v2ray.ang.util.Utils;
import java.util.ArrayList;
import java.util.List;

/**
 * vpn工具类
 */
public class UniV2rayUtil {

    private final static String TAG= "UniV2rayUtil";
    private final static String SERVICE_CACHE= "serviceCache";

    public static int REQUEST_START=1002;

    /**
     * 服务配置持久化
     */
    private MMKV subStorage,mainStorage,settingsStorage;

    /**
     * uuid
     */
    private String uuid;

    /**
     * 节点索引集合
     */
    private List<ServersCache> serversCache=new ArrayList<>();
    /**
     * 节点
     */
    private List<String> serverList = new ArrayList<>();

    private UniV2rayUtil(){}

    private static class UniV2rayUtilHolder{
        private static final UniV2rayUtil instance=new UniV2rayUtil();
    }
    public static UniV2rayUtil getInstance(){
        return UniV2rayUtilHolder.instance;
    }

    /**
     * 初始化，使用之前必须且调用一次
     * @param context
     */
    public ResultBean v2RayInit(Context context) {
        try {
            MMKV.initialize(context);
            subStorage= MMKV.mmkvWithID(MmkvManager.ID_SUB, MMKV.MULTI_PROCESS_MODE);
            mainStorage= MMKV.mmkvWithID(MmkvManager.ID_MAIN, MMKV.MULTI_PROCESS_MODE);
            settingsStorage= MMKV.mmkvWithID(MmkvManager.ID_SETTING, MMKV.MULTI_PROCESS_MODE);
        }catch (Exception e){
            e.printStackTrace();
            return new ResultBean("-1","v2ray ini fail"+e.getMessage());
        }
        return new ResultBean("200","v2ray ini success");
    }

    /**
     * 配置服务地址
     * @param url
     * @param name
     * @param context
     */
    public void setService(final String url, String name, final Context context, final OnV2RayListener listener){
        try {
            SubscriptionItem subItemBean=new SubscriptionItem();
            subItemBean.setRemarks(name);
            subItemBean.setUrl(url);
            subItemBean.setEnabled(true);
            uuid= Utils.INSTANCE.getUuid();
            subStorage.encode(uuid,new Gson().toJson(subItemBean));

            BaseThreadHelper.getInstance().run(new Runnable() {
                @Override
                public void run() {
                    try {
                        final String configText= Utils.INSTANCE.getUrlContentWithCustomUserAgent(url);
                        BaseThreadHelper.getInstance().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                importBatchConfig(configText, uuid,context,listener);
                            }
                        });
                    }catch (Exception e){
                        e.printStackTrace();
                        listener.callback("-1","r2var setService fail:"+e.getMessage());
                    }
                }
            });
        }catch (Exception e){
            e.printStackTrace();
            listener.callback("-1","r2var setService fail:"+e.getMessage());
        }

    }

    /**
     * 开始链接
     * @param context
     */
    public void Start(Context context){
        String mode=settingsStorage.decodeString(AppConfig.PREF_MODE);
        if (TextUtils.isEmpty(mode)||TextUtils.equals("VPN",mode)) {
            Intent intent = VpnService.prepare(context);
            if (intent == null) {
                startV2Ray(context);
            } else {
                ((Activity)context).startActivityForResult(intent,REQUEST_START);
            }
        } else {
            startV2Ray(context);
        }
    }

    public void stop(Context context){
        Utils.INSTANCE.stopVService(context);
    }

    public void startV2Ray(Context context){
        if (TextUtils.isEmpty(mainStorage.decodeString(MmkvManager.KEY_SELECTED_SERVER))) {
            Log.e(TAG, " StartV2ray error: KEY_SELECTED_SERVER is empty" );
            return;
        }
        V2RayServiceManager.INSTANCE.startV2Ray(context);
    }


    public void testCurrentServerRealPing(Context context) {
        MessageUtil.INSTANCE.sendMsg2Service(context, AppConfig.MSG_MEASURE_DELAY, "");
    }


    private void importBatchConfig(String server, String subid,Context context,OnV2RayListener listener) {
        boolean append = TextUtils.isEmpty(subid);
        int  count = AngConfigManager.INSTANCE.importBatchConfig(server, subid, append);
        if (count <= 0) {
            count = AngConfigManager.INSTANCE.importBatchConfig(Utils.INSTANCE.decode(server), subid, append);
        }
        if (count > 0) {
            Log.d(AppConfig.ANG_PACKAGE, "v2ray importBatchConfig: success");
            serverList= MmkvManager.INSTANCE.decodeServerList();
            for (String guid:serverList){
                ServerConfig config = MmkvManager.INSTANCE.decodeServerConfig(guid);
                if (config!=null){
                    serversCache.add(new ServersCache(guid,config));
                }
            }
            Gson gson = new Gson();
            subStorage.encode(SERVICE_CACHE,gson.toJson(serversCache));
            listener.callback("200","v2ray importBatchConfig: fail count"+count);

            mainStorage.encode(MmkvManager.KEY_SELECTED_SERVER, serverList.get(0));
        } else {
            Log.d(AppConfig.ANG_PACKAGE, "v2ray importBatchConfig: fail");
            listener.callback("-1","v2ray importBatchConfig: fail count=0");
        }
    }

}
