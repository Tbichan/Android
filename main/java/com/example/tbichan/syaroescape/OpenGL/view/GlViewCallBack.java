package com.example.tbichan.syaroescape.OpenGL.view;

/**
 * Created by tbichan on 2017/10/16.
 */
import android.util.Log;
import android.view.SurfaceHolder;

import com.example.tbichan.syaroescape.MainActivity;
import com.example.tbichan.syaroescape.scene.SceneBase;
import com.example.tbichan.syaroescape.scene.SceneManager;
import com.example.tbichan.syaroescape.title.TitleScene;

public class GlViewCallBack implements SurfaceHolder.Callback, Runnable{

    private SurfaceHolder holder = null;
    private Thread thread = null;
    private boolean isAttached = true;

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,	int height) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        // TODO 自動生成されたメソッド・スタブ
        this.holder = holder;
        thread = new Thread(this);
        thread.start(); //スレッドを開始
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        // TODO 自動生成されたメソッド・スタブ
        isAttached = false;
        thread = null; //スレッドを終了
    }

    @Override
    public void run() {
        // 初期シーン
        SceneBase firstScene = new TitleScene(MainActivity.getContext());

        // シーンマネージャーにセット
        SceneManager.getInstance().setFirstScene(firstScene);
        SceneManager sceneManager = SceneManager.getInstance();
        sceneManager.sceneMain();
    }
}
