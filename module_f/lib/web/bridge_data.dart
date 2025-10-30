import 'dart:convert';

///js调用flutter数据
class Js2FlutterModel {
  // 事件名
  final String methodName;

  // 参数
  final Map<String, dynamic> params;

  // js回调函数名
  final String callback;

  const Js2FlutterModel(this.methodName, this.params, this.callback);

  factory Js2FlutterModel.fromJson(String jsonStr) {
    final json = jsonDecode(jsonStr);
    return Js2FlutterModel(
      json["methodName"]??"",
      json["params"] ?? {},
      json["callback"] ?? "",
    );
  }
}

///flutter回传js数据
class Flutter2JsModel {

  bool success = false;
  ///请求获取的内容
  dynamic result;

  String code;

  Flutter2JsModel({required this.success, required this.result, required this.code});

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = <String, dynamic>{};
    data['success'] = success;
    data['code'] = code;
    data['result'] = result??"";
    return data;
  }
}
