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

public class SavingsFragmentDirections {
  private SavingsFragmentDirections() {
  }

  @NonNull
  public static NavigateToViewSavingCatFromSavings navigateToViewSavingCatFromSavings(
      int idSavings) {
    return new NavigateToViewSavingCatFromSavings(idSavings);
  }

  @NonNull
  public static NavigateToViewDebtFromSavings navigateToViewDebtFromSavings(int idDebt) {
    return new NavigateToViewDebtFromSavings(idDebt);
  }

  @NonNull
  public static NavDirections actionSavingsFragmentToSelectExpenseTypeFragment2() {
    return new ActionOnlyNavDirections(R.id.action_savingsFragment_to_selectExpenseTypeFragment2);
  }

  @NonNull
  public static ActionSavingsFragmentToEditDebtFragment actionSavingsFragmentToEditDebtFragment() {
    return new ActionSavingsFragmentToEditDebtFragment();
  }

  @NonNull
  public static ActionSavingsFragmentToEditSavingCatFragment actionSavingsFragmentToEditSavingCatFragment(
      ) {
    return new ActionSavingsFragmentToEditSavingCatFragment();
  }

  public static class NavigateToViewSavingCatFromSavings implements NavDirections {
    private final HashMap arguments = new HashMap();

    @SuppressWarnings("unchecked")
    private NavigateToViewSavingCatFromSavings(int idSavings) {
      this.arguments.put("id_savings", idSavings);
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public NavigateToViewSavingCatFromSavings setIdSavings(int idSavings) {
      this.arguments.put("id_savings", idSavings);
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    @NonNull
    public Bundle getArguments() {
      Bundle __result = new Bundle();
      if (arguments.containsKey("id_savings")) {
        int idSavings = (int) arguments.get("id_savings");
        __result.putInt("id_savings", idSavings);
      }
      return __result;
    }

    @Override
    public int getActionId() {
      return R.id.navigate_to_viewSavingCat_from_savings;
    }

    @SuppressWarnings("unchecked")
    public int getIdSavings() {
      return (int) arguments.get("id_savings");
    }

    @Override
    public boolean equals(Object object) {
      if (this == object) {
          return true;
      }
      if (object == null || getClass() != object.getClass()) {
          return false;
      }
      NavigateToViewSavingCatFromSavings that = (NavigateToViewSavingCatFromSavings) object;
      if (arguments.containsKey("id_savings") != that.arguments.containsKey("id_savings")) {
        return false;
      }
      if (getIdSavings() != that.getIdSavings()) {
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
      result = 31 * result + getIdSavings();
      result = 31 * result + getActionId();
      return result;
    }

    @Override
    public String toString() {
      return "NavigateToViewSavingCatFromSavings(actionId=" + getActionId() + "){"
          + "idSavings=" + getIdSavings()
          + "}";
    }
  }

  public static class NavigateToViewDebtFromSavings implements NavDirections {
    private final HashMap arguments = new HashMap();

    @SuppressWarnings("unchecked")
    private NavigateToViewDebtFromSavings(int idDebt) {
      this.arguments.put("id_debt", idDebt);
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public NavigateToViewDebtFromSavings setIdDebt(int idDebt) {
      this.arguments.put("id_debt", idDebt);
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    @NonNull
    public Bundle getArguments() {
      Bundle __result = new Bundle();
      if (arguments.containsKey("id_debt")) {
        int idDebt = (int) arguments.get("id_debt");
        __result.putInt("id_debt", idDebt);
      }
      return __result;
    }

    @Override
    public int getActionId() {
      return R.id.navigate_to_viewDebt_from_savings;
    }

    @SuppressWarnings("unchecked")
    public int getIdDebt() {
      return (int) arguments.get("id_debt");
    }

    @Override
    public boolean equals(Object object) {
      if (this == object) {
          return true;
      }
      if (object == null || getClass() != object.getClass()) {
          return false;
      }
      NavigateToViewDebtFromSavings that = (NavigateToViewDebtFromSavings) object;
      if (arguments.containsKey("id_debt") != that.arguments.containsKey("id_debt")) {
        return false;
      }
      if (getIdDebt() != that.getIdDebt()) {
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
      result = 31 * result + getIdDebt();
      result = 31 * result + getActionId();
      return result;
    }

    @Override
    public String toString() {
      return "NavigateToViewDebtFromSavings(actionId=" + getActionId() + "){"
          + "idDebt=" + getIdDebt()
          + "}";
    }
  }

  public static class ActionSavingsFragmentToEditDebtFragment implements NavDirections {
    private final HashMap arguments = new HashMap();

    private ActionSavingsFragmentToEditDebtFragment() {
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public ActionSavingsFragmentToEditDebtFragment setIdDebtEdit(int idDebtEdit) {
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
      } else {
        __result.putInt("id_debt_edit", 0);
      }
      return __result;
    }

    @Override
    public int getActionId() {
      return R.id.action_savingsFragment_to_editDebtFragment;
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
      ActionSavingsFragmentToEditDebtFragment that = (ActionSavingsFragmentToEditDebtFragment) object;
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
      return "ActionSavingsFragmentToEditDebtFragment(actionId=" + getActionId() + "){"
          + "idDebtEdit=" + getIdDebtEdit()
          + "}";
    }
  }

  public static class ActionSavingsFragmentToEditSavingCatFragment implements NavDirections {
    private final HashMap arguments = new HashMap();

    private ActionSavingsFragmentToEditSavingCatFragment() {
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public ActionSavingsFragmentToEditSavingCatFragment setIdSavingCat(int idSavingCat) {
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
      } else {
        __result.putInt("id_saving_cat", 0);
      }
      return __result;
    }

    @Override
    public int getActionId() {
      return R.id.action_savingsFragment_to_editSavingCatFragment;
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
      ActionSavingsFragmentToEditSavingCatFragment that = (ActionSavingsFragmentToEditSavingCatFragment) object;
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
      return "ActionSavingsFragmentToEditSavingCatFragment(actionId=" + getActionId() + "){"
          + "idSavingCat=" + getIdSavingCat()
          + "}";
    }
  }
}
