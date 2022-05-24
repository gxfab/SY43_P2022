package fr.sy43.studzero.sqlite.shared_data;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import fr.sy43.studzero.sqlite.model.Budget;
import fr.sy43.studzero.sqlite.model.Category;

public class CreateBudgetSharedData implements Serializable {
    public static final String KEY_CREATE_BUDGET_SHARED_DATA = "KeyCreateBudgetSharedData";
    private Budget newBudget;
    private List<Category> listCategoriesNewBudget;

    public CreateBudgetSharedData() {
        newBudget = new Budget();
        listCategoriesNewBudget = new LinkedList<>();
    }

    /**
     * @return new budget
     */
    public Budget getNewBudget() {
        return newBudget;
    }

    /**
     * Set new budget
     * @param newBudget
     */
    public void setNewBudget(Budget newBudget) {
        this.newBudget = newBudget;
    }

    public void addCategory(Category category) {
        listCategoriesNewBudget.add(category);
    }

}
