package com.yang.appkt.util

import android.content.Context
import android.content.Intent
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor

object FlutterEngineManager {

    var mainEngin="id_engine_main"

    //初始化一个flutter引擎
    fun registerEngine(context: Context) {
        // 创建并缓存Flutter引擎
        val flutterEngine = FlutterEngine(context)

        // 开启Flutter引擎的Dart代码执行
        flutterEngine.dartExecutor.executeDartEntrypoint(
            DartExecutor.DartEntrypoint.createDefault()
        )

        // 将Flutter引擎缓存起来
        FlutterEngineCache
            .getInstance()
            .put(mainEngin, flutterEngine)
    }

    // 获取这个引擎
    fun getEngine(context: Context) : Intent {
        val intent = FlutterActivity
            .withCachedEngine(mainEngin)
            .build(context)
        return intent
    }
}