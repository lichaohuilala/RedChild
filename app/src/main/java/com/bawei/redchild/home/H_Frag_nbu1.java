package com.bawei.redchild.home;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.redchild.MainActivity;
import com.bawei.redchild.R;
import com.bawei.redchild.home.baseadapter.H_recyview_adapter;
import com.bawei.redchild.home.bean.Home_frag1_bena_nbu;
import com.bawei.redchild.home.h_url_f1.Gsons;
import com.bawei.redchild.home.h_url_f1.Tianhenxian;
import com.bawei.redchild.home.h_url_f1.Url;
import com.bumptech.glide.Glide;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import static android.R.attr.id;
import static android.R.id.list;


public class H_Frag_nbu1 extends Fragment {

    public String path;
  private RecyclerView review_home_nbuxs;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.h__frag_nbu1, container, false);
        review_home_nbuxs= (RecyclerView) inflate.findViewById(R.id.recview_home_nbuxs);
       review_home_nbuxs.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        initview1();
        return inflate;
    }

    private void initview1() {
        OkHttpUtils.get().url(Url.jsonpath+path).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                Home_frag1_bena_nbu home_frag1_bena_nbu = Gsons.GetGsonfrom(response, Home_frag1_bena_nbu.class);
                List<Home_frag1_bena_nbu.DataBean> data = home_frag1_bena_nbu.getData();
                H_recyview_adapter h_recyview_adapter = new H_recyview_adapter(data, getActivity());
                review_home_nbuxs.setAdapter(h_recyview_adapter);
                review_home_nbuxs.addItemDecoration(new Tianhenxian(getActivity(),Tianhenxian.VERTICAL_LIST));


            }
        });

    }

}
