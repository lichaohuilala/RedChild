package com.bawei.redchild.groupon;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.redchild.R;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * date:2017/5/24.
 * author:刘宏亮.
 * function:
 */

public class SecondAdapter extends RecyclerView.Adapter<SecondAdapter.ViewHolder1>{
    private List<GsonBean2.EnrollsBean.ListBean>mList;
    private Context mContext;

    public SecondAdapter(List<GsonBean2.EnrollsBean.ListBean> list, Context context) {
        mList = list;
        mContext = context;
    }

    @Override
    public SecondAdapter.ViewHolder1 onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.list_item3, null);
        return new ViewHolder1(view);
    }

    @Override
    public void onBindViewHolder(SecondAdapter.ViewHolder1 holder, int position) {
        GsonBean2.EnrollsBean.ListBean bean = mList.get(position);
        holder.setData(bean);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder1 extends RecyclerView.ViewHolder {

        private final ImageView mIv_item3;
        private final TextView mTv_item3_name,mTv_item3_desc,mTv_item3_price;

        public ViewHolder1(View itemView) {
            super(itemView);
            mIv_item3 = (ImageView) itemView.findViewById(R.id.item3_image);
            mTv_item3_name = (TextView) itemView.findViewById(R.id.item3_textView);
            mTv_item3_desc= (TextView) itemView.findViewById(R.id.item3_textView2);
            mTv_item3_price= (TextView) itemView.findViewById(R.id.item3_textView3);
        }

        public void setData(GsonBean2.EnrollsBean.ListBean bean2) {
            Glide.with(mContext).load(bean2.getImgUrl()).into(mIv_item3);
            mTv_item3_name.setText(bean2.getItemName());
            mTv_item3_desc.setText(bean2.getItemDesc());
            mTv_item3_price.setText(bean2.getPrice()+"");
        }
    }
}
