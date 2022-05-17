package fr.sy43.studzero.sqlite.model;

import java.util.Date;

/**
 * This class represents a payment done during a budget period
 */
public class Payment {
    /**
     * id of the payment
     */
    private int idPayment;
    /**
     * amount payed
     */
    private float amount;
    /**
     * date of the payment
     */
    private Date datePayment;
    /**
     * Id of the category that the payment is linked to
     */
    private int category;

    /**
     * Constructor of the class
     * @param idPayment
     * @param amount
     * @param datePayment
     * @param category
     */
    public Payment(int idPayment, float amount, Date datePayment, int category) {
        this.idPayment = idPayment;
        this.amount = amount;
        this.datePayment = datePayment;
        this.category = category;
    }

    /**
     * Constructor of the class
     * @param amount
     * @param datePayment
     * @param category
     */
    public Payment(float amount, Date datePayment, int category) {
        this.idPayment = 0;
        this.amount = amount;
        this.datePayment = datePayment;
        this.category = category;
    }

    /**
     * @return id of the payment
     */
    public int getIdPayment() {
        return idPayment;
    }

    /**
     * Set the id of the payment
     * @param idPayment
     */
    public void setIdPayment(int idPayment) {
        this.idPayment = idPayment;
    }

    /**
     * @return amount payed
     */
    public float getAmount() {
        return amount;
    }

    /**
     * Set the amount payed
     * @param amount
     */
    public void setAmount(float amount) {
        this.amount = amount;
    }

    /**
     * @return the date of the payment
     */
    public Date getDatePayment() {
        return datePayment;
    }

    /**
     * Set the date of the payment
     * @param datePayment
     */
    public void setDatePayment(Date datePayment) {
        this.datePayment = datePayment;
    }

    /**
     * @return id of the category the payment is linked to
     */
    public int getCategory() {
        return category;
    }

    /**
     * Set the id of the category the payment is linked to
     * @param category
     */
    public void setCategory(int category) {
        this.category = category;
    }
}
