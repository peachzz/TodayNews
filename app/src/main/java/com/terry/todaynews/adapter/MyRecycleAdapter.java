package com.terry.todaynews.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.terry.todaynews.R;

import java.util.List;

/**
 * Created by Taozi on 2016/5/27.
 */
public class MyRecycleAdapter extends RecyclerView.Adapter<MyRecycleAdapter.MyViewHolder> implements View.OnClickListener{

    private OnRecyclerViewItemClickListener mOnItemClickListener = null;
    private List<String> datas;

    public MyRecycleAdapter(List<String> datas) {
        this.datas = datas;
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取数据
            mOnItemClickListener.onItemClick(v,(String)v.getTag());
        }
    }

    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, String data);
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        MyViewHolder vh = new MyViewHolder(view);
        view.setOnClickListener(this);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.itemView.setTag(datas.get(position));
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
