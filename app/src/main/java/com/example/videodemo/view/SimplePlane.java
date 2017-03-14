package com.example.videodemo.view;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class SimplePlane extends Mesh {


    SimplePlane() {
        super();
    }

    SimplePlane(float width, float height) {
        float[] textureCoordinates = {0.0f, 2.0f,
                2.0f, 2.0f,
                0.0f, 0.0f,
                2.0f, 0.0f};
        short[] indices = new short[]{0, 1, 2,
                1, 3, 2};
        float[] vertices = new float[]{
                -0.5f, -0.5f, 0.0f,
                0.5f, -0.5f, 0.0f,
                -0.5f, 0.5f, 0.0f,
                0.5f, 0.5f, 0.0f
        };
        setIndices(indices);
        setVertices(vertices);
        setTextture(textureCoordinates);

    }

}
