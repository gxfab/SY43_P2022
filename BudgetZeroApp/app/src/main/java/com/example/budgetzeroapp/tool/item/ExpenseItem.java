package com.example.budgetzeroapp.tool.item;


public class ExpenseItem extends ListItem {
    private final int date;
    private final int type;
    private final String categoryName;
    private final float amount;

    public ExpenseItem(int id, String name, int date, int type, String catName, float amount) {
        super(id, name);
        this.date = date;
        categoryName = catName;
        this.amount = amount;
        this.type = type;
    }

    public int getDate(){return date;}
    public String getCategoryName(){ return categoryName; }
    public float getAmount(){ return amount; }
}
