
import 'package:flutter/material.dart';
import '../../base/provider/provider_widget.dart';
import '../../page_model/identify_code_model.dart';
import '../../widgets/my_app_bar.dart';
import 'code_input_container.dart';

///验证码校验
class IdentifyCodePage extends StatefulWidget {

  ///当前发送短信的电话号码透传值
  final String _phone;

  const IdentifyCodePage(this._phone, {super.key});

  @override
  State<StatefulWidget> createState() => IdentifyCodeState();
}

class IdentifyCodeState extends State<IdentifyCodePage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: const MyAppBar(),
        body: Container(
          color: Colors.white,
          width: double.infinity,
          height: double.infinity,
          child: ProviderWidget(
              builder: (ctx, model, child) => CodeInputContainer(
                    count: 6,
                    phone: widget._phone,
                    onRestart: () async {
                      var result = await model.sendMsg(widget._phone);
                      return result.success;
                    },
                    onResult: (code) {
                      model.identifyCode(context, widget._phone, code);
                    },
                  ),
              model: IdentifyCodeModel(),
              child: const Text("data")),

        ));
  }
}
