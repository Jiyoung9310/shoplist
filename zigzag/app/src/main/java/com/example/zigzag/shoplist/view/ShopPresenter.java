package com.example.zigzag.shoplist.view;

import com.example.zigzag.shoplist.adapter.AdpRecycler;
import com.example.zigzag.shoplist.view.model.RecyclerModel;

/**
 * Created by user on 2018-05-14.
 */

public class ShopPresenter implements Contract.ShopPresenter{

	AdpRecycler recyclerAdapter;
	RecyclerModel<ItemRecycler> adapterModel;

	@Override
	public void setAdapterView(AdpRecycler adapterView) {
		recyclerAdapter = adapterView;
	}

	@Override
	public void setAdapterModel(RecyclerModel<ItemRecycler> adapterModel) {
		this.adapterModel = adapterModel;
	}

	@Override
	public void loadList(int count) {

	}
}
