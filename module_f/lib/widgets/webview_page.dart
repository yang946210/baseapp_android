import 'dart:async';
import 'dart:convert';
import 'package:common_utils/common_utils.dart';
import 'package:flutter/material.dart';
import 'package:model_flutter/res/gaps.dart';
import 'package:model_flutter/web/bridge_data.dart';
import 'package:model_flutter/web/bridge_util.dart';
import 'package:webview_flutter/webview_flutter.dart';

import '../global/constant.dart';
import 'my_app_bar.dart';

class WebViewPage extends StatefulWidget {
  const WebViewPage({
    super.key,
    this.title,
    required this.url,
  });

  final String? title;
  final String url;

  @override
  State createState() => _WebViewPageState();
}

class _WebViewPageState extends State<WebViewPage> {
  late final WebViewController _controller;
  int _progressValue = 0;

  @override
  void initState() {
    super.initState();
    _controller = WebViewController()
      ..setJavaScriptMode(JavaScriptMode.unrestricted)
      ..setNavigationDelegate(_delegate())
      ..loadRequest(Uri.parse(widget.url))
      ..enableZoom(false)
      ..addJavaScriptChannel(Constant.flutterBridge, onMessageReceived: _dealH5Message);
  }

  @override
  Widget build(BuildContext context) {
    return WillPopScope(
      onWillPop: () async {
        final bool canGoBack = await _controller.canGoBack();
        if (canGoBack) {
          // 网页可以返回时，优先返回上一页
          await _controller.goBack();
          return Future.value(false);
        }
        return Future.value(true);
      },
      child: Scaffold(
        appBar: widget.title==""
            ? MyAppBar(
                title: "${widget.title}",
              )
            : PreferredSize(
                preferredSize: const Size(0, 0),
                child: Container(
                  color: Colors.transparent, // 你想设置的 AppBar 背景色
                ),
              ),
        body: Stack(
          children: [
            WebViewWidget(
              controller: _controller,
            ),
            if (_progressValue != 100)
              LinearProgressIndicator(
                value: _progressValue / 100,
                backgroundColor: Colors.transparent,
                minHeight: 2,
              )
            else
              Gaps.empty,
          ],
        ),
      ),
    );
  }

  ///处理H5原生请求并返回结果
  void _dealH5Message(JavaScriptMessage jsBridge) async {

    ///H5回传标志
    late String callbackName;

    ///回调数据
    Flutter2JsModel? call;

    try {
      ///H5数据转model
      Js2FlutterModel bridge = Js2FlutterModel.fromJson(jsBridge.message);

      callbackName = bridge.callback;

      ///通过methodName执行对应原生方法得到想要的值
      call = await getMethodByJsName(bridge);
    } catch (e) {
      call = Flutter2JsModel(success: false, result: e.toString(), code: callFail);
    } finally {
      if (callbackName.isNotEmpty) {
        /// 把值最终还是通过JSON的方式返回给H5
        _controller.runJavaScript("$callbackName(${jsonEncode(call?.toJson() ?? Flutter2JsModel(success: false, result: "null", code: callFail))})");
      }
    }
  }

  ///进度条相关
  NavigationDelegate _delegate() {
    return NavigationDelegate(
      onProgress: (int progress) {
        if (!mounted) {
          return;
        }
        LogUtil.d('webView is loading (progress : $progress%)');
        setState(() {
          _progressValue = progress;
        });
      },
    );
  }
}
