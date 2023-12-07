package com.example.avi.menusdkapi.mediacodec

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.example.lib_avi.databinding.ActivityMediaCodecBinding
import com.yang.ktbase.base.BaseBindActivity
import com.yang.ktbase.util.BaseFileUtil


/**
 * 使用MediaCodec
 * PCM-->ACC硬编/硬解
 * YUV-->H.264硬编/硬解
 */
class MediaCodecActivity : BaseBindActivity<ActivityMediaCodecBinding>() {

    override fun initView(savedInstanceState: Bundle?) {
        binding.apply {
            tvChoose.setOnClickListener {
                val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
                intent.type = "*/*" // 设置文件类型，这里设置为任意类型的文件
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                startActivityForResult(intent, 10086)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode==10086&&resultCode== Activity.RESULT_OK){
            val filePath=data?.let {
                BaseFileUtil.getFilePathFromUri(it.data!!,this@MediaCodecActivity)
            }
        }
    }
}