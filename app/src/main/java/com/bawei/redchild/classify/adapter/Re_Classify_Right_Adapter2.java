package com.bawei.redchild.classify.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bawei.redchild.R;
import com.bawei.redchild.classify.dao.Classify;

import java.util.List;

/**
 * Created by lichaohui on 2017/5/23.
 */
public class Re_Classify_Right_Adapter2 extends RecyclerView.Adapter<Re_Classify_Right_Adapter2.ViewHolder> {
    private Context context;
    private  List<Classify.RsBean.ChildrenBeanX> children;
    private final DisplayMetrics dm;

    public Re_Classify_Right_Adapter2(Context context, List<Classify.RsBean.ChildrenBeanX> children) {
        this.context = context;
        this.children = children;
        dm = new DisplayMetrics();
//        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.rv_classify_item_right2, parent, false);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams((dm.widthPixels - dip2px(20)) / 3, (dm.widthPixels - dip2px(20)) / 3);
        inflate.setLayoutParams(lp);
        ViewHolder viewHolder = new ViewHolder(inflate);
        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText(children.get(position).getDirName());
        Re_Classify_Right_Adapter adapter = new Re_Classify_Right_Adapter(context, children,position);
        holder.recycle.setAdapter(adapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 3);
        holder.recycle.setLayoutManager( gridLayoutManager);
    }

    @Override
    public int getItemCount() {
        return children.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final RecyclerView recycle;
        private final TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv_classif_item);
            recycle = (RecyclerView) itemView.findViewById(R.id.rv_classify_right_recycleview);
        }
    }
    int dip2px(float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
