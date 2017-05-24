package com.bawei.redchild.groupon;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * date:2017/5/23.
 * author:刘宏亮.
 * function:
 */

public class MyFragmentAdapter extends FragmentPagerAdapter{
    private List<String> mList_title;
    private List<String> mList_uri;

    public MyFragmentAdapter(FragmentManager fm, List<String> mList_uri , List<String> mList_title  ) {
        super(fm);
        this.mList_title=mList_title;
        this.mList_uri=mList_uri;
    }
    public MyFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position==0){
            return new FirstFragment(mList_uri.get(0));
        }else{
            return MyTestFragment.getInstance(mList_uri.get(position));
        }

    }

    @Override
    public int getCount() {
        return mList_uri.size();
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return mList_title.get(position);
    }
}
