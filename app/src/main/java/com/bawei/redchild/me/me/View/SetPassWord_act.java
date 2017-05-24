package com.bawei.redchild.me.me.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bawei.redchild.R;

public class SetPassWord_act extends AppCompatActivity implements View.OnClickListener {

    private Toolbar tb_setpass;
    private TextView tv_phone_setpass;
    private TextView tv_setpass_setpass;
    private EditText et_yzm_setpass;
    private Button but_again_setpass;
    private TextView tv_yzm_setpass;
    private EditText et_setpass_setpass;
    private Button but_submit_setpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setpassword);
        initView();
        Intent intent = getIntent();
        String phone = intent.getStringExtra("phone");
        if (phone!=null){
            tv_phone_setpass.setText("短信验证码已发送至"+phone);
        }
    }

    private void initView() {
        tb_setpass = (Toolbar) findViewById(R.id.tb_setpass);
        tb_setpass.setNavigationIcon(R.mipmap.btn_back);
        tb_setpass.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tv_phone_setpass = (TextView) findViewById(R.id.tv_phone_setpass);
        tv_setpass_setpass = (TextView) findViewById(R.id.tv_setpass_setpass);
        et_yzm_setpass = (EditText) findViewById(R.id.et_yzm_setpass);
        but_again_setpass = (Button) findViewById(R.id.but_again_setpass);
        tv_yzm_setpass = (TextView) findViewById(R.id.tv_yzm_setpass);
        et_setpass_setpass = (EditText) findViewById(R.id.et_setpass_setpass);
        but_submit_setpass = (Button) findViewById(R.id.but_submit_setpass);

        but_again_setpass.setOnClickListener(this);
        but_submit_setpass.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.but_again_setpass:

                break;
            case R.id.but_submit_setpass:

                break;
        }
    }


}
