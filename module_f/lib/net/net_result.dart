
class NetResult {

  NetResult(this.success, this.code,this.result ,{this.errMsg=""}) ;

  ///后端原始参数
  bool success;

  ///后端自定义返回码
  String code;

  ///错误消息，如果有
  String errMsg;

  ///返回数据
  dynamic result;

}



