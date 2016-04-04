package com.wondertwo.propertyanime;

import android.view.animation.Interpolator;

/**
 * DeceAcceInterpolator自定义减速加速插值器
 * Created by wondertwo on 2016/3/25.
 */
public class DeceAcceInterpolator implements Interpolator {
    @Override
    public float getInterpolation(float input) {
        return ((4*input-2)*(4*input-2)*(4*input-2))/16f + 0.5f;
    }
}
