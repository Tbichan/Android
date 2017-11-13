package com.example.tbichan.syaroescape;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.tbichan.syaroescape.OpenGL.view.GlView;
import com.example.tbichan.syaroescape.scene.SceneBase;
import com.example.tbichan.syaroescape.scene.SceneManager;
import com.example.tbichan.syaroescape.title.TitleScene;
import com.example.tbichan.syaroescape.OpenGL.*;

public class MainActivity extends Activity {

    protected GlView glView;

    protected static MainActivity instance;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // 初期ビュー
        glView = new GlView(this);
        setContentView(glView);

        instance = this;

        /*
        // 初期シーン
        SceneBase firstScene = new TitleScene(this);

        // シーンマネージャーにセット
        SceneManager.getInstance().setFirstScene(firstScene);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                SceneManager sceneManager = SceneManager.getInstance();
                sceneManager.sceneMain();
            }
        });

        thread.start();*/
    }

    @Override
    protected void onResume(){
        super.onResume();
        if (glView != null) glView.onResume();
    }

    @Override
    protected void onPause(){
        super.onPause();
        if (glView != null) glView.onPause();
    }

    // ビューを設定する。
    public static void setGlView(GlViewBase glView) {
        instance.setContentView(glView);
    }

    // ビューを取得する。
    public static GlView getGlView(){
        return instance.glView;
    }

    public static Context getContext() { return (Context)instance; }

}