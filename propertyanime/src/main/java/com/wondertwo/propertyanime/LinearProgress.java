package com.wondertwo.propertyanime;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;

/**
 * ObjectAnimator使用示例
 * Created by wondertwo on 2016/3/22.
 */
public class LinearProgress extends Activity {

    private ImageView mPoint_1;
    private ImageView mPoint_2;
    private ImageView mPoint_3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear_progress);

        mPoint_1 = (ImageView) findViewById(R.id.iv_point_1);
        mPoint_2 = (ImageView) findViewById(R.id.iv_point_2);
        mPoint_3 = (ImageView) findViewById(R.id.iv_point_3);


        Button startAni = (Button) findViewById(R.id.start_ani_1);
        startAni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 开启动画
                beginPropertyAni1();
            }
        });
    }

    /**
     * 启动动画
     */
    private void beginPropertyAni1() {
        ObjectAnimator animator_1 = ObjectAnimator.ofFloat(
                mPoint_1,
                "x",
                0,
                140,
                280);
        animator_1.setDuration(2*1000);
        animator_1.setInterpolator(new AccelerateDecelerateInterpolator());

        ObjectAnimator animator_2 = ObjectAnimator.ofFloat(
                mPoint_2,
                "x",
                0,
                140,
                280);
        animator_2.setStartDelay(400);
        animator_2.setDuration(2*1000 + 400);
        animator_2.setInterpolator(new AccelerateDecelerateInterpolator());

        ObjectAnimator animator_3 = ObjectAnimator.ofFloat(
                mPoint_3,
                "x",
                0,
                140,
                280);
        animator_3.setStartDelay(2 * 400);
        animator_3.setDuration(2*1000 + 2 * 400);
        animator_3.setInterpolator(new AccelerateDecelerateInterpolator());

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(animator_1).with(animator_2).with(animator_3);
        animatorSet.start();
    }
}
