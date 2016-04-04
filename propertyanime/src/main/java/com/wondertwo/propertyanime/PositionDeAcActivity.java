package com.wondertwo.propertyanime;

import android.app.Activity;
import android.os.Bundle;

/**
 * PositionDeAcActivity自定义减速加速插值器测试类
 * Created by wondertwo on 2016/3/25.
 */
public class PositionDeAcActivity extends Activity {

    // public static PositionView positionView;// 定义PositionView自定义view对象
    // private float screenWidth, screenHeight;// 屏幕宽高
    public static Activity mainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dece_acce);

        // 获取PositionDeAcActivity上下文对象
        mainActivity = PositionDeAcActivity.this;

        /*// 创建PositionView自定义view对象
        positionView = new PositionView(PositionDeAcActivity.this, null);
        // 获取屏幕宽高
        screenWidth = getWindowManager().getDefaultDisplay().getWidth();
        screenHeight = getWindowManager().getDefaultDisplay().getHeight();

        // 注册按钮事件
        Button start = (Button) findViewById(R.id.btn_start_ani);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPropertyAni();
            }
        });*/
    }

    /*private void startPropertyAni() {
        *//*PositionView.PositionPoint point_1 = positionView.createPoint(screenWidth / 2, 20f);
        PositionView.PositionPoint point_2 = positionView.createPoint(screenWidth / 2, screenHeight);*//*
        ValueAnimator animator = ValueAnimator.ofObject(
                new PositionEvaluator(),
                positionView.createPoint(screenWidth / 2, 40f),
                positionView.createPoint(screenWidth / 2, screenHeight));
        animator.setDuration(10 * 1000).start();
    }*/
}
