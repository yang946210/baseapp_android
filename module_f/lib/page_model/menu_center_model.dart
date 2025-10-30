
import 'package:flutter/cupertino.dart';
import 'package:fluttertoast/fluttertoast.dart';
import '../base/provider/base_change_notifier.dart';
import '../entity/home_entity.dart';
import '../net/net_api.dart';
import '../net/net_utils.dart';
import '../res/colours.dart';

class MenuCenterModel extends BaseChangeNotifier{

  ///是否是编辑状态
  var editState=false;

  ///最大可编辑保存数
  var maxMenuSize=10;

  ///最小可编辑保存数
  var minMenuSize=1;

  ///左边选中下标
  var leftIndex=0;


  ///菜单数据
  var menus=List<MenuEntity>.empty(growable: true);

  ///编辑/保存状态切换
  void changeEditState(){
    editState=!editState;
    notifyListeners();
  }

  ///左侧菜单点击
  void leftClick(int index){
    leftIndex=index;
    notifyListeners();
  }


  ///编辑菜单
  void editMenu(MenuEntity menu,int index){
    if(menus.length<2){
      return;
    }
    //常用菜单
    var homeMenu=menus[0].children;
    //所有菜单
    var allMenu=menus[1].children;

    //从常用中删除
    if(index==0){
      if(homeMenu!.length<=minMenuSize){
        Fluttertoast.showToast(msg: "首页能低于$minMenuSize个常用应用");
        return;
      }
      menu.isAdd=true;
      homeMenu.remove(menu);
      allMenu?.add(menu);
      notifyListeners();

    }
    //从添加到常用
    else if(index==1){
      //是否包含
      bool any=homeMenu?.any((element) => element.menuId==menu.menuId)??false;
      
      if(any){
        Fluttertoast.showToast(msg: "常用应用中已存在,不能重复添加");
      }else if(homeMenu!.length>=maxMenuSize){
        Fluttertoast.showToast(msg: "最多可选择$maxMenuSize个常用应用");
      }else{
        menu.isAdd=false;
        homeMenu.add(menu);
        allMenu?.remove(menu);
        notifyListeners();
      }
    }
  }


  ///获取/处理菜单数据
  void getMenu() async{

    //常用菜单
    var homeResult= await NetUtil().requestSync(Method.post, NetApi.homeMenus);
    MenuEntity home=MenuEntity(
      menuName: "首页应用",
      children: homeResult.result?.map<MenuEntity>((item)=>MenuEntity.fromJson(item,isAdd: false)).toList(),
    );

    //全部菜单
    var allResult= await NetUtil().requestSync(Method.post, NetApi.allMenus);
    menus=allResult.result?.map<MenuEntity>((jsonItem) => MenuEntity.fromJson(jsonItem,isAdd: true)).toList();
    menus.insert(0,home);

    notifyListeners();

  }


  ///获取编辑范围区间
  void getMaxMenuSize(){
    NetUtil().request(Method.post, NetApi.prefMenusRange,onSuccess: (data){
      maxMenuSize=data.result["max"];
      minMenuSize=data.result["min"];
    });
  }


  //{"menuIds":["1206","1233","1232","1228","1224","1204","1203","1227"]}

  ///保存菜单
  void savePref(){
    //常用菜单id
    var homeMenu=menus[0].children?.map((e) => e.menuId as String).toList();
    var params={"menuIds":homeMenu};
    NetUtil().request(Method.post, NetApi.savePref,params: params,onSuccess: (data){
      if(data.success){
        Fluttertoast.showToast(msg: "保存成功");
        changeEditState();
      }else{
        Fluttertoast.showToast(msg: "保存失败：${data.errMsg}");
      }
    });
  }


  ///重置菜单
  void resetMenu(){
    NetUtil().request(Method.post, NetApi.menuReset,onSuccess: (data){
      if(data.success){
        Fluttertoast.showToast(msg: "重置成功");
        changeEditState();
        getMenu();
      }else{
        Fluttertoast.showToast(msg: "保存失败：${data.errMsg}");
      }
    });
  }


  ///重置菜单弹窗
  Future<void> showLoginOutDialog(BuildContext context) async {
    return showCupertinoDialog<void>(
      context: context,
      builder: (BuildContext dialogContext) {
        return CupertinoAlertDialog(
          title: const Text('是否恢复到默认菜单？'),
          actions: <Widget>[
            // 取消按钮
            CupertinoDialogAction(
              child: const Text('取消',style: TextStyle(color: Colours.color_0x0E0E0E)),
              onPressed: () {
                Navigator.pop(dialogContext); // 关闭对话框
              },
            ),
            // 确认按钮
            CupertinoDialogAction(
              isDefaultAction: true, // Can optionally set this as the default action (bold text)
              child: const Text('确认',style: TextStyle(color: Colours.color_0x0E0E0E),),
              onPressed: () {
                resetMenu();
                Navigator.pop(dialogContext); // 关
              },
            ),
          ],
        );
      },
    );
  }
}