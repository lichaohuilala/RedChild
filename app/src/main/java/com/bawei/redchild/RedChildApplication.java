package com.bawei.redchild;
import android.app.Application;
import com.bawei.redchild.me.me.Utils.Utils;
import com.iflytek.cloud.SpeechUtility;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;
/**
*日期:2017/5/25 
 * 时间:8:29
*类描述：
*/
public class RedChildApplication extends Application {
    {
        PlatformConfig.setQQZone("1106106081", "ETz0UvZADX01YwuH");
        PlatformConfig.setSinaWeibo("447858330", "1b7a78e7a1f6455d9426ad87c8bacb28","http://sns.whalecloud.com");
    }
    @Override
    public void onCreate() {
        super.onCreate();

        SpeechUtility.createUtility(this, "appid=" + getString(R.string.app_id));


        UMShareAPI.get(this);
        Utils.init(getApplicationContext());

    }
}
