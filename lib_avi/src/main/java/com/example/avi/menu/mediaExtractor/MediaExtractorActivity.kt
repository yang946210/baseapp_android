package com.example.avi.menu.mediaExtractor

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.content.Intent
import android.media.MediaExtractor
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.annotation.RequiresApi
import com.example.lib_avi.databinding.ActivityMediaExtractorBinding
import com.yang.ktbase.base.BaseBindActivity
import java.io.File


/**
 * 音视频解封装
 *
 */
class MediaExtractorActivity : BaseBindActivity<ActivityMediaExtractorBinding>() {

    override fun initView(savedInstanceState: Bundle?) {
        binding.apply {
            tvChoose.setOnClickListener {
                val intent = Intent(Intent.ACTION_GET_CONTENT)
                intent.type = "*/*" // 设置文件类型，这里设置为任意类型的文件
                intent.addCategory(Intent.CATEGORY_OPENABLE)
                startActivityForResult(intent, 10086)
            }
        }
    }


    /**
     * 使用MediaExtractor对音视频进行解封装
     */
    private fun getInfoByFilePath(filePath:String):String{
        val stringInfo=StringBuffer("文件地址：${filePath}\n\n")
        val extractor = MediaExtractor()
        extractor.setDataSource(filePath)
        stringInfo.append("轨道数：${extractor.trackCount}\n\n")

        for ( i in 1..extractor.trackCount){
            val format=extractor.getTrackFormat(i-1)
            stringInfo.append("通道$i：$format\n")
        }
        return stringInfo.toString()
    }


    @RequiresApi(Build.VERSION_CODES.Q)
    @SuppressLint("SetTextI18n")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 10086 && resultCode == RESULT_OK) {
            val uri = data?.data
            val filePath = getFilePathFromUri(uri!!)
            val info=getInfoByFilePath(filePath!!)
            binding.tvInfo.text="当前信息：${info}"
        }
    }


    /**
     * 根据uri获取filePath
     */
    @RequiresApi(Build.VERSION_CODES.Q)
    private fun getFilePathFromUri(uri: Uri): String? {
        val contentResolver: ContentResolver = contentResolver
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
                    val cacheDir = externalCacheDir
                    if (cacheDir != null) {
                        val cachePath = cacheDir.path + "/" + fileName
                        return copyFileToCache(uri, cachePath, contentResolver)
                    }
                }
            }
        }
        return null
    }

    @RequiresApi(Build.VERSION_CODES.Q)
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

    @RequiresApi(Build.VERSION_CODES.Q)
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