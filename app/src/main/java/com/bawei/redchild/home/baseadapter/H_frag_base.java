package com.bawei.redchild.home.baseadapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.bawei.redchild.home.H_Frag_nbu1;
import com.bawei.redchild.home.bean.Home_frag1_bean;

import java.util.List;

/**
 * 类描述：
 * data:2017/5/19
 * author:高伟振(lenovo)
 */

public class H_frag_base extends FragmentPagerAdapter {

   private List<Home_frag1_bean.DataBean.TagBean> list;

    public H_frag_base(FragmentManager fm, List<Home_frag1_bean.DataBean.TagBean>list) {
        super(fm);
        this.list = list;
    }


    @Override
    public Fragment getItem(int position) {
        H_Frag_nbu1 h_frag_nbu1 = new H_Frag_nbu1();
        h_frag_nbu1.path=list.get(position).getElementDesc();
        return h_frag_nbu1;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return list.get(position%list.size()).getElementName();
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
