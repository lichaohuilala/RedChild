package com.bawei.redchild.classify.fragment;

import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bawei.redchild.R;
import com.bawei.redchild.base.BaseFragment;
import com.bawei.redchild.classify.adapter.Re_Classify_Right_Adapter;
import com.bawei.redchild.classify.dao.Classify;

import java.util.List;
/**
 * A simple {@link Fragment} subclass.
 */
public class Classify_Right_Fragment extends BaseFragment {
    private RecyclerView mRv_classify_right;

   private  Classify.RsBean rsBean;
    private final List<Classify.RsBean.ChildrenBeanX> children;

    public Classify_Right_Fragment(Classify.RsBean rsBean) {
        this.rsBean = rsBean;
        children = rsBean.getChildren();
    }


    /**
     * 绑定布局文件
     *
     * @return 布局文件ID
     */
    @Override
    protected int attachLayoutRes() {

        return R.layout.fragment_classify__right_;
    }
    /**
     * 初始化 view控件
     */
    @Override
    protected void initView() {

        mRv_classify_right = (RecyclerView) getView().findViewById(R.id.rv_right_fragment);




        Re_Classify_Right_Adapter adapter = new Re_Classify_Right_Adapter(getActivity(), children);
        mRv_classify_right.setAdapter(adapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
        mRv_classify_right.setLayoutManager( gridLayoutManager);

    }
}
