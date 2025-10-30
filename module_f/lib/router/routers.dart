import 'package:fluro/fluro.dart';
import 'package:model_flutter/pages/login/identify_code_page.dart';
import 'package:model_flutter/pages/main/home/menu_center_page.dart';
import 'package:model_flutter/router/not_found_page.dart';
import '../pages/login/login_page.dart';
import '../pages/main/main_page.dart';
import '../widgets/webview_page.dart';

class Routers {
  ///webView
  static String webViewPage = '/webView';

  ///首页
  static String home = '/home';

  ///首页-首页-菜单
  static String menuCenter = '/home/menuCenter';

  ///登录
  static String loginPage = '/login/login';

  ///登录-验证码
  static String identifyCodePage = '/login/yzm';

  ///路由
  static final FluroRouter router = FluroRouter();

  static void init() {
    ///错误跳转路由
    router.notFoundHandler = Handler(handlerFunc: (context, params) {
      return const NotFoundPage();
    });

    ///webView路由
    router.define(webViewPage, handler: Handler(handlerFunc: (_, params) {
      return WebViewPage(title: params['title']?.first ?? '', url: params['url']?.first ?? '');
    }));

    ///首页路由
    router.define(home, handler: Handler(handlerFunc: (context, params) => const MainPage()));

    ///应用中心
    router.define(menuCenter, handler: Handler(handlerFunc: (context, params) => const MenuCenterPage()));

    ///登录
    router.define(loginPage, handler: Handler(handlerFunc: (_, __) => const LoginPage()));

    ///验证码校验
    router.define(identifyCodePage, handler: Handler(handlerFunc: (_, params) {
      final String phone = params['phone']?.first ?? '';
      return IdentifyCodePage(phone);
    }));
  }
}
