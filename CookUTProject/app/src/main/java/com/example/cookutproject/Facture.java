package com.example.cookutproject;

import java.util.Date;

public class Facture {

    private float amount;
    private Date date;
    private int id;

    Facture (float amount, Date date, int id){
        this.amount = amount;
        this.date = date;
        this.id =id;
    }
    public float getAmount() {
        return amount;
    }

    public Date getDate() {
        return date;
    }

    public int getId() {
        return id;
    }

}
