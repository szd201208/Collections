package com.example.videodemo.bazier;

import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.graphics.PointF;
import android.view.View;

/**
 * Created by suzhudan on 2017/3/7.
 */

public class BazierEvaluator implements TypeEvaluator<PointF> {
    private PointF pointF1, pointF2;//途径的两个点

    public BazierEvaluator(PointF pointF1, PointF pointF2) {
        this.pointF1 = pointF1;
        this.pointF2 = pointF2;
    }

    @Override
    public PointF evaluate(float time, PointF startValue, PointF endValue) {
        PointF point=new PointF(); //结果
        //套入公式
        point.x=startValue.x*(1-time)*(1-time)*(1-time)+3*pointF1.x*time*(1-time)*(1-time)+3*pointF2.x*time*time*(1-time)+endValue.x*time*time*time;
        point.y=startValue.y*(1-time)*(1-time)*(1-time)+3*pointF1.y*time*(1-time)*(1-time)+3*pointF2.y*time*time*(1-time)+endValue.y*time*time*time;
        return point;
    }
}
