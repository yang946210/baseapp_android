package com.example.avi.android.image

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import com.example.lib_avi.databinding.ActivityPicShowBinding
import com.yang.ktbase.base.BaseActivity

var imageBitmap: Bitmap? = null

var picBitmap: Bitmap? = null

class PicShowActivity : BaseActivity<ActivityPicShowBinding>() {
    override fun bindView(savedInstanceState: Bundle?) {
        kotlin.runCatching {
            //image加载图片
            imageBitmap = BitmapFactory.decodeStream(assets.open("image_info.webp"))
            picBitmap = BitmapFactory.decodeStream(assets.open("pic1.jpg"))
        }.onFailure {

        }


        mBinding.apply {
            ivShow.setImageBitmap(imageBitmap)
        }

    }

}