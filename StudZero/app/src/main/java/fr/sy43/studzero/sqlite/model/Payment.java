package fr.sy43.studzero.sqlite.model;

import java.util.Date;

public class Payment {
    private int idPayment;
    private float amount;
    private Date datePayment;
    private int category;

    public Payment(int idPayment, float amount, Date datePayment, int category) {
        this.idPayment = idPayment;
        this.amount = amount;
        this.datePayment = datePayment;
        this.category = category;
    }

    public Payment(float amount, Date datePayment, int category) {
        this.idPayment = Integer.parseInt(null);
        this.amount = amount;
        this.datePayment = datePayment;
        this.category = category;
    }

    public int getIdPayment() {
        return idPayment;
    }

    public void setIdPayment(int idPayment) {
        this.idPayment = idPayment;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public Date getDatePayment() {
        return datePayment;
    }

    public void setDatePayment(Date datePayment) {
        this.datePayment = datePayment;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }
}
