package com.wondertwo.propertyanime;

import android.animation.TypeEvaluator;

/**
 * CustomPointEvaluator自定义圆点移动估值器
 * Created by wondertwo on 2016/3/27.
 */
public class CustomPointEvaluator implements TypeEvaluator {
    @Override
    public Object evaluate(float fraction, Object startValue, Object endValue) {
        CustomPoint startPoint = (CustomPoint) startValue;
        CustomPoint endPoint = (CustomPoint) endValue;
        float x = startPoint.getX() + fraction * (endPoint.getX() - startPoint.getX());
        float y = startPoint.getY() + fraction * (endPoint.getY() - startPoint.getY());
        CustomPoint point = new CustomPoint(x, y);
        return point;
    }
}
