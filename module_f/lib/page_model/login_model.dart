import 'package:common_utils/common_utils.dart';
import 'package:flutter/cupertino.dart';
import 'package:fluttertoast/fluttertoast.dart';

import '../base/provider/base_change_notifier.dart';
import '../global/constant.dart';
import '../net/net_api.dart';
import '../net/net_utils.dart';
import '../router/router_utils.dart';
import '../router/routers.dart';

class LoginModel extends BaseChangeNotifier {
  ///输入数字
  int? number;

  ///是否已经勾选隐私
  bool isRead = true;

  ///是否满足短信发送状态
  bool canSend() {
    if (number == null) {
      return false;
    }
    return number.toString().length == 11;
  }

  void changeRead(bool value) {
    isRead = value;
    Fluttertoast.showToast(msg: "msg$value");
    notifyListeners();
  }

  ///发送验证码
  void getCode(BuildContext context) {
    if (!isRead) {
      Fluttertoast.showToast(msg: "请阅读并勾选用户协议和隐私策略");
      return;
    }
    NetUtil().request(
      Method.post,
      NetApi.sendMsg,
      params: {'tel':number},
      onError: (_, msg) => {LogUtil.d(msg)},
      onSuccess: (data) => {
        RouterUtils.push(context, "${Routers.identifyCodePage}?phone=$number")
      },
    );
  }
}
