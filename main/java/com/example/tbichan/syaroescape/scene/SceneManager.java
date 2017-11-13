package com.example.tbichan.syaroescape.scene;

import com.example.tbichan.syaroescape.MainActivity;
import com.example.tbichan.syaroescape.OpenGL.view.GlView;

/**
 * シーン管理クラス
 * Created by tbichan on 2017/10/15.
 */

public class SceneManager {

    // インスタンス
    private static SceneManager instance = new SceneManager();

    private SceneManager(){

    }

    public static SceneManager getInstance() {
        return instance;
    }

    // 前のシーン
    private SceneBase mPreScene = null;

    // 今のシーン
    private SceneBase mNowScene = null;

    // シーン保持用
    private SceneBase mTmpScene = null;

    // 初期シーンをセット
    public void setFirstScene(SceneBase firstScene) {
        mNowScene = firstScene;
    }

    // シーンメイン
    public void sceneMain(){

        // 最初のシーンをロード
        mNowScene.load();
        mNowScene.setLoad(true);
        //loadNextScene();

        // ループ
        while (true) {
            // 今のシーンを更新
            mNowScene.update();

            if (mTmpScene != null) loadNextScene();
        }

    }

    // 新しいシーンを設定
    public void setNextScene(SceneBase scene) {
        mTmpScene = scene;
    }

    // 新しいシーンにする。
    private void loadNextScene() {

        // アンロード
        if (mPreScene != null) mPreScene.unload();

        mPreScene = mNowScene;
        mNowScene = mTmpScene;

        GlView glView = MainActivity.getGlView();
        // ビューモデル削除
        glView.clearViewModel();

        // 読み込み
        mNowScene.load();
        mNowScene.setLoad(true);
        mTmpScene = null;
    }

    public boolean isNowSceneLoad() {
        return mNowScene.isLoad();
    }
}
