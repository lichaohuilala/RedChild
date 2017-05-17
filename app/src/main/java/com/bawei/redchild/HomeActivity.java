package com.bawei.redchild;

import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.bawei.redchild.base.BaseActivity;
import com.bawei.redchild.classify.ClassifyFragment;
import com.bawei.redchild.groupon.GrouponFragment;
import com.bawei.redchild.home.HomeFragment;
import com.bawei.redchild.me.MeFragment;
import com.bawei.redchild.shoppingCart.ShoppingCartFragment;

public class HomeActivity extends BaseActivity{


    private HomeFragment homeFragment;
    private ClassifyFragment classifyFragment;
    private ShoppingCartFragment shoppingCartFragment;
    private MeFragment meFragment;
    private GrouponFragment grouponFragment;

    private int tag;

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
        final RadioGroup rg= (RadioGroup) findViewById(R.id.rg_home_show);

        //加载默认 Fragment页面
        homeFragment = new HomeFragment();
        HomeActivity.super.addFragment(R.id.rl_home_show_fragment, homeFragment);
        //标记 当前被选中
        tag=rg.getCheckedRadioButtonId();

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                //为了防止重复点击

                //如果标记不等于被选中值，则进行页面改变
                if(tag!=checkedId){
                    //标记被改变值
                    tag=checkedId;

                    switch (checkedId){
                        case R.id.rb_home_home_show:
                            if(homeFragment==null){
                                homeFragment = new HomeFragment();
                            }
                            HomeActivity.super.replaceFragment(R.id.rl_home_show_fragment,homeFragment);
                            //提示
                            Toast.makeText(HomeActivity.this, "0", Toast.LENGTH_SHORT).show();
                            break;
                        case R.id.rb_home_classify_show:
                            if(classifyFragment==null){
                                classifyFragment = new ClassifyFragment();
                            }
                            HomeActivity.super.replaceFragment(R.id.rl_home_show_fragment,classifyFragment);
                            //提示
                            Toast.makeText(HomeActivity.this, "1", Toast.LENGTH_SHORT).show();
                            break;
                        case R.id.rb_home_shoppingcart_show:
                            if(shoppingCartFragment==null){
                                shoppingCartFragment = new ShoppingCartFragment();
                            }
                            HomeActivity.super.replaceFragment(R.id.rl_home_show_fragment,shoppingCartFragment);
                            //提示
                            Toast.makeText(HomeActivity.this, "2", Toast.LENGTH_SHORT).show();
                            break;
                        case R.id.rb_home_me_show:
                            if(meFragment==null){
                                meFragment = new MeFragment();
                            }
                            HomeActivity.super.replaceFragment(R.id.rl_home_show_fragment,meFragment);
                            //提示
                            Toast.makeText(HomeActivity.this, "3", Toast.LENGTH_SHORT).show();
                            break;
                        case R.id.rb_home_iv_show:
                            if(grouponFragment==null){
                                grouponFragment = new GrouponFragment();
                            }
                            HomeActivity.super.replaceFragment(R.id.rl_home_show_fragment,grouponFragment);
                            //提示
                            Toast.makeText(HomeActivity.this, "4", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            }
        });

    }


}
