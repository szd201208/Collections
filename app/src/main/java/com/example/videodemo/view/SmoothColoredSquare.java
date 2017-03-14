package com.example.videodemo.view;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by suzhudan on 2016/12/10.
 * smooth coloring 平滑颜色过渡
 */
public class SmoothColoredSquare extends Square {
    private float[] colors = {   //4个点颜色
            1.0f, 0.0f, 0.0f, 1.0f,  //0
            0.0f, 1.0f, 0.0f, 1.0f,  //1
            0.0f, 0.0f, 1.0f, 1.0f,  //2
            1.0f, 0.0f, 1.0f, 1.0f   //3
    };
    private final FloatBuffer floatBuffer;

    public SmoothColoredSquare() {
        super(); //将颜色数组放入Buffer中
        ByteBuffer buffer = ByteBuffer.allocateDirect(colors.length * 4);
        buffer.order(ByteOrder.nativeOrder());
        floatBuffer = buffer.asFloatBuffer();
        floatBuffer.put(colors);
        floatBuffer.position(0);
    }

    @Override
    public void draw(GL10 gl) {
        gl.glVertexPointer(3,GL10.GL_FLOAT,0,vertexBuffer);
        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
        gl.glColorPointer(4,GL10.GL_FLOAT,0,floatBuffer);
        super.draw(gl);
        gl.glDisableClientState(GL10.GL_COLOR_ARRAY);

    }
}
