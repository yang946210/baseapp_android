package com.v2ray.ang.util

import android.content.Context
import android.util.Log
import com.v2ray.ang.AppConfig
import java.io.File
import java.io.FileOutputStream
import kotlin.concurrent.thread

object UiUtil {

    public fun copyAssets(context:Context) {
        val extFolder = Utils.userAssetPath(context)
        thread {
            try {
                val geo = arrayOf("geosite.dat", "geoip.dat")
                context.assets.list("")
                    ?.filter { geo.contains(it) }
                    ?.filter { !File(extFolder, it).exists() }
                    ?.forEach {
                        val target = File(extFolder, it)
                        context.assets.open(it).use { input ->
                            FileOutputStream(target).use { output ->
                                input.copyTo(output)
                            }
                        }
                        Log.i(AppConfig.ANG_PACKAGE, "Copied from apk assets folder to ${target.absolutePath}")
                    }
            } catch (e: Exception) {
                Log.e(AppConfig.ANG_PACKAGE, "asset copy failed", e)
            }
        }.start()

    }

    public fun migrateLegacy(context: Context) {
        thread {
            val result = AngConfigManager.migrateLegacyConfig(context)
            if (result != null) {
                Log.i(AppConfig.ANG_PACKAGE, "migrateLegacy: $result")
            }
        }.start()

    }
}