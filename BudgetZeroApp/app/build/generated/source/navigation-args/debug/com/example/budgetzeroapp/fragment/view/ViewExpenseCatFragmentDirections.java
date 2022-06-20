package com.example.budgetzeroapp.fragment.view;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.navigation.NavDirections;
import com.example.budgetzeroapp.R;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;

public class ViewExpenseCatFragmentDirections {
  private ViewExpenseCatFragmentDirections() {
  }

  @NonNull
  public static ActionViewExpenseCatFragmentToEditExpenseCatFragment actionViewExpenseCatFragmentToEditExpenseCatFragment(
      int idExpenseCatEdit) {
    return new ActionViewExpenseCatFragmentToEditExpenseCatFragment(idExpenseCatEdit);
  }

  @NonNull
  public static ActionViewExpenseCatFragmentSelf actionViewExpenseCatFragmentSelf(
      int idExpenseCat) {
    return new ActionViewExpenseCatFragmentSelf(idExpenseCat);
  }

  @NonNull
  public static ActionViewExpenseCatFragmentToViewExpenseFragment actionViewExpenseCatFragmentToViewExpenseFragment(
      int idExpense) {
    return new ActionViewExpenseCatFragmentToViewExpenseFragment(idExpense);
  }

  public static class ActionViewExpenseCatFragmentToEditExpenseCatFragment implements NavDirections {
    private final HashMap arguments = new HashMap();

    @SuppressWarnings("unchecked")
    private ActionViewExpenseCatFragmentToEditExpenseCatFragment(int idExpenseCatEdit) {
      this.arguments.put("id_expense_cat_edit", idExpenseCatEdit);
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public ActionViewExpenseCatFragmentToEditExpenseCatFragment setIdExpenseCatEdit(
        int idExpenseCatEdit) {
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
      return R.id.action_viewExpenseCatFragment_to_editExpenseCatFragment;
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
      ActionViewExpenseCatFragmentToEditExpenseCatFragment that = (ActionViewExpenseCatFragmentToEditExpenseCatFragment) object;
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
      return "ActionViewExpenseCatFragmentToEditExpenseCatFragment(actionId=" + getActionId() + "){"
          + "idExpenseCatEdit=" + getIdExpenseCatEdit()
          + "}";
    }
  }

  public static class ActionViewExpenseCatFragmentSelf implements NavDirections {
    private final HashMap arguments = new HashMap();

    @SuppressWarnings("unchecked")
    private ActionViewExpenseCatFragmentSelf(int idExpenseCat) {
      this.arguments.put("id_expense_cat", idExpenseCat);
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public ActionViewExpenseCatFragmentSelf setIdExpenseCat(int idExpenseCat) {
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
      return R.id.action_viewExpenseCatFragment_self;
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
      ActionViewExpenseCatFragmentSelf that = (ActionViewExpenseCatFragmentSelf) object;
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
      return "ActionViewExpenseCatFragmentSelf(actionId=" + getActionId() + "){"
          + "idExpenseCat=" + getIdExpenseCat()
          + "}";
    }
  }

  public static class ActionViewExpenseCatFragmentToViewExpenseFragment implements NavDirections {
    private final HashMap arguments = new HashMap();

    @SuppressWarnings("unchecked")
    private ActionViewExpenseCatFragmentToViewExpenseFragment(int idExpense) {
      this.arguments.put("id_expense", idExpense);
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public ActionViewExpenseCatFragmentToViewExpenseFragment setIdExpense(int idExpense) {
      this.arguments.put("id_expense", idExpense);
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    @NonNull
    public Bundle getArguments() {
      Bundle __result = new Bundle();
      if (arguments.containsKey("id_expense")) {
        int idExpense = (int) arguments.get("id_expense");
        __result.putInt("id_expense", idExpense);
      }
      return __result;
    }

    @Override
    public int getActionId() {
      return R.id.action_viewExpenseCatFragment_to_viewExpenseFragment;
    }

    @SuppressWarnings("unchecked")
    public int getIdExpense() {
      return (int) arguments.get("id_expense");
    }

    @Override
    public boolean equals(Object object) {
      if (this == object) {
          return true;
      }
      if (object == null || getClass() != object.getClass()) {
          return false;
      }
      ActionViewExpenseCatFragmentToViewExpenseFragment that = (ActionViewExpenseCatFragmentToViewExpenseFragment) object;
      if (arguments.containsKey("id_expense") != that.arguments.containsKey("id_expense")) {
        return false;
      }
      if (getIdExpense() != that.getIdExpense()) {
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
      result = 31 * result + getIdExpense();
      result = 31 * result + getActionId();
      return result;
    }

    @Override
    public String toString() {
      return "ActionViewExpenseCatFragmentToViewExpenseFragment(actionId=" + getActionId() + "){"
          + "idExpense=" + getIdExpense()
          + "}";
    }
  }
}
