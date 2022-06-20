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

public class SelectExpenseTypeFragmentDirections {
  private SelectExpenseTypeFragmentDirections() {
  }

  @NonNull
  public static ActionSelectExpenseTypeFragmentToEditExpenseFragment3 actionSelectExpenseTypeFragmentToEditExpenseFragment3(
      int typeExpense) {
    return new ActionSelectExpenseTypeFragmentToEditExpenseFragment3(typeExpense);
  }

  public static class ActionSelectExpenseTypeFragmentToEditExpenseFragment3 implements NavDirections {
    private final HashMap arguments = new HashMap();

    @SuppressWarnings("unchecked")
    private ActionSelectExpenseTypeFragmentToEditExpenseFragment3(int typeExpense) {
      this.arguments.put("type_expense", typeExpense);
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public ActionSelectExpenseTypeFragmentToEditExpenseFragment3 setIdExpenseEdit(
        int idExpenseEdit) {
      this.arguments.put("id_expense_edit", idExpenseEdit);
      return this;
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public ActionSelectExpenseTypeFragmentToEditExpenseFragment3 setTypeExpense(int typeExpense) {
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
      } else {
        __result.putInt("id_expense_edit", 0);
      }
      if (arguments.containsKey("type_expense")) {
        int typeExpense = (int) arguments.get("type_expense");
        __result.putInt("type_expense", typeExpense);
      }
      return __result;
    }

    @Override
    public int getActionId() {
      return R.id.action_selectExpenseTypeFragment_to_editExpenseFragment3;
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
      ActionSelectExpenseTypeFragmentToEditExpenseFragment3 that = (ActionSelectExpenseTypeFragmentToEditExpenseFragment3) object;
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
      return "ActionSelectExpenseTypeFragmentToEditExpenseFragment3(actionId=" + getActionId() + "){"
          + "idExpenseEdit=" + getIdExpenseEdit()
          + ", typeExpense=" + getTypeExpense()
          + "}";
    }
  }
}
