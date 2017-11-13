package com.example.tbichan.syaroescape.OpenGL.renderer;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;

import com.example.tbichan.syaroescape.OpenGL.GlRendererBase;
import com.example.tbichan.syaroescape.OpenGL.model.GlModel;
import com.example.tbichan.syaroescape.OpenGL.viewmodel.GlViewModel;
import com.example.tbichan.syaroescape.R;
import com.example.tbichan.syaroescape.scene.SceneManager;

import java.util.ArrayList;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by tbichan on 2017/10/15.
 */

public class GlRenderer implements GLSurfaceView.Renderer {

    protected Context context;

    // viewmodelのリスト
    private ArrayList<GlViewModel> mViewmodelList = new ArrayList<>();

    public GlRenderer(Context _context){
        context = _context;
    }

    @Override
    public void onDrawFrame(GL10 gl) {

        // 今のシーンをロードしたら
        if (!SceneManager.getInstance().isNowSceneLoad()) return;

        // 描画用バッファをクリア
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

        // 更新
        for (int i = 0; i < mViewmodelList.size(); i++) {

            if (mViewmodelList.get(i).getCnt() == 0) mViewmodelList.get(i).start();
            else mViewmodelList.get(i).update(gl);
            mViewmodelList.get(i).addCnt();
        }

        // 描画
        for (int i = 0; i < mViewmodelList.size(); i++) {
            mViewmodelList.get(i).draw(gl);
        }
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL10.GL_PROJECTION);//プロジェクションモードに設定
        GLU.gluOrtho2D(gl, 0.0f, width, 0.0f, height);//平行投影用のパラメータをセット
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {

        //背景色をクリア
        gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
        //ディザを無効化
        gl.glDisable(GL10.GL_DITHER);
        //深度テストを有効化
        gl.glEnable(GL10.GL_DEPTH_TEST);
        //テクスチャ機能ON
        gl.glEnable(GL10.GL_TEXTURE_2D);
        //透明可能に
        gl.glEnable(GL10.GL_ALPHA_TEST);
        //ブレンド可能に
        gl.glEnable(GL10.GL_BLEND);
        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);

        //faceSprite.setTexture(gl,context.getResources(), R.drawable.face);
        //faceSprite2.setTexture(gl,context.getResources(), R.drawable.face2);
        //spriteList.add(faceSprite);
        //spriteList.add(faceSprite2);
        //face_img.setTexture(gl,context.getResources(), R.drawable.face);
    }

    // VMの追加
    public void addViewModel(GlViewModel glViewModel) {
        mViewmodelList.add(glViewModel);
    }

    // VMの削除
    public void clearViewModel() {
        mViewmodelList.clear();
    }

    public ArrayList<GlViewModel> getViewmodelList(){
        return mViewmodelList;
    }
}
