package com.example.econo_misons.database.models;

public class TreemapEnv {
    public int budgetID;
    public String dateEnv;
    public int categoryID;
    public String categoryName;
    public String categoryColor;
    public float sumEnv;

    public TreemapEnv(int budgetID, String dateEnv, int categoryID, String categoryName, String categoryColor, float sumEnv){
        this.budgetID = budgetID;
        this.dateEnv = dateEnv;
        this.categoryID = categoryID;
        this.categoryName = categoryName;
        this.categoryColor = categoryColor;
        this.sumEnv = sumEnv;
    }
}
