package com.example.tbichan.syaroescape.OpenGL.model;

import android.view.MotionEvent;

import com.example.tbichan.syaroescape.MainActivity;
import com.example.tbichan.syaroescape.OpenGL.view.GlView;
import com.example.tbichan.syaroescape.OpenGL.viewmodel.GlViewModel;
import com.example.tbichan.syaroescape.R;

/**
 * Created by tbichan on 2017/10/17.
 */

public abstract class GlButton extends GlModel {

    // クリックしているかどうか
    private boolean push = false;

    public GlButton(GlViewModel glViewModel, String name) {
        super(glViewModel, name);
        setTexture(R.drawable.button);
        setSize(GlView.TILE_SIZE * 1.618033988749895f, GlView.TILE_SIZE);   // 黄金比
        setTexWidth(getTexWidth() / 2);
    }

    @Override
    public void awake() {

    }

    // 初期処理(別インスタンス登録)
    @Override
    public void start() {

    }

    @Override
    public void update(){

    }

    // タップイベント
    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        float x, y;
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            // タッチした座標を取得
            x = ev.getX();
            y = ev.getY();

            // y軸だけ座標系が違う
            y = MainActivity.getGlView().getHeight() - y;

            // 実際のボタンの座標
            float rx = getX() + getGlViewModel().getX();
            float ry = getY() + getGlViewModel().getY();

            if (x >= rx && x <= (rx + getWidth())) {
                if (y >= ry && y <= (ry + getHeight())) {

                    // クリック実行
                    onClick();

                    // テクスチャ変更
                    setTexX(GlView.TILE_SIZE);

                    push = true;
                }
            }

        }

        else if(ev.getAction() == MotionEvent.ACTION_UP) {

            if (push) {

                // クリック実行
                upClick();

                // テクスチャ変更
                setTexX(0);

                push = false;
            }


        }
        return true;
    }

    // ボタンクリック時
    public abstract void onClick();

    // ボタンを離すとき
    public void upClick(){

    }
}
