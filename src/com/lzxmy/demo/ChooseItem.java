package com.lzxmy.demo;

public class ChooseItem {
	String name = "";
	int tag = 0;

	public ChooseItem(String name, int tag) {
		this.name = name;
		this.tag = tag;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTag() {
		return tag;
	}

	public void setTag(int tag) {
		this.tag = tag;
	}
}
