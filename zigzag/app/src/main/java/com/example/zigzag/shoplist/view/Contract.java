package com.example.zigzag.shoplist.view;

import com.example.zigzag.shoplist.adapter.AdpRecycler;
import com.example.zigzag.shoplist.view.model.RecyclerModel;

/**
 * Created by user on 2018-05-14.
 */

public interface Contract {

	interface ShopPresenter {

		void setAdapterView(AdpRecycler adapterView);

		void setAdapterModel(RecyclerModel<ItemRecycler> adapterModel);

		void loadList(int count);
	}

	interface ShopView {

	}
}
