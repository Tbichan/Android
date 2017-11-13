package com.example.tbichan.syaroescape.maingame.model;

import com.example.tbichan.syaroescape.OpenGL.model.GlModel;
import com.example.tbichan.syaroescape.OpenGL.viewmodel.GlViewModel;
import com.example.tbichan.syaroescape.maingame.viewmodel.OpponentViewModel;
import com.example.tbichan.syaroescape.maingame.viewmodel.PlayerViewModel;

import java.util.ArrayList;

/**
 * ステージマップを管理する
 * Created by tbichan on 2017/10/18.
 */

public class StageModel extends Sprite {

    public static final int EMPTY = 0;
    public static final int TABLE = 1;
    public static final int CUP = 2;
    public static final int PLAYER = 3;
    public static final int ENEMY = 4;

    // プレイヤーマップ
    private int[][] playerMap = {
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 2, 1, 0, 0, 1, 1, 1, 0, 1, 0, 0, 0, 0, 1},
            {1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 2, 1, 0, 0, 0, 1},
            {1, 0, 1, 1, 0, 0, 0, 2, 0, 0, 1, 1, 0, 0, 0, 1},
            {1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1},
            {1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1},
            {1, 0, 0, 0, 1, 1, 1, 1, 1, 2, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 1, 1, 0, 0, 2, 0, 0, 0, 0, 0, 0, 1},
            {1, 1, 0, 1, 0, 0, 1, 1, 1, 1, 1, 0, 1, 0, 4, 1},
            {1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 2, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
    };

    // 相手のマップ
    private int[][] comMap = new int[16][16];

    public StageModel(GlViewModel glViewModel, String name) {
        super(glViewModel, name);

    }

    @Override
    public void awake() {
        for (int j = 0; j < playerMap.length; j++) {
            for (int i = 0; i < playerMap[j].length; i++) {
                comMap[j][i] = playerMap[j][i];
            }
        }
    }

    // 初期処理(別インスタンス登録)
    @Override
    public void start() {

    }

    // 更新
    @Override
    public void update() {
        /*
        System.out.println("-----------------------------------");
        if (getCnt() % 60 == 0) {
            for (int j = 0; j < 16; j++) {
                for (int i = 0; i < 16; i++) {
                    System.out.print(playerMap[j][i] + ", ");
                }
                System.out.println();
            }
        }*/

    }

    // マップID取得
    public int getMapID(GlViewModel glViewModel, int lx, int ly) {
        if (glViewModel instanceof PlayerViewModel) return playerMap[ly][lx];
        if (glViewModel instanceof OpponentViewModel) return comMap[ly][lx];
        return -1;
    }

    // マップ取得
    public LMoveSprite getMap(GlViewModel glViewModel, int lx, int ly) {
        // モデル取得
        ArrayList<GlModel> glModelList = glViewModel.findModelAll();

        for (GlModel glModel : glModelList) {
            if (glModel instanceof LMoveSprite) {
                //if (glViewModel instanceof PlayerViewModel) System.out.println(((LMoveSprite) glModel).getLx() + ", " + ((LMoveSprite) glModel).getLy() + "," + lx + ", " + ly);
                // 格子点が同じかどうか
                if (((LMoveSprite) glModel).getLx() == lx && ((LMoveSprite) glModel).getLy() == ly) {

                    //if (glViewModel instanceof PlayerViewModel) {
                    //    System.out.println(((LMoveSprite) glModel).getLx() + ", " + ((LMoveSprite) glModel).getLy() + "," + lx + ", " + ly);
                        //if (glViewModel instanceof PlayerViewModel)System.out.println((LMoveSprite) glModel == null);

                    //}
                    return (LMoveSprite)glModel;
                }
            }
        }

        return null;
    }

    // マップID更新
    public void setMapID(int id, GlViewModel glViewModel, int lx, int ly) {
        if (glViewModel instanceof PlayerViewModel) playerMap[ly][lx] = id;
        if (glViewModel instanceof OpponentViewModel) comMap[ly][lx] = id;
    }

    // 通れるかどうか
    public boolean isPass(LMoveSprite lMoveSprite, LMoveSprite.Status status, int lx, int ly) {

        // ビューモデル取得
        GlViewModel glViewModel = lMoveSprite.getGlViewModel();

        int[][] map = null;

        // マップ選択
        if (glViewModel instanceof PlayerViewModel) map = playerMap;
        else if (glViewModel instanceof OpponentViewModel) map = comMap;

        switch (status) {
            case RIGHT:

                // テーブルが蹴れるか
                if (lMoveSprite.isKickTable()) {
                    // 通れない
                    LMoveSprite tmp1 = getMap(glViewModel, lx + 1, ly);
                    LMoveSprite tmp2 = getMap(glViewModel, lx + 2, ly);
                    if (tmp1 != null && tmp2 != null) {

                        if (!tmp1.isThroughTable() && !tmp2.isThroughTable()) {
                            return false;
                        }
                    }
                } else {

                    // 通れない
                    LMoveSprite tmp = getMap(glViewModel, lx + 1, ly);
                    if (tmp != null) {

                        if (!tmp.isThroughTable()) {
                            return false;
                        }
                    }
                }
                break;
            case UP:

                // テーブルが蹴れるか
                if (lMoveSprite.isKickTable()) {
                    // 通れない
                    LMoveSprite tmp1 = getMap(glViewModel, lx, ly - 1);
                    LMoveSprite tmp2 = getMap(glViewModel, lx, ly - 2);
                    if (tmp1 != null && tmp2 != null) {

                        if (!tmp1.isThroughTable() && !tmp2.isThroughTable()) {
                            return false;
                        }
                    }
                } else {

                    // 通れない
                    LMoveSprite tmp = getMap(glViewModel, lx, ly - 1);
                    if (tmp != null) {

                        if (!tmp.isThroughTable()) {
                            return false;
                        }
                    }
                }
                break;
            case LEFT:

                // テーブルが蹴れるか
                if (lMoveSprite.isKickTable()) {
                    // 通れない
                    LMoveSprite tmp1 = getMap(glViewModel, lx - 1, ly);
                    LMoveSprite tmp2 = getMap(glViewModel, lx - 2, ly);
                    if (tmp1 != null && tmp2 != null) {

                        if (!tmp1.isThroughTable() && !tmp2.isThroughTable()) {
                            return false;
                        }
                    }
                } else {

                    // 通れない
                    LMoveSprite tmp = getMap(glViewModel, lx - 1, ly);
                    if (tmp != null) {

                        if (!tmp.isThroughTable()) {
                            return false;
                        }
                    }
                }
                break;
            case DOWN:

                // テーブルが蹴れるか
                if (lMoveSprite.isKickTable()) {
                    // 通れない
                    LMoveSprite tmp1 = getMap(glViewModel, lx, ly + 1);
                    LMoveSprite tmp2 = getMap(glViewModel, lx, ly + 2);
                    if (tmp1 != null && tmp2 != null) {

                        if (!tmp1.isThroughTable() && !tmp2.isThroughTable()) {
                            return false;
                        }
                    }
                } else {

                    // 通れない
                    LMoveSprite tmp = getMap(glViewModel, lx, ly + 1);
                    if (tmp != null) {

                        if (!tmp.isThroughTable()) {
                            return false;
                        }
                    }
                }
                break;
        }


        return true;
    }

}
