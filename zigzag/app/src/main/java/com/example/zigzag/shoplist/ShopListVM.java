package com.example.zigzag.shoplist;

import java.io.Serializable;
import java.util.List;

/**
 * Created by user on 2018-05-14.
 */

public class ShopListVM implements Serializable {

	private String week;
	private List<ShopListDTO> list;

	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	public List<ShopListDTO> getList() {
		return list;
	}

	public void setList(List<ShopListDTO> list) {
		this.list = list;
	}

}
