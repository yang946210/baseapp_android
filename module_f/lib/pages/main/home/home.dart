
import 'package:flutter/material.dart';
import 'package:model_flutter/base/provider/provider_widget.dart';
import 'package:model_flutter/entity/model_main.dart';
import 'package:model_flutter/entity/rmd_emtity.dart';
import 'package:model_flutter/global/config.dart';
import 'package:model_flutter/page_model/home_model.dart';
import 'package:model_flutter/res/colours.dart';
import 'package:model_flutter/res/gaps.dart';
import 'package:model_flutter/router/router_utils.dart';
import 'package:model_flutter/utils/image_util.dart';
import 'package:model_flutter/widgets/load_image.dart';


/// tab-首页
class HomePage extends StatefulWidget {
  const HomePage({super.key});

  @override
  State<HomePage> createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  ///顶部banner
  var _titleData = List.empty(growable: true);

  @override
  void initState() {
    super.initState();
    _initData();
  }

  void _initData() {
    _titleData = [
      MainTitleModel("收费", "(万元)", "今日累计", "对比昨日同时", "icon_sf_bg"),
      MainTitleModel("拥堵", "(公立)", "当前拥堵", "对比昨日同时", "icon_yd_bg"),
      MainTitleModel("事故", "(起)", "正在处理", "对比昨日同时", "icon_sg_bg"),
      MainTitleModel("管制", "(个)", "正在管制", "对比昨日同时", "icon_gz_bg"),
    ];
  }

