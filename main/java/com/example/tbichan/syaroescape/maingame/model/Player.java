package com.example.tbichan.syaroescape.maingame.model;

import android.nfc.Tag;
import android.util.Log;
import android.view.MotionEvent;

import com.example.tbichan.syaroescape.MainActivity;
import com.example.tbichan.syaroescape.OpenGL.model.GlModel;
import com.example.tbichan.syaroescape.OpenGL.view.GlView;
import com.example.tbichan.syaroescape.OpenGL.viewmodel.GlViewModel;
import com.example.tbichan.syaroescape.R;

import java.util.ArrayList;

/**
 * Created by tbichan on 2017/10/17.
 */

public class Player extends LMoveSprite {

    public Player(GlViewModel glViewModel, String name) {
        super(glViewModel, name);
        setTexture(R.drawable.player);
        setId(StageModel.PLAYER);
        setKickTable(true);
    }

    // 読み込み
    @Override
    public void awake() {
        super.awake();

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
        ArrayList<GlModel> cupList = getGlViewModel().findTagAll("Cup");

        for (GlModel cup: cupList) {
            if (((Cup)cup).getLx() == getLx() && ((Cup)cup).getLy() == getLy()) {
                cup.delete();
                System.out.println("hit");
            }
        }

    }

    // クリック
    public boolean rightButtonClick() {
        return move(LMoveSprite.Status.RIGHT);
    }

    // クリック
    public boolean upButtonClick() {
        return move(LMoveSprite.Status.UP);
    }

    // クリック
    public boolean leftButtonClick() {
        return move(LMoveSprite.Status.LEFT);
    }

    // クリック
    public boolean downButtonClick() {
        return move(LMoveSprite.Status.DOWN);
    }
}
