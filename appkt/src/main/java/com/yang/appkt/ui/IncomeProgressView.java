package com.yang.appkt.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

public class IncomeProgressView extends View {

    //进度条颜色
    private int progressColor = 0xff67aae4;

    //进度
    private int progress;

    //画进度条
    private final Paint paintBg = new Paint();
    //画文字
    private final Paint paintText = new Paint();

    //画文字时底部的坐标
    private float textBottomY;

    //得到自定义视图的Y轴中心点
    private int viewCenterY;


    //文字
    private String text="";

    //文字总共移动的长度(即从0%到100%文字左侧移动的长度)
    private int totalMovedLength;

    public IncomeProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int viewHeight = getMeasuredHeight();
        int viewWidth = getMeasuredWidth();

        //进度条画笔
        paintBg.setColor(progressColor);
        paintBg.setAntiAlias(true);
        paintBg.setStyle(Paint.Style.FILL);
        paintBg.setStrokeWidth(12);
        paintBg.setStrokeCap(Paint.Cap.ROUND);
        //文字画笔的
        paintText.setColor(0xff0e0e0e);
        paintText.setTextSize(30);
        paintText.setAntiAlias(true);

        //获取文字宽度
        Rect rect = new Rect();
        paintText.getTextBounds(text, 0,text.length(), rect);
        int textWidth = rect.width();

        //获取中州线坐标
        viewCenterY = viewHeight / 2;
        //获取文字底部Y轴坐标
        textBottomY = viewCenterY + rect.height() / 2;
        //文字开始绘制坐标
        totalMovedLength = viewWidth - textWidth;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        //得到float型进度
        float progressFloat = progress / 100.0f;
        //当前文字移动的长度
        float currentMovedLentgh = totalMovedLength * progressFloat;
        //画进度条，长度为从Veiw左端到文字的左侧,-10是为了留点位置画圆圈
        canvas.drawLine(0, viewCenterY, currentMovedLentgh-10, viewCenterY, paintBg);
        //画文字(注意：文字要最后画，因为文字和进度条可能会有重合部分，所以要最后画文字，用文字盖住重合的部分)
        canvas.drawText(text, currentMovedLentgh, textBottomY, paintText);
    }


    /**
     * 设置进度
     */
    public void setProgress(int progress,String text) {
        this.progress = progress;
        this.text=text;
        invalidate();
    }

    /**
     * 设置进度条颜色
     */
    public void setProgressColor(int progressColor){
        this.progressColor=progressColor;
    }
}