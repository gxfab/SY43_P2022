package com.example.zerobudbanker.models;

public class CategoryModel {

    private int id_category;
    private String name;
    private boolean isIncome;

    //Constructors

    public CategoryModel(int id_category, String name, boolean isIncome) {
        this.id_category = id_category;
        this.name = name;
        this.isIncome = isIncome;
    }

    public CategoryModel() {
    }


    //toString

    @Override
    public String toString() {
        return "CategoryModel{" +
                "id_category=" + id_category +
                ", name='" + name + '\'' +
                ", isIncome=" + isIncome +
                '}';
    }


    //Getters and Setters

    public int getId_category() {
        return id_category;
    }

    public void setId_category(int id_category) {
        this.id_category = id_category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isIncome() {
        return isIncome;
    }

    public void setIncome(boolean income) {
        isIncome = income;
    }
}
