

/**
 * 网络请求接口回调
 */
interface IHttpCallback {

    /**
     * 请求成功
     * [data]请求成功数据
     */
    fun onSuccess(data:Any)


    /**
     * 请求失败
     * [error] 失败信息
     */
    fun onFail(error:Any)

}
