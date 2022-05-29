package com.sucelloztm.sucelloz.ui.spendings;

class Spendings {
    String name;
    String date;
    String amount;

    Spendings(String name, String date, String amount) {
        this.name = name;
        this.date = date;
        this.amount = amount;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
