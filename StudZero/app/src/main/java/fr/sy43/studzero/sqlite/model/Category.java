package fr.sy43.studzero.sqlite.model;

/**
 * This class represents a payment category for a budget
 */
public class Category {
    /**
     * id of the category
     */
    private int idCategory;
    /**
     * theoretical amount of money that the user will spend for this category
     */
    private float theoreticalAmount;
    /**
     * real amount of money that the user spend for this category
     */
    private float realAmount;
    /**
     * Id of the budget the category is linked to
     */
    private int budget;
    /**
     * Id of the type this category is
     */
    private int type;

    /**
     * Constructor of the class
     * @param idCategory
     * @param theoreticalAmount
     * @param realAmount
     * @param budget
     * @param type
     */
    public Category(int idCategory, float theoreticalAmount, float realAmount, int budget, int type) {
        this.idCategory = idCategory;
        this.theoreticalAmount = theoreticalAmount;
        this.realAmount = realAmount;
        this.budget = budget;
        this.type = type;
    }

    /**
     * Constructor of the class
     * @param theoreticalAmount
     * @param realAmount
     * @param budget
     * @param type
     */
    public Category(float theoreticalAmount, float realAmount, int budget, int type) {
        this.idCategory = 0;
        this.theoreticalAmount = theoreticalAmount;
        this.realAmount = realAmount;
        this.budget = budget;
        this.type = type;
    }

    /**
     * @return id of the category
     */
    public int getIdCategory() {
        return idCategory;
    }

    /**
     * Set the id of the category
     * @param idCategory
     */
    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    /**
     * @return the theoretical amount of money that will be spend on this category
     */
    public float getTheoreticalAmount() {
        return theoreticalAmount;
    }

    /**
     * Set the theoretical amount of money that will be spend on this category
     * @param theoreticalAmount
     */
    public void setTheoreticalAmount(float theoreticalAmount) {
        this.theoreticalAmount = theoreticalAmount;
    }

    /**
     * @return the real amount of money that the user spend for this category
     */
    public float getRealAmount() {
        return realAmount;
    }

    /**
     * Set the real amount of money that the user spend for this category
     * @param realAmount
     */
    public void setRealAmount(float realAmount) {
        this.realAmount = realAmount;
    }

    /**
     * @return the id of the budget this category is linked to
     */
    public int getBudget() {
        return budget;
    }

    /**
     * Set the id of the budget this category is linked to
     * @param budget
     */
    public void setBudget(int budget) {
        this.budget = budget;
    }

    /**
     * @return the id of the type this category is
     */
    public int getType() {
        return type;
    }

    /**
     * Set the id of the type this category is
     * @param type
     */
    public void setType(int type) {
        this.type = type;
    }
}
