package com.example.tbichan.syaroescape.maingame.viewmodel;

import android.content.Context;
import android.view.MotionEvent;

import com.example.tbichan.syaroescape.OpenGL.model.GlButton;
import com.example.tbichan.syaroescape.OpenGL.view.GlView;
import com.example.tbichan.syaroescape.OpenGL.viewmodel.GlViewModel;
import com.example.tbichan.syaroescape.maingame.model.COM;
import com.example.tbichan.syaroescape.maingame.model.Cup;
import com.example.tbichan.syaroescape.maingame.model.Player;
import com.example.tbichan.syaroescape.maingame.model.StageModel;

import javax.microedition.khronos.opengles.GL10;

/**
 * UI用のビューです。
 * Created by tbichan on 2017/10/16.
 */

public class UIViewModel extends GlViewModel {

    // ステージモデル
    private StageModel stageModel;

    public UIViewModel(GlView glView, Context context){
        super(glView, context);

    }

    // 読み込み
    @Override
    public void awake() {
        // rightButton
        GlButton rightButton = new GlButton(this, "RightButton"){

            // クリック時
            @Override
            public void onClick(){

                // プレイヤー検索
                Player player = (Player)getGlView().findModel("Player");

                // プレイヤーにクリック処理を伝達
                if (player.rightButtonClick()) {

                    // COMを検索
                    COM com = (COM) getGlView().findModel("COM");

                    // comにクリック処理を伝達
                    com.playerMove();
                }
            }
        };

        // upButton
        GlButton upButton = new GlButton(this, "UpButtonButton"){

            // クリック時
            @Override
            public void onClick(){

                // プレイヤー検索
                Player player = (Player)getGlView().findModel("Player");

                // 相手にクリック処理を伝達
                if (player.upButtonClick()) {

                    // COMを検索
                    COM com = (COM) getGlView().findModel("COM");

                    // comにクリック処理を伝達
                    com.playerMove();
                }
            }
        };

        // leftButton
        GlButton leftButton = new GlButton(this, "LeftButton"){

            // クリック時
            @Override
            public void onClick(){

                // プレイヤー検索
                Player player = (Player)getGlView().findModel("Player");

                // 相手にクリック処理を伝達
                if (player.leftButtonClick()) {

                    // COMを検索
                    COM com = (COM) getGlView().findModel("COM");

                    // comにクリック処理を伝達
                    com.playerMove();
                }
            }
        };

        // downButton
        GlButton downButton = new GlButton(this, "DownButtonButton"){

            // クリック時
            @Override
            public void onClick(){

                // プレイヤー検索
                Player player = (Player)getGlView().findModel("Player");

                // 相手にクリック処理を伝達
                if (player.downButtonClick()) {

                    // COMを検索
                    COM com = (COM) getGlView().findModel("COM");

                    // comにクリック処理を伝達
                    com.playerMove();
                }
            }
        };

        stageModel = new StageModel(this, "stagemodel");

        rightButton.setPosition(GlView.TILE_SIZE * 2, 100);
        upButton.setPosition(GlView.TILE_SIZE, GlView.TILE_SIZE + 100);
        leftButton.setPosition(0, 100);
        downButton.setPosition(GlView.TILE_SIZE, -GlView.TILE_SIZE + 100);
        addModel(rightButton);
        addModel(upButton);
        addModel(leftButton);
        addModel(downButton);
        addModel(stageModel);

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
        return true;
    }
}


