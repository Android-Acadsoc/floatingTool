package com.yhao.floatwindow.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.yhao.floatwindow.FloatWindow;
import com.yhao.floatwindow.R;
import com.yhao.floatwindow.enums.MoveType;
import com.yhao.floatwindow.enums.Screen;
import com.yhao.floatwindow.interfaces.BaseFloatWindow;

/**
 * @author Lynch
 * @date 2019/6/14 12:49
 * @desc 全局悬浮窗工具，暂时用于测试入口
 * 求稳可申请权限：<uses-permission android:name="android.permission.SYSTEM_ALERT_WINDOW" />
 * 原仓库：
 */
public class FloatingTool {
    private static FloatingTool sFloatingTool;
    private static Application mApplication;
    private View.OnClickListener action;

    private FloatingTool() {
    }

    public static FloatingTool getInstance(Application application) {
        if (sFloatingTool == null) {
            sFloatingTool = new FloatingTool();
        }
        mApplication = application;
        return sFloatingTool;
    }

    private Activity currentA;

    public Activity getCurrentActivity() {
        return currentA;
    }

    private final static String TestBtnTag = "mFirstWindow";
    private Runnable mRunnable;

    /**
     * @param clickAction 悬浮按钮单击事件
     */
    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public void registFloating(Runnable clickAction) {
        this.mRunnable = clickAction;
        mApplication.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle bundle) {
                currentA = activity;
            }

            @Override
            public void onActivityStarted(Activity activity) {
                currentA = activity;

            }

            @Override
            public void onActivityResumed(Activity activity) {
                FloatWindow.destroy(TestBtnTag);
                showFloating();
            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });
    }

    private FloatWindow.Builder mBuilderA = null;
    private BaseFloatWindow mFirstWindow = null;
    private ImageView mImageView2 = null;

    //    @TargetApi(Build.VERSION_CODES.DONUT)
    private void showFloating() {

        mFirstWindow = FloatWindow.get(TestBtnTag);

        mImageView2 = new ImageView(mApplication);
        mImageView2.setImageResource(R.drawable.c_outline_settings_black_48dp);
      /*  TextView test = new TextView(mApplication);
        test.setText("杨过");
        test.setBackgroundColor(Color.BLACK);
        test.setTextColor(Color.WHITE);
//        test.setPadding(20, 20, 20, 20);*/
        mImageView2.setOnClickListener(action == null ? action = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mRunnable == null) {

                } else {
                    mRunnable.run();
                }
            }
        } : action);
        mBuilderA = FloatWindow.with(mApplication).setView(mImageView2).setWidth(Screen.WIDTH, 0.2f)
                .setHeight(Screen.WIDTH, 0.2f).setX(Screen.WIDTH, 0.8f).setY(Screen.HEIGHT, 0.3f)
                .setMoveType(MoveType.SLIDE)
//                        .setMoveStyle(500, new BounceInterpolator()).setDesktopShow(true)
                .setTag(TestBtnTag)
                /*.setViewStateListener(new ViewStateListener() {
                    @Override
                    public void onPositionUpdate(int x, int y) {

                    }

                    @Override
                    public void onShow() {

                    }

                    @Override
                    public void onHide() {

                    }

                    @Override
                    public void onDismiss() {

                    }

                    @Override
                    public void onMoveAnimStart() {
                        *//*if (mRunnable == null) {

                        } else {
                            mRunnable.run();
                        }*//*

                    }

                    @Override
                    public void onMoveAnimEnd() {
                    }

                    @Override
                    public void onBackToDesktop() {

                    }
                })*/;
        mBuilderA.build();


        FloatWindow.get(TestBtnTag).show();
    }

    public void hideFloating() {
        hideFloating(TestBtnTag);
    }

    public void hideFloating(String tag) {
        mFirstWindow = FloatWindow.get(tag);
        if (mFirstWindow != null) {
            try {
                mFirstWindow.hide();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
        }
    }
}
