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


public class ActMain extends AppCompatActivity implements Contract.ShopView {

	ArrayList<ItemRecycler> testData = new ArrayList<>();
	ArrayList<ItemRecycler> mShopList = new ArrayList<>();
	ArrayList<String> mFilter = new ArrayList<>();
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
				mFilter = data != null ? data.getStringArrayList(ActFilter.RESULT_SELECTED_VALUE) : null;
				if (mFilter != null) {
					if (mFilter.isEmpty()) {
						//모든 쇼핑몰 리스트
						makeShopList();
					} else {
						changeShopList(mFilter);
					}
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

	private void changeShopList(ArrayList<String> list) {
		ArrayList<ShopListDTO> mData = mShopModel.getShopData();
		mShopList.clear();
		int i = 0;
		for (ShopListDTO shop : mData) {
			for (String type : list) {
				if (shop.getS().contains(type)) {
					i++;
					ItemRecycler item = new ItemRecycler(i, shop.getN(), mShopModel.getAgeGroup(shop.getA()), shop.getS(), mShopModel.getImgUrl(shop.getU()));
					mShopList.add(item);
				}
			}
		}
		mAdapter.notifyDataSetChanged();

	}

	private void startFilterActivity() {
		Intent intent = new Intent(this, ActFilter.class);
		intent.putExtra(ActFilter.KEY_SELECTED_VALUE, mFilter);
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
