package com.bawei.redchild.home.baseadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bawei.redchild.R;
import com.bawei.redchild.home.bean.Home_frag1_bean;
import com.bawei.redchild.home.bean.Home_frag1_bena_nbu;
import com.bawei.redchild.home.h_url_f1.Url;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * 类描述：
 * data:2017/5/21
 * author:高伟振(lenovo)
 */

public class H_recyview_adapter extends RecyclerView.Adapter<H_recyview_adapter.ViewHolder> {
    List<Home_frag1_bena_nbu.DataBean._$88001Bean.TagBean> list;
    private Context mContext;

    public H_recyview_adapter(List<Home_frag1_bena_nbu.DataBean._$88001Bean.TagBean> list, Context context) {
        this.list = list;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = View.inflate(mContext, R.layout.home_fr1_adapter_recyview, null);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
//         holder.img.
        Glide
                .with(mContext)
                .load(Url.tupin+list.get(position).getPicUrl())
                .into(holder.img);
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
}
