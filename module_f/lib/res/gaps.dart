import 'package:flutter/material.dart';

import 'dimens.dart';

/// 间隔
/// 官方做法：https://github.com/flutter/flutter/pull/54394
class Gaps {
  static const Widget hGap2 = SizedBox(height: Dimens.gap_2);
  static const Widget hGap4 = SizedBox(height: Dimens.gap_4);
  static const Widget hGap5 = SizedBox(height: Dimens.gap_5);
  static const Widget hGap8 = SizedBox(height: Dimens.gap_8);
  static const Widget hGap10 = SizedBox(height: Dimens.gap_10);
  static const Widget hGap12 = SizedBox(height: Dimens.gap_12);
  static const Widget hGap15 = SizedBox(height: Dimens.gap_15);
  static const Widget hGap16 = SizedBox(height: Dimens.gap_16);
  static const Widget hGap20 = SizedBox(height: Dimens.gap_20);
  static const Widget hGap24 = SizedBox(height: Dimens.gap_24);
  static const Widget hGap32 = SizedBox(height: Dimens.gap_32);
  static const Widget hGap50 = SizedBox(height: Dimens.gap_50);
  static const Widget hGap60 = SizedBox(height: Dimens.gap_60);

  static const Widget wGap4 = SizedBox(width: Dimens.gap_4);
  static const Widget wGap5 = SizedBox(width: Dimens.gap_5);
  static const Widget wGap8 = SizedBox(width: Dimens.gap_8);
  static const Widget wGap10 = SizedBox(width: Dimens.gap_10);
  static const Widget wGap12 = SizedBox(width: Dimens.gap_12);
  static const Widget wGap15 = SizedBox(width: Dimens.gap_15);
  static const Widget wGap16 = SizedBox(width: Dimens.gap_16);
  static const Widget wGap20 = SizedBox(width: Dimens.gap_20);
  static const Widget wGap24 = SizedBox(width: Dimens.gap_24);
  static const Widget wGap32 = SizedBox(width: Dimens.gap_32);
  static const Widget wGap50 = SizedBox(width: Dimens.gap_50);
  static const Widget wGap60 = SizedBox(width: Dimens.gap_60);


  static const Widget line = Divider();

  static const Widget hLine = SizedBox(
    width: 0.6,
    height: 24.0,
    child: VerticalDivider(),
  );
  
  static const Widget empty = SizedBox.shrink();

  /// 补充一种空Widget实现 https://github.com/letsar/nil
  /// https://github.com/flutter/flutter/issues/78159
}
