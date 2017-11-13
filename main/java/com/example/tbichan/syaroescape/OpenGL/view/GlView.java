package com.example.tbichan.syaroescape.OpenGL.view;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

import com.example.tbichan.syaroescape.OpenGL.GlRendererBase;
import com.example.tbichan.syaroescape.OpenGL.model.GlModel;
import com.example.tbichan.syaroescape.OpenGL.renderer.GlRenderer;
import com.example.tbichan.syaroescape.OpenGL.viewmodel.GlViewModel;

import java.util.ArrayList;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by tbichan on 2017/10/15.
 */

public class GlView extends GLSurfaceView {

    public static final int TILE_SIZE = 64;

    // レンダラー
    protected GlRenderer mRenderer;

    // コールバック
    private GlViewCallBack cb;

    // 直前に検索されたGlModel
    private GlModel preGlModel = null;

    public GlView(Context context) {
        super(context);
        mRenderer = new GlRenderer(context);
        // 作成したレンダラーをセット
        setRenderer(mRenderer);

        SurfaceHolder holder = getHolder();
        cb = new GlViewCallBack();
        holder.addCallback(cb);
    }

    // VMの追加
    public void addViewModel(GlViewModel glViewModel) {
        mRenderer.addViewModel(glViewModel);
        glViewModel.awake();
    }

    // VMの削除
    public void clearViewModel() {
        mRenderer.clearViewModel();
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int x, y;
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            // タッチした座標を取得
            x = (int)ev.getX();
            y = (int)ev.getY();
            System.out.println("x:" + x + ", y:" + y);
        }

        // viewmodelのリスト
        ArrayList<GlViewModel> viewmodelList = mRenderer.getViewmodelList();

        // vmにタップイベントを渡す
        for (GlViewModel glViewModel: viewmodelList) {
            glViewModel.onTouchEvent(ev);
        }

        return true;
    }

    // Modelの検索を行います。
    public GlModel findModel(String name) {

        // 直前の検索かどうか
        if (preGlModel != null) {
            if (preGlModel.getName().equals(name)) {
                return preGlModel;
            }
        }

        // viewmodelのリスト
        ArrayList<GlViewModel> viewmodelList = mRenderer.getViewmodelList();

        // vmにタップイベントを渡す
        for (GlViewModel glViewModel: viewmodelList) {
            GlModel glModel = glViewModel.findModel(name);
            if (glModel != null) {
                preGlModel = glModel;
                return glModel;
            }
        }

        return null;
    }

    // Modelのタグ検索を行います。
    public ArrayList<GlModel> findTagAll(String tag) {
        // viewmodelのリスト
        ArrayList<GlViewModel> viewmodelList = mRenderer.getViewmodelList();

        ArrayList<GlModel> glModelList = new ArrayList<>();

        // vmに渡す
        for (GlViewModel glViewModel: viewmodelList) {
            ArrayList<GlModel> hogeList = glViewModel.findTagAll(tag);
            glModelList.addAll(hogeList);

        }

        return glModelList;
    }

}
