package com.sucelloztm.sucelloz.ui.spendings;

/**
 * POJO for displaying Spendings
 */
public class Spendings {
    private String name;
    private int amount;
    private String date;
    private String subCategory;

    /**
     * Custom constructor
     *
     * @param name        name
     * @param amount      amount
     * @param date        date
     * @param subCategory subcategory
     */
    public Spendings(String name, int amount, String date, String subCategory) {
        this.name = name;
        this.amount = amount;
        this.date = date;
        this.subCategory = subCategory;
    }

    /**
     * Getter
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter
     *
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter
     *
     * @return amount
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Setter
     *
     * @param amount amount
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * Getter
     *
     * @return date
     */
    public String getDate() {
        return date;
    }

    /**
     * Setter
     *
     * @param date date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * getter
     *
     * @return subcategory
     */
    public String getSubCategory() {
        return subCategory;
    }

    /**
     * setter
     *
     * @param subCategory subcategory
     */
    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }
}
