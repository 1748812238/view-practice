package com.wondertwo.propertyanime;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * CustomPointView自定义view
 * Created by wondertwo on 2016/3/27.
 */
public class CustomPointView extends View {

    public static final float RADIUS = 20f;
    private CustomPoint currentPoint;
    private Paint mPaint;
    private String color;

    public CustomPointView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.YELLOW);
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
        mPaint.setColor(Color.parseColor(color));
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (currentPoint == null) {
            currentPoint = new CustomPoint(RADIUS, RADIUS);
            drawCircle(canvas);
            startAnimation();
        } else {
            drawCircle(canvas);
        }
    }

    private void drawCircle(Canvas canvas) {
        float x = currentPoint.getX();
        float y = currentPoint.getY();
        canvas.drawCircle(x, y, RADIUS, mPaint);
    }

    private void startAnimation() {

        // 位移动画
        CustomPoint startPoint = new CustomPoint(RADIUS, RADIUS);
        CustomPoint endPoint = new CustomPoint(getWidth() - RADIUS, getHeight() - RADIUS);
        ValueAnimator animator_1 = ValueAnimator.ofObject(
                new CustomPointEvaluator(),
                startPoint,
                endPoint);
        animator_1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currentPoint = (CustomPoint) animation.getAnimatedValue();
                invalidate();
                /*float aniValue = animation.getAnimatedFraction();
                Log.d("当前动画对应的数值是：", "" + aniValue);*/
            }
        });

        // 颜色动画
        ObjectAnimator animator_2 = ObjectAnimator.ofObject(
                this,
                "color",
                new CustomColorEvaluator(),
                "#0000FF",
                "#FF0000");

        AnimatorSet set = new AnimatorSet();
        set.play(animator_1).with(animator_2);
        /*set.setInterpolator(new DeceAcceInterpolator());*/
        set.setDuration(5000).start();
    }
}
