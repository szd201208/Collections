package com.example.videodemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 */
public class DrawView extends View {
    public DrawView(Context context) {
        super(context);
    }

    public DrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint=new Paint();
        paint.setColor(Color.RED);
      /*  canvas.drawText("画圆",20,20,paint);
        canvas.drawCircle(100,100,30,paint);
        paint.setAntiAlias(true);
        canvas.drawCircle(300,100,100,paint);

        canvas.drawText("画曲线",500,20,paint);
        paint.setStyle(Paint.Style.STROKE);  //空心
        RectF rectf=new RectF(550f,20f,650f,40f);
        canvas.drawArc(rectf,180,180,false,paint);

        canvas.drawText("矩形",20,400,paint);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLACK);
        canvas.drawRect(50,40,50,40,paint);*/
        //贝塞尔曲线
        Path p=new Path();
        p.moveTo(100,100); //起点
        p.quadTo(200,200,300,300);
        canvas.drawPath(p,paint);
    }
}
