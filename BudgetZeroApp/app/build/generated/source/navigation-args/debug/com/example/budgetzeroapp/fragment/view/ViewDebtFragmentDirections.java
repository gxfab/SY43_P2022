package com.example.budgetzeroapp.fragment.view;

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

public class ViewDebtFragmentDirections {
  private ViewDebtFragmentDirections() {
  }

  @NonNull
  public static NavDirections navigateToSavingsFromDebt() {
    return new ActionOnlyNavDirections(R.id.navigate_to_savings_from_debt);
  }

  @NonNull
  public static NavigateToEditDebtFromDebt navigateToEditDebtFromDebt(int idDebtEdit) {
    return new NavigateToEditDebtFromDebt(idDebtEdit);
  }

  @NonNull
  public static ActionViewDebtFragmentToViewExpenseFragment actionViewDebtFragmentToViewExpenseFragment(
      int idExpense) {
    return new ActionViewDebtFragmentToViewExpenseFragment(idExpense);
  }

  public static class NavigateToEditDebtFromDebt implements NavDirections {
    private final HashMap arguments = new HashMap();

    @SuppressWarnings("unchecked")
    private NavigateToEditDebtFromDebt(int idDebtEdit) {
      this.arguments.put("id_debt_edit", idDebtEdit);
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public NavigateToEditDebtFromDebt setIdDebtEdit(int idDebtEdit) {
      this.arguments.put("id_debt_edit", idDebtEdit);
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    @NonNull
    public Bundle getArguments() {
      Bundle __result = new Bundle();
      if (arguments.containsKey("id_debt_edit")) {
        int idDebtEdit = (int) arguments.get("id_debt_edit");
        __result.putInt("id_debt_edit", idDebtEdit);
      }
      return __result;
    }

    @Override
    public int getActionId() {
      return R.id.navigate_to_editDebt_from_debt;
    }

    @SuppressWarnings("unchecked")
    public int getIdDebtEdit() {
      return (int) arguments.get("id_debt_edit");
    }

    @Override
    public boolean equals(Object object) {
      if (this == object) {
          return true;
      }
      if (object == null || getClass() != object.getClass()) {
          return false;
      }
      NavigateToEditDebtFromDebt that = (NavigateToEditDebtFromDebt) object;
      if (arguments.containsKey("id_debt_edit") != that.arguments.containsKey("id_debt_edit")) {
        return false;
      }
      if (getIdDebtEdit() != that.getIdDebtEdit()) {
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
      result = 31 * result + getIdDebtEdit();
      result = 31 * result + getActionId();
      return result;
    }

    @Override
    public String toString() {
      return "NavigateToEditDebtFromDebt(actionId=" + getActionId() + "){"
          + "idDebtEdit=" + getIdDebtEdit()
          + "}";
    }
  }

  public static class ActionViewDebtFragmentToViewExpenseFragment implements NavDirections {
    private final HashMap arguments = new HashMap();

    @SuppressWarnings("unchecked")
    private ActionViewDebtFragmentToViewExpenseFragment(int idExpense) {
      this.arguments.put("id_expense", idExpense);
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public ActionViewDebtFragmentToViewExpenseFragment setIdExpense(int idExpense) {
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
      return R.id.action_viewDebtFragment_to_viewExpenseFragment;
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
      ActionViewDebtFragmentToViewExpenseFragment that = (ActionViewDebtFragmentToViewExpenseFragment) object;
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
      return "ActionViewDebtFragmentToViewExpenseFragment(actionId=" + getActionId() + "){"
          + "idExpense=" + getIdExpense()
          + "}";
    }
  }
}
