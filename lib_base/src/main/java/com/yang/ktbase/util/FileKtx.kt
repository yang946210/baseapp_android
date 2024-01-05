package com.yang.ktbase.util


import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import java.io.File
import java.text.DecimalFormat



/**
 * 获取文件大小(带单位)
 */
fun File.getSizeWithUnit(): String {
    val kb = 1024
    val mb = kb * 1024
    val gb = mb * 1024
    val df = DecimalFormat("0.00")
    length().let {
        return when {
            it / gb >= 1 -> df.format(it / gb.toFloat()) + "GB"
            it / mb >= 1 -> df.format(it / mb.toFloat()) + "MB"
            it / kb >= 1 -> df.format(it / kb.toFloat()) + "KB"
            else -> it.toString() + "B"
        }
    }
}

/**
 * 根据uri获取filePath
 */
fun Uri.getFilePath(context: Context): String? {
    val contentResolver: ContentResolver = context.contentResolver
    val returnCursor = contentResolver.query(this, null, null, null, null)
    returnCursor?.use { cursor ->
        if (cursor.moveToFirst()) {
            val columnIndex = cursor.getColumnIndex(MediaStore.MediaColumns.DISPLAY_NAME)
            if (columnIndex != -1) {
                val fileName = cursor.getString(columnIndex)
                val path = getMediaStoreFilePath(this, contentResolver)
                if (path != null) {
                    return path
                }
                val cacheDir = context.externalCacheDir
                if (cacheDir != null) {
                    val cachePath = cacheDir.path + "/" + fileName
                    return copyFileToCache(this, cachePath, contentResolver)
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

