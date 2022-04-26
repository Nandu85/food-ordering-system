package com.narola.fooddelivery.category;

public class SubCategory {
	private int categoryId;
	private String categoryName;
	private Category category;
	private String imageAsBase64;
	
	public String getImageAsBase64() {
		return imageAsBase64;
	}
	public void setImageAsBase64(String imageAsBase64) {
		this.imageAsBase64 = imageAsBase64;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
	
	@Override
	public String toString() {
		return "SubCategory [categoryId=" + categoryId + ", categoryName=" + categoryName + ", category=" + category
				+ ", imageAsBase64=" + imageAsBase64 + "]";
	}
	
	
	
}
