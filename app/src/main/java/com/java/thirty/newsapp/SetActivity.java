package com.java.thirty.newsapp;

import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;


/**
 * Created by fansy on 2017/9/5.
 */

public class SetActivity extends AppCompatActivity {
    private Button mIndexButton, mCollectButton, mInterestButton, mFilterButton, mCleanButton;
    private Switch mNightSwitch, mNoImageSwitch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_set_view);

        mIndexButton = (Button) findViewById(R.id.index_button);
        mCollectButton = (Button) findViewById(R.id.collect_button);
        mInterestButton = (Button) findViewById(R.id.my_interest);
        mFilterButton = (Button) findViewById(R.id.my_filter);
        mCleanButton = (Button) findViewById(R.id.clean_cache);
        mNightSwitch = (Switch) findViewById(R.id.night_mode);
        mNoImageSwitch = (Switch) findViewById(R.id.no_image_mode);

        //首页切换
        mIndexButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //我的收藏切换
        mCollectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SetActivity.this, CollectActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //我的关注
        mInterestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SetActivity.this, InterestSetActivity.class);
                startActivity(intent);
            }
        });

        //屏蔽词
        mFilterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SetActivity.this, FilterActivity.class);
                startActivity(intent);
            }
        });

        //清除缓存
        mCleanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseApi.clearAll();
                Vibrator vibrator =  (Vibrator)getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                long[] pattern = {0, 30};
                vibrator.vibrate(pattern, -1);
                Toast toast = Toast.makeText(getApplicationContext(),
                        getResources().getString(R.string.cache_cleaned), Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        });


        //夜间模式
        mNightSwitch.setChecked(getSharedPreferences("settings", Context.MODE_PRIVATE).getBoolean("night_mode", false));
        mNightSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    //night
                    getSharedPreferences("settings", Context.MODE_PRIVATE).edit().putBoolean("night_mode", true).apply();
                }
                else{
                    //day
                    getSharedPreferences("settings", Context.MODE_PRIVATE).edit().putBoolean("night_mode", false).apply();
                }
            }
        });

        //无图模式
        mNoImageSwitch.setChecked(getSharedPreferences("settings", Context.MODE_PRIVATE).getBoolean("no_image_mode", false));
        mNoImageSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    //no image
                    getSharedPreferences("settings", Context.MODE_PRIVATE).edit().putBoolean("no_image_mode", true).apply();
                }
                else{
                    //image
                    getSharedPreferences("settings", Context.MODE_PRIVATE).edit().putBoolean("no_image_mode", false).apply();
                }
            }
        });
    }
}