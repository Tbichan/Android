package com.example.tbichan.syaroescape.maingame.viewmodel;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;

import com.example.tbichan.syaroescape.OpenGL.model.GlButton;
import com.example.tbichan.syaroescape.OpenGL.view.GlView;
import com.example.tbichan.syaroescape.OpenGL.viewmodel.GlViewModel;
import com.example.tbichan.syaroescape.R;
import com.example.tbichan.syaroescape.maingame.GameScene;
import com.example.tbichan.syaroescape.maingame.model.Cup;
import com.example.tbichan.syaroescape.maingame.model.Enemy;
import com.example.tbichan.syaroescape.maingame.model.Player;
import com.example.tbichan.syaroescape.maingame.model.Sprite;
import com.example.tbichan.syaroescape.maingame.model.StageModel;
import com.example.tbichan.syaroescape.maingame.model.Table;
import com.example.tbichan.syaroescape.scene.SceneManager;

import javax.microedition.khronos.opengles.GL10;

/**
 * プレイヤー用のビューです。（左側）
 * Created by tbichan on 2017/10/16.
 */

public class PlayerViewModel extends GlViewModel {

    private Player player;

    // ステージモデル
    private StageModel stageModel;

    public PlayerViewModel(GlView glView, Context context){
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
            if (stageModel.getMapID(this, i % size, i / size) == StageModel.TABLE) {
                Table table = new Table(this, "Table" + i);
                table.setLPosition(i % size, i / size);
                addModel(table);
            } else if (stageModel.getMapID(this, i % size, i / size) == StageModel.CUP) {
                Cup cup = new Cup(this, "Cup" + i);
                cup.setLPosition(i % size, i / size);
                addModel(cup);
            }

            else if (stageModel.getMapID(this, i % size, i / size) == StageModel.ENEMY) {
                Enemy enemy = new Enemy(this, "Enemy" + i);
                enemy.setLPosition(i % size, i / size);
                addModel(enemy);
            }
        }

        // Player
        player = new Player(this, "Player");
        player.setLPosition(1, 1);
        // 追加
        addModel(player);
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


