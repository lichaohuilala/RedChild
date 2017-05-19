package com.bawei.redchild.baby_info;

import android.view.View;

import com.bawei.redchild.R;
import com.bawei.redchild.baby_info.fragment.BabyFragment;
import com.bawei.redchild.baby_info.fragment.PrePregnantFragment;
import com.bawei.redchild.baby_info.fragment.PregnantFragment;
import com.bawei.redchild.base.BaseFragment;

/**
 * Effect :
 * <p>
 * Created by Administrator
 * Time by 2017/5/19 0019
 */

public class BabyInfoFragment extends BaseFragment implements View.OnClickListener{


    private View checked_pregnant,checked_baby,checked_prepregnant;

    /**
     * 绑定布局文件
     *
     * @return 布局文件ID
     */
    @Override
    protected int attachLayoutRes() {
        return R.layout.babyinfo_fragment_babyinfo;
    }

    /**
     * 初始化 view控件
     */
    @Override
    protected void initView() {

        //获取三个view控件
        getActivity().findViewById(R.id.iv_babyinfo_pregnant_icon).setOnClickListener(this);
        getActivity().findViewById(R.id.iv_babyinfo_baby_icon).setOnClickListener(this);
        getActivity().findViewById(R.id.iv_babyinfo_prepregnant_icon).setOnClickListener(this);

        checked_pregnant = getActivity().findViewById(R.id.iv_babyinfo_checked_pregnant);
        checked_baby = getActivity().findViewById(R.id.iv_babyinfo_checked_baby);
        checked_prepregnant = getActivity().findViewById(R.id.iv_babyinfo_checked_prepregnant);

    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_babyinfo_pregnant_icon:
                checked_pregnant.setVisibility(View.VISIBLE);
                checked_baby.setVisibility(View.INVISIBLE);
                checked_prepregnant.setVisibility(View.INVISIBLE);
                /**
                 * 展示 我怀孕了 页面
                 * 替换or隐藏显示
                 */
                super.replaceFragment(R.id.activity_babyinfo_context,new PregnantFragment(),"pregnant");
                break;
            case R.id.iv_babyinfo_baby_icon:
                checked_pregnant.setVisibility(View.INVISIBLE);
                checked_baby.setVisibility(View.VISIBLE);
                checked_prepregnant.setVisibility(View.INVISIBLE);
                /**
                 * 展示 家有宝宝 页面
                 */
                super.replaceFragment(R.id.activity_babyinfo_context,new BabyFragment(),"baby");
                break;
            case R.id.iv_babyinfo_prepregnant_icon:
                checked_pregnant.setVisibility(View.INVISIBLE);
                checked_baby.setVisibility(View.INVISIBLE);
                checked_prepregnant.setVisibility(View.VISIBLE);
                /**
                 * 展示 我在备孕 页面
                 */
                super.replaceFragment(R.id.activity_babyinfo_context,new PrePregnantFragment(),"prepregnant");
                break;
        }
    }
}
