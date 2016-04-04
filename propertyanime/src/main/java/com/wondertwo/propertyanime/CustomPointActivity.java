package com.wondertwo.propertyanime;

import android.app.Activity;
import android.os.Bundle;

/**
 * CustomPointActivity
 * Created by wondertwo on 2016/3/27.
 */
public class CustomPointActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置包含属性动画的自定义view
        setContentView(R.layout.activity_custom_point);
    }
}
