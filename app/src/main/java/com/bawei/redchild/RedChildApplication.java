package com.bawei.redchild;

import android.app.Application;

import com.bawei.redchild.me.me.Utils.Utils;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

/**
 * Effect : Application 程序入口
 * <p>
 * Created by Administrator
 * Time by 2017/5/17 0017
 */

public class RedChildApplication extends Application {
    {
        PlatformConfig.setQQZone("1106106081", "ETz0UvZADX01YwuH");
        PlatformConfig.setSinaWeibo("447858330", "1b7a78e7a1f6455d9426ad87c8bacb28","http://sns.whalecloud.com");
    }
    @Override
    public void onCreate() {
        super.onCreate();
        UMShareAPI.get(this);
        Utils.init(getApplicationContext());
    }
}
