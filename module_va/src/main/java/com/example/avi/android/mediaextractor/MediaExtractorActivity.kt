package com.example.avi.android.mediaextractor

import android.annotation.SuppressLint
import android.content.Intent
import android.media.MediaExtractor
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.example.lib_avi.databinding.ActivityMediaExtractorBinding
import com.yang.ktbase.activity.BaseActivity
import com.yang.ktbase.util.getFilePath


/**
 * 音视频解封装
 *
 */
class MediaExtractorActivity : BaseActivity<ActivityMediaExtractorBinding>() {

    override fun initView(savedInstanceState: Bundle?) {
        mBinding.apply {
            tvChoose.setOnClickListener {
                val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
                intent.type = "*/*" // 设置文件类型，这里设置为任意类型的文件
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
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
            val filePath =data?.data?.getFilePath(this@MediaExtractorActivity)
            val info=getInfoByFilePath(filePath!!)
            mBinding.tvInfo.text="当前信息：${info}"
        }
    }
}