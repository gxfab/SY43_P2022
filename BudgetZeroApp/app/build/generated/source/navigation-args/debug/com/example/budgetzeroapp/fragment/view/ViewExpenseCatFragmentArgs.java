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

public class ViewExpenseCatFragmentArgs implements NavArgs {
  private final HashMap arguments = new HashMap();

  private ViewExpenseCatFragmentArgs() {
  }

  @SuppressWarnings("unchecked")
  private ViewExpenseCatFragmentArgs(HashMap argumentsMap) {
    this.arguments.putAll(argumentsMap);
  }

  @NonNull
  @SuppressWarnings("unchecked")
  public static ViewExpenseCatFragmentArgs fromBundle(@NonNull Bundle bundle) {
    ViewExpenseCatFragmentArgs __result = new ViewExpenseCatFragmentArgs();
    bundle.setClassLoader(ViewExpenseCatFragmentArgs.class.getClassLoader());
    if (bundle.containsKey("id_expense_cat")) {
      int idExpenseCat;
      idExpenseCat = bundle.getInt("id_expense_cat");
      __result.arguments.put("id_expense_cat", idExpenseCat);
    } else {
      throw new IllegalArgumentException("Required argument \"id_expense_cat\" is missing and does not have an android:defaultValue");
    }
    return __result;
  }

  @NonNull
  @SuppressWarnings("unchecked")
  public static ViewExpenseCatFragmentArgs fromSavedStateHandle(
      @NonNull SavedStateHandle savedStateHandle) {
    ViewExpenseCatFragmentArgs __result = new ViewExpenseCatFragmentArgs();
    if (savedStateHandle.contains("id_expense_cat")) {
      int idExpenseCat;
      idExpenseCat = savedStateHandle.get("id_expense_cat");
      __result.arguments.put("id_expense_cat", idExpenseCat);
    } else {
      throw new IllegalArgumentException("Required argument \"id_expense_cat\" is missing and does not have an android:defaultValue");
    }
    return __result;
  }

  @SuppressWarnings("unchecked")
  public int getIdExpenseCat() {
    return (int) arguments.get("id_expense_cat");
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public Bundle toBundle() {
    Bundle __result = new Bundle();
    if (arguments.containsKey("id_expense_cat")) {
      int idExpenseCat = (int) arguments.get("id_expense_cat");
      __result.putInt("id_expense_cat", idExpenseCat);
    }
    return __result;
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public SavedStateHandle toSavedStateHandle() {
    SavedStateHandle __result = new SavedStateHandle();
    if (arguments.containsKey("id_expense_cat")) {
      int idExpenseCat = (int) arguments.get("id_expense_cat");
      __result.set("id_expense_cat", idExpenseCat);
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
    ViewExpenseCatFragmentArgs that = (ViewExpenseCatFragmentArgs) object;
    if (arguments.containsKey("id_expense_cat") != that.arguments.containsKey("id_expense_cat")) {
      return false;
    }
    if (getIdExpenseCat() != that.getIdExpenseCat()) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    int result = 1;
    result = 31 * result + getIdExpenseCat();
    return result;
  }

  @Override
  public String toString() {
    return "ViewExpenseCatFragmentArgs{"
        + "idExpenseCat=" + getIdExpenseCat()
        + "}";
  }

  public static final class Builder {
    private final HashMap arguments = new HashMap();

    @SuppressWarnings("unchecked")
    public Builder(@NonNull ViewExpenseCatFragmentArgs original) {
      this.arguments.putAll(original.arguments);
    }

    @SuppressWarnings("unchecked")
    public Builder(int idExpenseCat) {
      this.arguments.put("id_expense_cat", idExpenseCat);
    }

    @NonNull
    public ViewExpenseCatFragmentArgs build() {
      ViewExpenseCatFragmentArgs result = new ViewExpenseCatFragmentArgs(arguments);
      return result;
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public Builder setIdExpenseCat(int idExpenseCat) {
      this.arguments.put("id_expense_cat", idExpenseCat);
      return this;
    }

    @SuppressWarnings({"unchecked","GetterOnBuilder"})
    public int getIdExpenseCat() {
      return (int) arguments.get("id_expense_cat");
    }
  }
}
