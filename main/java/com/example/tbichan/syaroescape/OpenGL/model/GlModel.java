package com.example.tbichan.syaroescape.OpenGL.model;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;
import android.util.Log;
import android.view.MotionEvent;

import com.example.tbichan.syaroescape.MainActivity;
import com.example.tbichan.syaroescape.OpenGL.viewmodel.GlViewModel;

import java.io.IOException;
import java.io.InputStream;

import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.opengles.GL11;
import javax.microedition.khronos.opengles.GL11Ext;

/**
 * Created by tbichan on 2017/10/15.
 */

public abstract class GlModel {

    // 名前
    private String name = "";

    // タグ
    private String tag = "unknown";

    // 座標
    protected float x, y;

    // カウンタ
    private int cnt = 0;

    // 表示中かどうか
    private boolean visible = true;

    //テクスチャNo
    public int textureNo;

    // テクスチャをロードしたかどうか
    private boolean loadTex = false;

    public boolean isLoadTex() {return loadTex; }

    //テクスチャ（画像）の位置とサイズ
    private int texX;
    private int texY;
    private int texWidth;
    private int texHeight;
    //配置する時の幅と高さ
    private float width;
    private float height;

    // モデルビュー
    private GlViewModel glViewModel;

    public GlViewModel getGlViewModel(){
        return glViewModel;
    }

    // 画像
    Bitmap bitmap;

    public GlModel(GlViewModel glViewModel, String name){
        this.glViewModel = glViewModel;
        this.name = name;
    }

    // テクスチャをセット
    public void setTexture(int id){

        Resources res = MainActivity.getContext().getResources();
        InputStream is = res.openRawResource(id);
        try{
            bitmap = BitmapFactory.decodeStream(is);
        } catch (Exception e) {
            Log.d("err", "err");
        }
        finally{
            try{
                is.close();
            }
            catch(IOException e){   }
        }

        texX = 0;
        texY = bitmap.getHeight();
        texWidth = bitmap.getWidth();
        texHeight = -bitmap.getHeight();
        //x = 0;
        //y = 0;
        width = bitmap.getWidth();
        height = bitmap.getHeight();
    }

    // テクスチャをロード
    public void loadTexture(GL10 gl){

        if (bitmap != null) {

            gl.glEnable(GL10.GL_ALPHA_TEST);
            gl.glEnable(GL10.GL_BLEND);
            gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
            gl.glTexEnvf(GL10.GL_TEXTURE_ENV, GL10.GL_TEXTURE_ENV_MODE, GL10.GL_MODULATE);
            //テクスチャIDを割り当てる
            int[] textureID = new int[1];
            gl.glGenTextures(1, textureID, 0);
            textureNo = textureID[0];

            //テクスチャIDのバインド
            gl.glBindTexture(GL10.GL_TEXTURE_2D, textureNo);
            //OpenGL ES用のメモリ領域に画像データを渡す。上でバインドされたテクスチャIDと結び付けられる。
            GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);
            //テクスチャ座標が1.0fを超えたときの、テクスチャを繰り返す設定
            gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S, GL10.GL_REPEAT);
            gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T, GL10.GL_REPEAT);
            //テクスチャを元のサイズから拡大、縮小して使用したときの色の使い方を設定
            gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);
            gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_LINEAR);
        }

        loadTex = true;
    }

    // 読み込み
    public void awake(){

    }

    // 初期処理
    public abstract void start();

    // 更新
    public abstract void update();

    public void draw(GL10 gl) {
        gl.glDisable(GL10.GL_DEPTH_TEST);
        //背景色を白色で塗りつぶし
        gl.glColor4x(0x10000, 0x10000, 0x10000, 0x10000);
        //テクスチャ0番をアクティブにする
        gl.glActiveTexture(GL10.GL_TEXTURE0);
        //テクスチャIDに対応するテクスチャをバインドする
        gl.glBindTexture(GL10.GL_TEXTURE_2D, textureNo);
        //テクスチャの座標と幅と高さを指定
        int rect[] = {texX, texY, texWidth, texHeight};
        //テクスチャ画像のどの部分を使うかを指定
        ((GL11)gl).glTexParameteriv(GL10.GL_TEXTURE_2D, GL11Ext.GL_TEXTURE_CROP_RECT_OES, rect, 0);
        //描画
        ((GL11Ext)gl).glDrawTexfOES(x + glViewModel.getX(), y + glViewModel.getY(), 0, width, height);

        gl.glEnable(GL10.GL_DEPTH_TEST);
    }

    // 削除
    public void delete(){
        glViewModel.deleteModel(this);
    }

    // タップイベント
    public boolean onTouchEvent(MotionEvent ev) {

        return true;
    }

    // カウントアップ
    public final void addCnt(){
        cnt++;
    }

    public final int getCnt(){
        return cnt;
    }

    public final void setVisible(boolean visible) {
        this.visible = visible;
    }

    public final boolean isVisible() {
        return visible;
    }

    public String getName() {
        return name;
    }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getWidth(){
        return width;
    }

    public float getHeight(){
        return height;
    }

    public void setSize(float w, float h) {
        width = w;
        height = h;
    }

    public void setTexPosition(int texX, int texY, int texWidth, int texHeight) {
        this.texX = texX;
        this.texY = texY;
        this.texWidth = texWidth;
        this.texHeight = texHeight;
    }

    public void setTexX(int texX) {
        this.texX = texX;

    }
    public void setTexY(int texY) {
        this.texY = texY;

    }

    public void setTexWidth(int texWidth) {
        this.texWidth = texWidth;

    }

    public void setTexHeight(int texHeight) {
        this.texHeight = texHeight;
    }

    public int getTexX() {
        return texX;
    }

    public int getTexY() {
        return texY;
    }

    public int getTexWidth() {
        return texWidth;
    }

    public int getTexHeight() {
        return texHeight;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

}
