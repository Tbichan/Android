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
        
        // �^�C�g���͖���
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        
        //�@FrameLayout������
        FrameLayout fl = new FrameLayout(this);
        setContentView(fl);
        
        // �����r���[
        glView = new GlView(this);
        fl.addView(glView,new ViewGroup.LayoutParams(WC, WC));
        
        // �e�L�X�g�{�b�N�X
        final EditText edit = new EditText(this);
        edit.setHeight(200);
        
        fl.addView(edit, new ViewGroup.LayoutParams(FP, WC));
        
        // ���C�A�E�g������Đݒ�
        /*
        LinearLayout layout = new LinearLayout(this);
        layout.setBackgroundColor(Color.rgb(255, 255, 0));
        layout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.FILL_PARENT));
        layout.setOrientation(LinearLayout.VERTICAL);
        this.setContentView(layout);
        
        // �e�L�X�g�{�b�N�X
        final EditText edit = new EditText(this);
        edit.setHeight(50);
        
        layout.addView(edit);
        

        // �����r���[
        glView = new GlView(this);
        glView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        //glView.setBackgroundColor(Color.CYAN);

        layout.addView(glView);
        
        */

        // �u���[�h�L���X�g���X�i�[
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (action != null) {
                    if (action.equals(Intent.ACTION_SCREEN_ON)) {
                        // ���ON��
                        Log.d("surface", "SCREEN_ON");
                        // �e�N�X�`�������[�h
                        MainActivity.getGlView().loadTexAll();
                    } else if (action.equals(Intent.ACTION_SCREEN_OFF)) {
                        // ���OFF��
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

    // �r���[��ݒ肷��B
    public static void setGlView(GlViewBase glView) {
        instance.setContentView(glView);
    }

    // �r���[���擾����B
    public static GlView getGlView(){
        return instance.glView;
    }
    
    // Model�̃^�O�������s���܂��B
    public static ArrayList<GlModel> findTagAll(String tag) { 
        return instance.glView.findTagAll(tag);
    }

    public static Context getContext() { return (Context)instance; }

}