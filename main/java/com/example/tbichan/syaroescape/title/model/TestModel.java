package com.example.tbichan.syaroescape.title.model;

import com.example.tbichan.syaroescape.MainActivity;
import com.example.tbichan.syaroescape.OpenGL.model.GlModel;
import com.example.tbichan.syaroescape.OpenGL.viewmodel.GlViewModel;
import com.example.tbichan.syaroescape.R;

/**
 * Created by tbichan on 2017/10/16.
 */

public class TestModel extends GlModel {

    private float vx = 1.0f;

    public void setVx(float vx) {
        this.vx = vx;
    }

    public TestModel(GlViewModel glViewModel, String name) {
        super(glViewModel, name);
    }

    // 読み込み
    @Override
    public void awake() {

    }

    // 初期処理(別インスタンス登録)
    @Override
    public void start() {

    }

    // 更新
    @Override
    public void update() {
        x += vx;

        if (getCnt() == 150) {
            GlViewModel glViewModel = getGlViewModel();
            // モデル読み込み
            TestModel testModel = new TestModel(glViewModel, "TestModel2");
            testModel.setTexture(R.drawable.face2);
            testModel.setVx(0.5f);
            // サイズ変更
            //testModel.setSize(glViewModel.getGlView().getWidth(), glViewModel.getGlView().getHeight());

            // 追加
            glViewModel.addModel(testModel);

            // 自身を削除
            //delete();
        }

    }
}
