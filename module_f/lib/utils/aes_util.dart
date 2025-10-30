import 'dart:math';
import 'dart:typed_data';
import 'dart:convert';
import 'package:pointycastle/block/aes_fast.dart';
import 'package:pointycastle/block/modes/cbc.dart';
import 'package:pointycastle/padded_block_cipher/padded_block_cipher_impl.dart';
import 'package:pointycastle/paddings/pkcs7.dart';
import 'package:pointycastle/pointycastle.dart';
import 'package:flutter/services.dart';

const String AES_ENCODE_PWD = "Y35V1XQECfNPnV^5"; //加密
const String AES_DECODE_PWD = "3u8uB4u0*EE4CQgP"; //解密
const List<String> _HEX_DIGITS = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'];
const String AES_PADDING = "PKCS7"; //服务端是PKCS5,Dart没有就用PKCS7？

class AESUtil {
  ///aes加密函数
  static Uint8List encrypt(String key, String data) {
    try {
      final iv = _getRandomIV();
      final cipher = PaddedBlockCipherImpl(
        PKCS7Padding(),
        CBCBlockCipher(AESFastEngine()),
      );
      cipher.init(
        true,
        PaddedBlockCipherParameters(
          ParametersWithIV<KeyParameter>(KeyParameter(utf8.encode(key)), iv),
          null,
        ),
      );
      Uint8List inputData = utf8.encode(data);
      final inTerm = cipher.process(inputData);
      return Uint8List.fromList(iv + inTerm);
    } catch (e) {
      throw Exception(e.toString());
    }
  }

  ///解密函数
  static Uint8List decrypt(String key, Uint8List input) {
    try {
      var iv = input.sublist(0, 16);
      var encryptedBytes = input.sublist(16);
      final cipher = PaddedBlockCipherImpl(
        PKCS7Padding(),
        CBCBlockCipher(AESFastEngine()),
      );
      cipher.init(
        false,
        PaddedBlockCipherParameters(
          ParametersWithIV<KeyParameter>(KeyParameter(utf8.encode(key)), iv),
          null,
        ),
      );
      return cipher.process(encryptedBytes);
    } catch (e) {
      throw Exception(e.toString());
    }
  }

  /// 计算偏移量
  static Uint8List _getRandomIV() {
    final random = Random.secure();
    final bytes = List<int>.generate(16, (_) => random.nextInt(256));
    return Uint8List.fromList(bytes);
  }

  ///byte转string
  static String bytes2HexString(List<int> bytes) {
    if (bytes.isEmpty) {
      return "";
    } else {
      List<String> result = [];
      for (var byte in bytes) {
        result.add(_HEX_DIGITS[byte >> 4 & 0xF]);
        result.add(_HEX_DIGITS[byte & 0xF]);
      }
      return result.join();
    }
  }
}
