package com.bawei.redchild.shoppingCart;

import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bawei.redchild.R;
import com.bawei.redchild.RedChildApplication;
import com.bawei.redchild.base.BaseFragment;
import com.bawei.redchild.shoppingCart.adapter.Shoppingcart_Rv_adapter;
import com.greendao.bean.Data;
import com.greendao.dao.DataDao;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.dao.query.QueryBuilder;

/**
 * Effect :
 * <p>
 * Created by Administrator
 * Time by 2017/5/17 0017
 */

public class ShoppingCartFragment extends BaseFragment implements View.OnClickListener{

    private TextView mTv_shopcaer_title_tv;
    private TextView mTv_shopcaer_compile_tv;
    private CheckBox mFragment_sc_item_title_cb_all;
    private TextView mFragment_sc_item_tatal_price;
    private TextView mFragment_sc_item_close;
    private LinearLayout mFragment_sc_complie_linear;
    private TextView mFragment_sc_item_select;
    private RecyclerView mRv_shopcaer_show_recyclerview;
    private CheckBox mFragment_sc_item_title_cb_all1;
    private Shoppingcart_Rv_adapter mShoppingcart_rv_adapter;
    private List<Data> mDatas;
    private Data data;
    private TextView mFragment_sc_item_tatal_price1;

