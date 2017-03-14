package com.example.videodemo;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;

import com.example.videodemo.activity.BasicMediaplayerActivity;
import com.example.videodemo.activity.LiveSendHeartActivity;
import com.example.videodemo.activity.MatcherActivity;
import com.example.videodemo.activity.NetActivity;
import com.example.videodemo.activity.SendGiftActivity;
import com.example.videodemo.activity.TChartActivity;
import com.example.videodemo.activity.ZoomActivity;
import com.example.videodemo.toolbar.StatusBarActivity;
import com.example.videodemo.utils.StatusBarUtils;

import java.util.regex.Matcher;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnCamera = (Button) findViewById(R.id.btn_camera);
        Button btnCanvas = (Button) findViewById(R.id.btn_canvas);
        Button btnTextview = (Button) findViewById(R.id.btn_textview);
        Button btnOpenGL = (Button) findViewById(R.id.btn_opengl);
        Button btnHeaderViewpager = (Button) findViewById(R.id.btn_header_viewpager);
        btnCamera.setOnClickListener(this);
        btnCanvas.setOnClickListener(this);
        btnTextview.setOnClickListener(this);
        btnOpenGL.setOnClickListener(this);
        btnHeaderViewpager.setOnClickListener(this);
        findViewById(R.id.btn_mediaplayer_basic).setOnClickListener(this);
        findViewById(R.id.btn_image_zoom).setOnClickListener(this);
        findViewById(R.id.btn_stock_kchart).setOnClickListener(this);
        findViewById(R.id.btn_matcher).setOnClickListener(this);
        findViewById(R.id.btn_live_sendheart).setOnClickListener(this);
        findViewById(R.id.btn_net).setOnClickListener(this);
        findViewById(R.id.btn_sendgift).setOnClickListener(this);
        findViewById(R.id.btn_statusbar).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_camera:
                Intent cameraIntent = new Intent(MainActivity.this, Camera2Activity.class);
                startActivity(cameraIntent);
                break;
            case R.id.btn_canvas:
                Intent canvasIntent = new Intent(MainActivity.this, CanvasActivity.class);
                startActivity(canvasIntent);
                break;
            case R.id.btn_textview:
                Intent textviewIntent = new Intent(MainActivity.this, RecycleViewActivity1.class);
                startActivity(textviewIntent);
                break;
            case R.id.btn_opengl:
                Intent openGLIntent = new Intent(MainActivity.this, OpenGLActivity.class);
                startActivity(openGLIntent);
                break;
            case R.id.btn_header_viewpager:
                Intent headerViewpagerIntent = new Intent(MainActivity.this, HeaderViewPagerActivity.class);
                startActivity(headerViewpagerIntent);
                break;
            case R.id.btn_mediaplayer_basic:
                Intent basicMediaplayerIntent = new Intent(MainActivity.this, BasicMediaplayerActivity.class);
                startActivity(basicMediaplayerIntent);
                break;
            case R.id.btn_image_zoom:
                Intent zoomIntent = new Intent(MainActivity.this, ZoomActivity.class);
                startActivity(zoomIntent);
                break;
            case R.id.btn_stock_kchart:
                Intent kchartIntent = new Intent(MainActivity.this, TChartActivity.class);
                startActivity(kchartIntent);
                break;
            case R.id.btn_matcher:
                Intent matcherIntent = new Intent(MainActivity.this, MatcherActivity.class);
                startActivity(matcherIntent);
                break;
            case R.id.btn_live_sendheart:
                Intent liveIntent = new Intent(MainActivity.this, LiveSendHeartActivity.class);
                startActivity(liveIntent);
                break;
            case R.id.btn_net:
                Intent netIntent = new Intent(MainActivity.this, NetActivity.class);
                startActivity(netIntent);
                break;
            case R.id.btn_sendgift:
                Intent sendGiftIntent = new Intent(MainActivity.this, SendGiftActivity.class);
                startActivity(sendGiftIntent);
                break;
            case R.id.btn_statusbar:
                Intent statusBarIntent = new Intent(MainActivity.this, StatusBarActivity.class);
                startActivity(statusBarIntent);
                break;
            default:
                break;
        }
    }

}
