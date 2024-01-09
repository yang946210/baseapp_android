package com.yang.ktbase

/**
 * Tag相关配置
 */
class TagConfig{
    companion object{
        //网络请求相关
        const val NET:String="tagNet"
    }

}

/**
 * Net相关配置
 */
class NetConfig{
   companion object{
       //请求超时或无响应
       const val NET_CONF_NO_DATA:String="executeHttp fail"
       //请求返回值为null
       const val NET_CONF_EMPTY_MSG:String="data is null"
       //错误码：请求返回为null
       const val NET_CONF_EMPTY_CODE:Int=0x100
   }
}