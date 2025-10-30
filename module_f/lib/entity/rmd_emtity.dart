


///为你推荐

class RmdEntity {
  int? total;
  int? pages;
  List<Rmd> list=List.empty(growable: true);

  RmdEntity({this.total, this.pages, required this.list});

  RmdEntity.fromJson(Map<String, dynamic> json) {
    total = json['total'];
    pages = json['pages'];
    if (json['list'] != null) {
      list = <Rmd>[];
      json['list'].forEach((v) {
        list.add(Rmd.fromJson(v));
      });
    }
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = <String, dynamic>{};
    data['total'] = total;
    data['pages'] = pages;
    data['list'] = list.map((v) => v.toJson()).toList();
      return data;
  }
}

class Rmd {
  String? img;
  String? odsId;
  int? readFlag;
  String? id;
  String? rmdTitle;
  int? rmdType;

  Rmd(
      {this.img,
        this.odsId,
        this.readFlag,
        this.id,
        this.rmdTitle,
        this.rmdType});

  Rmd.fromJson(Map<String, dynamic> json) {
    img = json['img'];
    odsId = json['odsId'];
    readFlag = json['readFlag'];
    id = json['id'];
    rmdTitle = json['rmdTitle'];
    rmdType = json['rmdType'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['img'] = img;
    data['odsId'] = odsId;
    data['readFlag'] = readFlag;
    data['id'] = id;
    data['rmdTitle'] = rmdTitle;
    data['rmdType'] = rmdType;
    return data;
  }
}
