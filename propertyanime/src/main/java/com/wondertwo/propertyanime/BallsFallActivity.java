package com.wondertwo.propertyanime;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.LinearLayout;

import java.util.ArrayList;

/**
 * 小球下落动画BallsFallActivity
 * Created by wondertwo on 2016/3/20.
 */
public class BallsFallActivity extends Activity {

    static final float BALL_SIZE = 50f;// 小球大小
    static final float FULL_TIME = 1000;// 下落时间

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ball_fall);

        LinearLayout container = (LinearLayout) findViewById(R.id.container);
        // 设置显示的view组件
        container.addView(new BallView(this));
    }

    /**
     * 自定义动画组件BallView
     */
    public class BallView extends View implements ValueAnimator.AnimatorUpdateListener {

        public final ArrayList<ShapeHolder> balls = new ArrayList<>();

        public BallView(Context context) {
            super(context);
            setBackgroundColor(Color.WHITE);
        }

        /**
         * 触摸事件处理逻辑
         */
        @Override
        public boolean onTouchEvent(MotionEvent event) {
            // 屏蔽ACTION_UP事件
            if (event.getAction() != MotionEvent.ACTION_DOWN && event.getAction() != MotionEvent.ACTION_MOVE) {
                return false;
            }
            // 在ACTION_DOWN事件发生点生成小球
            ShapeHolder newBall = addBall(event.getX(), event.getY());
            // 计算小球下落动画开始时Y坐标
            float startY = newBall.getY();
            // 计算小球下落动画结束时的Y坐标，即屏幕高度减去startY
            float endY = getHeight() - BALL_SIZE;
            // 获取屏幕高度
            float h = (float) getHeight();
            float eventY = event.getY();
            // 计算动画持续时间
            int duration = (int) (FULL_TIME * ((h - eventY) / h));

            /**
             * 定义小球下落动画
             */
            ValueAnimator fallAni = ObjectAnimator.ofFloat(
                    newBall,
                    "y",
                    startY,
                    endY);
            // 设置动画持续时间
            fallAni.setDuration(duration);
            // 设置加速插值器
            fallAni.setInterpolator(new AccelerateInterpolator());
            // 设置动画监听，当ValueAnimator属性值发生变化时会回调此方法
            fallAni.addUpdateListener(this);
            // 渐隐动画，设置alpha属性值1--->0
            ObjectAnimator fadeAni = ObjectAnimator.ofFloat(
                    newBall,
                    "alpha",
                    1f,
                    0f);
            // 设置动画持续时间
            fadeAni.setDuration(250);
            // 为fadeAni设置监听
            fadeAni.addListener(new AnimatorListenerAdapter() {
                // 动画结束
                @Override
                public void onAnimationEnd(Animator animation) {
                    // 动画结束时将该动画关联的ShapeHolder删除
                    balls.remove(((ObjectAnimator)(animation)).getTarget());
                }
            });
            // 为fadeAni设置监听，当ValueAnimator的属性值改变时，回调此方法
            fadeAni.addUpdateListener(this);
            // 创建AnimatorSet动画集合，用来组合动画
            AnimatorSet animatorSet = new AnimatorSet();
            // 先播放fallAni，再播放fadeAni
            animatorSet.play(fallAni).before(fadeAni);

            // 开始动画
            animatorSet.start();

            return true;
        }

        /**
         * 绘制动画view
         */
        @Override
        protected void onDraw(Canvas canvas) {
            // 遍历balls集合中的每个ShapeHolder对象
            for (ShapeHolder holder : balls) {
                // 保存canvas当前坐标系统
                canvas.save();
                // 坐标变换，把画布坐标系统移到ShapeHolder的X、Y坐标处
                canvas.translate(holder.getX(), holder.getY());
                // 将holder持有的圆形绘制在画布上
                holder.getShape().draw(canvas);
                // 回复canvas坐标系统
                canvas.restore();
            }
        }

        @Override
        public void onAnimationUpdate(ValueAnimator animation) {
            // 指定重绘该界面
            this.invalidate();
        }

        /**
         * 获取ShapeHolder对象，ShapeHolder对象持有小球
         */
        private ShapeHolder addBall(float x, float y) {
            // 创建一个椭圆
            OvalShape circle = new OvalShape();
            // 设置椭圆宽高
            circle.resize(BALL_SIZE, BALL_SIZE);
            // 把椭圆包装成Drawable对象
            ShapeDrawable drawble = new ShapeDrawable(circle);
            // 创建ShapeHolder对象
            ShapeHolder holder = new ShapeHolder(drawble);
            // 设置holder坐标
            holder.setX(x - BALL_SIZE / 2);
            holder.setY(y - BALL_SIZE / 2);

            int red = (int) (Math.random() * 255);
            int green = (int) (Math.random() * 255);
            int blue = (int) (Math.random() * 255);
            // 把red，green，blue三个颜色随机数组合成ARGB颜色
            int color = 0xff000000 + red << 16 | green << 8 | blue;
            // 获取drawble上关联的画笔
            Paint paint = drawble.getPaint();
            // 把red，green，blue三个颜色随机数除以4得到商值组合成ARGB颜色
            int darkColor = 0xff000000 | red / 4 << 16 | green / 4 << 8 | blue / 4;

            // 创建圆形渐变
            RadialGradient gradient = new RadialGradient(
                    37.5f,
                    12.5f,
                    BALL_SIZE,
                    color,
                    darkColor,
                    Shader.TileMode.CLAMP);
            paint.setShader(gradient);
            // 为ShapeHolder对象设置画笔
            holder.setPaint(paint);
            balls.add(holder);
            return holder;
        }
    }
}
