package com.yang.mvp.pai;




import com.yang.base.util.log.Logger;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * description：
 *
 */
public class RetrofitClient {

    //测试服
    private final static String URL = "https://v.juhe.cn";

    private static volatile RetrofitClient instance;

    private APIService apiService;

    private RetrofitClient() {
        //初始化一个client,不然retrofit会自己默认添加一个
        OkHttpClient client = new OkHttpClient().newBuilder()
                //设置Header
                .addInterceptor(getHeaderInterceptor())
                //设置拦截器
                .addInterceptor(getInterceptor())
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                //设置网络请求的Url地址
                .baseUrl(URL)
                //设置数据解析器
                .addConverterFactory(GsonConverterFactory.create())
                //设置网络请求适配器，使其支持RxJava与RxAndroid
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        //创建—— 网络请求接口—— 实例
        apiService = retrofit.create(APIService.class);
    }

    public static RetrofitClient getInstance() {
        if (instance == null) {
            synchronized (RetrofitClient.class) {
                if (instance == null) {
                    instance = new RetrofitClient();
                }
            }
        }
        return instance;
    }

    /**
     * 添加Header
     *
     * @return
     */
    private Interceptor getHeaderInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request.Builder requestBuilder = chain.request().newBuilder();
                requestBuilder.addHeader("language","ZH");
                return chain.proceed(requestBuilder.build());
            }
        };
    }
    /**
     * 添加报文Log打印
     * @return
     */
    private Interceptor getInterceptor() {
        Interceptor interceptor=new Interceptor() {
            @NotNull
            @Override
            public Response intercept(@NotNull Chain chain) throws IOException {
                Request request = chain.request();
                RequestBody requestBody = request.body();
                boolean hasRequestBody=requestBody!=null;

                StringBuilder message = new StringBuilder("intercept time:" + System.currentTimeMillis() +
                        "\nRequest:" + request.method() + "\nurl:" + request.url());

                if (hasRequestBody) {
                    message.append("\nContent-length:").append(requestBody.contentLength());
                }
                if (hasRequestBody&&requestBody.contentType() != null) {
                    message.append("\nContent-Type: ").append(requestBody.contentType());
                }

                Headers headers = request.headers();
                for (int i = 0, count = headers.size(); i < count; i++) {
                    String name = headers.name(i);
                    if (!"Content-Type".equalsIgnoreCase(name) && !"Content-Length".equalsIgnoreCase(name)) {
                        message.append("\n").append(name).append(":").append(headers.value(i));
                    }
                }
                if(hasRequestBody){
                    Buffer buffer = new Buffer();
                    requestBody.writeTo(buffer);
                    message.append(buffer.readString(StandardCharsets.UTF_8));
                }
                Response response;
                try {
                    response = chain.proceed(request);
                } catch (Exception e) {
                    Logger.e(message+"\nResponse failed: " + e);
                    throw e;
                }

                message.append("\nResponse code:").append(response.code()).append("message:").append(response.message().isEmpty() ? "" : ' ' + response.message());

                ResponseBody responseBody = response.body();
                BufferedSource source = responseBody.source();
                source.request(Long.MAX_VALUE); // request the entire body.
                Buffer responseBuffer = source.getBuffer();
                String bodyClone = responseBuffer.clone().readString(StandardCharsets.UTF_8);
                message.append("\ndata:").append(bodyClone);
                Logger.i(message.toString());
                return response;
            }
        };

        return interceptor;
    }

    /**
     * @return
     */
    public APIService getApi() {
        return apiService;
    }

}
