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

public class CashFlowFragmentDirections {
  private CashFlowFragmentDirections() {
  }

  @NonNull
  public static NavigateToViewExpenseFragmentFromCashflow navigateToViewExpenseFragmentFromCashflow(
      int idExpense) {
    return new NavigateToViewExpenseFragmentFromCashflow(idExpense);
  }

  @NonNull
  public static NavDirections actionCashFlowFragmentToSelectExpenseTypeFragment2() {
    return new ActionOnlyNavDirections(R.id.action_cashFlowFragment_to_selectExpenseTypeFragment2);
  }

  public static class NavigateToViewExpenseFragmentFromCashflow implements NavDirections {
    private final HashMap arguments = new HashMap();

    @SuppressWarnings("unchecked")
    private NavigateToViewExpenseFragmentFromCashflow(int idExpense) {
      this.arguments.put("id_expense", idExpense);
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public NavigateToViewExpenseFragmentFromCashflow setIdExpense(int idExpense) {
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
      return R.id.navigate_to_viewExpenseFragment_from_cashflow;
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
      NavigateToViewExpenseFragmentFromCashflow that = (NavigateToViewExpenseFragmentFromCashflow) object;
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
      return "NavigateToViewExpenseFragmentFromCashflow(actionId=" + getActionId() + "){"
          + "idExpense=" + getIdExpense()
          + "}";
    }
  }
}
