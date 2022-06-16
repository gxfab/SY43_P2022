package com.example.zerobudbanker.models;

public class UserModel {

    private int id_user;
    private String name;
    //private String password;
    private int creationDate; //number of the month for now
    private int nextMonthDate; //number of the day for now


    //Constructors

    public UserModel(int id_user, String name, int creationDate, int nextMonthDate) {
        this.id_user = id_user;
        this.name = name;
        this.creationDate = creationDate;
        this.nextMonthDate = nextMonthDate;
    }

    public UserModel() {
    }


    //to String

    @Override
    public String toString() {
        return "UserModel{" +
                "id_user=" + id_user +
                ", name='" + name + '\'' +
                ", creationDate=" + creationDate +
                ", nextMonthDate=" + nextMonthDate +
                '}';
    }


    //Getters and setters

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(int creationDate) {
        this.creationDate = creationDate;
    }

    public int getNextMonthDate() {
        return nextMonthDate;
    }

    public void setNextMonthDate(int nextMonthDate) {
        this.nextMonthDate = nextMonthDate;
    }
}
