
import 'dart:convert';

import 'package:flutter/cupertino.dart';
import 'package:fluttertoast/fluttertoast.dart';

import '../base/provider/base_change_notifier.dart';
import '../net/net_api.dart';
import '../net/net_result.dart';
import '../net/net_utils.dart';
import '../router/router_utils.dart';
import '../router/routers.dart';
import '../utils/user_util.dart';

class IdentifyCodeModel extends BaseChangeNotifier{


  ///重新发送验证码
  Future<NetResult> sendMsg(String phone) async{
    return NetUtil().requestSync(Method.post, NetApi.sendMsg, params: Map()={"tel":phone});
  }


  ///校验验证码
  void identifyCode(BuildContext context,String phone,String code){
    NetUtil().request(Method.post, NetApi.loginByTelMsg, params: Map()={"tel":phone,"msg":code}, onSuccess: (data) {
      Map<String, dynamic> resultMap = data.result;
      UserUtil().setUserData(jsonEncode(resultMap["user"]), resultMap["token"]);
      RouterUtils.push(context, Routers.home,clearStack:true);
    }, onError: (msg, code) {
      Fluttertoast.showToast(msg: "验证失败:$msg");
    });
  }

}