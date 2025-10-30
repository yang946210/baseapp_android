import 'dart:convert';

import 'package:common_utils/common_utils.dart';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:hive/hive.dart';
import 'package:model_flutter/pages/login/login_page.dart';
import 'package:model_flutter/pages/main/main_page.dart';
import 'package:model_flutter/router/router_utils.dart';
import 'package:model_flutter/router/routers.dart';
import 'package:model_flutter/utils/user_util.dart';
import 'package:path_provider/path_provider.dart';
import 'package:provider/provider.dart';
import 'global/config.dart';
import 'global/global_model.dart';
import 'net/net_api.dart';
import 'net/net_utils.dart';

void main() async {
  ///统计初始化的时间
  Stopwatch stopwatch = Stopwatch();
  stopwatch.start();

  ///初始化路由
  Routers.init();

  ///初始化log
  LogUtil.init(isDebug: Config.isDebug, maxLen: 1024);

  ///Hive初始化（数据持久化） Flutter 插件被初始化之前调用
  WidgetsFlutterBinding.ensureInitialized();
  final appDocumentDir = await getApplicationDocumentsDirectory();
  Hive.init(appDocumentDir.path);
  await UserUtil().init();

  ///token登录成功与否
  bool tokenLoginSuccess = false;
  if (UserUtil().getToken().isNotEmpty) {
    //token登录
    var data = await NetUtil().requestSync(Method.post, NetApi.loginByToken,params: {'token': UserUtil().getToken()});
    if (data.success) {
      Map<String, dynamic> resultMap = data.result;
      UserUtil().setUserData(jsonEncode(resultMap["user"]), resultMap["token"]);
      tokenLoginSuccess = true;
    }
  }

  stopwatch.stop();
  LogUtil.d("bigDataApp init time =${stopwatch.elapsedMilliseconds}  milliseconds");

  ///程序入口
  runApp(MyApp(tokenLoginSuccess));
}

class MyApp extends StatelessWidget {
  ///是否时token登录
  final bool tokenLogin;

  ///插件初始化相关
  const MyApp(this.tokenLogin, {super.key});

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return ChangeNotifierProvider(
      create: (_) => GlobalModel(),
      child: AnnotatedRegion<SystemUiOverlayStyle>(
        //全局通知栏白底灰字
        value: SystemUiOverlayStyle.light.copyWith(
          statusBarBrightness: Brightness.dark,
          statusBarIconBrightness: Brightness.dark,
          statusBarColor: Colors.white,
        ),
        child: MaterialApp(
          navigatorKey: RouterUtils.navigatorKey,
          debugShowCheckedModeBanner: false,
          title: 'Demo',
          theme: ThemeData(
            highlightColor: Colors.transparent,
            splashColor: Colors.transparent,
            colorScheme: ColorScheme.fromSeed(seedColor: Colors.redAccent),
            scaffoldBackgroundColor: const Color(0xffF2F4F7),
            useMaterial3: true,
          ),
          home: tokenLogin ? const MainPage() : const LoginPage(),
        ),
      ),
    );
  }
}
