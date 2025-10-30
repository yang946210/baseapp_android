import 'package:fluro/fluro.dart';
import 'package:flutter/material.dart';

import 'routers.dart';

///路由跳转工具类(fluro)
class RouterUtils {
  /// 全局key，用于无context跳转的情况
  static final GlobalKey<NavigatorState> navigatorKey = GlobalKey<NavigatorState>();

  ///正常跳转
  static void push(BuildContext context, String path, {bool replace = false, bool clearStack = false, Object? arguments}) {
    _unFocus();
    Routers.router.navigateTo(
      context,
      path,
      replace: replace,
      clearStack: clearStack,
      transition: TransitionType.native,
      routeSettings: RouteSettings(
        arguments: arguments,
      ),
    );
  }

  ///正常跳转带返回
  static void pushResult(BuildContext context, String path, void Function(Object) function,
      {bool replace = false, bool clearStack = false, Object? arguments}) {
    _unFocus();
    Routers.router
        .navigateTo(
      context,
      path,
      replace: replace,
      clearStack: clearStack,
      transition: TransitionType.native,
      routeSettings: RouteSettings(
        arguments: arguments,
      ),
    ).then((Object? result) {
      // 页面返回result为null
      if (result == null) {
        return;
      }
      function(result);
    }).catchError((dynamic error) {
      debugPrint('$error');
    });
  }

  // 无context跳转
  static void pushNoContext(
    String path, {
    bool replace = false,
    bool clearStack = false,
    Object? arguments,
    TransitionType? transition,
  }) {
    Routers.router.navigateTo(
      navigatorKey.currentContext!,
      path,
      replace: replace,
      clearStack: clearStack,
      transition: transition ?? TransitionType.inFromRight,
      // routeSettings需要在对应页面接收：ModalRoute.of(context)?.settings.arguments;
      routeSettings: RouteSettings(
        arguments: arguments,
      ),
    );
  }

  /// 返回
  static void goBack(BuildContext context) {
    _unFocus();
    Navigator.pop(context);
  }

  /// 带参数返回
  static void goBackWithParams(BuildContext context, Object result) {
    _unFocus();
    Navigator.pop<Object>(context, result);
  }

  // 无context返回,并指定路由返回多少层，默认返回上一页面, 返回带参数params
  static void back({int count = 1, Object? params}) {
    NavigatorState state = Navigator.of(navigatorKey.currentContext!);
    while (count-- > 0) {
      state = state..pop(params);
    }
  }

  /// 跳到WebView页
  static void openWebView(BuildContext context, String url,{ String title=""}) {
    push(context, '${Routers.webViewPage}?title=${Uri.encodeComponent(title)}&url=${Uri.encodeComponent(url)}');
  }

  static void _unFocus() {
    // 使用下面的方式，会触发不必要的build。FocusScope.of(context).unfocus();
    // https://github.com/flutter/flutter/issues/47128#issuecomment-627551073
    FocusManager.instance.primaryFocus?.unfocus();
  }
}
