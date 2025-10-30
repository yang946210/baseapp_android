import 'package:flutter/material.dart';
import 'package:model_flutter/base/provider/provider_widget.dart';
import 'package:model_flutter/res/colours.dart';
import 'package:model_flutter/res/gaps.dart';
import 'package:model_flutter/widgets/load_image.dart';
import 'package:model_flutter/widgets/my_app_bar.dart';

import '../../../page_model/menu_center_model.dart';

///应用中心
class MenuCenterPage extends StatefulWidget {
  const MenuCenterPage({super.key});

  @override
  State<StatefulWidget> createState() => MenuCenterState();
}

class MenuCenterState extends State<MenuCenterPage> {
  @override
  Widget build(BuildContext context) {
    return ProviderWidget(
        model: MenuCenterModel(),
        onModelReady: (model) {
          model.getMenu();
          model.getMaxMenuSize();
        },
        builder: (ctx, model, child) => Scaffold(
              appBar: MyAppBar(
                title: "应用中心",
                backgroundColor: Colors.white,
                customLeft: model.editState
                    ? GestureDetector(
                        onTap: () {
                          model.changeEditState();
                        },
                        child: const Center(
                          child: Text(
                            "取消",
                            style: TextStyle(color: Colours.color_0xE60012, fontSize: 16),
                          ),
                        ),
                      )
                    : null,
                actions: [
                  GestureDetector(
                      onTap: () {
                        model.showLoginOutDialog(ctx);
                      },
                      child: Visibility(
                        maintainState: false,
                        visible: model.editState,
                        child: const Text(
                          "重置",
                          style: TextStyle(color: Colours.color_0xE60012, fontSize: 16),
                        ),
                      )),
                  Gaps.wGap15,
                  GestureDetector(
                      onTap: () {
                        model.changeEditState();
                      },
                      child: Visibility(
                        maintainState: false,
                        visible: !model.editState,
                        child: const Text(
                          "编辑",
                          style: TextStyle(color: Colours.color_0xE60012, fontSize: 16),
                        ),
                      )),
                  GestureDetector(
                      onTap: () {
                        model.savePref();
                      },
                      child: Visibility(
                        maintainState: false,
                        visible: model.editState,
                        child: const Text(
                          "保存",
                          style: TextStyle(color: Colours.color_0xE60012, fontSize: 16),
                        ),
                      )),
                  Gaps.wGap15
                ],
              ),
              body: Row(
                children: [
                  SizedBox(
                    width: 80,
                    height: double.infinity,
                    child: ListView.builder(
                        itemCount: model.menus.length,
                        itemBuilder: (ctx, index) => SizedBox(
                              height: 65,
                              width: double.infinity,
                              child: GestureDetector(
                                onTap: () {
                                  model.leftClick(index);
                                },
                                child: Row(
                                  crossAxisAlignment: CrossAxisAlignment.center,
                                  children: [
                                    Visibility(
                                        visible: model.leftIndex == index,
                                        maintainSize: true,
                                        maintainAnimation: true,
                                        // 不保持组件里的动画
                                        maintainState: true,
                                        child: Container(
                                          width: 5,
                                          height: 15,
                                          decoration: BoxDecoration(color: Colours.color_0xFF6461, borderRadius: BorderRadius.circular(5)),
                                        )),
                                    Gaps.wGap5,
                                    Text("${model.menus[index].menuName}",
                                        style: TextStyle(
                                            color: Colours.color_0x0E0E0E,
                                            fontSize: 14,
                                            fontWeight: model.leftIndex == index ? FontWeight.bold : FontWeight.normal)),
                                  ],
                                ),
                              ),
                            )),
                  ),
                  Gaps.wGap5,
                  Expanded(
                      child: ListView.builder(
                          itemCount: model.menus.length,
                          itemBuilder: (ctx, groupIndex) => Container(
                                margin: const EdgeInsets.symmetric(vertical: 5),
                                decoration: BoxDecoration(
                                  borderRadius: BorderRadius.circular(5),
                                  color: Colors.white,
                                ),
                                child: Padding(
                                  padding: const EdgeInsets.symmetric(horizontal: 10, vertical: 13),
                                  child: Column(
                                    crossAxisAlignment: CrossAxisAlignment.start,
                                    children: [
                                      Text(
                                        "${model.menus[groupIndex].menuName}",
                                        style: const TextStyle(color: Colours.color_0x0E0E0E, fontSize: 17, fontWeight: FontWeight.bold),
                                      ),
                                      Gaps.hGap2,
                                      GridView.builder(
                                          itemCount: model.menus[groupIndex].children?.length,
                                          physics: const NeverScrollableScrollPhysics(),
                                          shrinkWrap: true,
                                          gridDelegate: const SliverGridDelegateWithFixedCrossAxisCount(
                                              crossAxisCount: 4, // 横轴元素个数
                                              crossAxisSpacing: 0, // 横轴间距
                                              mainAxisSpacing: 0, // 纵轴间距
                                              childAspectRatio: 0.88),
                                          itemBuilder: (ctx, index) => Container(
                                                height: 58,
                                                child: Stack(
                                                  children: [
                                                    Positioned(
                                                        top: 15,
                                                        left: 0,
                                                        right: 0,
                                                        child: Align(
                                                          child: LoadImage(
                                                            model.menus[groupIndex].children![index].getIconImage(),
                                                            width: 35,
                                                            height: 35,
                                                            holderImg: "icon_db_normal",
                                                          ),
                                                        )),
                                                    Positioned(
                                                      bottom: 8,
                                                      left: 0,
                                                      right: 0,
                                                      child: Align(
                                                        child: Text(
                                                          "${model.menus[groupIndex].children?[index].menuName}",
                                                          style: const TextStyle(color: Colours.color_0x0E0E0E, fontSize: 13),
                                                        ),
                                                      ),
                                                    ),
                                                    Positioned(
                                                      top: 8,
                                                      right: 6,
                                                      child: Visibility(
                                                          visible: model.editState || model.menus[groupIndex].children![index].isAccess(),
                                                          child: GestureDetector(
                                                            onTap: () {
                                                              model.editMenu(model.menus[groupIndex].children![index], groupIndex);
                                                            },
                                                            child: LoadImage(model.menus[groupIndex].children![index].getStateImage(),
                                                                width: 16, height: 16, holderImg: "icon_db_normal"),
                                                          )),
                                                    ),
                                                  ],
                                                ),
                                              ))
                                    ],
                                  ),
                                ),
                              ))),
                  Gaps.wGap5,
                ],
              ),
            ));
  }
}
