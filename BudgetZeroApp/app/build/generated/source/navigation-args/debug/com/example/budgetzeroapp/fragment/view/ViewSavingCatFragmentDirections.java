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

public class ViewSavingCatFragmentDirections {
  private ViewSavingCatFragmentDirections() {
  }

  @NonNull
  public static NavigateToEditSavingCatFromSavingCat navigateToEditSavingCatFromSavingCat(
      int idSavingCat) {
    return new NavigateToEditSavingCatFromSavingCat(idSavingCat);
  }

  @NonNull
  public static ActionViewSavingCatFragmentToViewExpenseFragment actionViewSavingCatFragmentToViewExpenseFragment(
      int idExpense) {
    return new ActionViewSavingCatFragmentToViewExpenseFragment(idExpense);
  }

  public static class NavigateToEditSavingCatFromSavingCat implements NavDirections {
    private final HashMap arguments = new HashMap();

    @SuppressWarnings("unchecked")
    private NavigateToEditSavingCatFromSavingCat(int idSavingCat) {
      this.arguments.put("id_saving_cat", idSavingCat);
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public NavigateToEditSavingCatFromSavingCat setIdSavingCat(int idSavingCat) {
      this.arguments.put("id_saving_cat", idSavingCat);
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    @NonNull
    public Bundle getArguments() {
      Bundle __result = new Bundle();
      if (arguments.containsKey("id_saving_cat")) {
        int idSavingCat = (int) arguments.get("id_saving_cat");
        __result.putInt("id_saving_cat", idSavingCat);
      }
      return __result;
    }

    @Override
    public int getActionId() {
      return R.id.navigate_to_editSavingCat_from_saving_cat;
    }

    @SuppressWarnings("unchecked")
    public int getIdSavingCat() {
      return (int) arguments.get("id_saving_cat");
    }

    @Override
    public boolean equals(Object object) {
      if (this == object) {
          return true;
      }
      if (object == null || getClass() != object.getClass()) {
          return false;
      }
      NavigateToEditSavingCatFromSavingCat that = (NavigateToEditSavingCatFromSavingCat) object;
      if (arguments.containsKey("id_saving_cat") != that.arguments.containsKey("id_saving_cat")) {
        return false;
      }
      if (getIdSavingCat() != that.getIdSavingCat()) {
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
      result = 31 * result + getIdSavingCat();
      result = 31 * result + getActionId();
      return result;
    }

    @Override
    public String toString() {
      return "NavigateToEditSavingCatFromSavingCat(actionId=" + getActionId() + "){"
          + "idSavingCat=" + getIdSavingCat()
          + "}";
    }
  }

  public static class ActionViewSavingCatFragmentToViewExpenseFragment implements NavDirections {
    private final HashMap arguments = new HashMap();

    @SuppressWarnings("unchecked")
    private ActionViewSavingCatFragmentToViewExpenseFragment(int idExpense) {
      this.arguments.put("id_expense", idExpense);
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public ActionViewSavingCatFragmentToViewExpenseFragment setIdExpense(int idExpense) {
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
      return R.id.action_viewSavingCatFragment_to_viewExpenseFragment;
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
      ActionViewSavingCatFragmentToViewExpenseFragment that = (ActionViewSavingCatFragmentToViewExpenseFragment) object;
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
      return "ActionViewSavingCatFragmentToViewExpenseFragment(actionId=" + getActionId() + "){"
          + "idExpense=" + getIdExpense()
          + "}";
    }
  }
}
