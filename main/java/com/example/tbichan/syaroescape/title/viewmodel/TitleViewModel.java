package com.example.tbichan.syaroescape.title.viewmodel;

import android.content.Context;
import android.view.MotionEvent;

import com.example.tbichan.syaroescape.MainActivity;
import com.example.tbichan.syaroescape.OpenGL.view.GlView;
import com.example.tbichan.syaroescape.OpenGL.viewmodel.GlViewModel;
import com.example.tbichan.syaroescape.R;
import com.example.tbichan.syaroescape.maingame.GameScene;
import com.example.tbichan.syaroescape.scene.SceneManager;
import com.example.tbichan.syaroescape.title.model.TestModel;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by tbichan on 2017/10/16.
 */

public class TitleViewModel extends GlViewModel {

    public TitleViewModel(GlView glView, Context context){
        super(glView, context);


    }

    // 読み込み
    @Override
    public void awake() {

        // モデル読み込み
        TestModel testModel = new TestModel(this, "TestModel");
        testModel.setTexture(R.drawable.face);
        testModel.setPosition(1000,0);
        testModel.setVx(1.0f);
        // 追加
        addModel(testModel);

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
            System.out.println("idou");
            SceneManager.getInstance().setNextScene(new GameScene(getContext()));
        }
        return true;
    }
}


