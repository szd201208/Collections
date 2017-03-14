package com.example.videodemo.view;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by suzhudan on 2016/12/10.
 *
 *     //flat coloring 单色  glColor4f(red,green,blue,alpha)
 */
public class FlatColoredSquare extends Square {
    public FlatColoredSquare(){
        super();
    }
    @Override
    public void draw(GL10 gl) {
        gl.glColor4f(0.5f,0.5f,1.0f,1.0f);
        super.draw(gl);
    }
}
