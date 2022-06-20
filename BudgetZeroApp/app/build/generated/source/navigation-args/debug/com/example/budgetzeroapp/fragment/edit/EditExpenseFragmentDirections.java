package com.example.budgetzeroapp.fragment.edit;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.navigation.NavDirections;
import com.example.budgetzeroapp.R;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;

public class EditExpenseFragmentDirections {
  private EditExpenseFragmentDirections() {
  }

  @NonNull
  public static ActionEditExpenseFragmentToCashFlowFragment actionEditExpenseFragmentToCashFlowFragment(
      int idExpense) {
    return new ActionEditExpenseFragmentToCashFlowFragment(idExpense);
  }

  public static class ActionEditExpenseFragmentToCashFlowFragment implements NavDirections {
    private final HashMap arguments = new HashMap();

    @SuppressWarnings("unchecked")
    private ActionEditExpenseFragmentToCashFlowFragment(int idExpense) {
      this.arguments.put("id_expense", idExpense);
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public ActionEditExpenseFragmentToCashFlowFragment setIdExpense(int idExpense) {
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
      return R.id.action_editExpenseFragment_to_cashFlowFragment;
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
      ActionEditExpenseFragmentToCashFlowFragment that = (ActionEditExpenseFragmentToCashFlowFragment) object;
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
      return "ActionEditExpenseFragmentToCashFlowFragment(actionId=" + getActionId() + "){"
          + "idExpense=" + getIdExpense()
          + "}";
    }
  }
}
