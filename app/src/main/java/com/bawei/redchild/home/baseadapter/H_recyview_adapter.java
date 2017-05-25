package com.bawei.redchild.home.baseadapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.redchild.R;
import com.bawei.redchild.home.bean.Home_frag1_bean;
import com.bawei.redchild.home.bean.Home_frag1_bena_nbu;

import com.bawei.redchild.home.h_url_f1.Tianhenxian;
import com.bawei.redchild.home.h_url_f1.Url;
import com.bumptech.glide.Glide;

import java.util.List;



/**
 * 类描述：
 * data:2017/5/21
 * author:高伟振(lenovo)
 */

public class H_recyview_adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<Home_frag1_bena_nbu.DataBean> list;
    private Context mContext;
private int TIAOMU1=0;
    private int TIAOMU2=1;
    private int TIAOMU3=2;
    private int TIAOMU4=3;
    private int TIAOMU5=4;
    private int TIAOMU6=5;
    public H_recyview_adapter(List<Home_frag1_bena_nbu.DataBean> list, Context context) {
        this.list = list;
        mContext = context;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
         return TIAOMU1;
        }else if (position == 1) {
            return TIAOMU2;
        }else if (position == 2) {
            return TIAOMU3;
        }else if (position == 3) {
            return TIAOMU6;
        }else if (position == 4) {
            return TIAOMU4;
        } else {
            return TIAOMU5;
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TIAOMU1) {
            View itemView = LayoutInflater.from(mContext).inflate(R.layout.home_fr1_adapter_recyview, parent, false);

            ViewHolder viewHolder = new ViewHolder(itemView);
            return viewHolder;
        }else if (viewType == TIAOMU2) {
            View itemView = LayoutInflater.from(mContext).inflate(R.layout.home_fr1_adapter_recyview2, parent, false);
            ViewHolder2 viewHolder2 = new ViewHolder2(itemView);
            return viewHolder2;
        }else if (viewType == TIAOMU3) {
            View itemView = LayoutInflater.from(mContext).inflate(R.layout.home_fr1_adapter_recyview3, parent, false);
            ViewHolder3 viewHolder3 = new ViewHolder3(itemView);
            return viewHolder3;
        }else if (viewType == TIAOMU4) {
            View itemView = LayoutInflater.from(mContext).inflate(R.layout.home_fr1_adapter_recyview4, parent, false);
            ViewHolder4 viewHolder4 = new ViewHolder4(itemView);
            return viewHolder4;
        }else if (viewType == TIAOMU5) {
            View itenView = LayoutInflater.from(mContext).inflate(R.layout.home_fr1_adapter_recyview5, parent, false);
            ViewHolder5 viewHolder5 = new ViewHolder5(itenView);
            return viewHolder5;
        }else if (viewType == TIAOMU6) {
            View itemView = LayoutInflater.from(mContext).inflate(R.layout.home_fr1_adapter_recyview6, parent, false);
            ViewHolder6 viewHolder6 = new ViewHolder6(itemView);
            return viewHolder6;
        }

        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            ViewHolder viewh= (ViewHolder) holder;
            Glide
                    .with(mContext)
                    .load(Url.tupin+list.get(0).get_$88001().getTag().get(0).getPicUrl())
                    .into(viewh.img);
        }else if (holder instanceof ViewHolder2){
            ViewHolder2 viewh2= (ViewHolder2) holder;

            Glide.with(mContext)
                    .load(Url.tupin+list.get(1).get_$88003().getTag().get(0).getPicUrl())
                    .into(viewh2.img2);
            viewh2.mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false));
            H_frag_recyclervi_nbu_adapter h_frag_recyclervi_nbu_adapter = new H_frag_recyclervi_nbu_adapter(list, mContext);
            viewh2.mRecyclerView.setAdapter(h_frag_recyclervi_nbu_adapter);
            viewh2.mRecyclerView.addItemDecoration(new Tianhenxian(mContext,Tianhenxian.VERTICAL_LIST));


        }else if (holder instanceof ViewHolder3) {
            ViewHolder3 view3= (ViewHolder3) holder;

            Glide.with(mContext)
                    .load(Url.tupin+list.get(3).get_$88005().getTag().get(0).getPicUrl())
                    .into(view3.img3_1);
            Glide.with(mContext)
                    .load(Url.tupin+list.get(4).get_$88015().getTag().get(0).getPicUrl())
                    .into(view3.img3_2);
            view3.tv3.setText(list.get(4).get_$88015().getTag().get(0).getElementName());
        }else if (holder instanceof ViewHolder6) {
            ViewHolder6 view6= (ViewHolder6) holder;

            Glide.with(mContext)
                    .load(Url.tupin+list.get(6).get_$88015().getTag().get(0).getPicUrl())
                    .into(view6.img6);
            view6.tv6_1.setText(list.get(6).get_$88015().getTag().get(0).getElementName());
            view6.tv6_2.setText(list.get(6).get_$88015().getTag().get(0).getElementDesc());
         } else if (holder instanceof ViewHolder4) {
            ViewHolder4 view4= (ViewHolder4) holder;
            Glide.with(mContext)
                    .load(Url.tupin+list.get(9).get_$88005().getTag().get(0).getPicUrl())
                    .into(view4.img4_1);
            Glide.with(mContext)
                    .load(Url.tupin+list.get(10).get_$88010().getTag().get(0).getPicUrl())
                    .into(view4.img4_2);
            view4.mRecyclerView4444.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false));
            H_frag_recyclervi_nbu__haioqi_adapter h_frag_recyclervi_nbu__haioqi_adapter = new H_frag_recyclervi_nbu__haioqi_adapter(list, mContext);
            view4.mRecyclerView4444.setAdapter(h_frag_recyclervi_nbu__haioqi_adapter);
            view4.mRecyclerView4444.addItemDecoration(new Tianhenxian(mContext,Tianhenxian.VERTICAL_LIST));
        }else if (holder instanceof ViewHolder5) {
            ViewHolder5 view5= (ViewHolder5) holder;
            Glide.with(mContext)
                    .load(Url.tupin+list.get(16).get_$88010().getTag().get(0).getPicUrl())
                    .into(view5.img5);
         view5.mRecyclerView5.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false));
            H_frag_recyclervi_nbu__yiying_adapter h_frag_recyclervi_nbu__yiying_adapter = new H_frag_recyclervi_nbu__yiying_adapter(list, mContext);

            view5.mRecyclerView5.setAdapter(h_frag_recyclervi_nbu__yiying_adapter);
            view5.mRecyclerView5.addItemDecoration(new Tianhenxian(mContext,Tianhenxian.VERTICAL_LIST));
        }

    }



    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView img;

        public ViewHolder(View itemView) {
            super(itemView);
          img= (ImageView) itemView.findViewById(R.id.img_fr1_adap_review);

        }
    }

    public class ViewHolder2 extends RecyclerView.ViewHolder {
        private ImageView img2;
        private RecyclerView mRecyclerView;
        public ViewHolder2(View itemView) {
            super(itemView);
            img2= (ImageView) itemView.findViewById(R.id.img2_fr1_adap_review2);
            mRecyclerView= (RecyclerView) itemView.findViewById(R.id.hon_nbu_recyview_sd);
        }
    }

    public class ViewHolder3 extends RecyclerView.ViewHolder {
        private ImageView img3_1,img3_2;
        private TextView tv3;

        public ViewHolder3(View itemView) {
            super(itemView);
          img3_1= (ImageView) itemView.findViewById(R.id.img3_fr1_adap_review_buju3);
           img3_2= (ImageView) itemView.findViewById(R.id.img4_fr1_adap_review_buju3);
            tv3= (TextView) itemView.findViewById(R.id.tv1_fr1_adap_review_buju3);
        }
    }

    public class ViewHolder4 extends RecyclerView.ViewHolder {
        private ImageView img4_1,img4_2;
        private RecyclerView mRecyclerView4444;
        public ViewHolder4(View itemView) {
            super(itemView);
         img4_1= (ImageView) itemView.findViewById(R.id.img1_fr1_adap_review4);
            img4_2= (ImageView) itemView.findViewById(R.id.img2_fr1_adap_review4);
mRecyclerView4444= (RecyclerView) itemView.findViewById(R.id.hon_nbu_recyview4444_sd4);
        }
    }

    public class ViewHolder5 extends RecyclerView.ViewHolder {
        private ImageView img5;
        private RecyclerView mRecyclerView5;
        public ViewHolder5(View itemView) {
            super(itemView);
            img5= (ImageView) itemView.findViewById(R.id.img2_fr1_adap_review5);
            mRecyclerView5= (RecyclerView) itemView.findViewById(R.id.hon_nbu_recyview5_sd);
        }
    }

    public class ViewHolder6 extends RecyclerView.ViewHolder {
        private ImageView img6;
        private TextView tv6_1,tv6_2;
        public ViewHolder6(View itemView) {
            super(itemView);
img6= (ImageView) itemView.findViewById(R.id.img1_fr1_adap_review6);
            tv6_1= (TextView) itemView.findViewById(R.id.tv0_fr1_adap_review6);
            tv6_2= (TextView) itemView.findViewById(R.id.tv1_fr1_adap_review6);
        }
    }
}
