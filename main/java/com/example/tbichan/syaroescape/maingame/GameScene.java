package com.example.tbichan.syaroescape.maingame;

import android.content.Context;
import android.util.Log;

import com.example.tbichan.syaroescape.MainActivity;
import com.example.tbichan.syaroescape.OpenGL.view.GlView;
import com.example.tbichan.syaroescape.maingame.viewmodel.BackGroundViewModel;
import com.example.tbichan.syaroescape.maingame.viewmodel.OpponentViewModel;
import com.example.tbichan.syaroescape.maingame.viewmodel.PlayerViewModel;
import com.example.tbichan.syaroescape.maingame.viewmodel.UIViewModel;
import com.example.tbichan.syaroescape.scene.SceneBase;

/**
 * タイトルシーン
 * Created by tbichan on 2017/10/15.
 */

public class GameScene extends SceneBase {

    // ビュー
    //TitleGlView glTitleView;

    int cnt = 0;

    // コンストラクタ
    public GameScene(Context context){
        super(context);
    }

    // シーンのロード
    @Override
    public void load() {
        System.out.println("game");

        // ビューに追加
        GlView glView = MainActivity.getGlView();
        // vmの追加
        BackGroundViewModel backGroundViewModel = new BackGroundViewModel(MainActivity.getGlView(), mContext);
        glView.addViewModel(backGroundViewModel);
        PlayerViewModel playerViewModel = new PlayerViewModel(MainActivity.getGlView(), mContext);
        playerViewModel.setPosition(100, 256);
        glView.addViewModel(playerViewModel);
        OpponentViewModel enemyViewModel = new OpponentViewModel(MainActivity.getGlView(), mContext);
        enemyViewModel.setPosition(1252, 256);
        glView.addViewModel(enemyViewModel);
        UIViewModel uiViewModel = new UIViewModel(MainActivity.getGlView(), mContext); // UIVM
        glView.addViewModel(uiViewModel);

    }

    // シーンの更新
    @Override
    public void update(){

        cnt++;
    }
}
