package com.java.thirty.newsapp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

/**
 * Created by zyj on 2017/9/6.
 */

public class GetDetailedNewsRunnable implements Runnable
{
    GetDetailedNewsRunnable(Handler handler, String newsID)
    {
        this.handler = handler;
        this.newsID = newsID;
    }
    @Override
    public void run()
    {
        Bundle bundle = new Bundle();
        try
        { bundle = NewsApiCaller.getDetailedNews(newsID); }
        catch (Exception e)
        { e.printStackTrace(); }
        Message message = Message.obtain();
        message.setData(bundle);
        handler.sendMessage(message);
    }
    Handler handler;
    String newsID;
}
