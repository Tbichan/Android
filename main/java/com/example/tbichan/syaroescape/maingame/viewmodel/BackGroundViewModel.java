package com.example.tbichan.syaroescape.maingame.viewmodel;

import android.content.Context;
import android.view.MotionEvent;

import com.example.tbichan.syaroescape.OpenGL.view.GlView;
import com.example.tbichan.syaroescape.OpenGL.viewmodel.GlViewModel;
import com.example.tbichan.syaroescape.R;
import com.example.tbichan.syaroescape.maingame.GameScene;
import com.example.tbichan.syaroescape.maingame.model.Sprite;
import com.example.tbichan.syaroescape.scene.SceneManager;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by tbichan on 2017/10/16.
 */

public class BackGroundViewModel extends GlViewModel {

    public BackGroundViewModel(GlView glView, Context context){
        super(glView, context);

    }

    // 読み込み
    @Override
    public void awake() {

        // モデル読み込み

        // 背景
        Sprite sprite = new Sprite(this, "BG"){

            // 初期処理(別インスタンス登録)
            @Override
            public void start() {

            }
            // 更新
            @Override
            public void update() {

            }

        };
        sprite.setTexture(R.drawable.sand);
        sprite.setSize(getGlView().getWidth(), getGlView().getHeight());

        // 追加
        addModel(sprite);

    }

    // 初期処理(別インスタンス登録)
    @Override
    public void start() {

    }

    @Override
    public void update(GL10 gl){
        super.update(gl);
    }

    // タップイベント
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        super.onTouchEvent(ev);
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            //System.out.println("idou2");

        }
        return true;
    }
}


