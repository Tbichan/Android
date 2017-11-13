package com.example.tbichan.syaroescape.maingame.model;

import com.example.tbichan.syaroescape.OpenGL.model.GlModel;
import com.example.tbichan.syaroescape.OpenGL.viewmodel.GlViewModel;
import com.example.tbichan.syaroescape.R;

import java.util.ArrayList;

/**
 * Created by tbichan on 2017/10/17.
 */

public class COM extends LMoveSprite {

    Status[] StatusArrays = {Status.RIGHT, Status.UP, Status.LEFT, Status.DOWN};

    public COM(GlViewModel glViewModel, String name) {
        super(glViewModel, name);

    }

    // 読み込み
    @Override
    public void awake() {
        super.awake();
        setTexture(R.drawable.com);

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

        // Cupを取得
        ArrayList<GlModel> cupList = getGlViewModel().containModelAll("Cup");

        for (GlModel cup: cupList) {
            if (((Cup)cup).getLx() == getLx() && ((Cup)cup).getLy() == getLy()) {
                cup.delete();
                System.out.println("hit");
            }
        }
    }

    // プレイヤーが動いたとき
    public void playerMove() {
        while (!move(StatusArrays[(int)(Math.random() * 4)])){

        }
    }

}
