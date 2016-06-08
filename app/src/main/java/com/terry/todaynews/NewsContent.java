package com.terry.todaynews;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.terry.todaynews.bean.NewsItem;
import com.terry.todaynews.fragment.NewsListFragment;

/**
 * Created by Taozi on 2016/6/7.
 */
public class NewsContent extends AppCompatActivity {

    private WebView mNewsContent;
    private NewsItem newsItem;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_content);
        mNewsContent = (WebView) findViewById(R.id.news_content);
        Bundle bundle = getIntent().getExtras();
        newsItem = (NewsItem) bundle.getSerializable("url");
//        String url = getIntent().getStringExtra("url");
        Log.d("TGA",newsItem.getLink());
        mNewsContent.getSettings().setJavaScriptEnabled(true);
        mNewsContent.setWebViewClient(new WebViewClient());

        mNewsContent.loadUrl(newsItem.getLink());
    }
}
