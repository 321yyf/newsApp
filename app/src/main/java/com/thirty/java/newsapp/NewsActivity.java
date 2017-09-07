package com.thirty.java.newsapp;

import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;

import com.iflytek.cloud.*;

import org.w3c.dom.Text;


/**
 * Created by fansy on 2017/9/5.
 */

public class NewsActivity extends AppCompatActivity {
    private TextView mNewsTitle, mNewsAuthor, mNewsTime, mNewsContent;
    private Button mBackButton, mReadButton;

    private SynthesizerListener mSynListener = new SynthesizerListener() {
        //会话结束回调接口，没有错误时，error为null
        public void onCompleted(SpeechError error) {
            Log.i("xunfei", "" + error);
        }
        //缓冲进度回调
        //percent为缓冲进度0~100，beginPos为缓冲音频在文本中开始位置，endPos表示缓冲音频在文本中结束位置，info为附加信息。
        public void onBufferProgress(int percent, int beginPos, int endPos, String info) {
            Log.i("xunfei", "onBufferProgress");
        }
        //开始播放
        public void onSpeakBegin() {
            Log.i("xunfei", "onSpeakBegin");
        }
        //暂停播放
        public void onSpeakPaused() {
            Log.i("xunfei", "onSpeakPaused");
        }
        //播放进度回调
        //percent为播放进度0~100,beginPos为播放音频在文本中开始位置，endPos表示播放音频在文本中结束位置.
        public void onSpeakProgress(int percent, int beginPos, int endPos) {
            Log.i("xunfei", "onSpeakProgress");
        }
        //恢复播放回调接口
        public void onSpeakResumed() {
            Log.i("xunfei", "onSpeakResumed");
        }
        //会话事件回调接口
        public void onEvent(int arg0, int arg1, int arg2, Bundle arg3) {
            Log.i("xunfei", "onEvent");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_news_view);

        DetailedNews detailedNews = (DetailedNews) getIntent().getParcelableExtra("News");

        mNewsTitle = (TextView) findViewById(R.id.news_name);
        mNewsTitle.setText(detailedNews.newsTitle);


        mNewsAuthor = (TextView) findViewById(R.id.news_author);
        mNewsAuthor.setText(detailedNews.newsAuthor);

        mNewsTime = (TextView) findViewById(R.id.news_time);
        mNewsTime.setText(detailedNews.newsTime);

        mNewsContent = (TextView) findViewById(R.id.news_text);
        mNewsContent.setText(detailedNews.newsContent);

        //退出新闻界面
        mBackButton = (Button) findViewById(R.id.back_button);
        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //朗读新闻
        mReadButton = (Button) findViewById(R.id.read_button);
        mReadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("xunfei", "start");
                //讯飞初始化
                SpeechUtility.createUtility(NewsActivity.this, SpeechConstant.APPID + "=59b0ae8e");

                //语音合成
                SpeechSynthesizer mTts = SpeechSynthesizer.createSynthesizer(NewsActivity.this, null);
                mTts.setParameter(SpeechConstant.VOICE_NAME, "xiaoyan");//设置发音人
                mTts.setParameter(SpeechConstant.SPEED, "50");//设置语速
                mTts.setParameter(SpeechConstant.VOLUME, "80");//设置音量，范围0~100
                mTts.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_CLOUD); //设置云端

                mTts.startSpeaking(((DetailedNews) getIntent().getParcelableExtra("News")).newsContent, mSynListener);
                Log.i("xunfei", "end");
            }
        });
    }
}
