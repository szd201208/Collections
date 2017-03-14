package com.example.videodemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.text.Layout;
import android.util.AttributeSet;
import android.widget.TextView;

import java.lang.reflect.Field;

/**
 * Created by zhangxiaoqi on 2016/12/30.
 */
public class EllipTextView extends TextView {

    private boolean isOverSize;
    private OnOverSizeChangedListener changedListener;

    public EllipTextView(Context context) {
        super(context);
    }

    public EllipTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EllipTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setChangedListener(OnOverSizeChangedListener listener) {
        changedListener = listener;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (changedListener != null) {
            changedListener.onChanged(checkOverLine());
        }
    }

    public interface OnOverSizeChangedListener {
        void onChanged(boolean isOverSize);
    }

    public boolean checkOverLine() {
        try {
            Field field = getClass().getSuperclass().getDeclaredField("mLayout");
            field.setAccessible(true);

            Layout mLayout = (Layout) field.get(this);
            if (mLayout == null)
                return false;
            isOverSize = mLayout.getEllipsisCount(mLayout.getLineCount()-1) > 0 ? true : false;
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return isOverSize;
    }
}
