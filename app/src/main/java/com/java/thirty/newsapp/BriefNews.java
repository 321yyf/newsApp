package com.java.thirty.newsapp;

import android.os.Parcelable;
import android.os.Parcel;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zyj on 2017/9/5.
 */

public class BriefNews implements Parcelable{
    private static final Pattern pattern = Pattern.compile("http://.*?\\.jpe?g");
    public String newsClassTag;
    public String newsAuthor;
    public String newsID;
    public String newsPicture;
    public String newsSource;
    public String newsTime;
    public String newsTitle;
    public String newsURL;
    public String newsIntro;

    public BriefNews(){

    }

    public BriefNews(String newsClassTag, String newsAuthor, String newsID, String unsplitNewsPictures, String newsSource,
                     String newsTime, String newsTitle, String newsURL, String newsIntro)
    {
        this.newsPicture = "";
        Matcher matcher = pattern.matcher(unsplitNewsPictures);
        if (matcher.find())
            this.newsPicture = matcher.group();
        this.newsClassTag = newsClassTag;
        this.newsAuthor = newsAuthor;
        this.newsID = newsID;
        this.newsSource = newsSource;
        this.newsTime = newsTime;
        this.newsTitle = newsTitle;
        this.newsURL = newsURL;
        this.newsIntro = newsIntro;
    }
    @Override
    public int describeContents()
    {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel out, int flags)
    {
        out.writeString(newsClassTag);
        out.writeString(newsAuthor);
        out.writeString(newsID);
        out.writeString(newsPicture);
        out.writeString(newsSource);
        out.writeString(newsTime);
        out.writeString(newsTitle);
        out.writeString(newsURL);
        out.writeString(newsIntro);
    }
    public static final Parcelable.Creator<BriefNews> CREATOR = new Creator<BriefNews>()
    {
        @Override
        public BriefNews[] newArray(int size)
        {
            return new BriefNews[size];
        }

        @Override
        public BriefNews createFromParcel(Parcel in)
        {
            return new BriefNews(in);
        }
    };

    public BriefNews(Parcel in)
    {
        newsClassTag = in.readString();
        newsAuthor = in.readString();
        newsID = in.readString();
        newsPicture = in.readString();
        newsSource = in.readString();
        newsTime = in.readString();
        newsTitle = in.readString();
        newsURL = in.readString();
        newsIntro = in.readString();
    }

    public boolean containsKeyword(List<String> keywordList)
    {
        for (int i = 0; i < keywordList.size(); i++)
        {
            if (newsTitle.indexOf(keywordList.get(i)) != -1)
                return true;
            if (newsIntro.indexOf(keywordList.get(i)) != -1)
                return true;
        }
        return false;
    }

    public boolean containsPicture()
    {
        if (newsPicture == "" || newsPicture == null)
            return false;
        return true;
    }

    public static List<BriefNews> filter(List<BriefNews> briefNewsList, List<String> keywordList)
    {
        List<BriefNews> resultList = new ArrayList<BriefNews>();
        for (int i = 0; i < briefNewsList.size(); i++)
        if (!briefNewsList.get(i).containsKeyword(keywordList))
            resultList.add(briefNewsList.get(i));
        return resultList;
    }
}
