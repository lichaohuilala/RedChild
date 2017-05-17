package com.bawei.redchild;

import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.bawei.redchild.base.BaseActivity;

public class HomeActivity extends BaseActivity{




    /**
     * 初始化 Layout布局
     *
     */
    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_home;
    }

    /**
     * 初始化 View控件
     */
    @Override
    protected void initView() {
        RadioGroup rg= (RadioGroup) findViewById(R.id.rg_home_show);

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {

                switch (checkedId){
                    case R.id.rb_home_home_show:
                        Toast.makeText(HomeActivity.this, "0", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.rb_home_classify_show:
                        Toast.makeText(HomeActivity.this, "1", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.rb_home_shoppingcart_show:
                        Toast.makeText(HomeActivity.this, "2", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.rb_home_me_show:
                        Toast.makeText(HomeActivity.this, "3", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.rb_home_iv_show:
                        Toast.makeText(HomeActivity.this, "4", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

    }


}
