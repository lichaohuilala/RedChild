package com.bawei.redchild.home;


import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.bawei.redchild.R;
import com.bawei.redchild.home.baseadapter.H_frag_base;
import com.bawei.redchild.home.baseadapter.H_recyview_adapter;
import com.bawei.redchild.home.bean.Home_frag1_bean;
import com.bawei.redchild.home.h_url_f1.Gsons;
import com.bawei.redchild.home.h_url_f1.Url;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private ViewPager vp_homef1_hua_viewp;
    private TabLayout tb_homef1_biaot_tabl;
    List<String> mList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View inflate = inflater.inflate(R.layout.fragment_home, container, false);
        vp_homef1_hua_viewp = (ViewPager) inflate.findViewById(R.id.vp_homef1_hua_viewp);
        tb_homef1_biaot_tabl = (TabLayout) inflate.findViewById(R.id.tb_homef1_biaot_tabl);

       initview();
        return inflate;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    private void initview() {

        OkHttpUtils.get().url(Url.path).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                Home_frag1_bean home_frag1_bean = Gsons.GetGsonfrom(response, Home_frag1_bean.class);
                List<Home_frag1_bean.DataBean> data = home_frag1_bean.getData();
                List<Home_frag1_bean.DataBean.TagBean> tag = data.get(0).getTag();
                H_frag_base h_frag_base = new H_frag_base(getChildFragmentManager(), tag);
                vp_homef1_hua_viewp.setAdapter(h_frag_base);
                tb_homef1_biaot_tabl.setupWithViewPager(vp_homef1_hua_viewp);

            }
        });

    }

}