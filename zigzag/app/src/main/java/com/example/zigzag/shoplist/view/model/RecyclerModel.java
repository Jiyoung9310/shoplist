package com.example.zigzag.shoplist.view.model;

import java.util.List;

/**
 * Created by user on 2018-05-14.
 */

public interface RecyclerModel<ITEM> {

	void addItem(ITEM item);
	void addItems(List<ITEM> items);

	ITEM getItem(int position);
	List<ITEM> getItems();

	void removeItem(ITEM item);
	void removeItem(int position);

	void clear();

	int getItemCount();
}
