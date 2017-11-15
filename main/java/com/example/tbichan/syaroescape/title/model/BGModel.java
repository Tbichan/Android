package title;

import android.util.Log;

import com.example.syaroescape.R;

import opengl.GlModel;
import opengl.GlViewModel;

/**
 * Created by tbichan on 2017/10/16.
 */

public class BGModel extends GlModel {

    public BGModel(GlViewModel glViewModel, String name) {
        super(glViewModel, name);
    }

    // 読み込み
    @Override
    public void awake() {

    }

    // 初期処理(別インスタンス登録)
    @Override
    public void start() {
    	setTexture(R.drawable.title_bg);
    	setSize(1280*2, 727*2);
    }

    // 更新
    @Override
    public void update() {

    }
}
