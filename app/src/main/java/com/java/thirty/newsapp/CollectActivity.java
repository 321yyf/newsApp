package com.java.thirty.newsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.view.View;


/**
 * Created by fansy on 2017/9/5.
 */

public class CollectActivity extends AppCompatActivity {
    private Button mIndexButton, mSetButton;
    private RecyclerView mRecyclerView;
    private MyAdapter mAdapter;

    @Override
    public void onResume(){
        super.onResume();
        mAdapter.mDataset = getCollectNewsData();
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_collect_view);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        BriefNews[] newsData = getCollectNewsData();
        mAdapter = new MyAdapter(newsData);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view , int position){
                Intent intent = new Intent(CollectActivity.this, NewsActivity.class);
                intent.putExtra("NewsID", mAdapter.mDataset[position].newsID);
                startActivity(intent);
            }
        });

        //首页切换
        mIndexButton = (Button) findViewById(R.id.index_button);
        mIndexButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //设置界面切换
        mSetButton = (Button) findViewById(R.id.set_button);
        mSetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CollectActivity.this, SetActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    BriefNews[] getCollectNewsData(){
        return DatabaseApi.getAllInCollection().toArray(new BriefNews[0]);
    }
}
