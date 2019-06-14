package com.example.fixedfloatwindow;

import android.app.Application;
import android.widget.Toast;

import com.yhao.floatwindow.utils.FloatingTool;

/**

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
//        FloatingTool.getInstance(this).getCurrentActivity()
        alert("无需权限的悬浮窗");
//        FloatingTool.getInstance(this).hideFloating();
    }

    private void alert(String status) {
        Toast.makeText(this, status, Toast.LENGTH_SHORT).show();
    }
}
