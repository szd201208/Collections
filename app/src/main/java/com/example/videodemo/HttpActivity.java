package com.example.videodemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ImageFormat;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.videodemo.utils.BitmapUtils;
import com.example.videodemo.utils.HttpUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

//retrofit2 + okhttp3 + rxjava
public class HttpActivity extends AppCompatActivity {
    public static final String TAG = HttpActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
    }

}
