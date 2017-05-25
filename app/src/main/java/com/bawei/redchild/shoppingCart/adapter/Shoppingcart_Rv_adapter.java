package com.bawei.redchild.shoppingCart.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.redchild.R;
import com.greendao.bean.Data;

import java.util.List;

/**
 * date: 2017/5/23
 * author:侯亮亮候亮亮
 */

public class Shoppingcart_Rv_adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TWO = 1;
    private static final int ONE = 0;
    public  static  int tag=0;
    private final LayoutInflater mLayoutInflater;
    public List<Data> arr;
    int connt=0;
    private Context mContext;
    CheckBox cb;
    public boolean mFlag=true;
    // 存储勾选框状态的map集合
    TextView mFragment_sc_item_close;
    TextView mFragment_sc_item_select;
    TextView mTv_shopcaer_compile_tv;
    public Shoppingcart_Rv_adapter(List<Data> arr, Context context, CheckBox cb,TextView mFragment_sc_item_close,TextView mFragment_sc_item_select,TextView mTv_shopcaer_compile_tv) {
        this.arr = arr;
        mContext = context;
        this.cb = cb;
        mLayoutInflater = LayoutInflater.from(context);
        this.mFragment_sc_item_close=mFragment_sc_item_close;
        this.mFragment_sc_item_select=mFragment_sc_item_select;
        this.mTv_shopcaer_compile_tv=mTv_shopcaer_compile_tv;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case ONE:
                return new listViewholder1(mLayoutInflater.inflate(R.layout.fragment_shoppingcart_rv_item,null,false),arr);
            case TWO:
                return new listViewholder2(mLayoutInflater.inflate(R.layout.fragment_shoppomhcart_rv_item_rv_item,null,false));
        }
        return new listViewholder1(mLayoutInflater.inflate(R.layout.fragment_shoppingcart_rv_item,null,false),arr);

    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
          if (holder instanceof listViewholder1){
              tag=position;
              ((listViewholder1)holder).mFragment_sc_item_title.setText(arr.get(position).getShopName());

              ((listViewholder1)holder).fragment_sc_item_title_cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                  @Override
                  public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                      for (int i = 0; i < arr.size(); i++) {
                          if(arr.get(position).getShopName().equals(arr.get(i).getShopName())){
                              arr.get(i).setZhuangtai(isChecked);
                  }
                                                                                             }
//                      notifyDataSetChanged();
                  }
              });
              ((listViewholder1)holder).fragment_sc_item_title_cb.setChecked(arr.get(position).getZhuangtai());


          }else if (holder instanceof  listViewholder2) {
              ((listViewholder2) holder).mFragment_sc_rv_item_rv_item_content.setText(arr.get(position).getName());
              ((listViewholder2) holder).mFragment_sc_rv_item_rv_item_cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                                                                                        @Override
                                                                                                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                                                                                                            arr.get(position).setZhuangtai(isChecked);
//                          cb.setChecked(false);

                                                                                                        }
                                                                                                    }
              );

              ((listViewholder2) holder).mFragment_sc_rv_item_rv_item_cb.setChecked(arr.get(position).getZhuangtai());
              ((listViewholder2) holder).mFragment_sc_rv_item_rv_item_cb.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      for (int i=0;i<arr.size();i++){
                          Boolean zhuangtai = arr.get(i).getZhuangtai();
                          if (zhuangtai){
                              connt++;

                          }
                      }
                            Toast.makeText(mContext, "name不能为空"+connt+"aaaaaa"+arr.size(), Toast.LENGTH_SHORT).show();

                      Log.d("TAG",connt+"");
                      Log.d("TAG2",arr.size()+"");
                      if (mTv_shopcaer_compile_tv.getText().equals("编辑")){
                      mFragment_sc_item_close.setText("去结算"+ "(" + arr.size() + ")");}else {


                          mFragment_sc_item_select.setText("（已选:"+arr.size()+")");
                      }
                      notifyDataSetChanged();
                      if (connt==arr.size()){

                          cb.setChecked(true);
                      }else {
                          cb.setChecked(false);

                      }
                      connt=0;
                  }
              });
          }
    }

    @Override
    public int getItemCount() {
        return arr.size();
    }

    @Override
    public int getItemViewType(int position) {

        Data data = arr.get(position);
        if (data.getIsTop()){
            return ONE;
        }
        return TWO;
    }

    public static class listViewholder1 extends  RecyclerView.ViewHolder{

        private final TextView mFragment_sc_item_title;
        private final CheckBox fragment_sc_item_title_cb;

        public listViewholder1(View itemView, final List<Data> arrs) {
            super(itemView);
            mFragment_sc_item_title = (TextView) itemView.findViewById(R.id.fragment_sc_item_title);
            fragment_sc_item_title_cb=(CheckBox) itemView.findViewById(R.id.fragment_sc_item_title_cb);
        }
    }
    public static class listViewholder2 extends  RecyclerView.ViewHolder{

        private final TextView mFragment_sc_rv_item_rv_item_content;
        private final CheckBox mFragment_sc_rv_item_rv_item_cb;

        public listViewholder2(View itemView) {
            super(itemView);
            mFragment_sc_rv_item_rv_item_content = (TextView) itemView.findViewById(R.id.fragment_sc_rv_item_rv_item_content);
            mFragment_sc_rv_item_rv_item_cb = (CheckBox) itemView.findViewById(R.id.fragment_sc_rv_item_rv_item_cb);
        }
    }

}
