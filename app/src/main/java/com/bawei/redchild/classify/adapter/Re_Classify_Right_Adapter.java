package com.bawei.redchild.classify.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.redchild.R;
import com.bawei.redchild.classify.dao.Classify;
import com.bumptech.glide.Glide;

import java.util.List;
/**
 * Created by lichaohui on 2017/5/23.
 */
public class Re_Classify_Right_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
     private  List<Classify.RsBean.ChildrenBeanX> children;
    private Context context;
    int  aa ;
    private ViewHolder viewHolder;
    private final List<Classify.RsBean.ChildrenBeanX.ChildrenBean> children1;
    public Re_Classify_Right_Adapter(Context context, List<Classify.RsBean.ChildrenBeanX> children,int aa) {
        this.context = context;
        Classify.RsBean.ChildrenBeanX childrenBeanX = children.get(aa);
        children1 = childrenBeanX.getChildren();
        this.children = children;
        this.aa=aa;

    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.rv_classify_item_right, parent, false);
        viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Glide.with( context);// 绑定Context22
        Glide.with(context).load(children1.get(position).getImgApp()).into(viewHolder.im_classify_item_right);
        viewHolder.tv_classify_item_right.setText(children1.get(position).getDirName());
    }
    @Override
    public int getItemCount() {
        return children1.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView im_classify_item_right;
        public  TextView tv_classify_item_right;

        public ViewHolder(View itemView) {
            super(itemView);
            im_classify_item_right = (ImageView) itemView.findViewById(R.id.im_classify_item_right);
            tv_classify_item_right = (TextView) itemView.findViewById(R.id.tv_classify_item_right);
        }
    }
}
