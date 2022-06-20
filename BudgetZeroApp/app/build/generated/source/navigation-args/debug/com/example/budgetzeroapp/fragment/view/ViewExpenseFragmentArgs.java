package com.example.budgetzeroapp.fragment.view;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.lifecycle.SavedStateHandle;
import androidx.navigation.NavArgs;
import java.lang.IllegalArgumentException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;

public class ViewExpenseFragmentArgs implements NavArgs {
  private final HashMap arguments = new HashMap();

  private ViewExpenseFragmentArgs() {
  }

  @SuppressWarnings("unchecked")
  private ViewExpenseFragmentArgs(HashMap argumentsMap) {
    this.arguments.putAll(argumentsMap);
  }

  @NonNull
  @SuppressWarnings("unchecked")
  public static ViewExpenseFragmentArgs fromBundle(@NonNull Bundle bundle) {
    ViewExpenseFragmentArgs __result = new ViewExpenseFragmentArgs();
    bundle.setClassLoader(ViewExpenseFragmentArgs.class.getClassLoader());
    if (bundle.containsKey("id_expense")) {
      int idExpense;
      idExpense = bundle.getInt("id_expense");
      __result.arguments.put("id_expense", idExpense);
    } else {
      throw new IllegalArgumentException("Required argument \"id_expense\" is missing and does not have an android:defaultValue");
    }
    return __result;
  }

  @NonNull
  @SuppressWarnings("unchecked")
  public static ViewExpenseFragmentArgs fromSavedStateHandle(
      @NonNull SavedStateHandle savedStateHandle) {
    ViewExpenseFragmentArgs __result = new ViewExpenseFragmentArgs();
    if (savedStateHandle.contains("id_expense")) {
      int idExpense;
      idExpense = savedStateHandle.get("id_expense");
      __result.arguments.put("id_expense", idExpense);
    } else {
      throw new IllegalArgumentException("Required argument \"id_expense\" is missing and does not have an android:defaultValue");
    }
    return __result;
  }

  @SuppressWarnings("unchecked")
  public int getIdExpense() {
    return (int) arguments.get("id_expense");
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public Bundle toBundle() {
    Bundle __result = new Bundle();
    if (arguments.containsKey("id_expense")) {
      int idExpense = (int) arguments.get("id_expense");
      __result.putInt("id_expense", idExpense);
    }
    return __result;
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public SavedStateHandle toSavedStateHandle() {
    SavedStateHandle __result = new SavedStateHandle();
    if (arguments.containsKey("id_expense")) {
      int idExpense = (int) arguments.get("id_expense");
      __result.set("id_expense", idExpense);
    }
    return __result;
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) {
        return true;
    }
    if (object == null || getClass() != object.getClass()) {
        return false;
    }
    ViewExpenseFragmentArgs that = (ViewExpenseFragmentArgs) object;
    if (arguments.containsKey("id_expense") != that.arguments.containsKey("id_expense")) {
      return false;
    }
    if (getIdExpense() != that.getIdExpense()) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    int result = 1;
    result = 31 * result + getIdExpense();
    return result;
  }

  @Override
  public String toString() {
    return "ViewExpenseFragmentArgs{"
        + "idExpense=" + getIdExpense()
        + "}";
  }

  public static final class Builder {
    private final HashMap arguments = new HashMap();

    @SuppressWarnings("unchecked")
    public Builder(@NonNull ViewExpenseFragmentArgs original) {
      this.arguments.putAll(original.arguments);
    }

    @SuppressWarnings("unchecked")
    public Builder(int idExpense) {
      this.arguments.put("id_expense", idExpense);
    }

    @NonNull
    public ViewExpenseFragmentArgs build() {
      ViewExpenseFragmentArgs result = new ViewExpenseFragmentArgs(arguments);
      return result;
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public Builder setIdExpense(int idExpense) {
      this.arguments.put("id_expense", idExpense);
      return this;
    }

    @SuppressWarnings({"unchecked","GetterOnBuilder"})
    public int getIdExpense() {
      return (int) arguments.get("id_expense");
    }
  }
}
