package com.example.budgetzeroapp.fragment;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.example.budgetzeroapp.R;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;

public class BudgetFragmentDirections {
  private BudgetFragmentDirections() {
  }

  @NonNull
  public static NavDirections actionBudgetFragmentToSelectExpenseTypeFragment2() {
    return new ActionOnlyNavDirections(R.id.action_budgetFragment_to_selectExpenseTypeFragment2);
  }

  @NonNull
  public static ActionBudgetFragmentToEditExpenseCatFragment actionBudgetFragmentToEditExpenseCatFragment(
      int idExpenseCatEdit) {
    return new ActionBudgetFragmentToEditExpenseCatFragment(idExpenseCatEdit);
  }

  @NonNull
  public static ActionBudgetFragmentToViewExpenseCatFragment actionBudgetFragmentToViewExpenseCatFragment(
      int idExpenseCat) {
    return new ActionBudgetFragmentToViewExpenseCatFragment(idExpenseCat);
  }

  public static class ActionBudgetFragmentToEditExpenseCatFragment implements NavDirections {
    private final HashMap arguments = new HashMap();

    @SuppressWarnings("unchecked")
    private ActionBudgetFragmentToEditExpenseCatFragment(int idExpenseCatEdit) {
      this.arguments.put("id_expense_cat_edit", idExpenseCatEdit);
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public ActionBudgetFragmentToEditExpenseCatFragment setIdExpenseCatEdit(int idExpenseCatEdit) {
      this.arguments.put("id_expense_cat_edit", idExpenseCatEdit);
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    @NonNull
    public Bundle getArguments() {
      Bundle __result = new Bundle();
      if (arguments.containsKey("id_expense_cat_edit")) {
        int idExpenseCatEdit = (int) arguments.get("id_expense_cat_edit");
        __result.putInt("id_expense_cat_edit", idExpenseCatEdit);
      }
      return __result;
    }

    @Override
    public int getActionId() {
      return R.id.action_budgetFragment_to_editExpenseCatFragment;
    }

    @SuppressWarnings("unchecked")
    public int getIdExpenseCatEdit() {
      return (int) arguments.get("id_expense_cat_edit");
    }

    @Override
    public boolean equals(Object object) {
      if (this == object) {
          return true;
      }
      if (object == null || getClass() != object.getClass()) {
          return false;
      }
      ActionBudgetFragmentToEditExpenseCatFragment that = (ActionBudgetFragmentToEditExpenseCatFragment) object;
      if (arguments.containsKey("id_expense_cat_edit") != that.arguments.containsKey("id_expense_cat_edit")) {
        return false;
      }
      if (getIdExpenseCatEdit() != that.getIdExpenseCatEdit()) {
        return false;
      }
      if (getActionId() != that.getActionId()) {
        return false;
      }
      return true;
    }

    @Override
    public int hashCode() {
      int result = 1;
      result = 31 * result + getIdExpenseCatEdit();
      result = 31 * result + getActionId();
      return result;
    }

    @Override
    public String toString() {
      return "ActionBudgetFragmentToEditExpenseCatFragment(actionId=" + getActionId() + "){"
          + "idExpenseCatEdit=" + getIdExpenseCatEdit()
          + "}";
    }
  }

  public static class ActionBudgetFragmentToViewExpenseCatFragment implements NavDirections {
    private final HashMap arguments = new HashMap();

    @SuppressWarnings("unchecked")
    private ActionBudgetFragmentToViewExpenseCatFragment(int idExpenseCat) {
      this.arguments.put("id_expense_cat", idExpenseCat);
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public ActionBudgetFragmentToViewExpenseCatFragment setIdExpenseCat(int idExpenseCat) {
      this.arguments.put("id_expense_cat", idExpenseCat);
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    @NonNull
    public Bundle getArguments() {
      Bundle __result = new Bundle();
      if (arguments.containsKey("id_expense_cat")) {
        int idExpenseCat = (int) arguments.get("id_expense_cat");
        __result.putInt("id_expense_cat", idExpenseCat);
      }
      return __result;
    }

    @Override
    public int getActionId() {
      return R.id.action_budgetFragment_to_viewExpenseCatFragment;
    }

    @SuppressWarnings("unchecked")
    public int getIdExpenseCat() {
      return (int) arguments.get("id_expense_cat");
    }

    @Override
    public boolean equals(Object object) {
      if (this == object) {
          return true;
      }
      if (object == null || getClass() != object.getClass()) {
          return false;
      }
      ActionBudgetFragmentToViewExpenseCatFragment that = (ActionBudgetFragmentToViewExpenseCatFragment) object;
      if (arguments.containsKey("id_expense_cat") != that.arguments.containsKey("id_expense_cat")) {
        return false;
      }
      if (getIdExpenseCat() != that.getIdExpenseCat()) {
        return false;
      }
      if (getActionId() != that.getActionId()) {
        return false;
      }
      return true;
    }

    @Override
    public int hashCode() {
      int result = 1;
      result = 31 * result + getIdExpenseCat();
      result = 31 * result + getActionId();
      return result;
    }

    @Override
    public String toString() {
      return "ActionBudgetFragmentToViewExpenseCatFragment(actionId=" + getActionId() + "){"
          + "idExpenseCat=" + getIdExpenseCat()
          + "}";
    }
  }
}
