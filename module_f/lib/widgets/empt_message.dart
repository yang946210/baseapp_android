import 'package:flutter/material.dart';
import 'package:model_flutter/utils/image_util.dart';

///消息列表空页面
class EmptyMessage extends StatelessWidget {
  const EmptyMessage(
      {super.key,
      this.imagePath = "icon_empty",
      this.emptyTips = "没有新的消息哦",
      this.emptyNextTips = "呀,这里什么内容都没有"});

  final String imagePath;
  final String emptyTips;
  final String emptyNextTips;

  @override
  Widget build(BuildContext context) {
    return SizedBox(
      width: double.infinity,
      child: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        children: [
          Image.asset(
            ImageUtils.getImgPath("icon_empty_msg"),
            width: 100,
            height: 100,
          ),
          Container(
            margin: const EdgeInsets.fromLTRB(0, 20, 0, 10),
            child: Text(
              emptyTips,
              style: const TextStyle(
                color: Color(0xff0b151e), fontSize: 15, ),
            ),
          ),
          Container(
            margin: const EdgeInsets.fromLTRB(0, 0, 0, 10),
            child: Text(emptyNextTips,
                style: const TextStyle(
                  color: Color(0xff6f8396), fontSize: 12,)),
          )
        ],
      ),
    );
  }
}
