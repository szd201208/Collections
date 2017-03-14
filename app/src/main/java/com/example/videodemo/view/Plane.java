package com.example.videodemo.view;

/**
 * Segments为形体宽度，高度，深度可以分成的份数
 */
public class Plane extends Mesh {
    Plane() {
        this(1, 1, 1, 1);
    }

    public Plane(float width, float height) {
        this(width, height, 1, 1);
    }

    public Plane(float width, float height, float widthSegments, float heightSegments) {
        float[] vertices = new float[(int) ((widthSegments + 1) * (heightSegments + 1) * 3)];
        short[] indices = new short[(int) ((widthSegments + 1) * (heightSegments + 1) * 6)];
        float xOffset=width/-2;
        float yOffset=height/-2;
        float xWidth=width/widthSegments;
        float yHeight=height/heightSegments;
        int currentVertex=0;
        int currentIndex=0;
        short w= (short) (widthSegments+1);
        for(int y=0;y<heightSegments+1;y++){
            for(int x=0;x<widthSegments+1;x++){
                vertices[currentVertex]=xOffset+x*xWidth;
                vertices[currentVertex+1]=yOffset+y*yHeight;
                vertices[currentVertex+2]=0;
                currentVertex+=3;
                int n= (int) (y*(widthSegments+1)+x);
                if(y<heightSegments&&x<widthSegments){
                    indices[currentIndex]= (short) n;
                    indices[currentIndex+1]= (short) (n+1);
                    indices[currentIndex+2]= (short) (n+w);
                    indices[currentIndex+3]= (short) (n+1);
                    indices[currentIndex+4]= (short) (n+1+w);
                    indices[currentIndex+5]= (short) (n+1+w-1);
                    currentIndex+=6;
                }
            }
        }
        setIndices(indices);
        setVertices(vertices);
    }
}
