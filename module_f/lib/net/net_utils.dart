import 'dart:convert';

import 'package:common_utils/common_utils.dart';
import 'package:dio/dio.dart';
import 'package:dio_cookie_manager/dio_cookie_manager.dart';
import 'package:cookie_jar/cookie_jar.dart';
import '../global/config.dart';
import '../utils/user_util.dart';
import 'intercept.dart';
import 'net_result.dart';

///后端定义成功标识
///成功
const String CODE_SUCCESS = "100000";
///token失效
const String CODE_TOKEN_LOST = "101404";

typedef Success = void Function(NetResult data);
typedef Fail = void Function(String code, String msg);

//单例
class NetUtil {
  static final NetUtil instance = NetUtil._();

  factory NetUtil() => instance;

  late Dio _dio;

  Dio get dio => _dio;

  NetUtil._() {
    //初始化
    _dio = Dio(BaseOptions(
      connectTimeout: const Duration(seconds: 15),
      receiveTimeout: const Duration(seconds: 15),
      sendTimeout: const Duration(seconds: 10),
      responseType: ResponseType.bytes,
      contentType: 'application/json;charset=UTF-8',
      baseUrl:Config.urlBase,
    ));
    //添加拦截器
    _dio.interceptors.addAll(
        [RequestInterceptor(), ResponseInterceptor(), MyLoginInterceptor()]);
    _dio.interceptors.add(CookieManager(CookieJar()));
  }


  //调用请求方法
  Future<dynamic> request(
    Method method,
    String url, {
    Success? onSuccess,
    Fail? onError,
    Object? params,
    Map<String, dynamic>? queryParameters,
    CancelToken? cancelToken,
    Options? options,
  }) {
    return _request(
      method.value,
      url,
      data: params,
      queryParameters: queryParameters,
      options: options,
      cancelToken: cancelToken,
    ).then<void>((NetResult result) {
      if (result.code == CODE_SUCCESS && result.success) {
        //成功
        onSuccess?.call(result);
      } else if(result.code==CODE_TOKEN_LOST){
        //token失效
        UserUtil().loginOut();
      } else {
        //其他错误
        onError?.call(result.code, result.errMsg);
      }
    }, onError: (dynamic e) {
      onError?.call("-10086", e.toString());
    });
  }

  //同步请求
  Future<NetResult> requestSync(
    Method method,
    String url, {
    Object? params,
    Map<String, dynamic>? queryParameters,
    CancelToken? cancelToken,
    Options? options,
  }) {
    return _request(
      method.value,
      url,
      data: params,
      queryParameters: queryParameters,
      options: options,
      cancelToken: cancelToken,
    );
  }

  //统一请求，异常处理
  Future<NetResult> _request(
    String method,
    String url, {
    Object? data,
    Map<String, dynamic>? queryParameters,
    CancelToken? cancelToken,
    Options? options,
  }) async {
    final Response response = await _dio.request(
      url,
      data: data??{},
      queryParameters: queryParameters??{},
      options: _checkOptions(method, options),
      cancelToken: cancelToken,
    );
    try {
      if ([200, 401, 422, 429].contains(response.statusCode)) {
        //网络请求完成之后获取正常的Json-Map
        final Map<String, dynamic> jsonMap =
            json.decode(response.data) as Map<String, dynamic>;
        String code = jsonMap['code'] ?? "-1";
        bool success = jsonMap['success'] ?? false;
        String msg = jsonMap['msg']??"";
        dynamic result=jsonMap['result'];
        dynamic body=jsonMap['body'];
        return NetResult(success, code, result??body??"",errMsg: msg);
      } else {
        return NetResult(false, "${response.statusCode}","",errMsg: "${response.statusMessage}");
      }
    } on DioException catch (e) {
      LogUtil.e("Http -DioException：$e  错误信息:${e.error.toString()}");
      if (e.response != null) {
        LogUtil.d("网络请求错误，data：${e.response?.data}");
        return NetResult(false, "${e.response?.statusCode}", "",errMsg:"错误码：${e.response?.statusCode} 错误信息：${e.response?.statusMessage}");
      } else if (e.type == DioExceptionType.connectionTimeout ||
          e.type == DioExceptionType.sendTimeout ||
          e.type == DioExceptionType.receiveTimeout) {
        return NetResult(false, "${e.response?.statusCode}",
            "",errMsg:"网络连接超时，请稍后再试 :${e.response?.statusMessage}");
      } else if (e.type == DioExceptionType.cancel) {
        return NetResult(false, "${e.response?.statusCode}",
            "",errMsg:"网络请求已取消 :${e.response?.statusMessage}");
      } else if (e.type == DioExceptionType.badCertificate) {
        return NetResult(false, "${e.response?.statusCode}",
            "",errMsg:"网络连接证书无效 :${e.response?.statusMessage}");
      } else if (e.type == DioExceptionType.badResponse) {
        return NetResult(false, "${e.response?.statusCode}",
            "",errMsg:"网络响应错误，请稍后再试 :${e.response?.statusMessage}");
      } else if (e.type == DioExceptionType.connectionError) {
        return NetResult(false, "${e.response?.statusCode}",
            "",errMsg:"网络连接错误，请检查网络连接 :${e.response?.statusMessage}");
      } else if (e.type == DioExceptionType.unknown) {
        return NetResult(false, "${e.response?.statusCode}",
            "",errMsg:"未知错误:${e.toString()} :${e.response?.statusMessage}");
      } else {
        return NetResult(false, "${e.response?.statusCode}", e.toString());
      }
    } on Exception catch (e) {
      LogUtil.e("Http -Exception：$e  错误信息::${e.toString()}");
      return NetResult(false, "-10086", e.toString());
    }
  }
}

///请求类型
enum Method { get, post, put, patch, delete, head }

///枚举转string
extension MethodExtension on Method {
  String get value => ['GET', 'POST', 'PUT', 'PATCH', 'DELETE', 'HEAD'][index];
}

///string转枚举
extension StringExtension on String {
  Method toMethod() {
    switch (toLowerCase()) {
      case 'get'||'GET':
        return Method.get;
      case 'post'||'POST':
        return Method.post;
      case 'put'||'PUT':
        return Method.put;
      case 'patch'||'PATCH':
        return Method.patch;
      case 'delete'||'DELETE':
        return Method.delete;
      case 'head'||'HEAD':
        return Method.head;
      default:
        return Method.get;
    }
  }
}

///配置
Options _checkOptions(String method, Options? options) {
  options ??= Options();
  options.method = method;
  return options;
}


