package com.example.zigzag.shoplist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class ActMain extends AppCompatActivity {

    ArrayList<ItemRecycler> testData = new ArrayList<>();
    AdpRecycler mAdapter;
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.rvList);

        UiInit();
    }

    private void UiInit() {
        mAdapter = new AdpRecycler(testData);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        addTestData();
    }

    private void addTestData() {
        testData.clear();
        for(int i=1; i<=50; i++) {
            String name = "쇼핑몰 이름" + i;
            String age = "10대, 20대";
            String style = "유니크, 포멀";
            ItemRecycler item = new ItemRecycler(i, name,  age, style);

            testData.add(item);
        }
        mAdapter.notifyDataSetChanged();
    }
}
