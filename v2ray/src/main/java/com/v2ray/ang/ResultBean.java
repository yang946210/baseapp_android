package com.v2ray.ang;

/**
 * 回调
 */
public class ResultBean {

    public ResultBean(){}

    public ResultBean(String code,String msg){
        this.code=code;
        this.msg=msg;
    }

    public String code;
    public String msg;

}
