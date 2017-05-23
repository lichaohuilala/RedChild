package com.bawei.redchild;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.bawei.redchild.baby_info.BabyInfoActivity;

/**
 *  导航页 Activity
 */
public class MainActivity extends AppCompatActivity {

    private SharedPreferences babyInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //sharedp 保存是否设置宝宝信息
        babyInfo = getSharedPreferences("babyInfo", MODE_PRIVATE);


        /**
         * 延时2s 跳转
         */
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                //查看 是否保存状态值
                //true：直接两秒跳转到主页面
                //false：跳转到 baby设置页面
                if(babyInfo.getBoolean("isTag",true)){
                /**
                 * 跳转到 babyInfo 设置页面
                 * 销毁当前页面
                 */
                    startActivity(new Intent(MainActivity.this,BabyInfoActivity.class));
                    finish();
                }else{
                    /**
                     * 跳转到 home主页面
                     */
                    startActivity(new Intent(MainActivity.this,HomeActivity.class));
                    finish();
                }
            }
        },2000);
    }



}
