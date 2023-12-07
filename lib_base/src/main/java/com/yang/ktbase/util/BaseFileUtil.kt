package com.yang.ktbase.util

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.annotation.RequiresApi
import java.io.File

/**
 * 文件相关工具类
 */
class BaseFileUtil {
    companion object{

        /**
         * 根据uri获取filePath
         */
        fun getFilePathFromUri(uri: Uri,context:Context): String? {
            val contentResolver: ContentResolver = context.contentResolver
            val returnCursor = contentResolver.query(uri, null, null, null, null)
            returnCursor?.use { cursor ->
                if (cursor.moveToFirst()) {
                    val columnIndex = cursor.getColumnIndex(MediaStore.MediaColumns.DISPLAY_NAME)
                    if (columnIndex != -1) {
                        val fileName = cursor.getString(columnIndex)
                        val path = getMediaStoreFilePath(uri, contentResolver)
                        if (path != null) {
                            return path
                        }
                        val cacheDir = context.externalCacheDir
                        if (cacheDir != null) {
                            val cachePath = cacheDir.path + "/" + fileName
                            return copyFileToCache(uri, cachePath, contentResolver)
                        }
                    }
                }
            }
            return null
        }

        private fun getMediaStoreFilePath(uri: Uri, contentResolver: ContentResolver): String? {
            val projection = arrayOf(MediaStore.MediaColumns.DATA)
            contentResolver.query(uri, projection, null, null, null)?.use { cursor ->
                if (cursor.moveToFirst()) {
                    val columnIndex = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA)
                    return cursor.getString(columnIndex)
                }
            }
            return null
        }

        private fun copyFileToCache(uri: Uri, cachePath: String, contentResolver: ContentResolver): String? {
            contentResolver.openInputStream(uri)?.use { inputStream ->
                File(cachePath).outputStream().use { outputStream ->
                    inputStream.copyTo(outputStream)
                }
                return cachePath
            }
            return null
        }





    }


}