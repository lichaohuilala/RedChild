package com.bawei.redchild.groupon;

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
 * date:2017/5/23.
 * author:刘宏亮.
 * function:
 */

public class FirstFragment extends BaseFragment {

    private RecyclerView mFirstrecyclerView;
    private String path;
    private MyRecyclerViewAdapter mAdapter;

    public FirstFragment(String path) {
        this.path = path;
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.firstfragment;
    }

    @Override
    protected void initView() {
        mFirstrecyclerView = (RecyclerView) getView().findViewById(R.id.recview_group_firstfragment);
        initData(path);
    }
    private void initData(String uri) {
        OkHttpUtils.get().url(uri).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                Log.d("zzz",response);
                Gson gson = new Gson();
                GsonBean gsonBean = gson.fromJson(response, GsonBean.class);
                mAdapter = new MyRecyclerViewAdapter(getActivity(),gsonBean);
                LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                mFirstrecyclerView.setLayoutManager(manager);
                mFirstrecyclerView.setAdapter(mAdapter);
            }
        });



    }
}
