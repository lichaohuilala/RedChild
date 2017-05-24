package com.bawei.redchild.me.me.View;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.redchild.R;
import com.bawei.redchild.base.BaseFragment;
import com.bumptech.glide.Glide;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

import static android.content.Context.MODE_PRIVATE;

/**
 * Effect :
 * <p>
 * Created by Administrator
 * Time by 2017/5/17 0017
 */

public class MeFragment extends BaseFragment implements View.OnClickListener{

    private ImageView mHeadicon;
    private TextView mName,mset;
    private SharedPreferences babyInfo;

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
        babyInfo = getActivity().getSharedPreferences("babyInfo", MODE_PRIVATE);
        mHeadicon = (ImageView) getView().findViewById(R.id.iv_head_success);
        mName = (TextView) getView().findViewById(R.id.tv_name_success);
        mset= (TextView) getView().findViewById(R.id.tv_set_success);
        mset.setOnClickListener(this);


        Bundle bundle = getArguments();
        if (bundle!=null){
            String name = bundle.getString("name");
            String iconurl = bundle.getString("iconurl");
            if (name!=null){
                mName.setText(name);
            }
            if (iconurl!=null){
                Glide.with(getActivity()).load(iconurl).bitmapTransform(new CropCircleTransformation(getActivity())).into(mHeadicon);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onEvent(String mPhone){
        if (mPhone!=null){
            mName.setText(mPhone);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_set_success:
                babyInfo.edit().clear().commit();
                Toast.makeText(getActivity(), "清除SharedP 缓存", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), Login_act.class);
                startActivity(intent);
                break;
        }
    }
}
