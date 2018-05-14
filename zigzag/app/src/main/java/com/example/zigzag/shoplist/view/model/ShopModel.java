package com.example.zigzag.shoplist.view.model;

import android.content.Context;
import android.content.res.AssetManager;

import com.example.zigzag.shoplist.data.ShopListDTO;
import com.example.zigzag.shoplist.data.ShopListVM;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by user on 2018-05-14.
 */

public class ShopModel {
	Context mContext;
	ArrayList<ShopListDTO> mData;

	public ShopModel(Context context) {
		mContext = context;
	}

	public void setDataList(ArrayList<ShopListDTO> mData) {
		this.mData = mData;
	}


	public ArrayList<ShopListDTO> getShopData() {
		String jsonData = readJsonData("shop_list.json");
		if (jsonData != null) {
			mData = (ArrayList<ShopListDTO>) parseJson(jsonData).getList();

			Collections.sort(mData, new Comparator<ShopListDTO>() {
				@Override
				public int compare(ShopListDTO shopListDTO, ShopListDTO t1) {
					return t1.getScore().compareTo(shopListDTO.getScore());
				}
			});
			return mData;
		}
		return null;
	}

	private String readJsonData(String fileName) {
		String json = null;
		AssetManager assetManager = mContext.getResources().getAssets();
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
}
