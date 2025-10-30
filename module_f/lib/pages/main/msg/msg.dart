import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import 'package:model_flutter/utils/image_util.dart';
import 'package:model_flutter/widgets/empt_message.dart';

/// tab-消息
class MsgPage extends StatefulWidget {
  const MsgPage({super.key});

  @override
  State<MsgPage> createState() => MsgPageState();
}

class MsgPageState extends State<MsgPage> {
  @override
  void initState() {
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return  Padding(
      padding: const EdgeInsets.symmetric(horizontal: 15),
      child: Column(
        children: [
          SizedBox(
            height: 45,
            width: double.infinity,
            child: Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                const Text(
                  "消息",
                  style: TextStyle(
                      color: Color(0xff0B151E),
                      fontWeight: FontWeight.bold,
                      fontSize: 21),
                ),
                const Expanded(child: SizedBox()),
                Column(
                  mainAxisAlignment: MainAxisAlignment.center,
                  crossAxisAlignment: CrossAxisAlignment.center,
                  children: [
                    Image.asset(ImageUtils.getImgPath("icon_message"),width: 16,height: 16,),
                    const Text(
                      "全部已读",
                      style: TextStyle(fontSize: 10, color: Color(0xff0B151E)),
                    )
                  ],
                )
              ],
            ),
          ),
          const Expanded(child: Center(child: EmptyMessage(),))
        ],
      ),
    );
  }
}
