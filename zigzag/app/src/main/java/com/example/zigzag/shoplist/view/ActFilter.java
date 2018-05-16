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
import android.widget.TextView;

import com.example.zigzag.shoplist.R;
import com.example.zigzag.shoplist.data.StyleTypeDB;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by user on 2018-05-15.
 */

public class ActFilter extends AppCompatActivity {
	public static final String KEY_SELECTED_VALUE = "KEY_SELECTED_VALUE";
	public static final String RESULT_SELECTED_VALUE = "RESULT_SELECTED_VALUE";

	private LinearLayout mLlStyle;
	private Toolbar mToolbar;
	private TextView mTvSelect;
	private ArrayList<String> styleTypeList;

	private ArrayList<String> setFilter = new ArrayList<>();

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putStringArrayList(KEY_SELECTED_VALUE, setFilter);
	}

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_filter);
		mToolbar = (Toolbar) findViewById(R.id.toolbar);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			mToolbar.setTitle("쇼핑몰 필터");
			mToolbar.setNavigationIcon(R.mipmap.ic_close_white);
			mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent data = new Intent();
					data.putExtra(RESULT_SELECTED_VALUE, setFilter);
					setResult(RESULT_OK, data);
					finish();
				}
			});
		}
		mTvSelect = (TextView) findViewById(R.id.tvSelect);

		mLlStyle = (LinearLayout) findViewById(R.id.llStyle);

		initVIew();

		ArrayList<String> selectedValue = getIntent().getStringArrayListExtra(KEY_SELECTED_VALUE);
		if( savedInstanceState != null ) {
			selectedValue = savedInstanceState.getStringArrayList(KEY_SELECTED_VALUE);
		}

		if(selectedValue != null && !selectedValue.isEmpty()) {
			setKeySelectedValue(selectedValue);
		}
	}

	private void setKeySelectedValue(ArrayList<String> selectedValue) {
		for(String value : selectedValue) {
			CheckBox btn = mLlStyle.findViewWithTag(value);
			btn.setChecked(true);
		}
	}

	private void initVIew() {
		styleTypeList = StyleTypeDB.instance(this).getStyleTypeData();

		int i = 0;
		for(String type : styleTypeList) {
			final CheckBox btn = new CheckBox(this);
			btn.setTag(type);
			btn.setText(type);
			btn.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					if(btn.isChecked()) {
						setFilter.add(String.valueOf(btn.getText()));
					} else {
						setFilter.remove(String.valueOf(btn.getText()));
					}
					mTvSelect.setText(Arrays.toString(setFilter.toArray()));
				}
			});
			mLlStyle.addView(btn);
		}
	}

}
