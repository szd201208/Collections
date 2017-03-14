package com.example.videodemo.bazier;

import android.animation.ValueAnimator;
import android.graphics.PointF;
import android.view.View;

/**
 * Created by suzhudan on 2017/3/7.
 */

public class BazierListener implements ValueAnimator.AnimatorUpdateListener {
    private View target;

    public BazierListener(View target) {
        this.target = target;
    }

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {  //动画过程中随时改变其属性
        PointF pointF = (PointF) animation.getAnimatedValue();
        target.setX(pointF.x);
        target.setY(pointF.y);
        target.setAlpha(1-animation.getAnimatedFraction());
    }
}
