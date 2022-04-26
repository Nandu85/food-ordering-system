package com.narola.fooddelivery.category;

public class Category {
	private int CategoryId;
	private String CategoryName;
	private int popularity;
	
	public int getCategoryId() {
		return CategoryId;
	}
	public void setCategoryId(int categoryId) {
		CategoryId = categoryId;
	}
	
	public String getCategoryName() {
		return CategoryName;
	}
	public void setCategoryName(String categoryName) {
		CategoryName = categoryName;
	}
	
	public int getPopularity() {
		return popularity;
	}
	public void setPopularity(int popularity) {
		this.popularity = popularity;
	}
	
	@Override
	public String toString() {
		return "Category [CategoryId=" + CategoryId + ", CategoryName=" + CategoryName + ", popularity=" + popularity
				+ "]\n";
	}
	
	
}
