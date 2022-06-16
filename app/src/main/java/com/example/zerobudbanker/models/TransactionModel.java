package com.example.zerobudbanker.models;

public class TransactionModel {

    private int id_transaction;
    private String name;
    private int amount;
    private int creationDate; //number of the month for now
    private String description;
    private String category;
    private int id_user;
    private int id_month;


    //Constructors

    public TransactionModel(int id_transaction, String name, int amount, int creationDate, String description, String category, int id_user, int id_month) {
        this.id_transaction = id_transaction;
        this.name = name;
        this.amount = amount;
        this.creationDate = creationDate;
        this.description = description;
        this.category = category;
        this.id_user = id_user;
        this.id_month = id_month;
    }

    public TransactionModel() {
    }


    //toString

    @Override
    public String toString() {
        return "N°" + id_transaction +
                ", " + name + '\'' +
                ", " + amount + "€" +
                ", Month: " + creationDate +
                ", Description: " + description + '\'' +
                ", Category: " + category +
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

    public int getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(int creationDate) {
        this.creationDate = creationDate;
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