  @override
  Widget build(BuildContext context) {
    return Container(
      width: double.infinity,
      height: double.infinity,
      decoration: const BoxDecoration(
          gradient: LinearGradient(
        begin: Alignment.topCenter,
        end: Alignment.bottomRight,
        colors: [Colours.color_0xFEE7E7, Colours.color_0xF2F5FA],
        // 颜色属性，可以添加多个颜色进去形成多色渐变
        stops: [0.0, 0.7], // 颜色停靠点，这里是从顶部左侧0.0开始渐变，到底部右侧1.0结束渐变
      )),
      child: SingleChildScrollView(
          padding: const EdgeInsets.symmetric(horizontal: 15),
          child: ProviderWidget(
            onModelReady: (model) => {model.getMenus(), model.getRmdList()},
            model: HomeModel(),
            builder: (context, model, child) => Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                SizedBox(
                  height: 45,
                  width: double.infinity,
                  child: Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      const Expanded(
                          child: Text(
                        "home",
                        style: TextStyle(color: Colours.color_0x0E0E0E, fontWeight: FontWeight.bold, fontSize: 24),
                      )),
                      Column(
                        mainAxisAlignment: MainAxisAlignment.center,
                        crossAxisAlignment: CrossAxisAlignment.center,
                        children: [
                          Image.asset(
                            ImageUtils.getImgPath("icon_scan_main"),
                            width: 20,
                            height: 20,
                          ),
                          const Text(
                            "扫码登录",
                            style: TextStyle(fontSize: 10, color: Color(0xff0B151E)),
                          )
                        ],
                      ),
                      Gaps.wGap12,
                      Column(
                        mainAxisAlignment: MainAxisAlignment.center,
                        crossAxisAlignment: CrossAxisAlignment.center,
                        children: [
                          Image.asset(
                            ImageUtils.getImgPath("icon_sign"),
                            width: 20,
                            height: 20,
                          ),
                          const Text(
                            "打卡",
                            style: TextStyle(fontSize: 10, color: Color(0xff0B151E)),
                          )
                        ],
                      )
                    ],
                  ),
                ),
                Gaps.hGap15,
                SizedBox(
                  width: double.infinity,
                  child: GestureDetector(
                    onTap: () {
                      RouterUtils.openWebView(context, Config.urlProtocol,title:  "xx");
                    },
                    child: Stack(
                      children: [
                        ClipRRect(
                          borderRadius: BorderRadius.circular(5),
                          child: Image.asset(
                            ImageUtils.getImgPath("icon_home_bg"),
                            width: double.infinity,
                            fit: BoxFit.fill,
                            height: 120,
                          ),
                        ),
                        Positioned(
                          top: 30,
                          left: 16,
                          child: Image.asset(
                            ImageUtils.getImgPath("icon_main_logo"),
                            width: 100,
                            height: 30,
                          ),
                        ),
                        const Positioned(
                            top: 70,
                            left: 16,
                            child: Text(
                              "title",
                              style: TextStyle(color: Colors.white70, fontSize: 16),
                            )),
                        Positioned(
                            top: 32,
                            right: 15,
                            child: Container(
                              width: 80,
                              height: 25,
                              decoration: BoxDecoration(color: Colors.white70, borderRadius: BorderRadius.circular(20)),
                              child: Row(
                                mainAxisAlignment: MainAxisAlignment.center,
                                children: [
                                  const Text(
                                    "公司简介",
                                    style: TextStyle(color: Colours.color_0xD90910, fontSize: 12),
                                  ),
                                  Gaps.wGap5,
                                  Image.asset(
                                    ImageUtils.getImgPath("icon_main_right"),
                                    width: 10,
                                    height: 10,
                                  )
                                ],
                              ),
                            ))
                      ],
                    ),
                  ),
                ),
                Gaps.hGap15,
                Row(
                  crossAxisAlignment: CrossAxisAlignment.center,
                  children: [
                    Image.asset(ImageUtils.getImgPath("icon_red_point"), width: 20, height: 20),
                    Gaps.wGap4,
                    const Text("公路运营实时数据", style: TextStyle(color: Colours.color_0x0E0E0E, fontSize: 20, fontWeight: FontWeight.bold))
                  ],
                ),
                Gaps.hGap10,
                SizedBox(
                  height: 130,
                  child: Row(
                    children: [
                      TitleItem(_titleData[0]),
                      Gaps.wGap8,
                      TitleItem(_titleData[1]),
                      Gaps.wGap8,
                      TitleItem(_titleData[2]),
                      Gaps.wGap8,
                      TitleItem(_titleData[3]),
                    ],
                  ),
                ),
                Gaps.hGap16,
                Container(
                  decoration: BoxDecoration(color: Colors.white, borderRadius: BorderRadius.circular(5)),
                  child: GridView.builder(
                      shrinkWrap: true,
                      // 这个属性使得GridView独立于外部SingleChildScrollView
                      physics: const NeverScrollableScrollPhysics(),
                      itemCount: model.menuList.length,
                      gridDelegate: const SliverGridDelegateWithFixedCrossAxisCount(
                          crossAxisCount: 5, // 横轴元素个数
                          crossAxisSpacing: 0, // 横轴间距
                          mainAxisSpacing: 0, // 纵轴间距
                          childAspectRatio: 0.88),
                      itemBuilder: (context, index) => GestureDetector(
                            onTap: () {
                              model.onMenuClick(context, model.menuList[index]);
                            },
                            child: Column(
                              mainAxisAlignment: MainAxisAlignment.center,
                              crossAxisAlignment: CrossAxisAlignment.center,
                              children: [
                                Image.network(
                                  Config.urlBase + model.menuList[index].getIconDrawableId(index),
                                  errorBuilder: (ctx, error, sta) => Image.asset(
                                    ImageUtils.getImgPath("icon_more_menu"),
                                    fit: BoxFit.fill,
                                    width: 44,
                                    height: 44,
                                  ),
                                  fit: BoxFit.fill,
                                  width: 50,
                                  height: 45,
                                ),
                                Text(
                                  "${model.menuList[index].menuName}",
                                  style: const TextStyle(fontSize: 13, color: Colours.color_0x292B2E),
                                  textAlign: TextAlign.center,
                                )
                              ],
                            ),
                          )),
                ),
                Gaps.hGap10,
                const Text(
                  "为你推荐",
                  style: TextStyle(color: Colours.color_0x0E0E0E, fontWeight: FontWeight.bold, fontSize: 18),
                ),
                Gaps.hGap10,
                GridView.builder(
                    shrinkWrap: true,
                    // 这个属性使得GridView独立于外部SingleChildScrollView
                    physics: const NeverScrollableScrollPhysics(),
                    itemCount: model.rmdEntity?.list.length ?? 0,
                    gridDelegate: const SliverGridDelegateWithFixedCrossAxisCount(
                        crossAxisCount: 2, // 横轴元素个数
                        crossAxisSpacing: 10,
                        mainAxisSpacing: 10,
                        childAspectRatio: 1.06),
                    itemBuilder: (context, index) => RecommendItem(model.rmdEntity!.list[index])),
                Gaps.hGap10,
              ],
            ),
          )),
    );
  }
}

