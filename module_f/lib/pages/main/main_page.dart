import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:model_flutter/res/colours.dart';
import 'package:model_flutter/utils/image_util.dart';
import 'home/home.dart';
import 'mine/mine.dart';
import 'msg/msg.dart';

/// 首页tab
class MainPage extends StatefulWidget {
  const MainPage({super.key});

  @override
  State createState() => _MainState();
}

class _MainState extends State<MainPage> {
  var _currentIndex = 0;

  // 把页面存放到数组里
  final List _pageList = [
    const HomePage(),
    const MsgPage(),
    const MinePage(),
  ];


  ///主页通知栏颜色color
  Color getTopColor(int index){
    if(index==0){
      return Colours.color_0xFEE7E7;
    }else if(index==1){
      return Colours.color_0xF2F5FA;
    }else if(index==2){
      return Colours.color_0x03AFF0;
    }
    return Colours.color_0x7D8999;
  }

  @override
  Widget build(BuildContext context) {
    return AnnotatedRegion<SystemUiOverlayStyle>(
      value: SystemUiOverlayStyle(statusBarColor: getTopColor(_currentIndex)),
      child: SafeArea(
          child: Scaffold(
            body: _pageList[_currentIndex],
            bottomNavigationBar: BottomNavigationBar(
              currentIndex: _currentIndex,
              //默认选中第几项
              elevation: 0,
              onTap: (index) {
                //导航栏点击获取索引值
                setState(() {
                  _currentIndex = index;
                });
              },
              iconSize: 22,
              //icon的大小
              unselectedFontSize: 11,
              selectedFontSize: 11,
              fixedColor: Colours.color_0xE60000,
              //选中的颜色
              type: BottomNavigationBarType.fixed,
              //配置底部tabs可以有多个按钮
              //定义导航栏的图片+名称
              items: [
                BottomNavigationBarItem(icon: Image.asset(ImageUtils.getImgPath(_currentIndex==0?"icon_home_selected":"icon_home_unselected"),width: 22,height: 22,), label: "首页"),
                BottomNavigationBarItem(icon: Image.asset(ImageUtils.getImgPath(_currentIndex==1?"icon_message_selected":"icon_message_unselected"),width: 22,height: 22,), label: "消息"),
                BottomNavigationBarItem(icon: Image.asset(ImageUtils.getImgPath(_currentIndex==2?"icon_mine_selected":"icon_mine_unselected"),width: 22,height: 22,), label: "我的"),
              ],
            ),
          )),
    );
  }





}
