package com.bawei.redchild.me.me.View;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.redchild.HomeActivity;
import com.bawei.redchild.R;

public class Success_act extends AppCompatActivity implements View.OnClickListener {

    private ImageView iv;
    private TextView tv;
    private TextView tv_back_success;
    private SharedPreferences babyInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_act);
        initView();
    }

    private void initView() {
        iv = (ImageView) findViewById(R.id.iv);
        tv = (TextView) findViewById(R.id.tv);
        tv_back_success = (TextView) findViewById(R.id.tv_back_success);
        tv_back_success.setOnClickListener(this);
        iv.setOnClickListener(this);
        babyInfo = getSharedPreferences("babyInfo", MODE_PRIVATE);
        babyInfo.edit().putBoolean("isLogin",true).commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv:

                Intent intent = new Intent(Success_act.this, HomeActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.tv_back_success:
                Intent intent1 = new Intent(Success_act.this, HomeActivity.class);
                startActivity(intent1);
                finish();
                break;
        }
    }
}
