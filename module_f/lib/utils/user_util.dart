import 'dart:convert';

import 'package:hive/hive.dart';
import 'package:model_flutter/global/constant.dart';

import '../router/router_utils.dart';
import '../router/routers.dart';

///用户数据操作类
class UserUtil {

  static final UserUtil _singleton = UserUtil._internal();

  factory UserUtil() => _singleton;

  UserUtil._internal();

  ///userBox
  late Box<String> _userInfoBox;

  ///初始化
  Future<bool> init() async {
    _userInfoBox = await Hive.openBox(Constant.userBoxName);
    return true;
  }

  ///获取token
  String getToken() => _userInfoBox.get(Constant.token) ?? "";

  ///设置/获取用户表相关
  void setUserData(String userJsonStr,String token){
    _userInfoBox.put(Constant.userJsonStr, userJsonStr);
    _userInfoBox.put(Constant.token, token);
  }

  //清楚用户数据
  void _cleanUserData(){
    _userInfoBox.clear();
  }

  ///获
  Map<String, dynamic> getUserData() =>
      jsonDecode(_userInfoBox.get(Constant.userJsonStr) ?? "{}");

  //根据key获取user
  String getUserById(UserKey key) => getUserData()[key.value] ?? "";

  ///退出登录
  void loginOut(){
    _cleanUserData();
    RouterUtils.pushNoContext(Routers.loginPage,clearStack: true);
  }


}

//{
//     "user": {
//         "commpanycode": "10029",
//         "unitName": "蜀道智慧交通集团",
//         "commpanyname": "智慧高速公司",
//         "roleId": 1,
//         "mobileno": "15228967773",
//         "departname": "网络信息部",
//         "groupName": "蜀道集团",
//         "billUserId": "15228967773",
//         "unitCode": "05031",
//         "departid": "1002909",
//         "usercode": "U510100012114",
//         "communistUserCode": "15228967773",
//         "dataOrg": "10029",
//         "groupCode": "00001",
//         "status": "1",
//         "username": "杨国强"
//     },
//     "token": "3bd58432b5a34f2f90eee759764c140b"
// }
enum UserKey {
  commpanycode,
  unitName,
  commpanyname,
  roleId,
  mobileno,
  departname,
  groupName,
  billUserId,
  unitCode,
  departid,
  usercode,
  communistUserCode,
  dataOrg,
  groupCode,
  status,
  username
}

extension MethodExtension on UserKey {
  String get value => [
        'commpanycode',
        'unitName',
        'commpanyname',
        "roleId",
        'mobileno',
        'departname',
        'groupName',
        "billUserId",
        "unitCode",
        "departid",
        "usercode",
        "communistUserCode",
        "dataOrg",
        "groupCode",
        "status",
        "username"
      ][index];
}
