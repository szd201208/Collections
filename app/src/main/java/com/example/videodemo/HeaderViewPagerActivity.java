package com.example.videodemo;


import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class HeaderViewPagerActivity extends AppCompatActivity {
    public static final String TAG = "HeaderViewPagerActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
    }

}
