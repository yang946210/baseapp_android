import 'package:chewie/chewie.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:video_player/video_player.dart';

import '../../base/provider/provider_widget.dart';
import '../../global/config.dart';
import '../../page_model/login_model.dart';
import '../../router/router_utils.dart';

///登录页
class LoginPage extends StatefulWidget {
  const LoginPage({super.key});

  @override
  State<StatefulWidget> createState() => LoginState();
}

class LoginState extends State<LoginPage> {
  ///背景视频播放
  late VideoPlayerController _controller;
  late ChewieController _cheController;

  @override
  void initState() {
    super.initState();
    _controller = VideoPlayerController.asset('assets/videos/login_bg.mp4');
  }

  @override
  Widget build(BuildContext context) {
    return AnnotatedRegion<SystemUiOverlayStyle>(
        value: const SystemUiOverlayStyle(statusBarColor: Colors.transparent),
        child: SafeArea(
          child: Scaffold(
            body: LayoutBuilder(builder: (BuildContext context, BoxConstraints constraints) {
              double parentWidth = constraints.maxWidth;
              double parentHeight = constraints.maxHeight;
              return ProviderWidget<LoginModel>(
                  builder: (context, model, child) => Chewie(
                      controller: _cheController = ChewieController(
                          videoPlayerController: _controller,
                          autoPlay: true,
                          looping: true,
                          aspectRatio: parentWidth / parentHeight,
                          showControls: false,
                          overlay: Center(
                            child: Column(
                              crossAxisAlignment: CrossAxisAlignment.center,
                              children: [
                                const SizedBox(
                                  height: 80,
                                ),
                                const Text(
                                  "手机号登录",
                                  style: TextStyle(color: Colors.white, fontSize: 30, fontWeight: FontWeight.bold),
                                ),
                                const SizedBox(
                                  height: 10,
                                ),
                                const Text("请使用公司申请的手机号码", style: TextStyle(color: Colors.white54, fontSize: 17)),
                                Container(
                                  margin: const EdgeInsets.fromLTRB(37, 100, 37, 20),
                                  height: 45,
                                  decoration: const BoxDecoration(borderRadius: BorderRadius.all(Radius.circular(30)), color: Colors.white24),
                                  child: Row(
                                    crossAxisAlignment: CrossAxisAlignment.center,
                                    children: [
                                      const SizedBox(width: 16),
                                      const Text(
                                        "+86",
                                        style: TextStyle(color: Colors.white, fontSize: 16, fontWeight: FontWeight.bold),
                                      ),
                                      Padding(
                                        padding: const EdgeInsets.symmetric(horizontal: 13),
                                        child: Container(
                                          width: 1,
                                          height: 20,
                                          color: Colors.white,
                                        ),
                                      ),
                                      Expanded(
                                          child: CupertinoTextField(
                                        placeholder: "请输入手机号码",
                                        maxLength: 11,
                                        placeholderStyle: const TextStyle(color: Colors.white54, fontSize: 15, fontWeight: FontWeight.bold),
                                        decoration: const BoxDecoration(color: Colors.transparent),
                                        style: const TextStyle(color: Colors.white, fontSize: 16, letterSpacing: 1.0),
                                        keyboardType: TextInputType.phone,
                                        onChanged: (value) {
                                          setState(() {
                                            model.number = value.isEmpty?null:int.parse(value);
                                          });
                                        },
                                      )),
                                      const SizedBox(
                                        width: 20,
                                      )
                                    ],
                                  ),
                                ),
                                Container(
                                  margin: const EdgeInsets.fromLTRB(37, 0, 37, 21),
                                  width: double.infinity,
                                  height: 45,
                                  child: ElevatedButton(
                                      onPressed: model.canSend() ? () => model.getCode(context) : null,
                                      style: ButtonStyle(
                                        backgroundColor: model.canSend()
                                            ? MaterialStateProperty.all<Color>(const Color(0xfffa3e45))
                                            : MaterialStateProperty.all<Color>(const Color(0x60fa3e45)),
                                      ),
                                      child: Text("获取验证码",
                                          style: TextStyle(
                                              letterSpacing: 1.4,
                                              color: model.number.toString().length == 11 ? Colors.white : Colors.white54,
                                              fontSize: 15,
                                              fontWeight: FontWeight.bold))),
                                ),
                                const Text(
                                  "没有申请账号请联系公司",
                                  style: TextStyle(color: Colors.white54, fontSize: 12),
                                ),
                                const SizedBox(
                                  height: 15,
                                ),
                                 Row(
                                  mainAxisAlignment: MainAxisAlignment.center,
                                  children: <Widget>[
                                    SizedBox(
                                      width: 18,
                                      height: 18,
                                      child: Checkbox(value: model.isRead, onChanged: (bool? change)=>{
                                        model.changeRead(change??false),
                                      }),
                                    ),
                                    const SizedBox(
                                      width: 4,
                                    ),
                                    const Text("我已详细阅读并同意", style: TextStyle(color: Colors.white54, fontSize: 13)),
                                    GestureDetector(
                                      onTap: (){
                                        RouterUtils.openWebView(context, Config.urlProtocol,title: "隐私政策");
                                      },
                                      child: const Text("《隐私政策》", style: TextStyle(color: Colors.white, fontSize: 14)),
                                    ),
                                    const Text("和", style: TextStyle(color: Colors.white54, fontSize: 13)),
                                    GestureDetector(
                                      onTap: (){
                                        RouterUtils.openWebView(context, Config.urlUser,title: "用户协议");
                                      },
                                      child: const Text("《用户协议》", style: TextStyle(color: Colors.white, fontSize: 14)),
                                    ),
                                  ],
                                )
                              ],
                            ),
                          ))),
                  model: LoginModel());
            }),
          ),
        ));
  }

  @override
  void dispose() {
    super.dispose();
    _controller.dispose();
    _cheController.dispose();
  }
}
