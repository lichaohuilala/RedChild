package com.bawei.redchild.groupon;

import android.content.Context;
import android.graphics.Rect;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.redchild.R;
import com.bumptech.glide.Glide;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * date:2017/5/23.
 * author:刘宏亮.
 * function:
 */

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context context;
    private GsonBean mGsonBean;
    private MyPagerAdapter mPagerAdapter;
    Map<Integer,Integer> map=new HashMap();

    public MyRecyclerViewAdapter(Context context, GsonBean mGsonBean) {
        this.context = context;
        this.mGsonBean=mGsonBean;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
View view=null;
        RecyclerView.ViewHolder viewHolder=null;
        switch (viewType){
            case 0:
                view= LayoutInflater.from(context).inflate(R.layout.list_item1,null);
                viewHolder=new ViewHolder1(view);
                break;
            case 1:
                view= LayoutInflater.from(context).inflate(R.layout.list_item2,null);
                viewHolder=new ViewHolder2(view);
                break;
            case 2:
                view= LayoutInflater.from(context).inflate(R.layout.list_item3,null);
                viewHolder=new ViewHolder3(view);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int itemViewType = getItemViewType(position);
        switch (itemViewType){
            case 0:
                final ViewHolder1 vh = (ViewHolder1) holder;
                mPagerAdapter=new MyPagerAdapter(context,mGsonBean.getAds());
                vh.mViewPager.setAdapter(mPagerAdapter);
                Iterator iter = map.entrySet().iterator();
                while (iter.hasNext()) {
                    Map.Entry entry = (Map.Entry) iter.next();
                    int key = (int) entry.getKey();
                    int val = (int) entry.getValue();
                    if (position==key){
                        vh.mViewPager.setCurrentItem(val);
                    }
                }
                vh.mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                    }

                    @Override
                    public void onPageSelected(int position) {
                        int recyclerPosition = vh.getLayoutPosition();
                        map.put(recyclerPosition,position);
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                });
                break;
            case 1:
                final ViewHolder2 vh2 = (ViewHolder2) holder;
                GridAdapter adapter = new GridAdapter(mGsonBean.getEnrolls_1().getList());
                GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 1);
                gridLayoutManager.setOrientation(GridLayoutManager.HORIZONTAL);
                vh2.mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
                    @Override
                    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                        outRect.set(10, 10, 10, 10);
                    }
                });
                vh2.mRecyclerView.setLayoutManager(gridLayoutManager);
                vh2.mRecyclerView.setAdapter(adapter);
                break;
            case 2:
                final ViewHolder3 vh3 = (ViewHolder3) holder;
                List<GsonBean.EnrollsBean.ListBean> list = mGsonBean.getEnrolls().getList();
                GsonBean.EnrollsBean.ListBean bean = list.get(position);
                vh3.setData(bean);
                break;
        }


    }

    @Override
    public int getItemCount() {
        return mGsonBean.getEnrolls().getList().size();
    }
        @Override
    public int getItemViewType(int position) {
        if (position==0){
            return 0;
        }
        if (position==1){
            return 1;
        }
        return 2;
    }
    class ViewHolder1 extends RecyclerView.ViewHolder{


        private final ViewPager mViewPager;

        public ViewHolder1(View itemView) {
            super(itemView);
            mViewPager = (ViewPager) itemView.findViewById(R.id.viewpager);

        }
    }
    class ViewHolder2 extends RecyclerView.ViewHolder{


        private final RecyclerView mRecyclerView;

        public ViewHolder2(View itemView) {
            super(itemView);
            mRecyclerView = (RecyclerView) itemView.findViewById(R.id.item2_recycler);

        }
    }
    class ViewHolder3 extends RecyclerView.ViewHolder{

        private final ImageView mImageView;
        private final TextView mTextView,mTextView2,mTextView3;

        public ViewHolder3(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.item3_image);
            mTextView = (TextView) itemView.findViewById(R.id.item3_textView);
            mTextView2= (TextView) itemView.findViewById(R.id.item3_textView2);
            mTextView3= (TextView) itemView.findViewById(R.id.item3_textView3);
        }

        public void setData(GsonBean.EnrollsBean.ListBean bean) {
            Glide.with(context).load(bean.getImgUrl()).into(mImageView);
            mTextView.setText(bean.getItemName());
            mTextView2.setText(bean.getItemDesc());
            mTextView3.setText(bean.getPrice()+"");
        }
    }


    class MyPagerAdapter extends PagerAdapter {
        private Context context;
        private List<GsonBean.AdsBean> list;


        public MyPagerAdapter(Context context, List<GsonBean.AdsBean> list) {
            this.context = context;
            this.list = list;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            View view = View.inflate(context, R.layout.item_item, null);
            ImageView imageView= (ImageView) view.findViewById(R.id.item_item_image);
            Glide.with(context).load(list.get(position).getImgUrl()).into(imageView);
            container.addView(view);
            return view;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
    class GridAdapter extends RecyclerView.Adapter<GridAdapter.GridViewHolder>{

        private List<GsonBean.Enrolls1Bean.ListBeanX> mList;

        public GridAdapter(List<GsonBean.Enrolls1Bean.ListBeanX> list) {
            mList = list;
        }

        @Override
        public GridAdapter.GridViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = View.inflate(context, R.layout.item_item2, null);
            return new GridViewHolder(view);
        }

        @Override
        public void onBindViewHolder(GridAdapter.GridViewHolder holder, int position) {
            mList = mGsonBean.getEnrolls_1().getList();
            GsonBean.Enrolls1Bean.ListBeanX listBeanX = mList.get(position);
            holder.setData(listBeanX);

        }

        @Override
        public int getItemCount() {
            return mList.size();
        }

        public class GridViewHolder extends RecyclerView.ViewHolder {

            private final ImageView mIv_logo;
            private final TextView mTv_name,mTv_desc,mTv_price;

            public GridViewHolder(View itemView) {
                super(itemView);
                mIv_logo = (ImageView) itemView.findViewById(R.id.iv_logo);
                mTv_name = (TextView) itemView.findViewById(R.id.tv_group_name);
                mTv_desc= (TextView) itemView.findViewById(R.id.tv_group_desc);
                mTv_price= (TextView) itemView.findViewById(R.id.tv_group_price);
            }

            public void setData(GsonBean.Enrolls1Bean.ListBeanX x) {
                Glide.with(context).load(x.getImgUrl()).into(mIv_logo);
                mTv_name.setText(x.getItemName());
                mTv_desc.setText(x.getItemDesc());
                mTv_price.setText(x.getPrice()+"");
            }
        }
    }
}
