package com.yang.baseapp.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/***
 * @desc
 * @time 2021/6/10
 * @author yangguoq
 */

public class JsonUtil {



    public static void getJson(){
        String[] other={
                "81275120", "63215787", "79910138", "87110935", "98563713",
                "30128366", "59900595", "61332103", "31831283", "95290289",
                "31006617", "65757760", "78959939", "05672790", "70911932",
                "11628180", "63088107", "61769200", "82852631", "67596257",
                "76072622", "51286290", "99503376", "83387579", "07621652",
                "33133190", "81562012", "09571979", "71386752", "73303797",
                "26560313", "50253276", "98959895", "02106377", "00318205",
                "17519800", "02883176", "77315736", "13565827", "38005986",
                "97220123", "68135160", "26755707", "59681152", "32006268",
                "29852251", "00908998", "73929669", "03968890"};
        String[] secretary={
                "38807251","57573395","98271103","12816066","00291615",
                "11936091","93578026","87237606","12088850","95860173",
                "01353035","85551998","91230005","59528061","27917526",
                "85181710","22132729","58070299","91533887","59527278",
                "02508606","10402134","73109109","67763266","16606536",
                "97197079","38775758"};

        JSONArray array=new JSONArray();
        for (int i = 0; i < other.length; i++) {
            JSONObject object=new JSONObject();
            object.put("userId",other[i]);
            object.put("info","other");
            array.add(object);
        }
        for (int i = 0; i < secretary.length; i++) {
            JSONObject object=new JSONObject();
            object.put("userId",secretary[i]);
            object.put("info","secretary");
            array.add(object);
        }

        String s=array.toJSONString();
        String s1=s;
    }

}
