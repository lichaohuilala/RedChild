package com.bawei.redchild.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Effect :
 * <p>
 * Created by Administrator
 * Time by 2017/5/17 0017
 */

public abstract class BaseFragment extends Fragment {


    private Context mContext;
    private View inflaterView;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(inflaterView==null){
            inflaterView=inflater.inflate(attachLayoutRes(),container,false);
        }
        ViewGroup parent = (ViewGroup) inflaterView.getParent();
        if (parent != null) {
            parent.removeView(inflaterView);
        }
        return inflaterView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        /**
         * 初始化控件
         */
        initView();
    }

    /**
     * 绑定布局文件
     * @return  布局文件ID
     */
    protected abstract int attachLayoutRes();

    /**
     * 初始化 view控件
     */
    protected abstract void initView();

}
