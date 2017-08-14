package com.jiyun.dell.myzuoye811;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by DELL zhanghuirong on 2017/8/11.
 */

public class Circle extends View {


    public Circle(Context context) {
        super(context);
    }

//    public Circle(Context context, @Nullable AttributeSet attrs) {
//        this(context, attrs,0);
//    }
//
//    public Circle(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint=new Paint();
        paint.setColor(Color.GREEN);
        canvas.drawCircle(800,80,50,paint);
    }
}
