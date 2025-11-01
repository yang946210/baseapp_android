import 'package:flutter/foundation.dart';

class Config{
  /// Debug和Profile为false,ture,Release=>false
  static const bool isDebug  = !kReleaseMode;

  ///基础地址
  static String get urlBase => isDebug?_urlTest:_urlRelease;

  ///生产/测试地址
  static const String _urlRelease = "";
  static const String _urlTest = "";


  ///隐私协议
  static const String urlProtocol="www.baicu.com";
  ///用户协议
  static const String urlUser="www.baidu.com";
  ///公司简介
  static  String urlIntroduction="${urlBase}app/h5/group-introduce/group.html";


}