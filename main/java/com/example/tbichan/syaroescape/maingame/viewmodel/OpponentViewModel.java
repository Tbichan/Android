package com.example.tbichan.syaroescape.maingame.viewmodel;

import android.content.Context;
import android.view.MotionEvent;

import com.example.tbichan.syaroescape.OpenGL.model.GlButton;
import com.example.tbichan.syaroescape.OpenGL.view.GlView;
import com.example.tbichan.syaroescape.OpenGL.viewmodel.GlViewModel;
import com.example.tbichan.syaroescape.R;
import com.example.tbichan.syaroescape.maingame.model.COM;
import com.example.tbichan.syaroescape.maingame.model.Cup;
import com.example.tbichan.syaroescape.maingame.model.Player;
import com.example.tbichan.syaroescape.maingame.model.StageModel;
import com.example.tbichan.syaroescape.maingame.model.Table;

import javax.microedition.khronos.opengles.GL10;

/**
 * 相手用のビューです。（右側）
 * Created by tbichan on 2017/10/16.
 */

public class OpponentViewModel extends GlViewModel {

    private COM com;

    // ステージモデル
    private StageModel stageModel;

    public OpponentViewModel(GlView glView, Context context){
        super(glView, context);


    }

    // 読み込み
    @Override
    public void awake() {

    }

    // 初期処理(別インスタンス登録)
    @Override
    public void start() {
        // モデル読み込み
        stageModel = ((StageModel)getGlView().findModel("stagemodel"));

        int size = 16;

        // table
        for (int i = 0; i < size*size; i++) {
            if (stageModel.getMapID(this, i % size, i / size) == 1) {
                Table table = new Table(this, "Table" + i);
                table.setLPosition(i % size, i / size);
                addModel(table);
            } else if (stageModel.getMapID(this, i % size, i / size) == 2) {
                Cup cup = new Cup(this, "Cup" + i);
                cup.setLPosition(i % size, i / size);
                addModel(cup);
            }
        }

        // Player
        com = new COM(this, "COM");
        com.setLPosition(1, 1);
        // 追加
        addModel(com);
    }

    @Override
    public void update(GL10 gl){
        super.update(gl);
    }

    // タップイベント
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        super.onTouchEvent(ev);
        return true;
    }
}


