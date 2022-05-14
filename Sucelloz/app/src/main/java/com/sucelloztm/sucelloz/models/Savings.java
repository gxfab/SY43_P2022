package com.sucelloztm.sucelloz.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.lang.String;

@Entity(tableName = "savings")
public class Savings {

    @PrimaryKey
    private long id;

    @ColumnInfo(name="name")
    private String name;

    @ColumnInfo(name="date")
    private int date;

    @ColumnInfo(name="amount")
    private int amount;

    // CONSTRUCTOR
    public Savings() {
    }

    public Savings(String name, int date, int amount) {
        this.name = name;
        this.date = date;
        this.amount = amount;
    }

    //GETTER
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getDate() {
        return date;
    }

    public int getAmount() {
        return amount;
    }

    //SETTER
    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
