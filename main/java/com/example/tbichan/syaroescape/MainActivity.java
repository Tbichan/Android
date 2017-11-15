package com.example.syaroescape;

import java.util.ArrayList;

import opengl.GlModel;
import opengl.GlView;
import opengl.GlViewBase;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.FrameLayout;

public class MainActivity extends Activity {

    protected GlView glView;

    protected static MainActivity instance;
    
    private final int FP = ViewGroup.LayoutParams.FILL_PARENT; 
    private final int WC = ViewGroup.LayoutParams.WRAP_CONTENT;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // タイトルは無し
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        
        //　FrameLayoutを準備
        FrameLayout fl = new FrameLayout(this);
        setContentView(fl);
        
        // 初期ビュー
        glView = new GlView(this);
        fl.addView(glView,new ViewGroup.LayoutParams(WC, WC));
        
        // テキストボックス
        final EditText edit = new EditText(this);
        edit.setHeight(200);
        
        fl.addView(edit, new ViewGroup.LayoutParams(FP, WC));
        
        // レイアウトを作って設定
        /*
        LinearLayout layout = new LinearLayout(this);
        layout.setBackgroundColor(Color.rgb(255, 255, 0));
        layout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.FILL_PARENT));
        layout.setOrientation(LinearLayout.VERTICAL);
        this.setContentView(layout);
        
        // テキストボックス
        final EditText edit = new EditText(this);
        edit.setHeight(50);
        
        layout.addView(edit);
        

        // 初期ビュー
        glView = new GlView(this);
        glView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        //glView.setBackgroundColor(Color.CYAN);

        layout.addView(glView);
        
        */

        // ブロードキャストリスナー
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (action != null) {
                    if (action.equals(Intent.ACTION_SCREEN_ON)) {
                        // 画面ON時
                        Log.d("surface", "SCREEN_ON");
                        // テクスチャリロード
                        MainActivity.getGlView().loadTexAll();
                    } else if (action.equals(Intent.ACTION_SCREEN_OFF)) {
                        // 画面OFF時
                        Log.d("surface", "SCREEN_OFF");
                    }
                }
            }
        };


        registerReceiver(broadcastReceiver, new IntentFilter(Intent.ACTION_SCREEN_ON));
        registerReceiver(broadcastReceiver, new IntentFilter(Intent.ACTION_SCREEN_OFF));
		
        instance = this;
    }

    @Override
    protected void onResume(){
        super.onResume();
        if (glView != null) glView.onResume();
    }

    @Override
    protected void onPause(){
        super.onPause();
        if (glView != null) glView.onPause();
    }

    // ビューを設定する。
    public static void setGlView(GlViewBase glView) {
        instance.setContentView(glView);
    }

    // ビューを取得する。
    public static GlView getGlView(){
        return instance.glView;
    }
    
    // Modelのタグ検索を行います。
    public static ArrayList<GlModel> findTagAll(String tag) { 
        return instance.glView.findTagAll(tag);
    }

    public static Context getContext() { return (Context)instance; }

}