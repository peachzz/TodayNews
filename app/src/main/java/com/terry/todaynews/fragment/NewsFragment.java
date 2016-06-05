package com.terry.todaynews.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.terry.todaynews.R;
import com.terry.todaynews.adapter.MyPagerAdapter;
import com.terry.todaynews.util.PagerSlidingTabStrip;

/**
 * Created by Taozi on 2016/6/5.
 */
public class NewsFragment extends Fragment {

    public static final int SHIZHENG = 1;
    public static final int MINGSHENG = 2;
    public static final int KEJI = 3;
    public static final int PLAY = 4;

    private PagerSlidingTabStrip tabStrip;
    private ViewPager mViewpager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_content, container, false);

        tabStrip = (PagerSlidingTabStrip) view.findViewById(R.id.tabs);
        mViewpager = (ViewPager) view.findViewById(R.id.view_pager);

        //Fragment中嵌套使用Fragment一定要使用getChildFragmentManager(),否则会有问题
        MyPagerAdapter adapter = new MyPagerAdapter(getChildFragmentManager());

        adapter.addFragment(new NewsListFragment().getNewsListFragmentInstance(SHIZHENG), "时政");
        adapter.addFragment(new NewsListFragment().getNewsListFragmentInstance(MINGSHENG), "民生");
        adapter.addFragment(new NewsListFragment().getNewsListFragmentInstance(KEJI), "科技");
        adapter.addFragment(new NewsListFragment().getNewsListFragmentInstance(PLAY), "玩物");

        mViewpager.setAdapter(adapter);
        tabStrip.setIndicatorColor(R.color.colorPrimary);
        //设置每个选项卡相同的权重，默认为false
        tabStrip.setShouldExpand(true);
        tabStrip.setViewPager(mViewpager);

        mViewpager.setCurrentItem(0);
        return view;
    }
}
