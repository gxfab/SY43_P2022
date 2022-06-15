package com.example.cookutproject.models;

public abstract class Aliment {

    private float price;
    private String title;

    Aliment (int price, String title){
        this.price = price;
        this.title = title;
    }
    public float getPrice() {
        return price;
    }

    public String getTitle() {
        return title;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
