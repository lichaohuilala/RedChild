package com.bawei.redchild;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.bawei.redchild.base.BaseActivity;
import com.bawei.redchild.classify.ClassifyFragment;
import com.bawei.redchild.me.me_view.Login_act;

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
                        //替换分类fragment
                        FragmentTransaction replace = getSupportFragmentManager().beginTransaction().replace(R.id.rl_home_show_fragment, new ClassifyFragment());
                        replace.commit();
                        Toast.makeText(HomeActivity.this, "1", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.rb_home_shoppingcart_show:
                        Toast.makeText(HomeActivity.this, "2", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.rb_home_me_show:
                        Intent intent = new Intent(HomeActivity.this, Login_act.class);
                        startActivity(intent);
                        break;
                    case R.id.rb_home_iv_show:
                        Toast.makeText(HomeActivity.this, "4", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

    }


}
