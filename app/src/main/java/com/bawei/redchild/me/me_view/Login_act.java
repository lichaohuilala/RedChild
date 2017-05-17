package com.bawei.redchild.me.me_view;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.widget.Toolbar;

import com.bawei.redchild.R;
import com.bawei.redchild.base.BaseActivity;

public class Login_act extends BaseActivity {

    private Toolbar tb_register;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_login_act;
    }

    @Override
    protected void initView() {

        tb_register = (Toolbar) findViewById(R.id.tb_login);
        setSupportActionBar(tb_register);
        tb_register.setNavigationIcon(R.mipmap.btn_back);
    }

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

    }
}
