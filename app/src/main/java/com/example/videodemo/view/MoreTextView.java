package com.example.videodemo.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Build;
import android.text.SpannableStringBuilder;
import android.util.AttributeSet;
import android.widget.TextView;

import com.example.videodemo.R;

/**
 * Created by suzhudan on 2016/12/8.
 */
public class MoreTextView extends TextView {

    private int showMaxLength = 45/*Integer.MAX_VALUE*/;
    private SpannableStringBuilder stringBuilder;

    public MoreTextView(Context context) {
        super(context);
    }

    public MoreTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public MoreTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public void init(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.MoreTextView);
        showMaxLength = ta.getInteger(R.styleable.MoreTextView_showMaxLength, /*Integer.MAX_VALUE*/40);
        ta.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onDraw(Canvas canvas) {
        int textLength = this.getText().length();
        if (textLength > showMaxLength) {
            stringBuilder = new SpannableStringBuilder("");
            stringBuilder.append(this.getText().toString().substring(0, showMaxLength));
            stringBuilder.append("...");
            setText(stringBuilder);
            int lastLineWidth = (int) getLayout().getLineWidth(getLineCount() - 1);
            int lastLineStart = getLayout().getLineStart(getLineCount() - 1);
        }
        int lastLineWidth = (int) getLayout().getLineWidth(getLineCount() - 1);
        if (lastLineWidth + getPaint().measureText("更多") <= getWidth()) {
//            stringBuilder.append("更多");
//            setText(stringBuilder);
            getPaint().setColor(Color.RED);
            canvas.drawText("更多", getWidth() - getPaint().measureText("更多"), getHeight(), getPaint());
        } else {
            this.setLines(getLineCount() + 1);
            getPaint().setColor(Color.RED);
            canvas.drawText("更多", getWidth() - getPaint().measureText("更多"), getHeight(), getPaint());
        }
        super.onDraw(canvas);
       /* TextPaint textPaint=new TextPaint(Paint.ANTI_ALIAS_FLAG);
        textPaint.density=getResources().getDisplayMetrics().density;
        textPaint.setTextSize(getTextSize());
        float singleWordWidth=textPaint.measureText("-")+getLineSpacingExtra();  //单个文字的宽度
        int eachLineCounts= (int) (getWidth()/singleWordWidth);    //每行最多可以放多少文字*/


    }
}
