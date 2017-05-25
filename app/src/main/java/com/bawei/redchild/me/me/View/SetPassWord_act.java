package com.bawei.redchild.me.me.View;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.redchild.R;

import java.util.Timer;
import java.util.TimerTask;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class SetPassWord_act extends AppCompatActivity implements View.OnClickListener {

    private Toolbar tb_setpass;
    private TextView tv_phone_setpass;
    private TextView tv_setpass_setpass;
    private EditText et_yzm_setpass;
    private Button but_again_setpass;
    private TextView tv_yzm_setpass;
    private EditText et_setpass_setpass;
    private Button but_submit_setpass;
    private int count=60;
    private SharedPreferences babyInfo;
    private String mPhone;
    private Timer mTimer;
        private Handler handler=new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    switch (msg.what){
                        case 1:
                            if (count==0){
                                mTimer.cancel();
                                but_again_setpass.setEnabled(true);
                                but_again_setpass.setText("重新获取");
                                but_again_setpass.setBackgroundResource(R.drawable.shape_loginbut);
                                count=60;

                            }else{
                                --count;
                                but_again_setpass.setText(count+"秒");
                                but_again_setpass.setEnabled(false);
                                but_again_setpass.setBackgroundResource(R.drawable.shape_againbut);
                            }
                            break;
                        case 2:
                            int event = msg.arg1;
                            int result = msg.arg2;
                            Object data = msg.obj;
                            if (result == SMSSDK.RESULT_COMPLETE) {
                                // 短信注册成功后，返回MainActivity,然后提示新好友
                                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                                    Toast.makeText(SetPassWord_act.this, "提交验证码成功",
                                            Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(SetPassWord_act.this,Success_act.class);
                                    babyInfo = getSharedPreferences("babyInfo", MODE_PRIVATE);
                                    babyInfo.edit().putString("name",mPhone).commit();
                                    startActivity(intent);
                                }
                                else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                                    Toast.makeText(SetPassWord_act.this, "验证码已经发送",
                                            Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                ((Throwable) data).printStackTrace();
                                Toast.makeText(SetPassWord_act.this, "验证码错误",
                                        Toast.LENGTH_SHORT).show();
                            }
                            break;
                    }
                }
            };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setpassword);
        SMSSDK.initSDK(this, "1e22ccd20b462", "e831ee410fd4305650dc19667b093067");
        initView();
        Intent intent = getIntent();
        mPhone = intent.getStringExtra("phone");
        if (mPhone !=null){
            tv_phone_setpass.setText("短信验证码已发送至"+ mPhone);
            regisMob();
            //请求获取验证码
            SMSSDK.getVerificationCode("86", mPhone);
        }
       starttime();

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
                starttime();
                SMSSDK.getVerificationCode("86", mPhone);
                break;
            case R.id.but_submit_setpass:
                String pass = et_setpass_setpass.getText().toString().trim();
                String yzm = et_yzm_setpass.getText().toString().trim();
                if (TextUtils.isEmpty(pass)){
                    Toast.makeText(SetPassWord_act.this,"请输入密码",Toast.LENGTH_SHORT).show();
                    return;
                }if (TextUtils.isEmpty(yzm)){
                Toast.makeText(SetPassWord_act.this,"请输入验证码",Toast.LENGTH_SHORT).show();
            }else{SMSSDK.submitVerificationCode("86",mPhone,yzm);}


                break;
        }
    }

    private void regisMob(){
        EventHandler eh = new EventHandler() {
            @Override
            public void afterEvent(int event, int result, Object data) {
                Message msg = new Message();
                msg.arg1 = event;
                msg.arg2 = result;
                msg.obj = data;
                msg.what=2;
                handler.sendMessage(msg);
            }
        };
        SMSSDK.registerEventHandler(eh);
    }
    private void starttime(){
        mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                Message message = new Message();
                message.what=1;
                handler.sendMessage(message);
            }
        },1000,1000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mTimer.cancel();
    }
}
