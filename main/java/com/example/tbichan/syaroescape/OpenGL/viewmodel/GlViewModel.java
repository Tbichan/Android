package com.example.tbichan.syaroescape.OpenGL.viewmodel;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;

import com.example.tbichan.syaroescape.OpenGL.model.GlModel;
import com.example.tbichan.syaroescape.OpenGL.view.GlView;
import com.example.tbichan.syaroescape.R;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by tbichan on 2017/10/16.
 */

public abstract class GlViewModel {

    // 幅、高さ
    private float width, height;

    // モデルのリスト
    private ArrayList<GlModel> modelList = new ArrayList<>();

    // 削除モデルのリスト
    private ArrayList<GlModel> removeModelList = new ArrayList<>();

    private Context context;

    // ビュー
    private GlView glView;

    // ビューのどこに描画するか
    private float x, y;

    private int cnt = 0;

    public GlView getGlView(){
        return glView;
    }

    public abstract void awake();

    public abstract void start();

    public GlViewModel(GlView glView, Context context) {
        this.glView = glView;
        this.context = context;
        x = y = 0;
    }

    public void update(GL10 gl){
        // 更新
        for (int i = 0; i < modelList.size(); i++) {
            GlModel glModel = modelList.get(i);
            // テクスチャロード
            if (!glModel.isLoadTex()) {
                glModel.loadTexture(gl);
            }

            if (glModel.getCnt() == 0) glModel.start();
            else glModel.update();
            // カウントアップ
            glModel.addCnt();
        }

        // 削除
        for (int i = 0; i < removeModelList.size(); i++) {
            modelList.remove(removeModelList.get(i));
            if (i == removeModelList.size() - 1) removeModelList.clear();
        }

    }

    public void draw(GL10 gl){

        //try {
        // 描画
        for (int i = 0; i < modelList.size(); i++) {
            GlModel glModel = modelList.get(i);
            if (glModel.isVisible()) glModel.draw(gl);
        }

    }

    // タップイベント
    public boolean onTouchEvent(MotionEvent ev) {
        for (GlModel glModel: modelList) {
            glModel.onTouchEvent(ev);
        }
        return true;
    }

    // モデル追加
    public void addModel(GlModel model) {
        modelList.add(model);
        model.awake();
    }

    // モデル削除
    public void deleteModel(GlModel model) {
        removeModelList.add(model);
    }

    public Context getContext() {
        return context;
    }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX(){
        return x;
    }

    public float getY() {
        return y;
    }

    // Modelの検索を行います。
    public GlModel findModel(String name) {
        for (GlModel glModel: modelList) {
            if (glModel.getName().equals(name)) return glModel;
        }
        return null;
    }

    // Modelの検索を行います。
    public ArrayList<GlModel> findModelAll() {
        ArrayList<GlModel> hogeList = new ArrayList<GlModel>();
        for (GlModel glModel: modelList) {
            hogeList.add(glModel);
        }
        return hogeList;
    }

    // Modelの検索を行います。
    public ArrayList<GlModel> findModelAll(String name) {
        ArrayList<GlModel> hogeList = new ArrayList<GlModel>();
        for (GlModel glModel: modelList) {
            if (glModel.getName().equals(name)) hogeList.add(glModel);
        }
        return hogeList;
    }

    // Modelの一部検索を行います。
    public ArrayList<GlModel> containModelAll(String name) {
        ArrayList<GlModel> hogeList = new ArrayList<GlModel>();
        for (GlModel glModel: modelList) {
            if (glModel.getName().contains(name)) hogeList.add(glModel);
        }
        return hogeList;
    }

    // Modelのタグ検索を行います。
    public ArrayList<GlModel> findTagAll(String tag) {
        ArrayList<GlModel> hogeList = new ArrayList<GlModel>();
        for (GlModel glModel: modelList) {
            if (glModel.getTag().equals(tag)) hogeList.add(glModel);
        }
        return hogeList;
    }

    public final void addCnt() {
        cnt++;
    }

    public final int getCnt() {
        return cnt;
    }
}
