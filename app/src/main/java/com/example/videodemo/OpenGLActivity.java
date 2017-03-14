package com.example.videodemo;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

/**
 * Mesh 网格
 *  坐标变换transformations 右手坐标系统
 *glTranslatef(x,y,z) 坐标系平移变换 glRotatef glScalef
 * glLoadIndentity()  恢复为最初无变换矩阵，即单位矩阵（无平移，缩放，旋转）
 * glPushMatrix 栈中保存当前矩阵
 * glPullMatrix  从栈中恢复矩阵
 *
 */
public class OpenGLActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Bitmap bitmap= BitmapFactory.decodeResource(OpenGLActivity.this.getResources(),R.mipmap.ic_launcher);

        GLSurfaceView glSurfaceView=new GLSurfaceView(this);
//        glSurfaceView.setRenderer(new OpenGLRender());
        setContentView(glSurfaceView);
    }
}
