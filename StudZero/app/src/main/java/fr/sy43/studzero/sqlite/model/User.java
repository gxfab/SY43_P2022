package fr.sy43.studzero.sqlite.model;

import java.util.Date;

public class User {
    private int idUser;
    private Date dateNextBudget;
    private int currentBudget;

    public User(int idUser, Date dateNextBudget, int currentBudget) {
        this.idUser = idUser;
        this.dateNextBudget = dateNextBudget;
        this.currentBudget = currentBudget;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public Date getDateNextBudget() {
        return dateNextBudget;
    }

    public void setDateNextBudget(Date dateNextBudget) {
        this.dateNextBudget = dateNextBudget;
    }

    public int getCurrentBudget() {
        return currentBudget;
    }

    public void setCurrentBudget(int currentBudget) {
        this.currentBudget = currentBudget;
    }
}
