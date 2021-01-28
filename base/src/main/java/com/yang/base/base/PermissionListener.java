package com.yang.base.base;


import java.util.List;

/***
 * @desc baseActivity/fragment
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
