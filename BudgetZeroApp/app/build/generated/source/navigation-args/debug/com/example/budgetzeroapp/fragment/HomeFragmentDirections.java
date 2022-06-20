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

public class HomeFragmentDirections {
  private HomeFragmentDirections() {
  }

  @NonNull
  public static NavDirections actionHomeFragmentToSelectExpenseTypeFragment2() {
    return new ActionOnlyNavDirections(R.id.action_homeFragment_to_selectExpenseTypeFragment2);
  }

  @NonNull
  public static ActionHomeFragmentToEditExpenseCatFragment actionHomeFragmentToEditExpenseCatFragment(
      int idExpenseCatEdit) {
    return new ActionHomeFragmentToEditExpenseCatFragment(idExpenseCatEdit);
  }

  @NonNull
  public static ActionHomeFragmentToViewExpenseCatFragment actionHomeFragmentToViewExpenseCatFragment(
      int idExpenseCat) {
    return new ActionHomeFragmentToViewExpenseCatFragment(idExpenseCat);
  }

  public static class ActionHomeFragmentToEditExpenseCatFragment implements NavDirections {
    private final HashMap arguments = new HashMap();

    @SuppressWarnings("unchecked")
    private ActionHomeFragmentToEditExpenseCatFragment(int idExpenseCatEdit) {
      this.arguments.put("id_expense_cat_edit", idExpenseCatEdit);
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public ActionHomeFragmentToEditExpenseCatFragment setIdExpenseCatEdit(int idExpenseCatEdit) {
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
      return R.id.action_homeFragment_to_editExpenseCatFragment;
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
      ActionHomeFragmentToEditExpenseCatFragment that = (ActionHomeFragmentToEditExpenseCatFragment) object;
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
      return "ActionHomeFragmentToEditExpenseCatFragment(actionId=" + getActionId() + "){"
          + "idExpenseCatEdit=" + getIdExpenseCatEdit()
          + "}";
    }
  }

  public static class ActionHomeFragmentToViewExpenseCatFragment implements NavDirections {
    private final HashMap arguments = new HashMap();

    @SuppressWarnings("unchecked")
    private ActionHomeFragmentToViewExpenseCatFragment(int idExpenseCat) {
      this.arguments.put("id_expense_cat", idExpenseCat);
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public ActionHomeFragmentToViewExpenseCatFragment setIdExpenseCat(int idExpenseCat) {
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
      return R.id.action_homeFragment_to_viewExpenseCatFragment;
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
      ActionHomeFragmentToViewExpenseCatFragment that = (ActionHomeFragmentToViewExpenseCatFragment) object;
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
      return "ActionHomeFragmentToViewExpenseCatFragment(actionId=" + getActionId() + "){"
          + "idExpenseCat=" + getIdExpenseCat()
          + "}";
    }
  }
}
