

////首页title四个大图标数据
class MainTitleModel {
  String title;
  String titleUnit;
  String nextTitle;
  String info;
  String bgPath;

  MainTitleModel(
      this.title, this.titleUnit, this.nextTitle, this.info, this.bgPath);
}

///主页目录
class MainMenuModel {
  String name;
  String imgPath;

  MainMenuModel(this.name, this.imgPath);
}

///为你推荐
class RecommendModel {
  int sourceDate=0;
  String? odsId;
  int? readFlag;
  String? id;
  String? rmdTitle;
  int? rmdType;
  String? img;

  RecommendModel(
      {
      this.sourceDate=0,
      this.odsId,
      this.readFlag,
      this.id,
      this.rmdTitle,
      this.rmdType,
      this.img});

  RecommendModel.fromJson(Map<String, dynamic> json) {
    sourceDate = json['sourceDate']??0;
    odsId = json['odsId'];
    readFlag = json['readFlag'];
    id = json['id'];
    rmdTitle = json['rmdTitle'];
    rmdType = json['rmdType'];
    img = json["img"] ?? "";
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = <String, dynamic>{};
    data['sourceDate'] = sourceDate;
    data['odsId'] = odsId;
    data['readFlag'] = readFlag;
    data['id'] = id;
    data['rmdTitle'] = rmdTitle;
    data['rmdType'] = rmdType;
    data['img'] = img;
    return data;
  }
}
