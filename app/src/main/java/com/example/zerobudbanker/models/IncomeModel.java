package com.example.zerobudbanker.models;

public class IncomeModel {

    private int id_income;
    private String name;
    private CategoryModel category;
    private String description;
    private int creationDate; //number of the month for now
    private int id_user;
    private int id_month; //m

    //Constructors

    public IncomeModel(int id_income, String name, CategoryModel category, String description, int creationDate, int id_user, int id_month) {
        this.id_income = id_income;
        this.name = name;
        this.category = category;
        this.description = description;
        this.creationDate = creationDate;
        this.id_user = id_user;
        this.id_month = id_month;
    }

    public IncomeModel() {
    }


    //toString

    @Override
    public String toString() {
        return "IncomeModel{" +
                "id_income=" + id_income +
                ", name='" + name + '\'' +
                ", category=" + category +
                ", description='" + description + '\'' +
                ", creationDate=" + creationDate +
                ", id_user=" + id_user +
                ", id_month=" + id_month +
                '}';
    }


    //Getters and Setters

    public int getId_income() {
        return id_income;
    }

    public void setId_income(int id_income) {
        this.id_income = id_income;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryModel getCategory() {
        return category;
    }

    public void setCategory(CategoryModel category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(int creationDate) {
        this.creationDate = creationDate;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_month() {
        return id_month;
    }

    public void setId_month(int id_month) {
        this.id_month = id_month;
    }
}
