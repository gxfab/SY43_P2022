package com.example.sy43;

import android.graphics.drawable.Drawable;

import java.util.List;

public class DataModel2 {

    private List<String> subcategoriesTitles;
    private List<String> subcategoriesAmounts;
    private String categoryTitle;
    private Drawable categoryImg;
    private String categoryTotalAmount;
    private boolean isExpandable;

    public DataModel2(List<String> subcategoriesTitles, List<String> subcategoriesAmounts, String categoryTitle, Drawable categoryImg, String categoryTotalAmount) {
        this.subcategoriesTitles = subcategoriesTitles;
        this.subcategoriesAmounts = subcategoriesAmounts;
        this.categoryTitle = categoryTitle;
        this.categoryImg = categoryImg;
        this.categoryTotalAmount = categoryTotalAmount;
        this.isExpandable = false;
    }

    public List<String> getSubcategoriesAmounts() {
        return subcategoriesAmounts;
    }

    public void setSubcategoriesAmounts(List<String> subcategoriesAmounts) {
        this.subcategoriesAmounts = subcategoriesAmounts;
    }

    public String getCategoryTotalAmount() {
        return categoryTotalAmount;
    }

    public void setCategoryTotalAmount(String categoryTotalAmount) {
        this.categoryTotalAmount = categoryTotalAmount;
    }

    public List<String> getSubcategoriesTitles() {
        return subcategoriesTitles;
    }


    public String getCategoryTitle() {
        return categoryTitle;
    }

    public Drawable getCategoryImg() {
        return categoryImg;
    }

    public boolean isExpandable() {
        return isExpandable;
    }

    public void setSubcategoriesTitles(List<String> subcategoriesTitles) {
        this.subcategoriesTitles = subcategoriesTitles;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    public void setCategoryImg(Drawable categoryImg) {
        this.categoryImg = categoryImg;
    }

    public void setExpandable(boolean expandable) {
        isExpandable = expandable;
    }
}
