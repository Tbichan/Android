package com.example.tbichan.syaroescape.OpenGL;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;

import com.example.tbichan.syaroescape.R;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public abstract class GlRendererBase implements GLSurfaceView.Renderer {

    protected Context context;

    //2次元スプライト
    //SampleSprite face_img = new SampleSprite();

    public GlRendererBase(Context _context) {
        context = _context;
    }
}





































