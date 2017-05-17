package com.bawei.redchild.me.me_view;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.bawei.redchild.R;
import com.bawei.redchild.base.BaseFragment;

/**
 * Effect :
 * <p>
 * Created by Administrator
 * Time by 2017/5/17 0017
 */

public class MeFragment extends BaseFragment {
    /**
     * 绑定布局文件
     *
     * @return 布局文件ID
     */
    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_me;
    }

    /**
     * 初始化 view控件
     */
    @Override
    protected void initView() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