///标题item布局
class TitleItem extends StatelessWidget {
  final MainTitleModel _data;

  const TitleItem(this._data, {super.key});

  @override
  Widget build(BuildContext context) {
    return Expanded(
        child:GestureDetector(
          onTap: (){
            RouterUtils.openWebView(context, "http://192.168.91.63:4000/bigdata");
          },
          child:  Stack(
            children: [
              Positioned(
                child: Image.asset(
                  ImageUtils.getImgPath(_data.bgPath),
                  fit: BoxFit.fill,
                  width: double.infinity,
                ),
              ),
              Positioned.fill(
                child: Column(
                  mainAxisAlignment: MainAxisAlignment.center,
                  crossAxisAlignment: CrossAxisAlignment.center,
                  children: [
                    Text(
                      _data.title,
                      style: const TextStyle(color: Colors.white, fontSize: 15),
                    ),
                    Text(_data.titleUnit, style: const TextStyle(color: Colors.white70, fontSize: 9)),
                    Text(_data.nextTitle, style: const TextStyle(color: Colors.white, fontSize: 10)),
                    const Text("0", style: TextStyle(color: Colors.white, fontSize: 15, fontWeight: FontWeight.bold)),
                    Text(_data.info, style: const TextStyle(color: Colors.white54, fontSize: 10)),
                    const Text("0", style: TextStyle(color: Colors.white, fontSize: 15, fontWeight: FontWeight.bold)),
                  ],
                ),
              )
            ],
          ),
        ));
  }
}

///为你推荐
class RecommendItem extends StatefulWidget {
  const RecommendItem(this._model, {super.key});

  final Rmd _model;

  ///根据id获取标签名
  String _getTag(int? type) {
    String tagName;
    switch (type) {
      case 1:
        tagName = "新闻热点";
      case 2:
        tagName = "视频监控";
      case 4:
        tagName = "交通事故";
      case 5:
        tagName = "活跃排名";
      default:
        tagName = "其它";
    }
    return tagName;
  }

  ///根据id获取标签名
  int _getTagColor(int? type) {
    int tagName;
    switch (type) {
      case 1:
        tagName = 0xFF27D3AB;
      case 2:
        tagName = 0xFF4287d5;
      case 4:
        tagName = 0xFFFF8A80;
      case 5:
        tagName = 0xFF8A72FF;
      default:
        tagName = 0xFF4287d5;
    }
    return tagName;
  }

  @override
  State<StatefulWidget> createState() => _RecommendItemState();
}

class _RecommendItemState extends State<RecommendItem> {
  @override
  Widget build(BuildContext context) {
    return Container(
      decoration: BoxDecoration(color: Colors.white, borderRadius: BorderRadius.circular(5)),
      child: Column(
        children: [
          Stack(
            children: [
              ClipRRect(
                child: LoadImage(
                  "${widget._model.img}",
                  width: double.infinity,
                  height: 85,
                ),
              ),
              Container(
                height: 18,
                decoration: BoxDecoration(
                    borderRadius: const BorderRadius.only(topLeft: Radius.circular(5), bottomRight: Radius.circular(5)),
                    color: Color(widget._getTagColor(widget._model.rmdType))),
                child: Padding(
                  padding: const EdgeInsets.fromLTRB(7, 0, 7, 0),
                  child: Text(
                    widget._getTag(widget._model.rmdType),
                    style: const TextStyle(fontSize: 11, color: Colors.white70),
                  ),
                ),
              )
            ],
          ),
          Container(
            margin: const EdgeInsets.all(10),
            child: Text(
              "${widget._model.rmdTitle}",
              maxLines: 2,
              overflow: TextOverflow.ellipsis,
              style: const TextStyle(
                color: Colours.color_0x0E0E0E,
                fontSize: 14,
              ),
            ),
          ),
        ],
      ),
    );
  }
}
