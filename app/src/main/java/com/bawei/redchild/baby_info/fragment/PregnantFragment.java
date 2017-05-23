package com.bawei.redchild.baby_info.fragment;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import com.bawei.redchild.HomeActivity;
import com.bawei.redchild.R;
import com.bawei.redchild.base.BaseFragment;

/**
 * Effect :
 * <p>
 * Created by Administrator
 * Time by 2017/5/19 0019
 */

public class PregnantFragment extends BaseFragment {
    /**
     * 绑定布局文件
     *
     * @return 布局文件ID
     */
    @Override
    protected int attachLayoutRes() {
        return R.layout.babyinfo_fragment_pregnant;
    }

    /**
     * 初始化 view控件
     */
    @Override
    protected void initView() {
        ImageView iv_animator= (ImageView) getActivity().findViewById(R.id.iv_frag_pregnant_animator);

        //获取 toolbar
        final Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.activity_babyinfo_toolbar);
        final ImageView back = (ImageView) toolbar.findViewById(R.id.activity_babyinfo_back);
        back.setVisibility(View.VISIBLE);
        back.findViewById(R.id.activity_babyinfo_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //回退
                getActivity().getSupportFragmentManager().popBackStack();
                //隐藏 回退
                back.setVisibility(View.INVISIBLE);
            }
        });


        /**
         * 旋转动画
         */
        RotateAnimation rotate = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        rotate.setDuration(4000);
        rotate.setRepeatCount(-1);
        iv_animator.startAnimation(rotate);

        getActivity().findViewById(R.id.btn_frag_pregnant_affirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到主页面
                Intent intent = new Intent(getActivity(), HomeActivity.class);

                intent.putExtra("state",1);
                intent.putExtra("duDate","20170519");

                startActivity(intent);
                getActivity().finish();
            }
        });
    }
}
