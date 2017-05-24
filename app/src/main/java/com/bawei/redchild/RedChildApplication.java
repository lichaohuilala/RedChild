package com.bawei.redchild;

import android.app.Application;

import com.iflytek.cloud.SpeechUtility;


/**
 * Effect : Application 程序入口
 * <p>
 * Created by Administrator
 * Time by 2017/5/17 0017
 */

public class RedChildApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SpeechUtility.createUtility(this, "appid=" + getString(R.string.app_id));

    }
}
