package com.wondertwo.propertyanime;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 *
 * Created by wondertwo on 2016/3/20.
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 按钮点击事件
     */
    public void startBallFallAni(View v) {
        startActivity(new Intent(this, BallsFallActivity.class));
    }
    public void startXBallFallAni(View v) {
        startActivity(new Intent(this, XBallsFallActivity.class));
    }
    public void startLinearProgress(View v) {
        startActivity(new Intent(this, LinearProgress.class));
    }
    public void startCircleProgress(View v) {
        startActivity(new Intent(this, CircleProgress.class));
    }
    public void startColorShade(View v) {
        startActivity(new Intent(this, BuleToRed.class));
    }
    public void startDeceAcce(View v) {
        startActivity(new Intent(this, PositionDeAcActivity.class));
    }
    public void startPointTranslate(View v) {
        startActivity(new Intent(this, CustomPointActivity.class));
    }
}
