//package com.example.videodemo.opengl;
//
//import android.opengl.GLSurfaceView;
//import android.opengl.GLU;
//
//import com.example.videodemo.view.FlatColoredSquare;
//import com.example.videodemo.view.SimplePlane;
//import com.example.videodemo.view.SmoothColoredSquare;
//
//import javax.microedition.khronos.egl.EGLConfig;
//import javax.microedition.khronos.opengles.GL10;
//
///**
// * Created by suzhudan on 2016/12/9.
// * 顶点 Vertex  float[] 通常存放在java.io的buffer类中
// * ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length * 4);     float是4个字节
// * vbb.order(ByteOrder.nativeOrder());
// * FloatBuffer vertexBuffer = vbb.asFloatBuffer();
// * vertexBuffer.put(vertices);
// * vertexBuffer.position(0);
// * 边 Edge
// * 面 Face OpenGL es中特指 三角形
// * 多边形 Polygon 多个三角形组成，不一定在同一个平面上
// * <p>
// * mode:GL_POINTS独立的点,GL_LINE_STRIP相邻两点相连,GL_LINE_LOOP相邻的点相邻，组成闭环,GL_LINES每两个点为一条线，多条线,
// * GL_TRIANELS每三个点为一个三角形，多个三角形,GL_TRIANGEL_STRIP每相邻三点为一个三角形,GL_TRIANGEL_FAN以一个点为三角形公共点，组成相邻三角形，
// * <p>
// * /*  // 多边形
// * short[] indices = {0, 1, 2, 0, 2, 3};
// * ByteBuffer buffer=ByteBuffer.allocateDirect(indices.length*2);
// * buffer.order(ByteOrder.nativeOrder());
// * ShortBuffer shortBuffer=buffer.asShortBuffer();
// * shortBuffer.put(indices);
// * shortBuffer.position(0);
// */
//public class OpenGLRender implements GLSurfaceView.Renderer { //Renderer提供
//    //    private SmoothColoredSquare square = new SmoothColoredSquare();
//    private SimplePlane square = new SimplePlane();
//
//    @Override
//    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
//        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);  //设置背景颜色黑色 rgba
//        gl.glShadeModel(GL10.GL_SMOOTH);  //
//        gl.glClearDepthf(1.0f);  //dethf  深度缓存区
//        gl.glEnable(GL10.GL_DEPTH_TEST);
//        gl.glDepthFunc(GL10.GL_LEQUAL);
//        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);  //角度计算良好
//
////        gl.glFrontFace(GL10.GL_CCW);  //设置逆时针方向为面的为前面
////        gl.glEnable(GL10.GL_CULL_FACE);  //打开忽略后面设置
////        gl.glCullFace(GL10.GL_BACK);//明确指明忽略哪面
//    }
//
//
//    /*
//     * (non-Javadoc)
//     *
//     * @see
//     * android.opengl.GLSurfaceView.Renderer#onDrawFrame(javax.
//         * microedition.khronos.opengles.GL10)
//     */
//    public void onDrawFrame(GL10 gl) {
//        /*gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT); //清除屏幕与深度缓存
//        gl.glLoadIdentity();  //重置maxtrix
//        gl.glTranslatef(0,0,-4);
//        square.draw(gl);*/
//        scaleRotateScale(gl);
//    }
//
//    public void scaleRotateScale(GL10 gl) {
//        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
//        gl.glLoadIdentity(); //恢复
//        gl.glTranslatef(0, 0, -10);
//        //A
//        gl.glPushMatrix(); //推
//        gl.glRotatef(angle, 0.0f, 0.0f, 1.0f);
//        square.draw(gl);
//        gl.glPopMatrix();
//        //B
//        gl.glPushMatrix();
//        gl.glRotatef(-angle, 0.0f, 0.0f, 1.0f);
//        gl.glTranslatef(2, 0, 0);
//        gl.glScalef(0.5f, 0.5f, 0.5f);
//        square.draw(gl);
////        gl.glPopMatrix();
//        //C
//        gl.glPushMatrix();
//        gl.glRotatef(-angle, 0.0f, 0.0f, 1.0f);  //中心为B
//        gl.glTranslatef(2, 0, 0);
//        gl.glScalef(0.5f, 0.5f, 0.5f);
//        gl.glRotatef(angle * 1, 0, 0, 1);  //中心为C
//        square.draw(gl);
//        gl.glPopMatrix();
//        gl.glPopMatrix();
//        angle++;
//    }
//
//    private int angle;
//
//
//    /*
//     * (non-Javadoc)
//     *
//     * @see
//     * android.opengl.GLSurfaceView.Renderer#onSurfaceChanged(javax.
//         * microedition.khronos.opengles.GL10, int, int)
//     */
//    public void onSurfaceChanged(GL10 gl, int width, int height) {
//        // Sets the current view port to the new size.
//        gl.glViewport(0, 0, width, height);// OpenGL docs.
//        // Select the projection matrix
//        gl.glMatrixMode(GL10.GL_PROJECTION);// OpenGL docs.
//        // Reset the projection matrix
//        gl.glLoadIdentity();// OpenGL docs.
//        // Calculate the aspect ratio of the window
//        GLU.gluPerspective(gl, 45.0f,
//                (float) width / (float) height,
//                0.1f, 100.0f);
//        // Select the modelview matrix
//        gl.glMatrixMode(GL10.GL_MODELVIEW);// OpenGL docs.
//        // Reset the modelview matrix
//        gl.glLoadIdentity();// OpenGL docs.
//    }
//
//}
