package com.bawei.redchild.me.me.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.redchild.R;
import com.bawei.redchild.me.me.Utils.RegexUtils;

public class Register_act extends AppCompatActivity implements View.OnClickListener {

    private Toolbar tb_register;
    private TextView tv_phone_register;
    private EditText et_phone_register;
    private CheckBox cb_agree_register;
    private TextView textView6;
    private TextView tv_suning_register;
    private Button but_next_register;
    private TextView tv_company_register;
    private TextView tv_and_register;
    private TextView tv_yfb_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_act);
        initView();

    }

    private void initView() {
        tb_register = (Toolbar) findViewById(R.id.tb_register);
        tb_register.setNavigationIcon(R.mipmap.btn_back);
        tb_register.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tv_phone_register = (TextView) findViewById(R.id.tv_phone_register);
        et_phone_register = (EditText) findViewById(R.id.et_phone_register);
        cb_agree_register = (CheckBox) findViewById(R.id.cb_agree_register);
        textView6 = (TextView) findViewById(R.id.textView6);
        tv_suning_register = (TextView) findViewById(R.id.tv_suning_register);
        but_next_register = (Button) findViewById(R.id.but_next_register);
        tv_company_register = (TextView) findViewById(R.id.tv_company_register);
        tv_and_register = (TextView) findViewById(R.id.tv_and_register);
        tv_yfb_register = (TextView) findViewById(R.id.tv_yfb_register);

        but_next_register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.but_next_register:
if (!cb_agree_register.isChecked()){
    Toast.makeText(Register_act.this,"请阅读条款",Toast.LENGTH_SHORT).show();
    return;
}if (TextUtils.isEmpty(et_phone_register.getText().toString())){
                Toast.makeText(Register_act.this,"请输入手机号",Toast.LENGTH_SHORT).show();
                return;
}if (!RegexUtils.isMobileExact(et_phone_register.getText().toString())){
                Toast.makeText(Register_act.this,"输入正确手机号",Toast.LENGTH_SHORT).show();
            }
            else{
                Intent intent = new Intent(this, SetPassWord_act.class);
                intent.putExtra("phone",et_phone_register.getText().toString().trim());
                startActivity(intent);
            }
                break;
        }
    }

}
