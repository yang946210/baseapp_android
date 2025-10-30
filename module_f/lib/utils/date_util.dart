import 'package:intl/intl.dart';

/// 时间格工具类
class DateUtil{

  static var TIMEyyyymmddHHmmss="yyyy-mm-dd_HH:mm:ss";

  ///根据时间戳获取时间格式
  static String formatDateTime(int timeMillis,{String dateFormat="yyyy-mm-dd_HH:mm:ss"}){
    var date =DateTime.fromMicrosecondsSinceEpoch(timeMillis);
    var formatter = DateFormat(dateFormat);
    return formatter.format(date);
  }



}