package com.sucelloztm.sucelloz.ui.savings;

import static java.lang.Math.round;

//TODO : Put this class in the MVP Pattern
public class Savings {
    String name;
    int initialAmount;
    int reachedAmount;
    String deadline;
    float percentage;



    public Savings(String name, int initialAmount, int reachedAmount, String deadline) {
        this.name = name;
        this.initialAmount = initialAmount;
        this.reachedAmount = reachedAmount;
        this.deadline = deadline;
        this.percentage=((float)reachedAmount/initialAmount)*100;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getInitialAmount() {
        return initialAmount;
    }

    public void setInitialAmount(int initialAmount) {
        this.initialAmount = initialAmount;
    }

    public int getReachedAmount() {
        return reachedAmount;
    }

    public void setReachedAmount(int reachedAmount) {
        this.reachedAmount = reachedAmount;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }
}
