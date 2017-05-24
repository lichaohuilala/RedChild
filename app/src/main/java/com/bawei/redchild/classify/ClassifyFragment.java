package com.bawei.redchild.classify;

import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bawei.redchild.R;
import com.bawei.redchild.base.BaseFragment;
import com.bawei.redchild.classify.adapter.Re_Classify_Left_Adapter;
import com.bawei.redchild.classify.dao.Classify;
import com.bawei.redchild.classify.dao.Url;
import com.bawei.redchild.classify.fragment.Classify_Right_Fragment;
import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

/**
 * Effect :
 * <p>
 * Created by Administrator
 * Time by 2017/5/17 0017
 */
public class ClassifyFragment extends BaseFragment {
    LinearLayoutManager layoutMgr;
    private Classify classify;
    private RecyclerView mRv_classify_left;
    private Re_Classify_Left_Adapter mleft_adapter;
    int oldposition =2;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    init();
                    break;
            }
        }
    };

    private List<Classify.RsBean> rs;
    private Classify.RsBean rsBean;
    private  int position=0;
    /**
     * 绑定布局文件
     * @return 布局文件ID
     */
    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_classify;
    }
    /**
     * 初始化 view控件
     */
    @Override
    protected void initView() {
        initdata();
        mRv_classify_left = (RecyclerView) getView().findViewById(R.id.rv_classify_left);

        mRv_classify_left.setHasFixedSize(true);
        layoutMgr = new LinearLayoutManager(getActivity());
        //2.设置布局管理器,参数  LinearLayoutManager
        mRv_classify_left.setLayoutManager(layoutMgr);
    }
    private void init() {
        rs = classify.getRs();
        mleft_adapter = new Re_Classify_Left_Adapter(getActivity(), rs);
        mRv_classify_left.setAdapter(mleft_adapter);

        mleft_adapter.setItemClickListener(new Re_Classify_Left_Adapter.MyItemClickListener() {

            private View childAt;

            @Override
            public void onItemClick(View view, int position) {
                // 获取 资源文件里的颜色
                int color = getResources().getColor(R.color.gainsboro);
                int color1 = getResources().getColor(R.color.white);
                    if (position==oldposition){
                        return;
                    }


                Classify.RsBean rsBean = rs.get(position);
                setfragment(new Classify_Right_Fragment(rsBean));

                ClassifyFragment.this.rsBean = rs.get(position);
                view.setBackgroundColor(color);
                // 从管理器上获取   不同的view

                    childAt = layoutMgr.getChildAt(oldposition);
                    childAt.setBackgroundColor(color1);

                LinearLayout layout = (LinearLayout)view;		//获取布局中任意控件对象
                TextView status = (TextView) layout.findViewById(R.id.classif_item_text);
                status.setTextColor(Color.YELLOW);

                LinearLayout layout1 = (LinearLayout)childAt;		//获取布局中任意控件对象
                TextView status1 = (TextView) layout1.findViewById(R.id.classif_item_text);

                int color3 = getResources().getColor(R.color.add_cart_disabled_text_color);
                status1.setTextColor(color3);
                oldposition=position;
            }
        });
        //---------------右边的Recycle view
        Classify.RsBean rsBean = rs.get(position);
        setfragment(new Classify_Right_Fragment(rsBean));





    }
    public  void  setfragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.rv_classify_right,fragment);
        fragmentTransaction.commit();
    }

    private void initdata() {
        OkHttpUtils
                .get()
                .url(Url.path)
                .build()
                .execute(new StringCallback()
                {
                    @Override
                    public void onError(Request request, Exception e)
                    {
                    }
                    @Override
                    public void onResponse(String response)
                    {
                        Log.d("zzz",response);
                        if (response!=null){
                            Gson gson=new Gson();
                            classify = gson.fromJson(response, Classify.class);
                            String dirName = classify.getRs().get(0).getDirName();
                            Log.d("zzz",dirName);
                            handler.sendEmptyMessage(1);
                        }
                    }
                });


    }
}
