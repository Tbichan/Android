package com.example.tbichan.syaroescape.maingame.model;

import com.example.tbichan.syaroescape.MainActivity;
import com.example.tbichan.syaroescape.OpenGL.model.GlModel;
import com.example.tbichan.syaroescape.OpenGL.viewmodel.GlViewModel;
import com.example.tbichan.syaroescape.R;

/**
 * Created by tbichan on 2017/10/16.
 */

public abstract class Sprite extends GlModel {

    public Sprite(GlViewModel glViewModel, String name) {
        super(glViewModel, name);
    }

}
