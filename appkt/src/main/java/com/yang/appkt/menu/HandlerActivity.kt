package com.yang.appkt.menu

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import com.yang.appkt.databinding.ActivityHandlerBinding
import com.yang.ktbase.base.BaseBindActivity
import com.yang.ktbase.ext.logD
import com.yang.ktbase.ext.notNull
import kotlinx.coroutines.*


/**
 * handler线程切换
 */
class HandlerActivity : BaseBindActivity<ActivityHandlerBinding>(), CoroutineScope by MainScope() {

    private val msgWhat: Int = 10001

    private var msg = 0

    private lateinit var ioHandler: Handler

    private var mainHandler: Handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            binding.tvShow.text = "${msg.obj}====${Thread.currentThread()}"
        }
    }


    override fun initView(savedInstanceState: Bundle?) {
        launch(Dispatchers.IO) {
            Looper.prepare()
            Looper.myLooper().notNull({ "========notNull".logD() }, { "=========Null".logD() })
            ioHandler = object : Handler(Looper.myLooper()!!) {
                override fun handleMessage(msg: Message) {
                    super.handleMessage(msg)
                    "${msg.obj}====${Thread.currentThread()}".also {
                        runOnUiThread {
                            binding.tvShow.text = it
                        }
                    }
                }
            }
            Looper.loop()
        }

        binding.apply {
            tvSendMsg.setOnClickListener {
                mainHandler.sendMessage(Message().apply {
                    this.what = msgWhat
                    this.obj = msg++
                })
            }
            tvSendMsgDelay.setOnClickListener {
                mainHandler.sendMessageDelayed(Message().apply {
                    this.what = msgWhat
                    this.obj = msg++
                }, 2000)
            }


            tvSendMsg2.setOnClickListener {
                ioHandler.sendMessage(Message().apply {
                    this.what = msgWhat
                    this.obj = msg++
                })
            }
            tvSendMsgDelay2.setOnClickListener {
                kotlin.runCatching {
                    ioHandler.sendMessageDelayed(Message().apply {
                        this.what = msgWhat
                        this.obj = msg++
                    }, 2000)
                }.onFailure {
                    it.toString().logD()
                }
            }

            tvPost.setOnClickListener {
                var index =0;

                var runnable = object: Runnable {
                    override fun run() {
                        if (index == 16) {
                            return
                        }
                        Thread.currentThread().toString().logD("${index++}")
                        mainHandler.postDelayed(this, 300)
                    }
                }
                mainHandler.postDelayed(runnable, 300)
            }



            tvPost2.setOnClickListener {
                mainHandler.postDelayed({
                    "1000post".logD("post")
                },1000)
            }
        }
    }


}