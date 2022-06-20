package com.example.zerobudbanker.models;

public class ProjectModel {

    private int id_transaction;
    private String name;
    private int amount;
    private String description;
    private String category;
    private int creationDate; //number of the month for now
    private boolean isCompleted;
    private int id_user;

    //Constructors

    public ProjectModel(int id_transaction, String name, int amount, String description, String category, int creationDate, boolean isCompleted, int id_user) {
        this.id_transaction = id_transaction;
        this.name = name;
        this.amount = amount;
        this.description = description;
        this.category = category;
        this.creationDate = creationDate;
        this.isCompleted = isCompleted;
        this.id_user = id_user;
    }

    public ProjectModel() {
    }


    //toString

    @Override
    public String toString() {
        return "ProjectModel{" +
                "id_transaction=" + id_transaction +
                ", name='" + name + '\'' +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", creationDate=" + creationDate +
                ", isCompleted=" + isCompleted +
                ", id_user=" + id_user +
                '}';
    }


    //Getters and Setters

    public int getId_transaction() {
        return id_transaction;
    }

    public void setId_transaction(int id_transaction) {
        this.id_transaction = id_transaction;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(int creationDate) {
        this.creationDate = creationDate;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }
}
