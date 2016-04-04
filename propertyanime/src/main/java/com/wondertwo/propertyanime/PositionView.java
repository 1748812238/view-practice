package com.wondertwo.propertyanime;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

/**
 *
 * Created by wondertwo on 2016/3/27.
 */
public class PositionView extends View {

    public static final float RADIUS = 20f;
    private PositionPoint currentPoint;
    private Paint mPaint;
    // 轨迹起始点坐标(160,20)
    private float startX = 160f;
    private float startY = 20f;
    Bitmap bmSource = BitmapFactory.decodeResource(getResources(), R.drawable.bm_round);

    public PositionView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.RED);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (currentPoint == null) {
            currentPoint = new PositionPoint(RADIUS, RADIUS);
            drawCircle(canvas);
            startPropertyAni();
        } else {
            drawCircle(canvas);
        }
    }

    private void drawCircle(Canvas canvas) {
        float x = currentPoint.getX();
        float y = currentPoint.getY();
        canvas.drawCircle(x, y, RADIUS, mPaint);
    }

    /**
     * 启动动画
     */
    private void startPropertyAni() {
        ValueAnimator animator = ValueAnimator.ofObject(
                new PositionEvaluator(),
                createPoint(RADIUS, RADIUS),
                createPoint(getWidth() - RADIUS, getHeight() - RADIUS));
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currentPoint = (PositionPoint) animation.getAnimatedValue();
                // 获取当前坐标
                float currentX = currentPoint.getX();
                float currentY = currentPoint.getY();
                // 绘制轨迹
                Drawable drawable = drawRound(startX, startY, currentX, currentY);
                setBackground(drawable);
                // 重置起始点坐标
                startX = currentX;
                startY = currentY;
                // 刷新view
                invalidate();
            }
        });
        animator.setInterpolator(new DeceAcceInterpolator());
        animator.setDuration(10 * 1000).start();
        /*Log.d("自定义view中开启动画", "startPropertyAni()");*/
    }

    /**
     * 绘制轨迹
     */
    private Drawable drawRound(float startX, float startY, float endX, float endY) {
        // 加载轨迹背景
        Bitmap bmRound = Bitmap.createBitmap(bmSource.getWidth(), bmSource.getHeight(), bmSource.getConfig());
        bmRound.isMutable();
        Canvas canvas = new Canvas(bmRound);
        Paint roundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        roundPaint.setColor(Color.RED);
        roundPaint.setStrokeWidth(5);
        canvas.drawLine(startX, startY, endX, endY, roundPaint);
        canvas.save();
        Drawable drawable = new BitmapDrawable(bmRound);
        return drawable;
    }

    /**
     * createPoint()创建PositionPointView对象
     */
    public PositionPoint createPoint(float x, float y) {
        return new PositionPoint(x, y);
    }

    /**
     * 小圆点内部类
     */
    class PositionPoint {
        private float x;
        private float y;

        public PositionPoint(float x, float y) {
            this.x = x;
            this.y = y;
        }

        public float getX() {
            return x;
        }

        public float getY() {
            return y;
        }
    }
}
