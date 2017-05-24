package com.bawei.redchild.groupon;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.util.Log;

import com.bawei.redchild.R;
import com.bawei.redchild.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import static com.bawei.redchild.groupon.URL.fzxb;
import static com.bawei.redchild.groupon.URL.ggmm;
import static com.bawei.redchild.groupon.URL.jx;
import static com.bawei.redchild.groupon.URL.ls;
import static com.bawei.redchild.groupon.URL.my;
import static com.bawei.redchild.groupon.URL.mz;
import static com.bawei.redchild.groupon.URL.ry;
import static com.bawei.redchild.groupon.URL.send;
import static com.bawei.redchild.groupon.URL.sgsx;
import static com.bawei.redchild.groupon.URL.smdq;
import static com.bawei.redchild.groupon.URL.znk;

/**
 * Effect :
 * <p>
 * Created by Administrator
 * Time by 2017/5/17 0017
 */

public class GrouponFragment extends BaseFragment {

    private TabLayout mTabLayout;
    private NoScrollViewPager mNoScrollViewPager;
    private int[]icon={R.drawable.jixuan,R.drawable.jajur,R.drawable.muying,R.drawable.lingshi,R.drawable.zhiniaoku,R.drawable.shuiguo,R.drawable.meizhuang,R.drawable.cat,R.drawable.baoyou,R.drawable.fushixiangbao,R.drawable.shumadianqi};
    private List<String> list_url;
    private List<String>list_title;
    /**
     * 绑定布局文件
     *
     * @return 布局文件ID
     */
    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_groupon;
    }

    /**
     * 初始化 view控件
     */
    @Override
    protected void initView() {
        mTabLayout = (TabLayout) getView().findViewById(R.id.tablayout_group);
        mNoScrollViewPager = (NoScrollViewPager) getView().findViewById(R.id.vp_group);
        initData();
        //设置TabLayout模式
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mTabLayout.setTabTextColors(Color.BLACK,Color.RED);
        //与自定义viewpager关联
        mTabLayout.setupWithViewPager(mNoScrollViewPager);
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //选中状态把它的字变成红色
                mTabLayout.setTabTextColors(Color.BLACK,Color.RED);
                Log.d("onTabSelected", "position=" + tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                Log.d("onTabUnselected", "position=" + tab.getPosition());

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                Log.d("onTabReselected", "position=" + tab.getPosition());

            }
        });
        MyFragmentAdapter fragmentAdapter = new MyFragmentAdapter(getChildFragmentManager(), list_url, list_title);
        mNoScrollViewPager.setAdapter(fragmentAdapter);
        setTabIcon();
    }
    private void setTabIcon() {
        for (int i = 0; i < icon.length; i++) {
            mTabLayout.getTabAt(i).setIcon(icon[i]);

        }
    }
    private void initData(){
        list_url=new ArrayList<>();
        list_title=new ArrayList<>();
        list_url.add(jx);
        list_url.add(ry);
        list_url.add(my);
        list_url.add(ls);
        list_url.add(znk);
        list_url.add(sgsx);
        list_url.add(mz);
        list_url.add(ggmm);
        list_url.add(send);
        list_url.add(fzxb);
        list_url.add(smdq);
        list_title.add("精选");
        list_title.add("日用");
        list_title.add("母婴");
        list_title.add("零食饮料");
        list_title.add("纸尿裤");
        list_title.add("水果生鲜");
        list_title.add("美妆");
        list_title.add("狗狗喵咪");
        list_title.add("9.9包邮");
        list_title.add("服装箱包");
        list_title.add("数码电器");
    }
}
