package com.example.zigzag.shoplist.view;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.zigzag.shoplist.R;
import com.example.zigzag.shoplist.adapter.AdpRecycler;
import com.example.zigzag.shoplist.data.ShopListDTO;
import com.example.zigzag.shoplist.view.model.ShopModel;
import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.ArrayList;
import java.util.Arrays;


public class ActMain extends AppCompatActivity {

	ArrayList<ItemRecycler> testData = new ArrayList<>();
	ArrayList<ItemRecycler> mShopList = new ArrayList<>();
	ArrayList<String> mFilterS = new ArrayList<>();
	int[] mFilterA = new int[7];
	AdpRecycler mAdapter;
	Toolbar mToolbar;
	RecyclerView mRecyclerView;
	Button mBtnFilter;
	TextView mTvRankTitle;

	ShopPresenter mPresenter;
	ShopModel mShopModel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Fresco.initialize(this);
		setContentView(R.layout.act_main);
		mToolbar = (Toolbar) findViewById(R.id.toolbar);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			mToolbar.setTitle("쇼핑몰 순위");
		}
		mRecyclerView = (RecyclerView) findViewById(R.id.rvList);
		mBtnFilter = (Button) findViewById(R.id.btnFilter);
		mBtnFilter.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				startFilterActivity();
			}
		});
		mTvRankTitle = (TextView) findViewById(R.id.tvRankTitle);

		mPresenter = new ShopPresenter();
		mShopModel = new ShopModel(this);
		UiInit();
	}


	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
		super.onActivityResult(requestCode, resultCode, intent);
		if(requestCode == 1234) {
			if (resultCode == RESULT_OK) {
				Bundle data = intent.getExtras();
				mFilterA = data != null ? data.getIntArray(ActFilter.RESULT_SELECTED_VALUE_AGE) : null;
				mFilterS = data != null ? data.getStringArrayList(ActFilter.RESULT_SELECTED_VALUE_STYLE) : null;
				if (mFilterA != null || mFilterS != null) {
					changeShopList(mFilterA, mFilterS);
				} else {
					makeShopList();
				}
			}
		}
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
		mTvRankTitle.setText(mShopModel.getTitle());
		mShopList.clear();
		int i = 0;
		for (ShopListDTO shop : mData) {
			i++;
			ItemRecycler item = new ItemRecycler(i, shop.getN(), mShopModel.getAgeGroup(shop.getA()), shop.getS(), mShopModel.getImgUrl(shop.getU()));
			mShopList.add(item);
		}
		mAdapter.notifyDataSetChanged();

	}

	private ArrayList<ShopListDTO> setAgeFilter (int[] ageList) {
		ArrayList<ShopListDTO> mData = mShopModel.getShopData();
		ArrayList<ShopListDTO> filterData = new ArrayList<>();

		if(ageList != null) {
			for (ShopListDTO shop : mData) {
				int[] get = shop.getA();
				for (int i = 0; i < ageList.length; i++) {
					if (ageList[i] == 1 && get[i] == 1) {
						filterData.add(shop);
						break;
					}
				}
			}
		} else {
			return mData;
		}
		return filterData;
	}

	private void setStyleFilter(ArrayList<ShopListDTO> filterData, ArrayList<String> typeList) {
		int i = 0;
		for (ShopListDTO shop : filterData) {
			if(typeList.isEmpty()) {
				i++;
				ItemRecycler item = new ItemRecycler(i, shop.getN(), mShopModel.getAgeGroup(shop.getA()), shop.getS(), mShopModel.getImgUrl(shop.getU()));
				mShopList.add(item);
			} else {
				for (String type : typeList) {
					if (shop.getS().contains(type)) {
						i++;
						ItemRecycler item = new ItemRecycler(i, shop.getN(), mShopModel.getAgeGroup(shop.getA()), shop.getS(), mShopModel.getImgUrl(shop.getU()));
						mShopList.add(item);
					}
				}
			}
		}
		mAdapter.notifyDataSetChanged();
	}

	private void changeShopList(int[] ageList, ArrayList<String> typeList) {
		mShopList.clear();

		if (Arrays.equals(ageList, new int[]{0, 0, 0, 0, 0, 0, 0})) {
			ageList = null;
		}
		setStyleFilter(setAgeFilter(ageList), typeList);

	}

	private void startFilterActivity() {
		Intent intent = new Intent(this, ActFilter.class);
		intent.putExtra(ActFilter.KEY_SELECTED_VALUE_STYLE, mFilterS);
		intent.putExtra(ActFilter.KEY_SELECTED_VALUE_AGE, mFilterA);
		startActivityForResult(intent, 1234);
	}

	private void addTestData() {
		testData.clear();
		for (int i = 1; i <= 50; i++) {
			String name = "쇼핑몰 이름" + i;
			String age = "10대, 20대";
			String style = "유니크, 포멀";
			ItemRecycler item = new ItemRecycler(i, name, age, style, null/*이미지 url*/);

			testData.add(item);
		}
		mAdapter.notifyDataSetChanged();
	}
}
