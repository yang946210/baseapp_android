package com.yang.appkt.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;


/**
 * 一个简单的背景表格view
 */
public class BoxView extends View {

    //view的宽高
    private float width =0;
    private float height =0;

    public BoxView(Context context) {
        super(context);
    }

    public BoxView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BoxView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measureWidth = MeasureSpec.getSize(widthMeasureSpec);
        int measureHeight = MeasureSpec.getSize(heightMeasureSpec);
        if (measureWidth != 0 && measureHeight != 0) {
            width = measureWidth;
            height = measureHeight;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setStrokeWidth(2);
        paint.setColor(Color.parseColor("#EAEFF5"));

        //画四条边
        canvas.drawLine(0, 0, width, 0, paint);
        canvas.drawLine(0, 0, 0, height, paint);
        canvas.drawLine(width, 0, width, height, paint);
        canvas.drawLine(0, height, width, height, paint);
        //需要画多少条竖线
        int maxiMum= (int) (width/height);
        //除一下正好保证多余的一点点宽度被均分
        float amendHeight= width/maxiMum;

        for (int i = 1; i < maxiMum; i++) {
           float dexWidth= amendHeight*i;
           if (dexWidth<width){
               canvas.drawLine(dexWidth, 0, dexWidth, height, paint);
           }
        }
    }
}
