package com.example.videodemo.view;

import android.graphics.Bitmap;
import android.opengl.GLUtils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * 基类Mesh
 */
public class Mesh {
    private static final String TAG = Mesh.class.getSimpleName();

    private FloatBuffer verticesBuffer = null;  //顶点
    private ShortBuffer indicesBuffer = null; //指数
    private FloatBuffer colorBuffer = null; //颜色
    private int numOfIndives = -1;  //指数的数目
    private float[] rgba = {1.0f, 1.0f, 1.0f, 1.0f};
    //translate
    private float x = 0;
    private float y = 0;
    private float z = 0;
    //rotate
    private float rx = 0;
    private float ry = 0;
    private float rz = 0;

    //simpleplane
    private float[] texttureCoordinates = new float[]{
            0.0f, 1.0f,  //0
            1.0f, 1.0f,  //1
            0.0f, 0.0f,  //2
            1.0f, 0.0f  //3
    };

    private int[] texture = new int[1];
    private FloatBuffer textureBuffer;

    public void draw(GL10 gl) {
  /*      //simple plane
        gl.glEnable(GL10.GL_TEXTURE_2D);
        gl.glBindTexture(GL10.GL_TEXTURE_2D, texture[0]);
        gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
        gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, textureBuffer);
        gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
        gl.glDisable(GL10.GL_TEXTURE_2D);*/

        gl.glFrontFace(GL10.GL_CCW);
        gl.glEnable(GL10.GL_CULL_FACE);
        gl.glCullFace(GL10.GL_BACK);
        //顶点
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, verticesBuffer);
        //颜色
        gl.glColor4f(rgba[0], rgba[1], rgba[2], rgba[3]);
        if (colorBuffer != null) {
            gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
            gl.glColorPointer(4, GL10.GL_FLOAT, 0, colorBuffer);
        }
        if(mShouldLoadTexture){
            loadGlTexture(gl);
            mShouldLoadTexture=false;
        }
        if(mTextureId!=-1&&textureBuffer!=null){
            gl.glEnable(GL10.GL_TEXTURE_2D);
            gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
            gl.glTexCoordPointer(2,GL10.GL_FLOAT,0,textureBuffer);
            gl.glBindTexture(GL10.GL_TEXTURE_2D,mTextureId);
        }


        gl.glTranslatef(x, y, z);
        gl.glRotatef(rx, 1, 0, 0);
        gl.glRotatef(ry, 0, 1, 0);
        gl.glRotatef(rz, 0, 0, 1);
        gl.glDrawElements(GL10.GL_TRIANGLES, numOfIndives, GL10.GL_UNSIGNED_SHORT, indicesBuffer);
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        if(mTextureId!=-1&&textureBuffer!=null) {
            gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
        }
        gl.glDisable(GL10.GL_CULL_FACE);
    }

    public void setVertices(float[] vertices) {
        ByteBuffer ibb = ByteBuffer.allocate(vertices.length * 4);
        ibb.order(ByteOrder.nativeOrder());
        verticesBuffer = ibb.asFloatBuffer();
        verticesBuffer.put(vertices);
        verticesBuffer.position(0);
    }

    public void setIndices(short[] indices) {
        ByteBuffer ibb = ByteBuffer.allocate(indices.length * 2);
        ibb.order(ByteOrder.nativeOrder());
        indicesBuffer = ibb.asShortBuffer();
        indicesBuffer.put(indices);
        indicesBuffer.position(0);
        numOfIndives = indices.length;
    }

    //simple plane
    public void setTextture(float[] texture) {
        ByteBuffer ibb = ByteBuffer.allocateDirect(texture.length * 4);
        ibb.order(ByteOrder.nativeOrder());
        textureBuffer = ibb.asFloatBuffer();
        textureBuffer.put(texttureCoordinates);
        textureBuffer.position(0);
    }

    public void setColor(float red, float green, float blue, float alpha) {
        rgba[0] = red;
        rgba[1] = green;
        rgba[2] = blue;
        rgba[3] = alpha;
    }

    public void setColors(float[] colors) {
        ByteBuffer ibb = ByteBuffer.allocate(colors.length * 4);
        ibb.order(ByteOrder.nativeOrder());
        colorBuffer = ibb.asFloatBuffer();
        colorBuffer.put(colors);
        colorBuffer.position(0);
    }

    //simple plane
    private Bitmap mBitmap;
    private boolean mShouldLoadTexture;
    private int mTextureId = -1;

    public void loadBitmap(Bitmap bitmap) {
        this.mBitmap = bitmap;
        mShouldLoadTexture = true;
    }

    public void loadGlTexture(GL10 gl) {
        int[] textures=new int[1];
        gl.glGenTextures(1,textures,0);
        mTextureId=textures[0];
        gl.glBindTexture(GL10.GL_TEXTURE_2D,mTextureId);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D,GL10.GL_TEXTURE_MIN_FILTER,GL10.GL_LINEAR);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D,GL10.GL_TEXTURE_MAG_FILTER,GL10.GL_LINEAR);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D,GL10.GL_TEXTURE_WRAP_S,GL10.GL_CLAMP_TO_EDGE);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D,GL10.GL_TEXTURE_WRAP_T,GL10.GL_REPEAT);
        GLUtils.texImage2D(GL10.GL_TEXTURE_2D,0,mBitmap,0);

    }

}
