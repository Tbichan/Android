package com.example.tbichan.syaroescape.title;

import android.content.Context;

import com.example.tbichan.syaroescape.MainActivity;
import com.example.tbichan.syaroescape.OpenGL.view.GlView;
import com.example.tbichan.syaroescape.scene.SceneBase;
import com.example.tbichan.syaroescape.title.viewmodel.TitleViewModel;

/**
 * タイトルシーン
 * Created by tbichan on 2017/10/15.
 */

public class TitleScene extends SceneBase {

    // ビュー
    //TitleGlView glTitleView;

    int cnt = 0;

    // コンストラクタ
    public TitleScene(Context context){
        super(context);

    }

    // シーンのロード
    @Override
    public void load() {
        System.out.println("title");
        TitleViewModel titleViewModel = new TitleViewModel(MainActivity.getGlView(), mContext);
        GlView glView = MainActivity.getGlView();
        glView.addViewModel(titleViewModel);
    }

    // シーンの更新
    @Override
    public void update(){

        cnt++;
    }
}
