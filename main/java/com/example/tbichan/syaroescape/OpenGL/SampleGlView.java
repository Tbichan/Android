package com.example.tbichan.syaroescape.OpenGL;

import android.content.Context;
import android.opengl.GLSurfaceView;

public class SampleGlView extends GlViewBase {


    public SampleGlView(Context context) {
        super(context);

    }

    // レンダラー作成
    @Override
    protected GlRendererBase createRenderer(Context context) {
        SampleRenderer sampleRenderer = new SampleRenderer(context);

        return sampleRenderer;
    }

}
