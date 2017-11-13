package com.example.tbichan.syaroescape.scene;

import android.content.Context;

/**
 * シーンの基底クラス
 * Created by tbichan on 2017/10/15.
 */

public abstract class SceneBase {

    protected Context mContext = null;

    private boolean load = false;

    public final void setLoad(boolean load){
        this.load = load;
    }

    public final boolean isLoad() {
        return load;
    }

    // コンストラクタ
    public SceneBase(Context context) {
        mContext = context;
    }

    // シーンのロード
    public abstract void load();

    // シーンの更新
    public abstract void update();

    // シーンのアンロード
    public void unload(){}
}
