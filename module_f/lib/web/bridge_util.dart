import 'dart:convert';
import 'dart:typed_data';
import 'package:model_flutter/net/net_utils.dart';
import 'package:model_flutter/utils/aes_util.dart';
import 'package:model_flutter/utils/user_util.dart';
import 'package:model_flutter/web/bridge_data.dart';

import '../net/net_result.dart';


const String callSuccess="10000";
const String callFail="-1";

///方法名映射表
final Map<String, Function> methodMap = {
  "getUserInfo": getUserInfo,
  "AESEncrypt": (params) => aesEncrypt(params),
  "AESDecrypt": (params) => aesDecrypt(params),
  "requestHttp":(params) => requestHttp(params),
};

///查询方法
dynamic getMethodByJsName(Js2FlutterModel bridge) {
  return  methodMap[bridge.methodName]?.call(bridge.params) ??
      Flutter2JsModel(success: false, result: "未找到${bridge.methodName}方法", code: callFail);
}

///获取用户信息
Flutter2JsModel getUserInfo(Map<String, dynamic> params) {
  return Flutter2JsModel(success: true, result: jsonEncode(UserUtil().getUserData()), code: callSuccess);
}

///AES加密
Flutter2JsModel aesEncrypt(Map<String, dynamic> params) {
  Uint8List encryptData = AESUtil.encrypt(AES_ENCODE_PWD, params["data"]);
  return Flutter2JsModel(success: true, result: encryptData, code: callSuccess);
}

///AES解密
Flutter2JsModel aesDecrypt(Map<String, dynamic> params) {
  Uint8List data = Uint8List.fromList(params["data"].cast<int>());
  Uint8List encryptData = AESUtil.decrypt(AES_ENCODE_PWD, data);
  return Flutter2JsModel(success: true, result: utf8.decode(encryptData), code: callSuccess);
}

///http请求，暂时解决跨域问题
  Future<Flutter2JsModel> requestHttp(Map<String, dynamic> params) async {
  var method=params["method"]??"post".toMethod();
  var url=params["url"];
  NetResult result= await NetUtil().requestSync(method, url,params: Map()={});
  return Flutter2JsModel(success: result.success, result:result.result, code: result.code);
}
