package com.example.fixedfloatwindow;

import android.app.Application;
import android.widget.Toast;

import com.yhao.floatwindow.utils.FloatingTool;

/**
 * @Copyright © 2017 Analysys Inc. All rights reserved.
 * @Description: https://github.com/yhaolpz
 * @Version: 1.0
 * @Create: 2017/12/18 16:57:40
 * @Author: yhao
 */
public class BaseApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        FloatingTool.getInstance(this).registFloating(new Runnable() {
            @Override
            public void run() {
                action();
            }
        });

    }

    protected void action() {
        alert("无需权限的悬浮窗");
        FloatingTool.getInstance(this).hideFloating();
    }

    private void alert(String status) {
        Toast.makeText(this, status, Toast.LENGTH_SHORT).show();
    }
}
