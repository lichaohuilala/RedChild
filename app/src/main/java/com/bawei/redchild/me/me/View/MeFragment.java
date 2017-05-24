package com.bawei.redchild.me.me.View;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.redchild.R;
import com.bawei.redchild.base.BaseFragment;
import com.bumptech.glide.Glide;

/**
 * Effect :
 * <p>
 * Created by Administrator
 * Time by 2017/5/17 0017
 */

public class MeFragment extends BaseFragment {

    private ImageView mHeadicon;
    private TextView mName;

    /**
     * 绑定布局文件
     *
     * @return 布局文件ID
     */
    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_loginsuccess;
    }

    /**
     * 初始化 view控件
     */
    @Override
    protected void initView() {
        mHeadicon = (ImageView) getView().findViewById(R.id.iv_head_success);
        mName = (TextView) getView().findViewById(R.id.tv_name_success);
        Bundle bundle = getArguments();
        if (bundle!=null){
            String name = bundle.getString("name");
            String iconurl = bundle.getString("iconurl");
            if (name!=null){
                mName.setText(name);
            }
            if (iconurl!=null){
                Glide.with(getActivity()).load(iconurl).centerCrop().into(mHeadicon);
            }
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
