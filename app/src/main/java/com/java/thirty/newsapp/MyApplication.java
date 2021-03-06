package com.java.thirty.newsapp;

import android.app.Application;
import android.content.Context;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by fansy on 2017/9/8.
 */

public class MyApplication extends Application {
    static final public String[] interestDateSet = new String[]{
            "推荐", "科技", "教育", "军事", "国内",
            "社会", "文化", "汽车", "国际", "体育",
            "财经", "健康", "娱乐"
    };

    public static final Map<String, Integer> map = new HashMap<String, Integer>(){{
        put("推荐", 0); put("科技", 1); put("教育", 2); put("军事", 3);
        put("国内", 4); put("社会", 5); put("文化", 6);
        put("汽车", 7); put("国际", 8); put("体育", 9);
        put("财经", 10); put("健康", 11); put("娱乐", 12);
    }};

    public static ArrayList<String> filterWords = new ArrayList<String>();

    static public boolean selected[] = new boolean[interestDateSet.length];
    static public double volumnOfCategory[] = new double[interestDateSet.length];
    static int focusPage = 0;
    static double readDelta = 1.0;
    static boolean night_mode;
    public GetLatestNewsStream myNewsStream;

    // zyj database initialize
    static private Context applicationContext;
    static public Context getContext()
    { return applicationContext; }
    // zyj database initialize


    public MyApplication(){
        super();
        for (int i = 1; i < interestDateSet.length; ++i)
            volumnOfCategory[i] = 1.0;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        // zyj database initialize
        applicationContext = getApplicationContext();
        // zyj database initialize

        Arrays.fill(selected, true);
        selected = PreferenceStorage.loadPreference();
    }
}
