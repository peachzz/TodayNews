package com.terry.todaynews.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.terry.todaynews.R;
import com.terry.todaynews.adapter.MyPagerAdapter;
import com.terry.todaynews.adapter.MyRecycleAdapter;
import com.terry.todaynews.util.RecycleViewDivider;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Taozi on 2016/6/4.
 */
public class NewsListFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private List<String> mDatas;
    private RecyclerView mRecyclerView;
    private MyRecycleAdapter myRecycleAdapter;
    private SwipeRefreshLayout mSwipeRefresh;
    private Toolbar mToolbar;

    private int mType = NewsFragment.SHIZHENG;

    public static NewsListFragment getNewsListFragmentInstance(int type){
        Bundle bundle = new Bundle();
        NewsListFragment fragment = new NewsListFragment();
        bundle.putInt("type",type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mType = getArguments().getInt("type");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_item_fragment,container,false);
        initData(view);
        mSwipeRefresh = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh);
        mSwipeRefresh.setColorSchemeResources(R.color.red, R.color.blue, R.color.yellow, R.color.green);
        mSwipeRefresh.setOnRefreshListener(this);
        return view;
    }

    protected void initData(View view) {
        mDatas = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            mDatas.add("" + i);
        }
        myRecycleAdapter = new MyRecycleAdapter(mDatas);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycle_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(myRecycleAdapter);
        mRecyclerView.addItemDecoration(new RecycleViewDivider(getContext(), LinearLayoutManager.HORIZONTAL));
        myRecycleAdapter.setOnItemClickListener(new MyRecycleAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, String data) {
                Toast.makeText(getContext(), data, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getContext(), "刷新成功", Toast.LENGTH_SHORT).show();
                mSwipeRefresh.setRefreshing(false);
            }
        }, 5000);
    }
}

