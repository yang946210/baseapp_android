package com.example.avi
import android.content.Intent
import android.os.Bundle
import com.example.avi.menu.audio.AudioActivity
import com.example.avi.menu.camera2.Camera2Activity
import com.example.avi.menu.image.PicShowActivity
import com.example.lib_avi.databinding.ActivityAviMenuBinding
import com.yang.ktbase.base.BaseBindActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
class AviMenuActivity : BaseBindActivity<ActivityAviMenuBinding>(), CoroutineScope by MainScope()  {
    override fun initView(savedInstanceState: Bundle?) {
        binding.apply {
            tvImage.setOnClickListener {
                startActivity(Intent(this@AviMenuActivity, PicShowActivity::class.java))
            }
            tvRecording.setOnClickListener {
                startActivity(Intent(this@AviMenuActivity, AudioActivity::class.java))
            }
            tvCamera2.setOnClickListener {
                startActivity(Intent(this@AviMenuActivity, Camera2Activity::class.java))
            }
        }
    }
}