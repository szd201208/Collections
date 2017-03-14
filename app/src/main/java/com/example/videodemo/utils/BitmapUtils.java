package com.example.videodemo.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;

/**
 * Created by suzhudan on 2016/12/3.
 */
public class BitmapUtils {

    //图片加文字水印
    public static Bitmap addTimeToBitmap(Bitmap bitmap, String text) {
        //创建一个一样宽高的位图
        Bitmap newBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(newBitmap);
        //画图片
        canvas.drawBitmap(bitmap, 0, 0, new Paint());
        //画文字
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setTextSize(30);
        int textWidth = (int) paint.measureText(text); //宽度
        int textHeight = (int) (paint.ascent() + paint.descent()); //高度
        canvas.drawText(text, 30, 30, paint);
        return newBitmap;
    }

    //图片旋转角度
    public static Bitmap rotateBitmap(Bitmap bitmap, int rotate) {
        Matrix matrix = new Matrix();
        matrix.setRotate((float)rotate,(float) bitmap.getWidth() / 2, (float)bitmap.getHeight() / 2);
//        float targetX=bitmap.getHeight();
//        float targetY=((rotate==90)?0:bitmap.getWidth());
//        float[] values={(float) Math.cos(rotate), (float) -Math.sin(rotate), (float) (-(float)(bitmap.getWidth()/2)*Math.cos(rotate)+(float)(bitmap.getHeight()/2)*Math.sin(rotate)+(float)bitmap.getWidth()/2), (float) Math.sin(rotate), (float) Math.cos(rotate), (float) (-(float)(bitmap.getWidth()/2)*Math.sin(rotate)-(float)(bitmap.getHeight()/2)*Math.cos(rotate)+(float)bitmap.getHeight()/2),0.0f,0.0f,1.0f};
//        matrix.setValues(values);
//        matrix.postTranslate(targetX-values[Matrix.MTRANS_X],targetY-values[Matrix.MTRANS_Y]);
//        Bitmap newBitmap = Bitmap.createBitmap(bitmap.getHeight(), bitmap.getWidth(), Bitmap.Config.ARGB_8888);
//        Canvas canvas=new Canvas(newBitmap);
//        canvas.drawBitmap(newBitmap,matrix,new Paint());
        try{
            Bitmap newBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            if(!bitmap.isRecycled()){
                bitmap.recycle();
            }
            return newBitmap;
        }catch(OutOfMemoryError e){
            e.printStackTrace();
        }
        return null;
    }
}
