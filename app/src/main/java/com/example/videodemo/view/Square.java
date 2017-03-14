package com.example.videodemo.view;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by suzhudan on 2016/12/10.
 * 绘制正方形
 */
public class Square {
    private float vertices[] = {
            -1.0f,  1.0f, 0.0f,  // 0, Top Left
            -1.0f, -1.0f, 0.0f,  // 1, Bottom Left
            1.0f, -1.0f, 0.0f,  // 2, Bottom Right
            1.0f,  1.0f, 0.0f,  // 3, Top Right
    };

    // The order we like to connect them.
    private short[] indices = { 0, 1, 2, 0, 2, 3 };

    // Our vertex buffer.
    public FloatBuffer vertexBuffer;

    // Our index buffer.
    private ShortBuffer indexBuffer;

    public Square() {
        // a float is 4 bytes, therefore we multiply the number if
        // vertices with 4.
        ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length * 4);
        vbb.order(ByteOrder.nativeOrder()); //返回本地jvm运行的硬件的字节顺序.使用和硬件一致的字节顺序可能使buffer更加有效
        vertexBuffer = vbb.asFloatBuffer();
        vertexBuffer.put(vertices);
        vertexBuffer.position(0);

        // short is 2 bytes, therefore we multiply the number if
        // vertices with 2.
        ByteBuffer ibb = ByteBuffer.allocateDirect(indices.length * 2);
        ibb.order(ByteOrder.nativeOrder());
        indexBuffer = ibb.asShortBuffer();
        indexBuffer.put(indices);
        indexBuffer.position(0);
    }

    /**
     * This function draws our square on screen.
     * @param gl
     */
    public void draw(GL10 gl) {
        // Counter-clockwise winding.
        gl.glFrontFace(GL10.GL_CCW); // OpenGL docs
        // Enable face culling.
        gl.glEnable(GL10.GL_CULL_FACE); // OpenGL docs
        // What faces to remove with the face culling.
        gl.glCullFace(GL10.GL_BACK); // OpenGL docs

        // Enabled the vertices buffer for writing and to be used during
        // rendering.
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);// OpenGL docs.
        // Specifies the location and data format of an array of vertex
        // coordinates to use when rendering.
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, // OpenGL docs
                vertexBuffer);

        gl.glDrawElements(GL10.GL_TRIANGLES, indices.length,// OpenGL docs
                GL10.GL_UNSIGNED_SHORT, indexBuffer);

        // Disable the vertices buffer.
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY); // OpenGL docs
        // Disable face culling.
        gl.glDisable(GL10.GL_CULL_FACE); // OpenGL docs
    }

/*

    private float[] points = {
            -1.0f, 1.0f, 0.0f,
            -1.0f, -1.0f, 0.0f,
            1.0f, -1.0f, 0.0f,
            1.0f, 1.0f, 0.0f
    };

    private short[] shorts={0,1,2,
            0,2,3
    };
    private final FloatBuffer floatBuffer;
    private final ShortBuffer shortBuffer;

    public Square(){
        ByteBuffer pointsByteBuffer=ByteBuffer.allocateDirect(points.length*4);
        pointsByteBuffer.order(ByteOrder.nativeOrder());
        floatBuffer = pointsByteBuffer.asFloatBuffer();
        floatBuffer.put(points);
        floatBuffer.position(0);

        ByteBuffer shortsByteBuffer=ByteBuffer.allocateDirect(shorts.length*2);
        shortsByteBuffer.order(ByteOrder.nativeOrder());
        shortBuffer = shortsByteBuffer.asShortBuffer();
        shortBuffer.put(shorts);
        shortBuffer.position(0);

    }

    public void draw(GL10 gl){
        gl.glFrontFace(GL10.GL_CCW);
        gl.glEnable(GL10.GL_CULL_FACE);
        gl.glCullFace(GL10.GL_BACK);
        //绘制
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glVertexPointer(3,GL10.GL_FLOAT,0,floatBuffer);
        gl.glDrawElements(GL10.GL_TRIANGLES,shorts.length,GL10.GL_UNSIGNED_SHORT,shortBuffer);
        //destroy
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisable(GL10.GL_CULL_FACE);
    }
*/

}
