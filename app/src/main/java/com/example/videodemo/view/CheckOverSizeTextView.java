package com.example.videodemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.videodemo.R;

public class CheckOverSizeTextView extends LinearLayout {

    TextView contentView; // 内容文本
    TextView openView; // “显示全文”或“收起”文本
    protected boolean isExpand = false; // 显示或收起标记
    private int defaultLine = 2; // 显示的行数,超过就隐藏

    public CheckOverSizeTextView(Context context,AttributeSet attrs){
        super(context, attrs);
        init(context);
    }
    public CheckOverSizeTextView(Context context) {
        super(context);
        init(context);
    }

    public void init(Context context){
        View view=LayoutInflater.from(context).inflate(R.layout.view_text_layout, this);
        contentView = (TextView) view.findViewById(R.id.view_text_layout_content);
        openView = (TextView) view.findViewById(R.id.view_text_layout_more);
        // 监听显示或收起按钮事件
        openView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                isExpand = !isExpand;
                if (onStateChangeListener != null) {
                    onStateChangeListener.onStateChange(isExpand);
                }
                if (isExpand) {
                    contentView.setLines(contentView.getLineCount());
                    openView.setText("收起");
                } else {
                    contentView.setLines(defaultLine);
                    openView.setText("展开显示");
                }
            }
        });
        this.addView(view);
    }

    // 给内容文本框赋值
    public void setText(String str) {
        contentView.setText(str);

        int count = contentView.getLayout() == null ? getLineNumber()
                : contentView.getLineCount();
        if (count > defaultLine) {
            contentView.setLines(defaultLine);
            openView.setVisibility(View.VISIBLE);
        } else {
            openView.setVisibility(View.GONE);
        }
    }

    // 计算内容文本框的占用的行数
    private int getLineNumber() {
        WindowManager wm = (WindowManager) getContext().getSystemService(
                Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth(); //获取屏幕的宽度

        int widthMeasureSpec = MeasureSpec.makeMeasureSpec(width,
                MeasureSpec.AT_MOST);
        int heightMeasureSpec = MeasureSpec.makeMeasureSpec(0,
                MeasureSpec.UNSPECIFIED);
        contentView.measure(widthMeasureSpec, heightMeasureSpec);
        int lineHeight = contentView.getLineHeight(); //单行文本的高度
        int lineNumber = contentView.getMeasuredHeight() / lineHeight;  //文本内容所占的行数
        return lineNumber;
    }

    // 改变状态的接口
    public interface OnStateChangeListener {
        void onStateChange(boolean isExpand);
    }

    public OnStateChangeListener onStateChangeListener;

    public void setOnStateChangeListener(
            OnStateChangeListener onStateChangeListener) {
        this.onStateChangeListener = onStateChangeListener;
    }

    // 改变当前标记的值，并判断当前处于何种状态
    public void setIsExpand(boolean isExpand) {
        this.isExpand = isExpand;
        if (isExpand) {
            contentView.setLines(contentView.getLineCount());
            openView.setText("收起");
        } else {
            contentView.setLines(defaultLine);
            openView.setText("展开显示");
        }
    }
}
