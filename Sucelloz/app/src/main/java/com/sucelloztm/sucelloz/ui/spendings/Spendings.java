package com.sucelloztm.sucelloz.ui.spendings;

public class Spendings {
    private String name;
    private int amount;
    private String date;
    private String subCategory;

    public Spendings(String name, int amount, String date, String subCategory) {
        this.name = name;
        this.amount = amount;
        this.date = date;
        this.subCategory = subCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }
}
