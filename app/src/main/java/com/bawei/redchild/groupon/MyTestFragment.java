package com.bawei.redchild.groupon;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.bawei.redchild.R;
import com.bawei.redchild.base.BaseFragment;
import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

/**
 * date:2017/4/12.
 * author:刘宏亮.
 * function:
 */

public class MyTestFragment extends BaseFragment {

    private String mMUri;
    private RecyclerView mGroup_recyclerView;

    public static Fragment getInstance(String uri){
        MyTestFragment fragment = new MyTestFragment();
        Bundle bundle = new Bundle();
        bundle.putString("uri",uri);
        fragment.setArguments(bundle);
        return fragment;

    }
    @Override
    protected int attachLayoutRes() {
        return R.layout.firstfragment;
    }

    @Override
    protected void initView() {
        mGroup_recyclerView = (RecyclerView) getView().findViewById(R.id.recview_group_firstfragment);
        Bundle bundle = getArguments();
        String uri = bundle.getString("uri");
        initData(uri);
    }




    private void initData(String uri) {
        OkHttpUtils.get().url(uri).build().execute(new StringCallback() {

            private SecondAdapter mAdapter;

            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                Log.d("zzz",response);
                Gson gson = new Gson();
                GsonBean2 bean2 = gson.fromJson(response, GsonBean2.class);
                mAdapter = new SecondAdapter(bean2.getEnrolls().getList(), getActivity());
                LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                mGroup_recyclerView.setLayoutManager(manager);
                mGroup_recyclerView.setAdapter(mAdapter);
            }
        });



    }



}
