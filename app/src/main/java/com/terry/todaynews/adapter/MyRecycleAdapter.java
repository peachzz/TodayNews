package com.terry.todaynews.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.squareup.picasso.Picasso;
import com.terry.todaynews.R;
import com.terry.todaynews.bean.News;
import com.terry.todaynews.bean.NewsItem;
import com.terry.todaynews.util.CircleTransform;

import java.util.List;

/**
 * Created by Taozi on 2016/5/27.
 */
public class MyRecycleAdapter extends RecyclerView.Adapter<MyRecycleAdapter.MyViewHolder> implements View.OnClickListener {

    private OnRecyclerViewItemClickListener mOnItemClickListener = null;
    private List<NewsItem> datas;
    private Context context;

    public MyRecycleAdapter(Context context, List<NewsItem> datas) {
        this.datas = datas;
        this.context = context;
    }

    public boolean addAll(List<NewsItem> newses) {

        this.datas.clear();
        this.datas.addAll(0, newses);
        //数据添加成功
        if (this.datas.size() > newses.size()) {
            return true;
        }
        return false;
    }


    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取数据
            mOnItemClickListener.onItemClick(v, (Integer) v.getTag());
        }
    }

    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        MyViewHolder vh = new MyViewHolder(view);
        view.setOnClickListener(this);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        NewsItem newsItem = datas.get(position);
        if (newsItem == null) {
            return;
        }
        holder.itemView.setTag(datas.get(position));
        holder.tvTitle.setText(newsItem.getTitle());
        holder.tvTime.setText(newsItem.getDate());
        holder.tvSummary.setText(newsItem.getSummary());
        holder.tvUpdata.setText(newsItem.getUpDate());
        holder.itemView.setTag(position);
        Picasso.with(context).load(newsItem.getImageLink()).into(holder.ivTitle);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView ivTitle;
        TextView tvTitle;
        TextView tvSummary;
        TextView tvTime;
        TextView tvUpdata;

        public MyViewHolder(View itemView) {
            super(itemView);
            ivTitle = (ImageView) itemView.findViewById(R.id.iv_title);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            tvSummary = (TextView) itemView.findViewById(R.id.tv_summary);
            tvTime = (TextView) itemView.findViewById(R.id.tv_time);
            tvUpdata = (TextView) itemView.findViewById(R.id.tv_updata);
        }
    }
}
