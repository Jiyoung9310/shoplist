package com.example.zigzag.shoplist.view;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.zigzag.shoplist.R;
import com.example.zigzag.shoplist.data.StyleTypeDB;

import java.util.ArrayList;

/**
 * Created by user on 2018-05-15.
 */

public class ActFilter extends AppCompatActivity {
	public static final String KEY_SELECTED_VALUE_STYLE = "KEY_SELECTED_VALUE_STYLE";
	public static final String KEY_SELECTED_VALUE_AGE = "KEY_SELECTED_VALUE_AGE";
	public static final String RESULT_SELECTED_VALUE_STYLE = "RESULT_SELECTED_VALUE_STYLE";
	public static final String RESULT_SELECTED_VALUE_AGE = "RESULT_SELECTED_VALUE_AGE";

	private RelativeLayout mRlAge;
	private LinearLayout mLlStyle;

	private int[] setAgeFilter = new int[7];
	private ArrayList<String> setStyleFilter = new ArrayList<>();

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putIntArray(KEY_SELECTED_VALUE_AGE, setAgeFilter);
		outState.putStringArrayList(KEY_SELECTED_VALUE_STYLE, setStyleFilter);
	}

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_filter);
		Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			mToolbar.setTitle("쇼핑몰 필터");
			mToolbar.setNavigationIcon(R.mipmap.ic_close_white);
			mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent data = new Intent();
					data.putExtra(RESULT_SELECTED_VALUE_STYLE, setStyleFilter);
					data.putExtra(RESULT_SELECTED_VALUE_AGE, setAgeFilter);
					setResult(RESULT_OK, data);
					finish();
				}
			});
		}
		mLlStyle = (LinearLayout) findViewById(R.id.llStyle);
		mRlAge = (RelativeLayout) findViewById(R.id.rlAge);


		initView();

		ArrayList<String> selectedValueS = getIntent().getStringArrayListExtra(KEY_SELECTED_VALUE_STYLE);
		int[] selectedValueA = getIntent().getIntArrayExtra(KEY_SELECTED_VALUE_AGE);
		if( savedInstanceState != null ) {
			selectedValueS = savedInstanceState.getStringArrayList(KEY_SELECTED_VALUE_STYLE);
			selectedValueA = savedInstanceState.getIntArray(KEY_SELECTED_VALUE_AGE);
		}

		if(selectedValueS != null && !selectedValueS.isEmpty()) {
			setKeySelectedValueS(selectedValueS);
		}
		if(selectedValueA != null ) {
			setKeySelectedValueA(selectedValueA);
		}
	}

	private void setKeySelectedValueS(ArrayList<String> selectedValue) {
		for(String value : selectedValue) {
			CheckBox btn = mLlStyle.findViewWithTag(value);
			btn.setChecked(true);
		}
	}

	private void setKeySelectedValueA(int[] selectedValue) {
		for(int i=0; i<selectedValue.length; i++) {
			if(selectedValue[i] == 1) {
				CheckBox btn = mRlAge.findViewWithTag(i+"");
				btn.setChecked(true);
			}
		}
	}

	private void initView() {
		ArrayList<String> styleTypeList = StyleTypeDB.instance(this).getStyleTypeData();

		for(String type : styleTypeList) {
			final CheckBox btn = new CheckBox(this);
			btn.setTag(type);
			btn.setText(type);
			btn.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					if(btn.isChecked()) {
						setStyleFilter.add(String.valueOf(btn.getText()));
					} else {
						setStyleFilter.remove(String.valueOf(btn.getText()));
					}
				}
			});
			mLlStyle.addView(btn);
		}

		for (int i = 0 ; i<7 ; i++) {
			final CheckBox btn = mRlAge.findViewWithTag(i+"");
			btn.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					if(btn.isChecked()) {
						setAgeFilter[Integer.parseInt(String.valueOf(btn.getTag()))] = 1;
					} else {
						setAgeFilter[Integer.parseInt(String.valueOf(btn.getTag()))] = 0;
					}
				}
			});
		}

	}

}
