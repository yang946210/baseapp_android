import 'dart:async';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:model_flutter/router/routers.dart';
import 'package:model_flutter/utils/image_util.dart';

import '../../router/router_utils.dart';

class SplashPage extends StatefulWidget {
  const SplashPage({super.key});

  @override
  State<StatefulWidget> createState() => SplashState();
}

class SplashState extends State<SplashPage> {
  int _seconds = 0;
  late Timer _timer;

  @override
  void initState() {
    super.initState();
    _countDown();
  }

  @override
  Widget build(BuildContext context) {
    return AnnotatedRegion<SystemUiOverlayStyle>(value: const SystemUiOverlayStyle(
        statusBarColor: Colors.transparent
    ), child: Image.asset(
      ImageUtils.getImgPath("bg_launch"),
      width: double.infinity,
      height: double.infinity,
      fit: BoxFit.fill,
    ));
  }

  @override
  void dispose() {
    super.dispose();
    _timer.cancel();
  }

  ///倒计时
  void _countDown() {
    const oneSec = Duration(seconds: 1);
    _timer = Timer.periodic(oneSec, (Timer timer) {
      setState(() {
        if (_seconds > 0) {
          _seconds--;
        } else {
          _goLogin();
          _timer.cancel();
        }
      });
    });
  }

  ///跳转登录
  void _goLogin() {
    RouterUtils.push(context, Routers.loginPage, replace: true);
  }
}
