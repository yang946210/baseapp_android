
import 'dart:convert';
import 'dart:typed_data';

import 'package:dio/dio.dart';

import '../global/config.dart';
import '../utils/aes_util.dart';
import '../utils/user_util.dart';


///请求数据统一处理
class RequestInterceptor extends Interceptor {
  @override
  void onRequest(RequestOptions options, RequestInterceptorHandler handler) {

    //添加统一header

    //添加cookie
    String cookies = "token=${UserUtil().getToken()}; appVersion=2.2.10; deviceModel=MI8; deviceId=a82aab67c82fda12; osType=2; osVersion=0; testFlag=1";
    options.headers['Cookie'] = cookies;

    //添加统一body
    var data= {...options.data, "userCode": "223"};

    //请求body加密
    options.data= AESUtil.encrypt(AES_ENCODE_PWD, json.encode(data));
    super.onRequest(options, handler);
  }
}




///返回数据统一处理
class ResponseInterceptor extends Interceptor{

  @override
  void onResponse(Response<dynamic> response, ResponseInterceptorHandler handler) {
    final Response<dynamic> r = decryptData(response);
    super.onResponse(r, handler);
  }

  ///返回数据解密
  Response<dynamic> decryptData(Response<dynamic> response) {

    //响应解密
    if(response.statusCode==200){
      try{
        Uint8List byte=response.data;
        var decodeByte=AESUtil.decrypt(AES_DECODE_PWD, byte);
        String content = utf8.decode(decodeByte);
        response.data=content;
      }
      catch(e){
        response.data="数据解析失败：$e";
      }
    }
    return response;
  }
}



///login统一处理
class MyLoginInterceptor extends Interceptor{
  MyLoginInterceptor({
    this.request = true,
    this.requestHeader = true,
    this.requestBody = true,
    this.responseHeader = true,
    this.responseBody = true,
    this.error = true,
    this.logPrint = _debugPrint,
  });

  /// Print request [Options]
  bool request;

  /// Print request header [Options.headers]
  bool requestHeader;

  /// Print request data [Options.data]
  bool requestBody;

  /// Print [Response.data]
  bool responseBody;

  /// Print [Response.headers]
  bool responseHeader;

  /// Print error message
  bool error;

  void Function(Object object) logPrint;

  @override
  void onRequest(
      RequestOptions options,
      RequestInterceptorHandler handler,
      ) async {
    logPrint('*** Request ***');
    _printKV('uri', options.uri);

    if (request) {
      _printKV('method', options.method);
      _printKV('responseType', options.responseType.toString());
      _printKV('followRedirects', options.followRedirects);
      _printKV('persistentConnection', options.persistentConnection);
      _printKV('connectTimeout', options.connectTimeout);
      _printKV('sendTimeout', options.sendTimeout);
      _printKV('receiveTimeout', options.receiveTimeout);
      _printKV(
        'receiveDataWhenStatusError',
        options.receiveDataWhenStatusError,
      );
      _printKV('extra', options.extra);
    }
    if (requestHeader) {
      logPrint('headers:');
      options.headers.forEach((key, v) => _printKV(' $key', v));
    }
    if (requestBody) {
      logPrint('data:');
      _printAll(options.data);
      if(Config.isDebug){
        logPrint('data decode:');
        var utf8Data =AESUtil.decrypt(AES_ENCODE_PWD, options.data);
        _printAll(utf8.decode(utf8Data));
      }
      logPrint('queryParameters:');
      _printAll(options.queryParameters);
    }
    logPrint('');

    handler.next(options);
  }

  @override
  void onResponse(Response response, ResponseInterceptorHandler handler) async {
    logPrint('*** Response ***');
    _printResponse(response);
    handler.next(response);
  }

  @override
  void onError(DioException err, ErrorInterceptorHandler handler) async {
    if (error) {
      logPrint('*** DioException ***:');
      logPrint('uri: ${err.requestOptions.uri}');
      logPrint('$err');
      if (err.response != null) {
        _printResponse(err.response!);
      }
      logPrint('');
    }

    handler.next(err);
  }

  void _printResponse(Response response) {
    _printKV('uri', response.requestOptions.uri);
    if (responseHeader) {
      _printKV('statusCode', response.statusCode);
      if (response.isRedirect == true) {
        _printKV('redirect', response.realUri);
      }

      logPrint('headers:');
      response.headers.forEach((key, v) => _printKV(' $key', v.join('\r\n\t')));
    }
    if (responseBody) {
      logPrint('Response Text:');
      _printAll(response.toString());
    }
    logPrint('');
  }

  void _printKV(String key, Object? v) {
    logPrint('$key: $v');
  }

  void _printAll(msg) {
    msg.toString().split('\n').forEach(logPrint);
  }
}

void _debugPrint(Object? object) {
  assert(() {
    print(object);
    return true;
  }());
}






