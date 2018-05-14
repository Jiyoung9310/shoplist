package com.example.zigzag.shoplist.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.zigzag.shoplist.R;
import com.example.zigzag.shoplist.adapter.AdpRecycler;
import com.example.zigzag.shoplist.data.ShopListDTO;
import com.example.zigzag.shoplist.view.model.ShopModel;
import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.ArrayList;

public class ActMain extends AppCompatActivity implements Contract.ShopView {

    ArrayList<ItemRecycler> testData = new ArrayList<>();
    ArrayList<ItemRecycler> mShopList = new ArrayList<>();
    AdpRecycler mAdapter;
    RecyclerView mRecyclerView;

    ShopPresenter mPresenter;
    ShopModel mShopModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.act_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.rvList);
        mPresenter = new ShopPresenter();
        mShopModel = new ShopModel(this);
        UiInit();
    }

    private void UiInit() {
        mAdapter = new AdpRecycler(mShopList);
        mPresenter.setAdapterView(mAdapter);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mRecyclerView.setAdapter(mAdapter);

        //addTestData();
        makeShopList();
    }

    private void makeShopList() {
        ArrayList<ShopListDTO> mData = mShopModel.getShopData();
        mShopList.clear();
        int i = 0;
        for(ShopListDTO shop : mData) {
            i++;
            ItemRecycler item = new ItemRecycler(i, shop.getN(), shop.getA().toString(), shop.getS(), shop.getU());
            mShopList.add(item);
        }
        mAdapter.notifyDataSetChanged();
    }



    private void addTestData() {
        testData.clear();
        for(int i=1; i<=50; i++) {
            String name = "쇼핑몰 이름" + i;
            String age = "10대, 20대";
            String style = "유니크, 포멀";
            ItemRecycler item = new ItemRecycler(i, name,  age, style, null/*이미지 url*/);

            testData.add(item);
        }
        mAdapter.notifyDataSetChanged();
    }
}
