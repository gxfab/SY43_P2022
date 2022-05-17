package fr.sy43.studzero.sqlite.model;

import java.util.Date;

/**
 * This class is used to store information about the user
 */
public class User {
    /**
     * Id of the user
     */
    private int idUser;
    /**
     * Date of the next budget
     */
    private Date dateNextBudget;
    /**
     * Id of the current budget
     */
    private int currentBudget;

    /**
     * Constructor of the class
     * @param idUser
     * @param dateNextBudget
     * @param currentBudget
     */
    public User(int idUser, Date dateNextBudget, int currentBudget) {
        this.idUser = idUser;
        this.dateNextBudget = dateNextBudget;
        this.currentBudget = currentBudget;
    }

    /**
     * Constructor of the class
     * @param dateNextBudget
     * @param currentBudget
     */
    public User(Date dateNextBudget, int currentBudget) {
        this.idUser = 0;
        this.dateNextBudget = dateNextBudget;
        this.currentBudget = currentBudget;
    }

    /**
     * @return the id of the user
     */
    public int getIdUser() {
        return idUser;
    }

    /**
     * Set the id of the user
     * @param idUser
     */
    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    /**
     * @return the date of the next budget
     */
    public Date getDateNextBudget() {
        return dateNextBudget;
    }

    /**
     * Set the id of the user
     * @param dateNextBudget
     */
    public void setDateNextBudget(Date dateNextBudget) {
        this.dateNextBudget = dateNextBudget;
    }

    /**
     * @return the id of the current budget
     */
    public int getCurrentBudget() {
        return currentBudget;
    }

    /**
     * Set the id of the current budget
     * @param currentBudget
     */
    public void setCurrentBudget(int currentBudget) {
        this.currentBudget = currentBudget;
    }
}
