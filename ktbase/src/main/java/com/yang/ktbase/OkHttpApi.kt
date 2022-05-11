import com.yang.base.HttpApi
import com.yang.base.IHttpCallback
import okhttp3.*
import java.io.File
import java.io.IOException
import java.util.concurrent.TimeUnit


/***
 * @desc 网络请求实现
 * @time 2022/5/11
 * @author yangguoq
 */

class OkHttpApi: HttpApi {

    companion object{
        private const val  TAG="okHttpApi"  //tag
    }

    private val baseUrl="http://api.qingyunke.com/"  //baseurl

    private val client:OkHttpClient=OkHttpClient().newBuilder()
        .callTimeout(10,TimeUnit.SECONDS)  //完整请求超时时间
        .connectTimeout(10,TimeUnit.SECONDS)  //与服务器建立连接时长
        .readTimeout(10,TimeUnit.SECONDS)  //读取服务器返回数据时长
        .writeTimeout(10,TimeUnit.SECONDS)  //像服务器写入数据时长
        .retryOnConnectionFailure(true)
        .followRedirects(false)
        .cache(Cache(File("sdcard/cache","okhttp"),1024))
        .build()

    override fun get(params: Map<String, Any>, path: String, callback: IHttpCallback) {
        val url="$baseUrl$path"
        val body =  FormBody.Builder()

        params.forEach{
            body.add(it.key,it.value.toString())
        }

        val request: Request=Request.Builder()
            .get()
            .post(body.build())
            .url(url)
            .build()


        client.newCall(request).enqueue(object:Callback{
            override fun onFailure(call: Call, e: IOException) {
                callback.onFail(e.message.toString())
            }

            override fun onResponse(call: Call, response: Response) {
                callback.onSuccess(response)
            }
        })
    }

    override fun post(params: Map<String, Any>, path: String, callback: IHttpCallback) {
        TODO("Not yet implemented")
    }
}


