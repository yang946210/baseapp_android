import 'package:common_utils/common_utils.dart';

import '../global/config.dart';

///首页常用菜单
class MenuEntity{
  String? bigIcon;
  String? disableIcon;
  List<MenuEntity>? children;
  bool? enable;
  bool? canAccess;
  String? icon;
  String? menuId;
  String? orderNum;
  String? menuName;
  Params? params;
  int? type;
  String? bigIconDisable;
  bool isAdd=true;

  MenuEntity(
      {this.bigIcon,
        this.disableIcon,
        this.children,
        this.enable,
        this.canAccess,
        this.icon,
        this.menuId,
        this.orderNum,
        this.menuName,
        this.params,
        this.type,
        this.bigIconDisable});

  MenuEntity.fromJson(Map<String, dynamic> json,{this.isAdd=true}) {
    bigIcon = json['bigIcon'];
    disableIcon = json['disableIcon'];
    children = json['children']?.map<MenuEntity>((jsonItem) => MenuEntity.fromJson(jsonItem)).toList();
    enable = json['enable'];
    canAccess = json['canAccess'];
    icon = json['icon'];
    menuId = json['menuId'].toString();
    orderNum = json['orderNum'];
    menuName = json['menuName'];
    params =
    json['params'] != null ? Params.fromJson(json['params']) : null;
    type = json['type'];
    bigIconDisable = json['bigIconDisable'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = <String, dynamic>{};
    data['bigIcon'] = bigIcon;
    data['disableIcon'] = disableIcon;
    data['children'] = children;
    data['enable'] = enable;
    data['canAccess'] = canAccess;
    data['icon'] = icon;
    data['menuId'] = menuId;
    data['orderNum'] = orderNum;
    data['menuName'] = menuName;
    if (params != null) {
      data['params'] = params!.toJson();
    }
    data['type'] = type;
    data['bigIconDisable'] = bigIconDisable;
    return data;
  }

   String getIconDrawableId(int pos) {
    if (pos > 4) {
      return  enable??false ? bigIcon??"" : bigIconDisable??"";
    } else {
      return  enable??false ? icon??"" : disableIcon??"";
    }
  }


  ///是否是锁权限
  bool isAccess(){
    var ca=canAccess??true;
    var eb=enable=enable??false;
    return !ca&&eb ;
  }


  ///根据相关状态获取右上角icon
  String getStateImage(){
    if(isAccess()){
      return "icon_limited";
    }else {
      return isAdd?"icon_add":"icon_reduce";
    }
  }

  String getIconImage(){
    var url="${Config.urlBase}${enable??false?icon:disableIcon}";
    LogUtil.d(url);
    return url;
  }

}

class Params {
  String? key;

  Params({this.key});

  Params.fromJson(Map<String, dynamic> json) {
    key = json['key'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = <String, dynamic>{};
    data['key'] = key;
    return data;
  }
}