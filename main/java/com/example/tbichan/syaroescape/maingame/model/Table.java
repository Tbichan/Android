package com.example.tbichan.syaroescape.maingame.model;

import android.util.Log;

import com.example.tbichan.syaroescape.OpenGL.viewmodel.GlViewModel;
import com.example.tbichan.syaroescape.R;

/**
 * Created by tbichan on 2017/10/17.
 */

public class Table extends LMoveSprite {

    public Table(GlViewModel glViewModel, String name) {
        super(glViewModel, name);
        setTexture(R.drawable.desk);
        setTag("Table");
        setId(StageModel.TABLE);

        // テーブルはけれない
        setKickTable(false);
        setThroughTable(false);
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
        super.update();
        // プレイヤーが動いたとき
        //move(LMoveSprite.Status.DOWN);

    }
}
