package com.example.avi.menusdkapi.image

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import com.example.lib_avi.databinding.ActivityPicShowBinding
import com.yang.ktbase.base.BaseBindActivity

var imageBitmap: Bitmap? = null

var picBitmap: Bitmap? = null

class PicShowActivity : BaseBindActivity<ActivityPicShowBinding>() {
    override fun initView(savedInstanceState: Bundle?) {
        kotlin.runCatching {
            //image加载图片
            imageBitmap = BitmapFactory.decodeStream(assets.open("image_info.webp"))
            picBitmap = BitmapFactory.decodeStream(assets.open("pic1.jpg"))
        }.onFailure {

        }


        binding.apply {
            ivShow.setImageBitmap(imageBitmap)
        }

    }

}