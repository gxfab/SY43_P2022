package com.sucelloztm.sucelloz.ui.spendings;

/**
 * spendings class
 */
public class Spendings {
    private String name;
    private int amount;
    private String date;
    private String subCategory;

    /**
     * custom constructor
     * @param name name
     * @param amount amount
     * @param date date
     * @param subCategory subcategory
     */
    public Spendings(String name, int amount, String date, String subCategory) {
        this.name = name;
        this.amount = amount;
        this.date = date;
        this.subCategory = subCategory;
    }

    /**
     * getter
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * setter
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * getter
     * @return amount
     */
    public int getAmount() {
        return amount;
    }

    /**
     * setter
     * @param amount amount
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * getter
     * @return date
     */
    public String getDate() {
        return date;
    }

    /**
     * setter
     * @param date date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * getter
     * @return subcategory
     */
    public String getSubCategory() {
        return subCategory;
    }

    /**
     * setter
     * @param subCategory subcategory
     */
    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }
}
