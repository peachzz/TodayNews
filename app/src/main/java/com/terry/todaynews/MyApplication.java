package com.terry.todaynews;

import android.app.Application;
import android.content.Context;

/**
 * Created by Taozi on 2016/6/6.
 */
public class MyApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
       context = getApplicationContext();
    }
    public static Context getContext(){
        return context;
    }
}
