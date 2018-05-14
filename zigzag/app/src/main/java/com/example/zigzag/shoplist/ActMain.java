package com.example.zigzag.shoplist;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ActMain extends AppCompatActivity {

    ArrayList<ItemRecycler> testData = new ArrayList<>();
    ArrayList<ShopListDTO> mData;
    ArrayList<ItemRecycler> mShopList = new ArrayList<>();
    AdpRecycler mAdapter;
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);

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
        makeShopList();
    }

    private ArrayList<ShopListDTO> getShopData(){
        String jsonData = readJsonData("shop_list.json");
        if(jsonData!=null) {
            return mData = (ArrayList<ShopListDTO>) parseJson(jsonData).getList();
        }
        return null;
    }

    private void makeShopList() {
        ArrayList<ShopListDTO> mData = getShopData();

    }

    private ShopListVM parseJson(String json) {
        if (json == null) {
            return null;
        }
        try {
            Gson gson = new Gson();
            ShopListVM shopList = gson.fromJson(json, ShopListVM.class);
            return shopList;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private String readJsonData(String fileName) {
        String json = null;
        AssetManager assetManager = getResources().getAssets();
        try {
            AssetManager.AssetInputStream ais = (AssetManager.AssetInputStream) assetManager.open(fileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(ais));
            StringBuilder sb = new StringBuilder();

            int size = ais.available();
            char[] buffer = new char[size];
            int resultSize = 0;
            while ((resultSize = br.read(buffer)) != -1) {
                if (resultSize == size) {
                    sb.append(buffer);
                } else {
                    for (int i = 0; i < resultSize; i++) {
                        sb.append(buffer[i]);
                    }
                }
            }
            //json = new String(buffer, "UTF-8");
            json = sb.toString();
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
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
