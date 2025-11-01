

import 'package:flutter/material.dart';
import 'package:fluttertoast/fluttertoast.dart';
import 'package:model_flutter/base/provider/provider_widget.dart';
import 'package:model_flutter/page_model/mine_model.dart';
import 'package:model_flutter/res/colours.dart';
import 'package:model_flutter/res/gaps.dart';
import 'package:model_flutter/res/source.dart';
import 'package:model_flutter/utils/image_util.dart';
import 'package:model_flutter/utils/user_util.dart';
import 'package:model_flutter/widgets/load_image.dart';


///tab我的
class MinePage extends StatefulWidget {
  const MinePage({super.key});

  @override
  State<MinePage> createState() => MinePageState();
}

class MinePageState extends State<MinePage> {
  @override
  Widget build(BuildContext context) {
    return ProviderWidget(
        onModelReady: (model) => {
          model.getAppVersion(),
          model.getVersionInfo(),
          model.getShareInfo(),
        },
        model: MineModel(),
        builder: (ctx, model, child) => Stack(
              children: [
                Image.asset(
                  ImageUtils.getImgPath("bg_mine"),
                  width: double.infinity,
                  height: 180,
                  fit: BoxFit.fill,
                ),
                const Positioned(
                  left: 15,
                  top: 20,
                  child: Text(
                    "我的",
                    style: TextStyle(color: Colors.white, fontSize: 24, fontWeight: FontWeight.bold),
                  ),
                ),
                Container(
                  width: double.infinity,
                  margin: const EdgeInsets.fromLTRB(15, 153, 15, 50),
                  child: Column(
                    children: [
                      Container(
                        decoration: BoxDecoration(color: Colors.white, borderRadius: BorderRadius.circular(10)),
                        height: 110,
                        child: Row(
                          children: [
                            Gaps.wGap15,
                            Container(
                              decoration: BoxDecoration(borderRadius: BorderRadius.circular(30)),
                              child: ClipRRect(
                                borderRadius: BorderRadius.circular(30.0), // 设置圆角半径
                                child: Image.asset(
                                  ImageUtils.getImgPath("icon_avatar_default"),
                                  width: 60,
                                  height: 60,
                                ), // 设置图片
                              ),
                            ),
                            Gaps.wGap10,
                            Column(
                              mainAxisAlignment: MainAxisAlignment.center,
                              crossAxisAlignment: CrossAxisAlignment.start,
                              children: [
                                Text(UserUtil().getUserById(UserKey.username),
                                    style: const TextStyle(color: Colours.color_0x0E0E0E, fontWeight: FontWeight.bold, fontSize: 22),
                                    textAlign: TextAlign.left),
                                Gaps.hGap8,
                                Text(
                                  UserUtil().getUserById(UserKey.commpanyname),
                                  style: const TextStyle(color: Colours.color_0x7D8999, fontSize: 14),
                                  textAlign: TextAlign.left,
                                )
                              ],
                            )
                          ],
                        ),
                      ),
                      Gaps.hGap10,
                      GestureDetector(
                        onTap: () {
                          showDialog(
                              context: context,
                              builder: (context) => Dialog(
                                    shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(10)),
                                    child: ClipRRect(
                                      borderRadius: BorderRadius.circular(10),
                                      child: Container(
                                        color: Colors.white,
                                        width: 300,
                                        height: 400,
                                        child: Stack(
                                          alignment: AlignmentDirectional.topCenter,
                                          children: [
                                            const LoadImage(
                                              "bg_feedback",
                                              width: 300,
                                              height: 300,
                                              fit: BoxFit.fill,
                                            ),
                                            const Positioned(top: 50, width: 48, height: 48, child: LoadImage("icon_feedback_tip")),
                                            const Positioned(
                                                top: 120,
                                                child:
                                                    Text("意见反馈", style: TextStyle(color: Colours.color_0x0B151E, fontSize: 18, fontWeight: FontWeight.bold))),
                                            const Positioned(
                                                top: 144, child: Text("请联系下方的邮件地址", style: TextStyle(color: Colours.color_0x0B151E, fontSize: 13))),
                                            const Positioned(
                                                top: 180,
                                                child: Text("gaoluxinxi_Customer@163.com", style: TextStyle(color: Colours.color_0xFF474E, fontSize: 14))),
                                            const Positioned(
                                                top: 233, child: Text("后续我们将开通在线反馈功能，敬请期待~", style: TextStyle(color: Colours.color_0x0B151E, fontSize: 13))),
                                            Positioned(
                                                bottom: 45,
                                                child: GestureDetector(
                                                  onTap: () {
                                                    Navigator.of(context).pop();
                                                  },
                                                  child: Container(
                                                      width: 180,
                                                      height: 40,
                                                      decoration: BoxDecoration(
                                                          borderRadius: BorderRadius.circular(20),
                                                          gradient: const LinearGradient(
                                                              colors: [Colours.color_0x19c2ff, Colours.color_0x19e8ff],
                                                              begin: Alignment.centerLeft,
                                                              end: Alignment.centerRight)),
                                                      child: const Center(
                                                        child: Text("我知道了", style: TextStyle(color: Colors.white, fontSize: 15)),
                                                      )),
                                                ))
                                          ],
                                        ),
                                      ),
                                    ),
                                  ));
                        },
                        child: Container(
                          decoration: BoxDecoration(color: Colors.white, borderRadius: BorderRadius.circular(5)),
                          width: double.infinity,
                          height: 60,
                          child: Padding(
                            padding: const EdgeInsets.symmetric(horizontal: 15),
                            child: Row(
                              crossAxisAlignment: CrossAxisAlignment.center,
                              children: [
                                Image.asset(
                                  ImageUtils.getImgPath("icon_mine_feedback"),
                                  width: 18,
                                  height: 18,
                                ),
                                const SizedBox(
                                  width: 16,
                                ),
                                const Text(
                                  "意见反馈",
                                  style: TextStyle(color: Color(0xff0B151E), fontSize: 17),
                                ),
                                const Expanded(child: SizedBox()),
                                Images.arrowRight
                              ],
                            ),
                          ),
                        ),
                      ),
                      Gaps.hGap10,
                      GestureDetector(
                        onTap: () {
                          Fluttertoast.showToast(msg: "清理成功");
                        },
                        child: Container(
                          decoration: BoxDecoration(color: Colors.white, borderRadius: BorderRadius.circular(5)),
                          height: 60,
                          child: Padding(
                            padding: const EdgeInsets.symmetric(horizontal: 15),
                            child: Row(
                              crossAxisAlignment: CrossAxisAlignment.center,
                              children: [
                                Image.asset(
                                  ImageUtils.getImgPath("icon_mine_cache"),
                                  width: 18,
                                  height: 18,
                                ),
                                const SizedBox(
                                  width: 16,
                                ),
                                const Text(
                                  "清理缓存",
                                  style: TextStyle(color: Color(0xff0B151E), fontSize: 17),
                                ),
                                const Expanded(
                                  child: Text(
                                    "0kb",
                                    textAlign: TextAlign.right,
                                    style: TextStyle(color: Color(0xff6F8396), fontSize: 12),
                                  ),
                                ),
                                const SizedBox(width: 10),
                                Images.arrowRight
                              ],
                            ),
                          ),
                        ),
                      ),
                      Gaps.hGap10,
                      GestureDetector(
                        onTap: (){
                          showDialog(
                              context: context,
                              builder: (context) => Dialog(
                                child: ClipRRect(
                                  borderRadius: BorderRadius.circular(10),
                                  child: Container(
                                    color: Colors.white,
                                    width: 300,
                                    height: 400,
                                    child:  Stack(
                                      alignment: AlignmentDirectional.topCenter,
                                      children: [
                                        const LoadImage(
                                          "bg_version",
                                          width: double.infinity,
                                          height: 120,
                                          fit: BoxFit.fill,
                                        ),
                                        Positioned(left: 15,top: 30,child: Column(
                                          crossAxisAlignment: CrossAxisAlignment.start,
                                          mainAxisAlignment: MainAxisAlignment.start,
                                          children: [
                                            const Text("当前已经是最新版本",style: TextStyle(color: Colors.white,fontSize: 17),),
                                            Gaps.hGap10,
                                            Text(model.currVersion,style: const TextStyle(color: Colors.white,fontSize: 24),),
                                            Gaps.hGap32,
                                            const Text("当前版本更新内容",style: TextStyle(color: Colours.color_0x0B151E,fontSize: 15),),
                                            Gaps.hGap8,
                                            Text(model.currVersionContent,style: const TextStyle(color: Colours.color_0x445260,fontSize: 15),),
                                          ],
                                        ),)

                                      ],
                                    ),
                                  ),
                                ),
                              ));
                        },
                        child: Container(
                            decoration: BoxDecoration(color: Colors.white, borderRadius: BorderRadius.circular(5)),
                            height: 60,
                            child: Padding(
                              padding: const EdgeInsets.symmetric(horizontal: 15),
                              child: Row(
                                crossAxisAlignment: CrossAxisAlignment.center,
                                children: [
                                  Image.asset(
                                    ImageUtils.getImgPath("icon_mine_version"),
                                    width: 18,
                                    height: 18,
                                  ),
                                  const SizedBox(
                                    width: 16,
                                  ),
                                  const Text(
                                    "检查更新",
                                    style: TextStyle(color: Color(0xff0B151E), fontSize: 17),
                                  ),
                                  Expanded(
                                    child: Text(
                                      model.appVersion,
                                      textAlign: TextAlign.right,
                                      style: const TextStyle(color: Color(0xff6F8396), fontSize: 12),
                                    ),
                                  ),
                                  const SizedBox(width: 10),
                                  Images.arrowRight
                                ],
                              ),
                            ),
                          ),
                      ),
                      Gaps.hGap10,
                      GestureDetector(
                        onTap: (){
                        showDialog(
                            context: context,
                            builder: (context) => Dialog(
                              child: ClipRRect(
                                borderRadius: BorderRadius.circular(10),
                                child: Container(
                                  color: Colors.white,
                                  width: 300,
                                  height: 480,
                                  child:   Stack(
                                    alignment: AlignmentDirectional.topCenter,
                                    children: [
                                      const LoadImage(
                                        "bg_share",
                                        width: double.infinity,
                                        height: double.infinity,
                                        fit: BoxFit.fill,
                                      ),
                                      Positioned(left: 28,top: 25,right:28,child: Column(
                                        crossAxisAlignment: CrossAxisAlignment.start,
                                        mainAxisAlignment: MainAxisAlignment.start,
                                        children: [
                                          const Text("分享",style: TextStyle(color: Colors.white,fontSize: 28),),
                                          Gaps.hGap2,
                                          const Text("大数据App",style: TextStyle(color: Colors.white,fontSize: 16),),
                                          Gaps.hGap20,
                                          LoadImage(model.shareImage,width: 244,height: 244,fit: BoxFit.fill),
                                          Gaps.hGap20,
                                          Center(
                                            child: ElevatedButton(
                                              style: ButtonStyle(
                                                backgroundColor: MaterialStateProperty.all(Colours.color_0xFFAF26),
                                                minimumSize: MaterialStateProperty.all(const Size(240, 40)),
                                              ),
                                              onPressed: () {
                                                model.saveImage();


                                              },
                                              child: const Text("保存二维码至手机",style: TextStyle(color: Colors.white,fontSize: 17),),
                                            ),
                                          ),

                                        ],
                                      ),)

                                    ],
                                  ),
                                ),
                              ),
                            ));
                      },
                        child: Container(
                          decoration: BoxDecoration(color: Colors.white, borderRadius: BorderRadius.circular(5)),
                          height: 60,
                          child: Padding(
                            padding: const EdgeInsets.symmetric(horizontal: 15),
                            child: Row(
                              crossAxisAlignment: CrossAxisAlignment.center,
                              children: [
                                Image.asset(
                                ImageUtils.getImgPath("icon_mine_share"),
                                width: 18,
                                height: 18,
                                ),
                                const SizedBox(
                                width: 16,
                                ),
                                const Text(
                                  "分享此应用",
                                  style: TextStyle(color: Color(0xff0B151E), fontSize: 17),
                                ),
                                const Expanded(child: SizedBox()),
                                Images.arrowRight
                              ],
                            ),
                          ),
                      ),),
                      Gaps.hGap10,
                      GestureDetector(
                        onTap: (){
                          model.showLoginOutDialog(ctx);
                        },
                        child: Container(
                          decoration: BoxDecoration(color: Colors.white, borderRadius: BorderRadius.circular(5)),
                          height: 60,
                          child: Padding(
                            padding: const EdgeInsets.symmetric(horizontal: 15),
                            child: Row(
                              crossAxisAlignment: CrossAxisAlignment.center,
                              children: [
                                Image.asset(
                                  ImageUtils.getImgPath("icon_mine_logout"),
                                  width: 18,
                                  height: 18,
                                ),
                                const SizedBox(
                                  width: 16,
                                ),
                                const Text(
                                  "退出登录",
                                  style: TextStyle(color: Color(0xff0B151E), fontSize: 17),
                                ),
                                const Expanded(child: SizedBox()),
                                Images.arrowRight
                              ],
                            ),
                          ),
                        ),
                      ),
                    ],
                  ),
                )
              ],
            ));
  }
}
