package com.example.videodemo.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Build;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.StaticLayout;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import com.example.videodemo.R;

/**
 * Created by suzhudan on 2016/12/8.
 */
public class MyTextView extends TextView {


    private String MORE = "查看更多";
    private String points = "...  ";
    private int MEX_LINE_COUNT = 1;

    // 行间距倍数
    private float mLineSpacingMultiplier = 1.0f;
    // 行间距额外像素
    private float mLineSpacingExtra = 0.0f;

    public MyTextView(Context context) {
        super(context);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public void init(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.MoreTextView);
        ta.recycle();
    }


    @Override
    public void setLineSpacing(float extra, float multiplier) {
        mLineSpacingExtra = extra;
        mLineSpacingMultiplier = multiplier;
        super.setLineSpacing(extra, multiplier);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Layout layout = getLayout();
        if (layout.getLineCount() > MEX_LINE_COUNT+1) {
            int lastPosition = layout.getLineEnd(MEX_LINE_COUNT - 1);
            if (lastPosition > 0) {
                mFullText = getText().subSequence(0, lastPosition).toString();
                layout = makeTextLayout(mFullText);
                setEllipsize(TextUtils.TruncateAt.MIDDLE);
                setMeasuredDimension(getMeasuredWidth(), layout.getHeight() + getPaddingTop() + getPaddingBottom());
            }
        }
    }

    private Layout makeTextLayout(String text) {
        return new StaticLayout(text, getPaint(), getWidth() - getPaddingLeft() - getPaddingRight(), Layout.Alignment
                .ALIGN_CENTER, mLineSpacingMultiplier, mLineSpacingExtra, true);
    }

    private String mFullText;

    @Override
    public void setText(CharSequence text, BufferType type) {
        if (TextUtils.isEmpty(mFullText)) {
            mFullText = String.valueOf(text);
        }
        super.setText(text, type);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onDraw(Canvas canvas) {
        if (getLayout().getLineCount() <= MEX_LINE_COUNT) {
            this.setText(mFullText);
        } else {
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(mFullText);
            spannableStringBuilder.append(MORE);
            spannableStringBuilder.setSpan(new ForegroundColorSpan(Color.RED), mFullText.length(), (mFullText + MORE).length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            setText(spannableStringBuilder);
        }
        super.onDraw(canvas);
    }
}
