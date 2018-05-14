package com.example.zigzag.shoplist.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by user on 2018-05-14.
 */

public class ShopListDTO implements Serializable {
	@SerializedName("0") private Integer score;
	private String n;
	private String u;
	private String S;
	private int[] A;

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getN() {
		return n;
	}

	public void setN(String n) {
		this.n = n;
	}

	public String getU() {
		return u;
	}

	public void setU(String u) {
		this.u = u;
	}

	public String getS() {
		return S;
	}

	public void setS(String s) {
		S = s;
	}

	public int[] getA() {
		return A;
	}

	public void setA(int[] a) {
		A = a;
	}

}
