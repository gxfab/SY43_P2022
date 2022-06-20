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

public class ViewExpenseFragmentDirections {
  private ViewExpenseFragmentDirections() {
  }

  @NonNull
  public static NavigateToEditExpenseFromExpense navigateToEditExpenseFromExpense(int idExpenseEdit,
      int typeExpense) {
    return new NavigateToEditExpenseFromExpense(idExpenseEdit, typeExpense);
  }

  public static class NavigateToEditExpenseFromExpense implements NavDirections {
    private final HashMap arguments = new HashMap();

    @SuppressWarnings("unchecked")
    private NavigateToEditExpenseFromExpense(int idExpenseEdit, int typeExpense) {
      this.arguments.put("id_expense_edit", idExpenseEdit);
      this.arguments.put("type_expense", typeExpense);
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public NavigateToEditExpenseFromExpense setIdExpenseEdit(int idExpenseEdit) {
      this.arguments.put("id_expense_edit", idExpenseEdit);
      return this;
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public NavigateToEditExpenseFromExpense setTypeExpense(int typeExpense) {
      this.arguments.put("type_expense", typeExpense);
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    @NonNull
    public Bundle getArguments() {
      Bundle __result = new Bundle();
      if (arguments.containsKey("id_expense_edit")) {
        int idExpenseEdit = (int) arguments.get("id_expense_edit");
        __result.putInt("id_expense_edit", idExpenseEdit);
      }
      if (arguments.containsKey("type_expense")) {
        int typeExpense = (int) arguments.get("type_expense");
        __result.putInt("type_expense", typeExpense);
      }
      return __result;
    }

    @Override
    public int getActionId() {
      return R.id.navigate_to_editExpense_from_expense;
    }

    @SuppressWarnings("unchecked")
    public int getIdExpenseEdit() {
      return (int) arguments.get("id_expense_edit");
    }

    @SuppressWarnings("unchecked")
    public int getTypeExpense() {
      return (int) arguments.get("type_expense");
    }

    @Override
    public boolean equals(Object object) {
      if (this == object) {
          return true;
      }
      if (object == null || getClass() != object.getClass()) {
          return false;
      }
      NavigateToEditExpenseFromExpense that = (NavigateToEditExpenseFromExpense) object;
      if (arguments.containsKey("id_expense_edit") != that.arguments.containsKey("id_expense_edit")) {
        return false;
      }
      if (getIdExpenseEdit() != that.getIdExpenseEdit()) {
        return false;
      }
      if (arguments.containsKey("type_expense") != that.arguments.containsKey("type_expense")) {
        return false;
      }
      if (getTypeExpense() != that.getTypeExpense()) {
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
      result = 31 * result + getIdExpenseEdit();
      result = 31 * result + getTypeExpense();
      result = 31 * result + getActionId();
      return result;
    }

    @Override
    public String toString() {
      return "NavigateToEditExpenseFromExpense(actionId=" + getActionId() + "){"
          + "idExpenseEdit=" + getIdExpenseEdit()
          + ", typeExpense=" + getTypeExpense()
          + "}";
    }
  }
}
