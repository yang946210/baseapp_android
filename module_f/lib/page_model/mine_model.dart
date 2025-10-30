import 'dart:typed_data';

import 'package:dio/dio.dart';
import 'package:flutter/cupertino.dart';
import 'package:fluttertoast/fluttertoast.dart';
import 'package:image_gallery_saver/image_gallery_saver.dart';
import 'package:model_flutter/net/net_utils.dart';
import 'package:package_info_plus/package_info_plus.dart';

import '../base/provider/base_change_notifier.dart';
import '../entity/app_version_entity.dart';
import '../net/net_api.dart';
import '../res/colours.dart';
import '../utils/user_util.dart';

class MineModel extends BaseChangeNotifier{

  //本机版本
  var appVersion="V0.0.0";

  //服务端最新版本
  var currVersion="";

  //服务端最新更新内容
  var currVersionContent="";

  //分享地址
  var shareImage="";

  ///获取本机版本信息
  getAppVersion () async{
    PackageInfo packageInfo = await PackageInfo.fromPlatform();
    appVersion= packageInfo.version;
    notifyListeners();
  }

  ///获取服务端最新版本信息
  getVersionInfo(){
    var param={"appId":"DSJAPP","osType":"android"};
    NetUtil().request(Method.post, NetApi.getAppVersion,params: param,onSuccess: (data){
      appVersionEntity versionEntity=appVersionEntity.fromJson(data.result);
      currVersion="${versionEntity.versionName}";
      currVersionContent=versionEntity.versionDesc??"";
      notifyListeners();
    });
  }


  ///获取分享二维码
  getShareInfo(){
    var param={};
    NetUtil().request(Method.post, NetApi.getShare,params: param,onSuccess: (data){
      shareImage=data.result["imageUrl"];
      notifyListeners();
    });
  }


  ///保存图片
  void saveImage() async{
    var dio = Dio();

    // 发起 GET 请求
    Response<List<int>> response = await dio.get<List<int>>(shareImage,
      // ResponseType.bytes 是为了告诉Dio我们期望返回一个字节流
      options: Options(responseType: ResponseType.bytes),
    );
    if (response.statusCode == 200) {
      final result = await ImageGallerySaver.saveImage(Uint8List.fromList(Uint8List.fromList(response.data!)));
      if (result['isSuccess']) {
        Fluttertoast.showToast(msg: "保存成功，请前往相册查看");
      } else {
        Fluttertoast.showToast(msg: "保存失败");
      }
    }else{
      Fluttertoast.showToast(msg: "保存失败");
    }
  }


  ///退出登录
  Future<void> showLoginOutDialog(BuildContext context) async {
    return showCupertinoDialog<void>(
      context: context,
      builder: (BuildContext dialogContext) {
        return CupertinoAlertDialog(
          title: const Text('确认退出登录吗'),
          actions: <Widget>[
            // 取消按钮
            CupertinoDialogAction(
              child: const Text('取消',style: TextStyle(color: Colours.color_0x0E0E0E)),
              onPressed: () {
                Navigator.pop(dialogContext); // 关闭对话框
              },
            ),
            // 确认按钮
            CupertinoDialogAction(
              isDefaultAction: true, // Can optionally set this as the default action (bold text)
              child: const Text('确认',style: TextStyle(color: Colours.color_0x0E0E0E),),
              onPressed: () {
                UserUtil().loginOut();
              },
            ),
          ],
        );
      },
    );
  }
}