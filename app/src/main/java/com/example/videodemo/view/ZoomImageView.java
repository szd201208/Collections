package com.example.videodemo.view;

import android.content.ComponentName;
import android.content.Context;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.DebugUtils;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import com.example.videodemo.utils.DeviceUtil;

/**
 * Created by suzhudan on 2017/2/7.
 */

public class ZoomImageView extends ImageView implements ScaleGestureDetector.OnScaleGestureListener, View.OnTouchListener, ViewTreeObserver.OnGlobalLayoutListener {
    private static final String TAG = ZoomImageView.class.getSimpleName();

    public static final float MAX_SCALE = 4.0f;
    public static final float SCALE_MID = 2;
    public static final float SCALE_MAX = 4;

    private Context context;

    private float initScale = 1.0f; //初始化缩放比例
    private float[] matixValues = new float[9];  //用来放9个存储值
    private Matrix scaleMatirx = new Matrix();
    private ScaleGestureDetector scaleGestureDetector = null;
    private GestureDetector gestureDetector;

    public ZoomImageView(Context context) {
        this(context, null);
    }

    private boolean isAutoScale = false;  //是否正在自动缩放

    public ZoomImageView(final Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        scaleGestureDetector = new ScaleGestureDetector(context, this);
        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onDoubleTap(MotionEvent e) {
                if (isAutoScale) {
                    return true;
                }
                float x = e.getX();
                float y = e.getY();
                if (getScale() < SCALE_MID) {
                    ZoomImageView.this.postDelayed(new AutoScaleRunnable(SCALE_MID, x, y), 10);
                    isAutoScale = true;
                } else if (getScale() > SCALE_MID && getScale() < SCALE_MAX) {
                    ZoomImageView.this.postDelayed(new AutoScaleRunnable(SCALE_MAX, x, y), 10);
                    isAutoScale = true;
                } else {
                    ZoomImageView.this.postDelayed(new AutoScaleRunnable(initScale, x, y), 10);
                    isAutoScale = true;
                }
                return true;
            }
        });
        touchSlop= ViewConfiguration.get(context).getScaledTouchSlop();
        this.setScaleType(ScaleType.MATRIX);
        this.setOnTouchListener(this);
    }


    @Override
    public boolean onScale(ScaleGestureDetector detector) {
        Log.e(TAG, "onScale");
        float scale = getScale();
        float scaleFactor = detector.getScaleFactor();  //比例因子
        if (getDrawable() == null)
            return true;
        if ((scale < MAX_SCALE && scaleFactor > 1.0f) || (scale > initScale && scaleFactor < 1.0f)) {
            if (scaleFactor * scale < initScale) {
                scaleFactor = initScale / scale;
            }
            if (scaleFactor * scale > MAX_SCALE) {
                scaleFactor = MAX_SCALE / scale;
            }
            //设置缩放比例
//            scaleMatirx.postScale(scaleFactor,scaleFactor, DeviceUtil.getScreenWidth(context)/2,DeviceUtil.getScreenHeight(context)/2);  //缩放中心为屏幕中心
            scaleMatirx.postScale(scaleFactor, scaleFactor, detector.getFocusX(), detector.getFocusY());//缩放中心为触摸点
            checkBorderAndCenterWhenScale();
            setImageMatrix(scaleMatirx);
        }
        return true;
    }

    @Override
    public boolean onScaleBegin(ScaleGestureDetector detector) {
        Log.e(TAG, "onScaleBegin");
        return true;
    }

    @Override
    public void onScaleEnd(ScaleGestureDetector detector) {
        Log.e(TAG, "onScaleEnd");
    }

    private int lastPointerCount = 0;
    private float lastX = 0, lastY = 0;
    private boolean isCanDrag = false;
    private boolean isHorizontalTranslate, isVerticalTranslate;
    private float touchSlop;

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if(gestureDetector.onTouchEvent(event))return true;
        scaleGestureDetector.onTouchEvent(event);
        //触摸点数量
        int pointerCount = event.getPointerCount();
        //获取多个触摸点平均值
        float x = 0, y = 0;
        for (int i = 0; i < pointerCount; i++) {
            x += event.getX(i);
            y = event.getY(i);
        }
        x = x / pointerCount;
        y = y / pointerCount;
        //触摸点发生变化时，重置lastX,lastY
        if (pointerCount != lastPointerCount) {
            isCanDrag = false;
            lastX = x;
            lastY = y;
        }
        lastPointerCount = pointerCount;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:// 不被拦截
                if(getMatrixRectF().width()>DeviceUtil.getScreenWidth(context)||getMatrixRectF().height()>DeviceUtil.getScreenHeight(context)){
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if(getMatrixRectF().width()>DeviceUtil.getScreenWidth(context)||getMatrixRectF().height()>DeviceUtil.getScreenHeight(context)){
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
                float dx = event.getX() - lastX;
                float dy = event.getY() - lastY;
                if (!isCanDrag) {
                    isCanDrag = Math.sqrt(dx * dx + dy * dy) >= touchSlop;
                }
                if (isCanDrag) {
                    RectF rect = getMatrixRectF();
                    if (getDrawable() != null) {
                        //到达边界的时候将移动事件传递给viewpager
                        if(rect.left==0&&dx>0){
                            getParent().requestDisallowInterceptTouchEvent(false);
                        }
                        if(rect.right==DeviceUtil.getScreenWidth(context)&&dx<0){
                            getParent().requestDisallowInterceptTouchEvent(false);
                        }

                        isHorizontalTranslate = isVerticalTranslate = true;
                        //如果宽度小于屏幕宽度，则禁止移动
                        if (rect.width() < DeviceUtil.getScreenWidth(context)) {
                            dx = 0;
                            isHorizontalTranslate = false;
                        }
                        if (rect.height() < DeviceUtil.getScreenHeight(context)) {
                            dy = 0;
                            isVerticalTranslate = false;
                        }
                        scaleMatirx.postTranslate(dx, dy);
                        checkBorder();
                        setImageMatrix(scaleMatirx);
                    }
                }
                lastX = x;
                lastY = y;
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                lastPointerCount = 0;
                break;
        }
        return true;
    }

    //移动的时候进行边界判断，主要判断宽或高大于屏幕的
    public void checkBorder() {
        RectF rectF = getMatrixRectF();
        float deltaX = 0, deltaY = 0;
        //判断移动或缩放后图片是否超出边界
        if (rectF.top > 0 && isVerticalTranslate) {
            deltaY = -rectF.top;
        }
        if (rectF.bottom < DeviceUtil.getScreenHeight(context) && isVerticalTranslate) {
            deltaY = DeviceUtil.getScreenHeight(context) - rectF.bottom;
        }
        if (rectF.left > 0 && isHorizontalTranslate) {
            deltaX = -rectF.left;
        }
        if (rectF.right < DeviceUtil.getScreenWidth(context) && isHorizontalTranslate) {
            deltaX = DeviceUtil.getScreenWidth(context) - rectF.right;
        }
        scaleMatirx.postTranslate(deltaX, deltaY);

    }

    public float getScale() {
        scaleMatirx.getValues(matixValues);
        return matixValues[Matrix.MSCALE_X];
    }

    //移动图片到屏幕中心
    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        getViewTreeObserver().addOnGlobalLayoutListener(this);
    }

    private boolean isFirst = true;

    @Override
    public void onGlobalLayout() {
        if (!isFirst) return;
        Drawable d = getDrawable();
        if (d == null) return;
        int screenWidth = DeviceUtil.getScreenWidth(context);
        int screenHeight = DeviceUtil.getScreenHeight(context);
        int drawableWidth = d.getIntrinsicWidth();
        int drawableHeight = d.getIntrinsicHeight();
        //图片宽或高大于屏幕，均缩放至屏幕大小
        float scale = 1.0f;
        if (drawableWidth > screenWidth && drawableHeight <= screenHeight) {
            scale = screenWidth / drawableWidth;
        }
        if (drawableHeight > screenHeight && drawableWidth <= screenWidth) {
            scale = screenHeight / drawableHeight;
        }
        //如果宽高都大于屏幕，则取最小值
        if (drawableWidth > screenWidth && drawableHeight > screenHeight) {
            scale = Math.min(screenWidth / drawableWidth, screenHeight / drawableHeight);
        }
        initScale = scale;
        //图片移至屏幕中心
        scaleMatirx.postTranslate((screenWidth - drawableWidth) / 2, (screenHeight - drawableHeight) / 2);
        scaleMatirx.postScale(initScale, initScale, screenWidth / 2, screenHeight / 2);
        setImageMatrix(scaleMatirx);
        isFirst = false;
    }

    //缩放的时候，进行图片显示范围的控制
    private void checkBorderAndCenterWhenScale() {
        RectF rect = getMatrixRectF();
        float deltaX = 0;
        float deltaY = 0;
        int screenWidth = DeviceUtil.getScreenWidth(context);
        int screenHeight = DeviceUtil.getScreenHeight(context);
        //如果宽或高大于屏幕，控制其范围
        if (rect.width() >= screenWidth) {
            if (rect.left > 0) {
                deltaX = -rect.left;
            }
            if (rect.right < screenWidth) {
                deltaX = screenWidth - rect.right;
            }
        }
        if (rect.height() >= screenHeight) {
            if (rect.top > 0) {
                deltaY = -deltaY;
            }
            if (rect.bottom < screenHeight) {
                deltaY = screenHeight - rect.bottom;
            }
        }
        //如果宽或高小于屏幕，则让其居中
        if (rect.width() < screenWidth) {
            deltaX = screenWidth / 2 + rect.width() / 2 - rect.right;
        }
        if (rect.height() < screenHeight) {
            deltaY = screenHeight / 2 + rect.height() / 2 - rect.bottom;
        }
        scaleMatirx.postTranslate(deltaX, deltaY);
    }

    //根据图片的marix获取图片的范围
    public RectF getMatrixRectF() {
        Matrix matrix = scaleMatirx;
        Drawable d = getDrawable();
        RectF rect = new RectF();
        if (d != null) {
            rect.set(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
            matrix.mapRect(rect);
        }
        return rect;
    }

    class AutoScaleRunnable implements Runnable {
        static final float BIGGER=1.07f;
        static final float SMALLER=0.93f;
        private float mScaleSize,x,y;
        private float tmpScale;
        AutoScaleRunnable(float scaleSize, float x, float y) {
            this.mScaleSize=scaleSize;
            this.x=x;
            this.y=y;
            if(getScale()<mScaleSize){
                tmpScale=BIGGER;
            }else{
                tmpScale=SMALLER;
            }
        }
        @Override
        public void run() {
            //缩放
            scaleMatirx.postScale(tmpScale,tmpScale,x,y);
            checkBorderAndCenterWhenScale();
            setImageMatrix(scaleMatirx);
            float currentScale=getScale();
            if((tmpScale>1.0f&&currentScale<mScaleSize)||(tmpScale<1.0f&&currentScale>mScaleSize)){
                ZoomImageView.this.postDelayed(this,10);
            }else{
                float deltaScale=mScaleSize/currentScale;
                scaleMatirx.postScale(deltaScale,deltaScale,x,y);
                checkBorderAndCenterWhenScale();
                setImageMatrix(scaleMatirx);
                isAutoScale=false;
            }

        }
    }
}
