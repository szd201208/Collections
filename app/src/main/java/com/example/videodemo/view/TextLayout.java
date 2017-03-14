package com.example.videodemo.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.videodemo.R;

/**
 * Created by suzhudan on 2016/12/27.
 */
public class TextLayout extends LinearLayout {

    private final TextView contentTv;
    private final TextView moreTv;

    public TextLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        View view = View.inflate(context, R.layout.view_text_layout, null);
        contentTv = (TextView) view.findViewById(R.id.view_text_layout_content);
        moreTv = (TextView) view.findViewById(R.id.view_text_layout_more);
        this.addView(view);
    }

    private String content;

    public void setText(String content) {
        this.content = content;
        contentTv.setText(content);
        requestLayout();
    }

    private boolean hasLayout = false;

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (hasLayout)
            return;
        if (contentTv.getLineCount() > 3) {
            contentTv.setMaxLines(3);
            contentTv.setEllipsize(TextUtils.TruncateAt.END);
            requestLayout();
            moreTv.setVisibility(VISIBLE);
            hasLayout = true;
        } else {
            moreTv.setVisibility(INVISIBLE);
        }
//        invalidate();
    }

    class MyRunnable implements Runnable {
        @Override
        public void run() {

        }
    }
}
