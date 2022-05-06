package fr.sy43.studzero.sqlite.model;

import java.util.Date;

public class Budget {
    private int idBudget;
    private Date dateStart;
    private Date dateEnd;
    private float budgetAmount;

    public Budget(int idBudget, Date dateStart, Date dateEnd, float budgetAmount) {
        this.idBudget = idBudget;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.budgetAmount = budgetAmount;
    }

    public Budget(Date dateStart, Date dateEnd, float budgetAmount) {
        this.idBudget = Integer.parseInt(null);
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.budgetAmount = budgetAmount;
    }

    public int getIdBudget() {
        return idBudget;
    }

    public void setIdBudget(int idBudget) {
        this.idBudget = idBudget;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public float getBudgetAmount() {
        return budgetAmount;
    }

    public void setBudgetAmount(float budgetAmount) {
        this.budgetAmount = budgetAmount;
    }
}
