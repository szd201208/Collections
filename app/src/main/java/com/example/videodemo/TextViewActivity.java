package com.example.videodemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.Log;

import com.example.videodemo.view.CheckOverSizeTextView;
import com.example.videodemo.view.FolderTextView;
import com.example.videodemo.view.MoreTextView;
import com.example.videodemo.view.MyTextView;
import com.example.videodemo.view.TextLayout;

//retrofit2 + okhttp3 + rxjava
public class TextViewActivity extends AppCompatActivity {
    public static final String TAG = TextViewActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_textview);
        CheckOverSizeTextView textView = (CheckOverSizeTextView) findViewById(R.id.textview);
        textView.setText("像机械迷城游戏里的小工具一样，每一个控件都是我们手里一个有用的工具，"
                +
                "由于时间的问题可以暂时先学会其基本用法，但是只要稍有时候还是需要系统的学习一下，" +
                "起码知道有这么个属性有这么个方法，以便对某些问题发挥关键的作用，只有充分了解了才会运用自如"
        );

      /*  Log.e(TAG,"length:"+textView.getText().length());
        SpannableStringBuilder spannableStringBuilder=new SpannableStringBuilder(textView.getText());
        spannableStringBuilder.setSpan(new ForegroundColorSpan(Color.RED),textView.getText().length()-2,textView.getText().length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);*/
    }

}
