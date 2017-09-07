package com.thirty.java.newsapp;

import android.content.Context;
import android.util.Log;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zyj on 2017/9/7.
 */

public class NewsDao
{
    private Dao<NewsBean, String> dao;
    private DBHelper dbHelper;
    public NewsDao(Context context)
    {
        try
        {
            dbHelper = DBHelper.getHelper(context);
            dao = dbHelper.getDao(NewsBean.class);
        } catch (SQLException e)
        { Log.i("back", "NewsDao(): " + e.toString()); }
    }
    public void insert(NewsBean newsBean) // insert if not exist (will print Error if exists)
    {
        try { dao.create(newsBean); } // see in 1.createIfNotExists 2.createOrUpdate
        catch (SQLException e)
        { Log.i("back", "add(): " + e.toString()); }
    }
    public void deleteById(String newsID) // delete if exists
    {
        try { dao.deleteById(newsID); }
        catch (SQLException e)
        { Log.i("back", "delete(): " + e.toString()); }
    }
    public void update(NewsBean news) // update if exists
    {
        try { dao.update(news); }
        catch (SQLException e)
        { Log.i("back", "update(): " + e.toString()); }
    }
    public NewsBean queryById(String newsID) // if not exists, return null
    {
        NewsBean news = null;
        try { news = dao.queryForId(newsID); }
        catch (SQLException e)
        { Log.i("back", "quaryForId(): " + e.toString()); }
        return news;
    }
    public List<NewsBean> queryForAll()
    {
        List<NewsBean> newsList = new ArrayList<NewsBean>();
        try { newsList = dao.queryForAll(); }
        catch (SQLException e)
        { Log.i("back", "queryForAll(): " + e.toString()); }
        return newsList;
    }
}