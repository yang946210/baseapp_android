/// 图片相关工具类

class ImageUtils {

  static String getImgPath(String name,
      {ImageFormat format = ImageFormat.png}) {
    return 'assets/images/$name.${format.value}';
  }

  static String getLottiePath(String name,
      {LottieFormat format = LottieFormat.json}) {
    return 'assets/lottie/$name.${format.value}';
  }
}

enum ImageFormat { png, jpg, gif, webp }

enum LottieFormat { json }

extension ImageFormatExtension on ImageFormat {
  String get value => ['png', 'jpg', 'gif', 'webp'][index];
}

extension LottieFormatExtension on LottieFormat {
  String get value => ['json'][index];
}