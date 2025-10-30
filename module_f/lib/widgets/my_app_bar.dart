import 'package:flutter/material.dart';
import 'package:model_flutter/utils/image_util.dart';

///通用导航栏 默认显示 title backBtn 支持自定义bottom 和 actions
class MyAppBar extends StatefulWidget implements PreferredSizeWidget {
  final PreferredSizeWidget? bottom;
  final String title;
  final List<Widget>? actions;
  final TextStyle? titleStyle;
  final Color? backgroundColor;
  final String? leftIcon;
  final bool isBack;
  final Widget? customLeft;

  const MyAppBar({
    super.key,
    this.bottom,
    this.title = "",
    this.actions,
    this.titleStyle,
    this.backgroundColor,
    this.leftIcon,
    this.isBack = true,
    this.customLeft
  });

  @override
  State createState() => _MyAppBarState();

  @override
  Size get preferredSize => const Size.fromHeight(45);
}

class _MyAppBarState extends State<MyAppBar> {
  @override
  Widget build(BuildContext context) {
    return AppBar(
      centerTitle: true,
      title: Text(
        widget.title,
        style: widget.titleStyle ??
            const TextStyle(
              color: Colors.black,
              fontSize: 17.0,
              fontWeight: FontWeight.bold,
            ),
      ),
      leading: widget.customLeft?? (widget.isBack
          ? ElevatedButton(
        style: ButtonStyle(
            backgroundColor:
            MaterialStateProperty.all(Colors.transparent),
            elevation: MaterialStateProperty.all(0),
            shadowColor: MaterialStateProperty.all(Colors.transparent),
            overlayColor: MaterialStateProperty.all(Colors.transparent),
            padding: MaterialStateProperty.all(EdgeInsets.zero)),
        child: Image(
          image: AssetImage(widget.leftIcon ?? ImageUtils.getImgPath("icon_return_back")),
          width: 9,
          height: 15,
        ),
        onPressed: () {
          Navigator.maybePop(context);
        },
      ): null),
      backgroundColor: widget.backgroundColor ?? Colors.white,
      elevation: 0,
      actions: widget.actions,
    );
  }
}
