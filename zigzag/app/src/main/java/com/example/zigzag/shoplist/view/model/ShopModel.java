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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

	public String getAgeGroup(int[] ageArray) {
		String group = "";

		boolean teen = false;
		boolean twenty = false;
		boolean thirty = false;

		if(ageArray[0] == 1) {
			teen = true;
			group += "10대";
		}

		if(ageArray[1] + ageArray[2] + ageArray[3] > 0) {
			twenty = true;
			group += " 20대";
		}

		if(ageArray[4] + ageArray[5] + ageArray[6] > 0) {
			thirty = true;
			group += " 30대";
		}

		if(teen && twenty && thirty) {
			group = "모두";
		}

		return group;
	}

	public String getImgUrl(String url) {
		String regex = "^http://(w{3})*\\.*(.*?)[\\..*?]";
		Pattern groupPattern = Pattern.compile(regex);
		Matcher groupMatcher = groupPattern.matcher(url);
		String imgURL = "https://cf.shop.s.zigzag.kr/images/";
		if (groupMatcher.find()) {
			imgURL += groupMatcher.group(2);
			imgURL += ".jpg";
		}

		return imgURL;
	}
}
