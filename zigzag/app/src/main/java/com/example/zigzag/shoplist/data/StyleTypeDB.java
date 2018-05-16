package com.example.zigzag.shoplist.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2018-05-15.
 */

public class StyleTypeDB {
	private static final String PREF_NAME               = "STYLEDATA";
	private static final String STYLE_LIST				= "STYLE_LIST";

	private Gson gson;
	private SharedPreferences mShared;
	private SharedPreferences.Editor mEditor;
	private Context mContext;
	public static volatile StyleTypeDB mInstance = null;

	public StyleTypeDB(Context context){
		gson = new Gson();
		mShared = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
		mEditor = mShared.edit();
		mContext = context;
	}

	public static StyleTypeDB instance(Context context) {
		if( mInstance == null ) {
			mInstance = new StyleTypeDB(context);
		}
		return mInstance;
	}

	public boolean saveStyleTypeData(List<String> style){
		mEditor.putString(STYLE_LIST, gson.toJson(style));
		return mEditor.commit();
	}

	public ArrayList<String> getStyleTypeData(){
		String styleJson = mShared.getString(STYLE_LIST, null);

		if(styleJson ==null)
			return null;

		Type LnbJsonType = new TypeToken<ArrayList<String>>(){}.getType();
		ArrayList<String> styleList = gson.fromJson(styleJson, LnbJsonType);
		return styleList;
	}

	public boolean clearStyleTypeDB() {
		mEditor.remove(STYLE_LIST);
		return mEditor.commit();
	}
}
