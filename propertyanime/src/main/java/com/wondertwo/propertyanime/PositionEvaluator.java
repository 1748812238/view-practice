package com.wondertwo.propertyanime;

import android.animation.TypeEvaluator;

/**
 * PositionEvaluator位置估值器
 * Created by wondertwo on 2016/3/23.
 */
public class PositionEvaluator implements TypeEvaluator {

    // 创建PositionView对象，用来调用createPoint()方法创建当前PositionPoint对象
    PositionView positionView = new PositionView(PositionDeAcActivity.mainActivity, null);

    @Override
    public Object evaluate(float fraction, Object startValue, Object endValue) {

        // 将startValue，endValue强转成PositionView.PositionPoint对象
        PositionView.PositionPoint point_1 = (PositionView.PositionPoint) startValue;
        /*PositionView.PositionPoint point_2 = (PositionView.PositionPoint) endValue;*/

        // 获取起始点Y坐标
        float currentY = point_1.getY();
        /*// 计算起始点到结束点Y坐标差值
        float diffY = Math.abs(point_1.getY() - point_2.getY());*/

        // 调用forCurrentX()方法计算X坐标
        float x = forCurrentX(fraction);
        // 调用forCurrentY()方法计算Y坐标
        float y = forCurrentY(fraction, currentY);

        return positionView.createPoint(x, y);
    }

    /**
     * 计算Y坐标
     */
    private float forCurrentY(float fraction, float currentY) {
        float resultY = currentY;
        if (fraction != 0f) {
            resultY = fraction * 400f + 20f;// 周期为3，故除以6^2
        }
        return resultY;
    }

    /**
     * 计算X坐标
     */
    private float forCurrentX(float fraction) {
        float range = 120f;// 振幅
        float resultX = 160f + (float) Math.sin((6 * fraction) * Math.PI) * range;// 周期为3，故为6fraction
        return resultX;
    }
}
