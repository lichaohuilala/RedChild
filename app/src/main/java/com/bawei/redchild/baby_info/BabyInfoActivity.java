package com.bawei.redchild.baby_info;

import android.graphics.Color;
import android.support.v7.widget.Toolbar;

import com.bawei.redchild.R;
import com.bawei.redchild.base.BaseActivity;

public class BabyInfoActivity extends BaseActivity{


    /**
     * 初始化 Layout布局
     *
     * @return
     */
    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_baby_info;
    }

    /**
     * 初始化 View控件
     */
    @Override
    protected void initView() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_babyinfo_toolbar);

        /**
         * 添加默认Fragment
         */
        addDefaultFragment();
    }

    private void addDefaultFragment() {
        super.addFragment(R.id.activity_babyinfo_context,new BabyInfoFragment());
    }


}
