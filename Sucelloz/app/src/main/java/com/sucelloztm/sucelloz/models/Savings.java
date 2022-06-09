package com.sucelloztm.sucelloz.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.lang.String;

@Entity(tableName = "savings")
public class Savings {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "savings_id")
    private long id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "date")
    private String deadline;

    @ColumnInfo(name = "initial-amount")
    private int initialAmount;

    @ColumnInfo(name = "reached-amount")
    private int reachedAmount;

    @ColumnInfo(name = "percentage")
    private float percentage;

    // CONSTRUCTOR
    public Savings() {
    }

    public Savings(String name, String deadline, int initialAmount) {
        this.name = name;
        this.deadline = deadline;
        this.initialAmount = initialAmount;
        this.reachedAmount = 0;
        this.percentage = ((float)reachedAmount/initialAmount)*100;
    }





    //GETTER
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDeadline() {
        return deadline;
    }

    public int getInitialAmount() {
        return initialAmount;
    }

    public int getReachedAmount() {
        return reachedAmount;
    }

    public float getPercentage() {
        return percentage;
    }

    //SETTER
    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public void setInitialAmount(int initialAmount) {
        this.initialAmount = initialAmount;
    }

    public void setReachedAmount(int reachedAmount) {
        this.reachedAmount = reachedAmount;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }
}
