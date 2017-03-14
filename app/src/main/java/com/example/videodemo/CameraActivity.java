package com.example.videodemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
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
import android.widget.Switch;

import com.example.videodemo.utils.BitmapUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class CameraActivity extends AppCompatActivity implements android.hardware.Camera.PreviewCallback, View.OnClickListener {
    public static final String TAG = "CameraActivity";

    private SurfaceView surfaceView,surfaceView2;
    private SurfaceHolder surfaceHolder;
    private int cameraId =1;  //0后置摄像头  1前置摄像头
    private android.hardware.Camera camera;
    private Button switchCamera;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        surfaceView = (SurfaceView) findViewById(R.id.camera_surfaceView);
        surfaceView2 = (SurfaceView) findViewById(R.id.camera_surfaceView2);
        switchCamera = (Button) findViewById(R.id.btn_switch_camera);
        imageView = (ImageView) findViewById(R.id.camera_image_show);
        switchCamera.setOnClickListener(this);

        new Thread() {
            @Override
            public void run() {
                initCamera();   //初始化camera需要开启线程
            }
        }.start();
    }

    public void initCamera() {
        try {
            camera = android.hardware.Camera.open(cameraId);
            camera.setPreviewDisplay(surfaceView.getHolder());
            android.hardware.Camera.Parameters parameters = camera.getParameters();
            //获取摄像头支持的数据格式
            List<Integer> formats = parameters.getSupportedPreviewFormats();
            for (int i = 0; i < formats.size(); i++) {
                Log.e(TAG, "formats:" + formats.get(i));
            }

            int previewWidth = 0;
            int previewHeight = 0;
            //预览尺寸
            List<android.hardware.Camera.Size> sizes = parameters.getSupportedPreviewSizes();
            for (int i = 0; i < sizes.size(); i++) {
                android.hardware.Camera.Size size = sizes.get(i);
                if (size.width >= previewWidth && size.height >= previewHeight) {
                    previewWidth = size.width;
                    previewHeight = size.height;
                    break;
                }
            }
//            parameters.setPreviewSize(previewWidth, previewHeight);   //摄像头预览大小
//            parameters.setPictureSize(previewWidth, previewHeight);  //照片大小0
            parameters.setPreviewFormat(ImageFormat.NV21);   //摄像头原生只支持两种：NV21,YV12
            camera.setDisplayOrientation(90);  //摄像头旋转角度
            parameters.setRotation(90);   //预览角度
            //聚焦
            camera.autoFocus(new android.hardware.Camera.AutoFocusCallback() {
                @Override
                public void onAutoFocus(boolean success, android.hardware.Camera camera) {
                    //聚焦之后再拍照
                    if (!success) {
                        return;
                    }
                    camera.takePicture(new android.hardware.Camera.ShutterCallback() {
                        @Override
                        public void onShutter() {
                            //拍照提示声音
                        }
                    }, new android.hardware.Camera.PictureCallback() {
                        @Override
                        public void onPictureTaken(byte[] data, android.hardware.Camera camera) {
                            //原生数据，NV21/YV12格式
                        }
                    }, new android.hardware.Camera.PictureCallback() {
                        @Override
                        public void onPictureTaken(byte[] data, android.hardware.Camera camera) {
                            //图片数据，可本地保存
                        }
                    });
                }
            });
            //预览
            camera.setParameters(parameters);
            camera.setPreviewCallback(this);
            camera.startPreview();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPreviewFrame(byte[] data, android.hardware.Camera camera) {
        Log.e(TAG, "onPreviewFrame:" + data.length);
        //NV21转ARGB
        
        android.hardware.Camera.Size size = camera.getParameters().getPreviewSize();
        YuvImage yuvImage = new YuvImage(data, ImageFormat.NV21, size.width, size.height, null);
        Bitmap bitmap = null;
        if (yuvImage != null) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            yuvImage.compressToJpeg(new Rect(0, 0, size.width, size.height), 80, byteArrayOutputStream);
            bitmap = BitmapFactory.decodeByteArray(byteArrayOutputStream.toByteArray(), 0, byteArrayOutputStream.size());
            //图片旋转
            bitmap=BitmapUtils.rotateBitmap(bitmap,cameraId==0?90:270);
            //加水印
            bitmap=BitmapUtils.addTimeToBitmap(bitmap,"我是一个水印");
            try {
                byteArrayOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            imageView.setImageBitmap(bitmap);

//            //将处理后的图片画到surfaceView上
            Canvas canvas=surfaceView2.getHolder().lockCanvas();
            if(canvas!=null){
                canvas.drawBitmap(bitmap,0,0,new Paint());
                surfaceView2.getHolder().unlockCanvasAndPost(canvas);
            }
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroyCamera();
    }

    /**
     * 清空回掉 停止预览 释放摄像头 摄像头置空
     */
    public void destroyCamera() {
        if (camera == null) return;
        camera.setPreviewCallback(null);
        camera.stopPreview();
        camera.release();
        camera = null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //切换前后摄像头
            case R.id.btn_switch_camera:
                cameraId = 1 - cameraId;
                //先销毁 再创建
                destroyCamera();
                new Thread() {
                    @Override
                    public void run() {
                        initCamera();
                    }
                }.start();
                break;
            default:
                break;
        }
    }
}
