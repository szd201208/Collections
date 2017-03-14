package com.example.videodemo.bazier;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ViewAnimator;

import com.example.videodemo.R;
import com.example.videodemo.bazier.BazierEvaluator;
import com.example.videodemo.bazier.BazierListener;

import java.util.Random;

/**
 * Created by suzhudan on 2017/3/7.
 */

public class FavorLayout extends RelativeLayout {
    private Random random = new Random(); //用于实现随机功能
    private int mWidth, mHeight;  //FavorLayout的宽高
    private int dWidth, dHeight;  //爱心的宽高
    private Drawable[] drawables;
    //随机变速效果
    private Interpolator[] interpolators;
    private Interpolator line = new LinearInterpolator();
    private Interpolator acc = new AccelerateInterpolator();
    private Interpolator dec = new DecelerateInterpolator();
    private Interpolator accdec = new AccelerateDecelerateInterpolator();

    public FavorLayout(Context context) {
        super(context);
        init();
    }

    public FavorLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
//        setBackgroundColor(context.getResources().getColor(R.color.bg_light_green));  //设置背景颜色，方便查看
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
    }

    private void init() {  //初始化
        randomDrawable();
        center();
        interpolators = new Interpolator[4];
        interpolators[0] = line;
        interpolators[1] = acc;
        interpolators[2] = dec;
        interpolators[3] = accdec;
    }

    public void addFavor() {  //添加一个爱心
        final ImageView imageView = new ImageView(getContext());
        imageView.setImageDrawable(drawables[random.nextInt(3)]);
        imageView.setLayoutParams(params);
        addView(imageView);
        Animator set = getAnimator(imageView);
        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }
            @Override
            public void onAnimationEnd(Animator animation) {
                removeView(imageView);
            }
            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        set.start();
    }

    private LayoutParams params;

    public void center() {//底部水平居中
        params = new LayoutParams(dWidth, dHeight);
        params.addRule(CENTER_HORIZONTAL, TRUE);
        params.addRule(ALIGN_PARENT_BOTTOM, TRUE);
        //设置给子view
    }

    public void randomDrawable() {  //随机爱心
        drawables = new Drawable[3];
        drawables[0] = getResources().getDrawable(R.mipmap.zan);
        drawables[1] = getResources().getDrawable(R.mipmap.zan1);
        drawables[2] = getResources().getDrawable(R.mipmap.zan2);
        dWidth = drawables[0].getIntrinsicWidth();
        dHeight = drawables[0].getIntrinsicHeight();
    }

    public AnimatorSet getEnterAnimator(View target) {  //初始动画
        ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(target, View.ALPHA, 0.2f, 1.0f);
        ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(target, View.SCALE_X, 0.2f, 1.0f);
        ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(target, View.SCALE_Y, 0.2f, 1.0f);
        AnimatorSet set = new AnimatorSet();
        set.setDuration(300);
        set.playTogether(alphaAnimator, scaleXAnimator, scaleYAnimator);
        set.setTarget(target);
        set.setInterpolator(new LinearInterpolator());  //以常量速率改变
        return set;
    }

    //获取贝塞尔动画
    public ValueAnimator getBeziorValueAnimator(View target) {
        BazierEvaluator evaluator = new BazierEvaluator(getPointF(2), getPointF(1));
        ValueAnimator valueAnimator = ValueAnimator.ofObject(evaluator, new PointF(mWidth / 2 - dWidth / 2, mHeight-dHeight), new PointF(random.nextInt(getWidth()), 0));
        valueAnimator.addUpdateListener(new BazierListener(target));
        valueAnimator.setTarget(target);
        valueAnimator.setDuration(4000);
        return valueAnimator;
    }

    //合并两种动画
    public AnimatorSet getAnimator(View target) {
        AnimatorSet set = getEnterAnimator(target);
        ValueAnimator animator = getBeziorValueAnimator(target);
        AnimatorSet finalSet = new AnimatorSet();
//        finalSet.playSequentially(set);
        finalSet.play(animator).after(set);
        finalSet.setInterpolator(interpolators[random.nextInt(4)]);
        finalSet.setTarget(target);
        return finalSet;
    }

    public PointF getPointF(int scale) {  //获取中间两点
        PointF pointF = new PointF();
        pointF.x = random.nextInt(mWidth/2 );
        pointF.y = random.nextInt((mHeight/2) / scale);
        return pointF;
    }


}
