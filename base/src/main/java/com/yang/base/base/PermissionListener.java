package com.yang.base.base;


import java.util.List;

/***
 * @desc baseActivity/fragment
 * @time 2020/11/20
 * @author yangguoq
 */

public interface PermissionListener {

    /**
     * 权限全部获取
     */
    void success();


    /**
     * 权限没有全部请求成功
     * @param failList
     */
    void fail(List<String> failList);
}
