package com.terry.todaynews.fragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.terry.todaynews.MainActivity;
import com.terry.todaynews.NewsContent;
import com.terry.todaynews.R;
import com.terry.todaynews.adapter.MyRecycleAdapter;
import com.terry.todaynews.bean.News;
import com.terry.todaynews.bean.NewsItem;
import com.terry.todaynews.util.CommonException;
import com.terry.todaynews.util.NewsBiz;
import com.terry.todaynews.util.RecycleViewDivider;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Taozi on 2016/6/4.
 */
public class NewsListFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private List<NewsItem> mDatas = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private MyRecycleAdapter myRecycleAdapter;
    private SwipeRefreshLayout mSwipeRefresh;
    private NewsBiz mNewsBiz = NewsBiz.getNewsBizInstance();

    private boolean isFirst = true;
    private boolean isRefresh = false;


    private int mType = NewsFragment.SHIZHENG;

    public static NewsListFragment getNewsListFragmentInstance(int type) {
        Bundle bundle = new Bundle();
        NewsListFragment fragment = new NewsListFragment();
        bundle.putInt("type", type);
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
        View view = inflater.inflate(R.layout.news_item_fragment, container, false);
        initData(view);
        mSwipeRefresh = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh);
        mSwipeRefresh.setColorSchemeResources(R.color.red, R.color.blue, R.color.yellow, R.color.green);

        mSwipeRefresh.setOnRefreshListener(this);
        if (isFirst) {
            mSwipeRefresh.post(new Runnable() {
                @Override
                public void run() {
                    onRefresh();
                }
            });
            isFirst = false;
        }
        return view;
    }

    protected void initData(View view) {
        myRecycleAdapter = new MyRecycleAdapter(getContext(), mDatas);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycle_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(myRecycleAdapter);
        mRecyclerView.addItemDecoration(new RecycleViewDivider(getContext(), LinearLayoutManager.HORIZONTAL));
        myRecycleAdapter.setOnItemClickListener(new MyRecycleAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(),NewsContent.class);
                intent.putExtra("url",mDatas.get(position));
                startActivity(intent);
            }
        });
    }

    @Override
    public void onRefresh() {
        new loadDatasTask().execute();
    }

    class loadDatasTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            try {
                List<NewsItem> newsItems = mNewsBiz.getNewsItem(mType);
                mDatas = newsItems;
            } catch (CommonException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

           boolean isRefresh = myRecycleAdapter.addAll(mDatas);
            myRecycleAdapter.notifyDataSetChanged();
                mSwipeRefresh.setRefreshing(false);
            if (isRefresh) {
                Toast.makeText(getContext(), "刷新成功", Toast.LENGTH_SHORT).show();
            }
        }
    }

}


