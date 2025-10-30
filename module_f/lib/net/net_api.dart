
class NetApi{

  ///发送验证码
  static const String sendMsg = '/app/user/public/sendMsg';
  ///验证码校验
  static const String ifVerify = 'app/public/verify/required';
  ///短信登录
  static const String loginByTelMsg = '/app/user/public/loginByTelMsg';
  ///token登录
  static const String loginByToken = '/app/user/public/loginByToken';
  ///获取版本信息
  static const String getAppVersion = '/app/homepage/public/appVersion/info';
  ///获取分享链接
  static const String getShare = '/app/share/qr-code';

  ///用户菜单
  static const String homeMenus = '/app/menu/homeMenus';
  ///全部菜单
  static const String allMenus = '/app/menu/allMenus';
  ///可编辑保存菜单数
  static const String prefMenusRange = '/app/menu/prefMenusRange';
  ///编辑菜单
  static const String savePref = '/app/menu/savePref';
  ///重置菜单
  static const String menuReset = '/app/menu/reset';


  ///为你推荐
  static const String rmdList = '/app/rmd/list';






}
