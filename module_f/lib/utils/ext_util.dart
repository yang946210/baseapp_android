//String拓展函数
extension StringExtension on String? {
  ///null转''串
  String get nullSafe => this ?? '';

  ///字符串判空
  bool get ifNotNull {
    return this != null && this!.isNotEmpty;
  }
}

///回调不为null执行
void ifNotNull(void Function()? callback) {
  if (callback != null) {
    callback.call();
  }
}
