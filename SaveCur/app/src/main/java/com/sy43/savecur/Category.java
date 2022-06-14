package com.sy43.savecur;

public class Category {
    private final String name;
    private int moneySpent;
    private int moneyLimit;

    public int getMoneyLimit() {
        return moneyLimit;
    }

    public int getMoneySpent() {
        return moneySpent;
    }

    public String getName() {
        return name;
    }

    public int getProgress() {
        return (moneySpent * 100) / moneyLimit;
    }

    public void setMoneyLimit(int moneyLimit) {
        this.moneyLimit = moneyLimit;
    }

    public void setMoneySpent(int moneySpent) {
        this.moneySpent = moneySpent;
    }

    public Category(String name, int moneyLimit, int moneySpent) {
        this.name = name;
        this.moneyLimit = moneyLimit;
        this.moneySpent = moneySpent;
    }
}
