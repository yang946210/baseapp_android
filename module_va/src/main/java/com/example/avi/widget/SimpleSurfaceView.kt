package com.example.avi.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.SurfaceHolder
import android.view.SurfaceView
import com.blankj.utilcode.util.ColorUtils
import com.blankj.utilcode.util.LogUtils
import com.example.lib_avi.R
import kotlin.concurrent.thread

//定义画布
private lateinit var mCanvas: Canvas

//定义画笔
private lateinit var mPaint: Paint

//定义SurfaceHolder用于得到对 Surface 操作的方法的调用
private lateinit var mHolder: SurfaceHolder

//开关
private var isDraw = false

class SimpleSurfaceView(context: Context, attrs: AttributeSet?) : SurfaceView(context, attrs) , SurfaceHolder.Callback {
    constructor(context: Context) : this(context, null)

    init {
        LogUtils.d("curr init =======thread=>${Thread.currentThread().name}")
        //调用getHolder()，获得当前SurfaceView中的surface对应的SurfaceHolder
        mHolder = this.holder
        //调用addCallback(),为SurfaceHolder添加一个SurfaceHolder.Callback回调接口。
        mHolder.addCallback(this)
        //实例化画笔
        mPaint = Paint()
        //其他需要的初始化....
    }

    override fun surfaceCreated(p0: SurfaceHolder) {
        LogUtils.d("curr surfaceCreated =======thread=>${Thread.currentThread().name}")
        isDraw=true
        thread {

            //用isDraw判断是否绘制
            while(isDraw)
            {
                LogUtils.d("curr thread =======thread=>${Thread.currentThread().name}")
                try {
                    //调用lockCanvas()方法得到画布
                    mCanvas = mHolder.lockCanvas();
                    //绘制代码同Canvas用法
                    mPaint.color=ColorUtils.getColor(R.color.A2EC8F)
                    mCanvas.drawColor(Color.YELLOW)
                    mCanvas.drawLine(10F, 10F,100f,100f, mPaint)
                }catch( e:Exception) {
                    e.printStackTrace()
                }finally {
                    //绘制完毕调用unlockCanvasAndPost()将画布提交给View显示，参数为你定义的Canvas
                    mHolder.unlockCanvasAndPost(mCanvas);
                }

            }
        }

    }

    /**
     * format
     * width
     * height
     */
    override fun surfaceChanged(p0: SurfaceHolder, format: Int, width: Int, height: Int) {
        LogUtils.d("curr surfaceChanged =======thread=>${Thread.currentThread().name}")
    }

    override fun surfaceDestroyed(p0: SurfaceHolder) {
        LogUtils.d("curr surfaceDestroyed =======thread=>${Thread.currentThread().name}")
        isDraw=false
    }



}