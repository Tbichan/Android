package com.example.tbichan.syaroescape.maingame.model;

import com.example.tbichan.syaroescape.MainActivity;
import com.example.tbichan.syaroescape.OpenGL.viewmodel.GlViewModel;
import com.example.tbichan.syaroescape.R;

/**
 * Created by tbichan on 2017/10/17.
 */

public class Cup extends LMoveSprite {

    public Cup(GlViewModel glViewModel, String name) {
        super(glViewModel, name);
        setTexture(R.drawable.cup);
        setTag("Cup");
        setId(StageModel.CUP);
        setThroughTable(true);
    }

    // 読み込み
    @Override
    public void awake() {

    }

    // 初期処理(別インスタンス登録)
    @Override
    public void start() {
        super.start();
    }

    // 更新
    @Override
    public void update() {

    }
}
