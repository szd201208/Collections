package com.example.videodemo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.videodemo.R;
import com.example.videodemo.bazier.FavorLayout;

/*
 *  重点：
 *  1.贝塞尔曲线
 *  2.
 */
public class LiveSendHeartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_send_heart);
        final FavorLayout favorLayout= (FavorLayout) findViewById(R.id.favor_layout);
        findViewById(R.id.live_send_heart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                favorLayout.addFavor();
            }
        });

    }


}
