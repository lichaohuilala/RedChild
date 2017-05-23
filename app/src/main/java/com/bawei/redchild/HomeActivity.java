package com.bawei.redchild;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.IdRes;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.bawei.redchild.base.BaseActivity;
import com.bawei.redchild.classify.ClassifyFragment;
import com.bawei.redchild.groupon.GrouponFragment;
import com.bawei.redchild.home.HomeFragment;
import com.bawei.redchild.me.me.MeFragment;
import com.bawei.redchild.shoppingCart.ShoppingCartFragment;

public class HomeActivity extends BaseActivity{


    private HomeFragment homeFragment;
    private ClassifyFragment classifyFragment;
    private ShoppingCartFragment shoppingCartFragment;
    private MeFragment meFragment;
    private GrouponFragment grouponFragment;

    private int tag;
    private SharedPreferences babyInfo;

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
        //标记 设置值
        babyInfo = getSharedPreferences("babyInfo", MODE_PRIVATE);
        if(babyInfo.getBoolean("isTag",true)) {
            initTag();
        }

        final RadioGroup rg= (RadioGroup) findViewById(R.id.rg_home_show);

        findViewById(R.id.iv_home_groupon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //既然 图片被点击了  那么就将radiogroup设置为null的那个
                rg.check(R.id.rb_home_groupon_show);

                if(grouponFragment==null){
                    grouponFragment = new GrouponFragment();
                }
                HomeActivity.super.replaceFragment(R.id.rl_home_show_fragment,grouponFragment);
                //提示
                babyInfo.edit().clear().commit();
                Toast.makeText(HomeActivity.this, "清除SharedP 缓存", Toast.LENGTH_SHORT).show();
            }
        });

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
                    }
                }
            }
        });

    }

    private void initTag() {
            Intent intent = getIntent();
            //获取 其中传递过来的值
            /**
             * 获取 Intent传递过来的值
             * 当前状态：state
             *  0:我在备孕
             *  1:我怀孕了
             *  2:家有宝宝
             *
             *  备孕：
             *   无字段
             *  怀孕：
             *   预产期：dueDate 20170519
             *  宝宝：
             *   出生日期：birthdate 20170519
             *   性别：sex 男，女
             *   名字：name
             */

            SharedPreferences.Editor edit = babyInfo.edit();
            //判断用户当前状态
            switch (intent.getIntExtra("state", -1)){
                case 0:
                    //我在备孕
                    edit.putBoolean("isTag",false).putString("state","我在备孕").commit();
                    break;
                case 1:
                    //我怀孕了
                    edit.putBoolean("isTag",false)
                            .putString("state","我怀孕了")
                            .putString("duDate",intent.getStringExtra("duDate"))
                            .commit();
                    break;
                case 2:
                    //家有宝宝
                    edit.putBoolean("isTag",false)
                            .putString("state","家有宝宝")
                            .putString("birthdate",intent.getStringExtra("birthdate"))
                            .putString("sex",intent.getStringExtra("sex"))
                            .putString("name",intent.getStringExtra("name"))
                            .commit();
                    break;
                default:
                    break;
            }
        }

}