    /**
     * 绑定布局文件
     *
     * @return 布局文件ID
     */
    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_shoppingcart;
    }

    /**
     * 初始化 view控件
     */
    @Override
    protected void initView() {
        //标题
        mTv_shopcaer_title_tv = (TextView) getView().findViewById(R.id.tv_shopcaer_title_tv);
        //编辑
        mTv_shopcaer_compile_tv = (TextView) getView().findViewById(R.id.tv_shopcaer_compile_tv);
        //全选checkBox
//        mFragment_sc_item_title_cb_all = (CheckBox) getView().findViewById(R.id.fragment_sc_item_title_cb_all);
        //合计金额
        mFragment_sc_item_tatal_price = (TextView) getView().findViewById(R.id.fragment_sc_item_tatal_price);
        //结算
        mFragment_sc_item_close = (TextView) getView().findViewById(R.id.fragment_sc_item_close);
        //合计金额和运费的外布局
        mFragment_sc_complie_linear = (LinearLayout) getView().findViewById(R.id.fragment_sc_complie_linear);
         //已选个数
        mFragment_sc_item_select = (TextView) getView().findViewById(R.id.fragment_sc_item_select);
         //RecyclerView
        mRv_shopcaer_show_recyclerview = (RecyclerView) getView().findViewById(R.id.rv_shopcaer_show_recyclerview);
         //全选按钮
        mFragment_sc_item_title_cb_all1 = (CheckBox) getView().findViewById(R.id.fragment_sc_item_title_cb_all);
         //合计金额
        mFragment_sc_item_tatal_price1 = (TextView) getView().findViewById(R.id.fragment_sc_item_tatal_price);

        mTv_shopcaer_compile_tv.setOnClickListener(this);
        mFragment_sc_item_title_cb_all1.setOnClickListener(this);
        mFragment_sc_item_title_cb_all1.setChecked(true);

        //check 改变监听
//        mFragment_sc_item_title_cb_all1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
////                for (int i = 0; i < mDatas.size(); i++) {
////                    mDatas.get(i).setZhuangtai(isChecked);
////                }
//
////                    mFragment_sc_item_title_cb_all1.setChecked(isChecked);
//
////                mShoppingcart_rv_adapter.notifyDataSetChanged();
//            }
//        });
            getNoteDao().deleteAll();
        //测试添加一条假数据
//        Data data = new Data();
//        data.setShopName("苏宁自营");
//        data.setFreight("免运费");
//        data.setImage("http://image.suning.cn/uimg/pgs/activity/149509915683171105.jpg");
//        data.setName("asdasdasdddddd");
//        data.setPrice("500.00");
//        data.setStatus("1");
//        data.setColor("白色");
//        data.setNumber("2");
//        data.setReduction("212");
//        data.setZhuangtai(true);
//        insertUser(data);
//
//
//        Data data2 = new Data();
//        data2.setShopName("苏宁自营");
//        data2.setFreight("免运费2");
//        data2.setStatus("1");
//        data2.setImage("http://image.suning.cn/uimg/pgs/activity/149509915683171105.jpg");
//        data2.setName("321321165165");
//        data2.setPrice("400.00");
//        data2.setColor("白色");
//        data2.setNumber("2");
//        data2.setReduction("212");
//        data2.setZhuangtai(true);
//        insertUser(data2);
//
//        mDatas = queryUserList();


        /**
         * 写法要求：
         *  数据必须是经过排序
         */
        mDatas=new ArrayList<>();
        for (int i = 0; i < 5; i++) {

            Data d = new Data();
            d.setShopName("苏宁易购");
            d.setName("测试"+i);
            d.setPrice(50+i);
            d.setZhuangtai(true);
            d.setIsTop(false);

            mDatas.add(d);
        }
        for (int i = 0; i < 5; i++) {

            Data d = new Data();
            d.setShopName("小熊之家");
            d.setName("试试"+i);
            d.setZhuangtai(true);
            d.setPrice(50+i);
            d.setIsTop(false);
            mDatas.add(d);
        }

        //创建一个集合  保存标记了top位置的下标值
        ArrayList<Integer> integers = new ArrayList<>();
        //创建一个集合  保存添加的店铺栏
        ArrayList<Data> tops = new ArrayList<>();

        boolean isFirst=true;
        String tag;
        //为了展示店铺名称 遍历集合
        for (int i = 0; i < mDatas.size(); i++) {
            //获取 店铺名称
            tag = mDatas.get(i).getShopName();
            //查询 该集合中是否具有相同的店铺商品
            for (int j = i; j < mDatas.size(); j++) {


                //判断 如果以后的集合中 具有相同店铺名称的商品时 标记循环值 从该标记后开始遍历
                if(tag.equals(mDatas.get(j).getShopName())){
                    // 起始为获取为0  起始标记为1
                    // 如果true 证明最少有两条数据
                    // 添加top即可
                    if(isFirst){
                        //获取当前的下标值 用于以后的添加
                        if(j==0){
                            integers.add(j);
                        }else {
                            integers.add(j+1);
                        }
                        data = new Data();
                        data.setShopName(tag);
                        data.setPrice(0);
                        data.setZhuangtai(true);
                        data.setIsTop(true);
                        tops.add(data);

                        isFirst=false;
                    }
                    //并且 我们可以为了节省内存 将i值 标记到该值 不做重复循环
                    i=j;
                }else{
                    //如果为true 证明都是一个条目下的产品 所以我们可以 为它添加一个 专门用显示店家的标题
                    //因为 店铺标题只需要添加一次 那么我们可以判断 添加一次就行
                    if(isFirst){
                        //获取当前的下标值 用于以后的添加
                        if(j==0){
                            integers.add(j);
                        }else {
                            integers.add(j+1);
                        }


                        data = new Data();
                        data.setShopName(tag);
                        data.setPrice(0);
                        data.setZhuangtai(true);
                        data.setIsTop(true);
                        tops.add(data);

                        isFirst=false;
                    }
                }
            }
            isFirst=true;
        }

        //遍历 top集合 将其添加到指定下标位置
        for (int i = 0; i < tops.size(); i++) {
            mDatas.add(integers.get(i),tops.get(i));
        }
        int pice=0;
        for (int i = 0; i < mDatas.size(); i++) {
            int price = mDatas.get(i).getPrice();
            pice=pice+price;
        }
        mFragment_sc_item_tatal_price1.setText("¥"+pice);

        mFragment_sc_item_close.setBackgroundColor(Color.RED);

        mFragment_sc_item_close.setText("去结算"+"("+mDatas.size()+")");
//        mFragment_sc_item_tatal_price1.setText("¥"+pice);
        mShoppingcart_rv_adapter = new Shoppingcart_Rv_adapter(mDatas, getActivity(),mFragment_sc_item_title_cb_all1,mFragment_sc_item_close,mFragment_sc_item_select,mTv_shopcaer_compile_tv);

        mRv_shopcaer_show_recyclerview.setAdapter(mShoppingcart_rv_adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mRv_shopcaer_show_recyclerview.setLayoutManager(linearLayoutManager);

//        boolean flag = mShoppingcart_rv_adapter.mFlag;
//        if (!flag){
//            mFragment_sc_item_title_cb_all1.setChecked(false);
//        }
    }


    private DataDao getNoteDao() {
        // 通过 BaseApplication 类提供的 getDaoSession() 获取具体 Dao
        return ((RedChildApplication) getActivity().getApplicationContext()).getDaoSession().getDataDao();
    }

    private SQLiteDatabase getDb() {
        // 通过 BaseApplication 类提供的 getDb() 获取具体 db
        return ((RedChildApplication) getActivity().getApplicationContext()).getDb();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_shopcaer_compile_tv:
                if (mTv_shopcaer_compile_tv.getText().toString().trim().equals("完成")){
                    mFragment_sc_complie_linear.setVisibility(View.VISIBLE);
                    mTv_shopcaer_compile_tv.setText("编辑");
                    mFragment_sc_item_close.setText("去结算");
                    mFragment_sc_item_close.setBackgroundColor(Color.RED);
                    mFragment_sc_item_select.setVisibility(View.GONE);

                }else {

                    mFragment_sc_complie_linear.setVisibility(View.GONE);
                    mTv_shopcaer_compile_tv.setText("完成");
                    mFragment_sc_item_close.setText("删除");
                    mFragment_sc_item_close.setBackgroundColor(Color.parseColor("#EDA200"));
                    mFragment_sc_item_select.setVisibility(View.VISIBLE);
                    mFragment_sc_item_select.setText("（已选:"+mDatas.size()+")");
                }
                break;

            case R.id.fragment_sc_item_title_cb_all:
                 if (mFragment_sc_item_close.getText().equals("删除")){
                     if (!mFragment_sc_item_title_cb_all1.isChecked()) {

                         for (int i = 0; i < mDatas.size(); i++) {
                             mDatas.get(i).setZhuangtai(false);
                         }
                         mFragment_sc_item_select.setText("（已选:"+0+")");
                         mFragment_sc_item_close.setClickable(false);
                         mShoppingcart_rv_adapter.notifyDataSetChanged();
//                    Map<Integer, Boolean> map = mShoppingcart_rv_adapter.getMap();
//
//                for (int i = 0; i < map.size(); i++) {
//
//                    map.put(i, true);
//                      for (int i=0;i<mDatas.size();i++){
//                          mDatas.get(i).setZhuangtai(true);
//                              }
                         mShoppingcart_rv_adapter.notifyDataSetChanged();
                     } else {
                         for (int i = 0; i < mDatas.size(); i++) {
                             mDatas.get(i).setZhuangtai(true);
                         }
                         mFragment_sc_item_select.setText("（已选:"+mDatas.size()+")");
                         mShoppingcart_rv_adapter.notifyDataSetChanged();

                     }

                 }else {
                     if (!mFragment_sc_item_title_cb_all1.isChecked()) {

                         for (int i = 0; i < mDatas.size(); i++) {
                             mDatas.get(i).setZhuangtai(false);
                         }
                         mFragment_sc_item_tatal_price1.setText("¥:" + 0);
                         mFragment_sc_item_close.setText("去结算");
                         mFragment_sc_item_close.setBackgroundColor(Color.parseColor("#4c4c4c"));
                         mFragment_sc_item_close.setClickable(false);
                         mShoppingcart_rv_adapter.notifyDataSetChanged();
//                    Map<Integer, Boolean> map = mShoppingcart_rv_adapter.getMap();
//
//                for (int i = 0; i < map.size(); i++) {
//
//                    map.put(i, true);
//                      for (int i=0;i<mDatas.size();i++){
//                          mDatas.get(i).setZhuangtai(true);
//                              }
                         mShoppingcart_rv_adapter.notifyDataSetChanged();
                     } else {
                         int pice = 0;
                         for (int i = 0; i < mDatas.size(); i++) {
                             mDatas.get(i).setZhuangtai(true);
                             int price = mDatas.get(i).getPrice();
                             pice = pice + price;
                         }
                         mFragment_sc_item_tatal_price1.setText("¥:" + pice);
                         mFragment_sc_item_close.setBackgroundColor(Color.RED);
                         mFragment_sc_item_close.setText("去结算" + "(" + mDatas.size() + ")");
                         mShoppingcart_rv_adapter.notifyDataSetChanged();

                     }
//                }else {
//                    for (int i = 0; i < mDatas.size(); i++) {
//                        mDatas.get(i).setZhuangtai(false);
//                    }
//                    mShoppingcart_rv_adapter.notifyDataSetChanged();
//                    break;
//                }
                 }
        }
    }


    public void insertUser(Data user) {

        getNoteDao().insert(user);
    }


    public List<Data> queryUserList() {
        QueryBuilder<Data> qb = getNoteDao().queryBuilder();
        List<Data> list = qb.list();
        return list;
    }

}
