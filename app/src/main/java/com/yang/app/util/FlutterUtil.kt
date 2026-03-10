package com.yang.app.util

import android.content.Context
import android.content.Intent
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor

object FlutterEngineManager {

    private const val MAIN_ENGINE_ID = "id_engine_main"

    // 初始化一个flutter引擎
    fun init(context: Context) {
        // 创建并缓存Flutter引擎
        val flutterEngine = FlutterEngine(context)

        // 开启Flutter引擎的Dart代码执行
        flutterEngine.dartExecutor.executeDartEntrypoint(
            DartExecutor.DartEntrypoint.createDefault()
        )

        // 将Flutter引擎缓存起来
        FlutterEngineCache
            .getInstance()
            .put(MAIN_ENGINE_ID, flutterEngine)
    }

    // 获取启动 Intent
    fun getIntent(context: Context): Intent {
        return FlutterActivity
            .withCachedEngine(MAIN_ENGINE_ID)
            .build(context)
    }
}