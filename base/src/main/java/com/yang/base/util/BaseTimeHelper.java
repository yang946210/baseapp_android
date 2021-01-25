package com.yang.base.util;


import java.text.SimpleDateFormat;
import java.util.Date;

/***
 * @desc 时间格式工具
 * @time 2020/11/18
 * @author yangguoq
 */

public class BaseTimeHelper {


    /**
     * 获取对应格式的时间
     * @param date 时间
     * @param pattern  时间格式 参考<yyyy年MM月dd HH时mm分ss秒>
     * @return
     */
    public static String getTime(Date date, String pattern){
        SimpleDateFormat format=new SimpleDateFormat(pattern);
        return format.format(date);
    };




}
