
import 'package:flutter/cupertino.dart';
import 'package:fluttertoast/fluttertoast.dart';
import 'package:model_flutter/router/routers.dart';

import '../base/provider/base_change_notifier.dart';
import '../entity/home_entity.dart';
import '../entity/rmd_emtity.dart';
import '../net/net_api.dart';
import '../net/net_utils.dart';
import '../router/router_utils.dart';

class HomeModel extends BaseChangeNotifier{

  ///我的菜单
  var menuList=List<MenuEntity>.empty(growable: true);

  ///为你推荐
  RmdEntity? rmdEntity;


  ///获取我的菜单数据
  void getMenus(){
    NetUtil().request(Method.post, NetApi.homeMenus,
      onSuccess: (data){
        menuList = data.result.map<MenuEntity>((jsonItem) => MenuEntity.fromJson(jsonItem)).toList();
        menuList.add(MenuEntity(
          menuId: "0",
          enable: true,
          canAccess: true,
          menuName: "更多"
        ));
        notifyListeners();
      },
      onError: (msg,code){
        Fluttertoast.showToast(msg: "获取菜单失败：$msg");
      }
    );
  }

  ///首页推荐
  void getRmdList(){
    NetUtil().request(Method.post, NetApi.rmdList,
        onSuccess: (data){
          rmdEntity=RmdEntity.fromJson(data.result);
        },
    );
    notifyListeners();
  }

  ///菜单剪辑
  void onMenuClick(BuildContext context,MenuEntity menu){
    if(menu.menuName=="更多"){
      RouterUtils.push(context, Routers.menuCenter);
    }

  }






}