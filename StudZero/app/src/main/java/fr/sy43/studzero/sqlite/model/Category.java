package fr.sy43.studzero.sqlite.model;

public class Category {
    private int idCategory;
    private float theoreticalAmount;
    private float realAmount;
    private int budget;
    private int type;

    public Category(int idCategory, float theoreticalAmount, float realAmount, int budget, int type) {
        this.idCategory = idCategory;
        this.theoreticalAmount = theoreticalAmount;
        this.realAmount = realAmount;
        this.budget = budget;
        this.type = type;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public float getTheoreticalAmount() {
        return theoreticalAmount;
    }

    public void setTheoreticalAmount(float theoreticalAmount) {
        this.theoreticalAmount = theoreticalAmount;
    }

    public float getRealAmount() {
        return realAmount;
    }

    public void setRealAmount(float realAmount) {
        this.realAmount = realAmount;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
