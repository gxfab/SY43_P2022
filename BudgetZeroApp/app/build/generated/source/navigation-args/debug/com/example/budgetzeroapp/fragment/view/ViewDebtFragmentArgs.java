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

public class ViewDebtFragmentArgs implements NavArgs {
  private final HashMap arguments = new HashMap();

  private ViewDebtFragmentArgs() {
  }

  @SuppressWarnings("unchecked")
  private ViewDebtFragmentArgs(HashMap argumentsMap) {
    this.arguments.putAll(argumentsMap);
  }

  @NonNull
  @SuppressWarnings("unchecked")
  public static ViewDebtFragmentArgs fromBundle(@NonNull Bundle bundle) {
    ViewDebtFragmentArgs __result = new ViewDebtFragmentArgs();
    bundle.setClassLoader(ViewDebtFragmentArgs.class.getClassLoader());
    if (bundle.containsKey("id_debt")) {
      int idDebt;
      idDebt = bundle.getInt("id_debt");
      __result.arguments.put("id_debt", idDebt);
    } else {
      throw new IllegalArgumentException("Required argument \"id_debt\" is missing and does not have an android:defaultValue");
    }
    return __result;
  }

  @NonNull
  @SuppressWarnings("unchecked")
  public static ViewDebtFragmentArgs fromSavedStateHandle(
      @NonNull SavedStateHandle savedStateHandle) {
    ViewDebtFragmentArgs __result = new ViewDebtFragmentArgs();
    if (savedStateHandle.contains("id_debt")) {
      int idDebt;
      idDebt = savedStateHandle.get("id_debt");
      __result.arguments.put("id_debt", idDebt);
    } else {
      throw new IllegalArgumentException("Required argument \"id_debt\" is missing and does not have an android:defaultValue");
    }
    return __result;
  }

  @SuppressWarnings("unchecked")
  public int getIdDebt() {
    return (int) arguments.get("id_debt");
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public Bundle toBundle() {
    Bundle __result = new Bundle();
    if (arguments.containsKey("id_debt")) {
      int idDebt = (int) arguments.get("id_debt");
      __result.putInt("id_debt", idDebt);
    }
    return __result;
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public SavedStateHandle toSavedStateHandle() {
    SavedStateHandle __result = new SavedStateHandle();
    if (arguments.containsKey("id_debt")) {
      int idDebt = (int) arguments.get("id_debt");
      __result.set("id_debt", idDebt);
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
    ViewDebtFragmentArgs that = (ViewDebtFragmentArgs) object;
    if (arguments.containsKey("id_debt") != that.arguments.containsKey("id_debt")) {
      return false;
    }
    if (getIdDebt() != that.getIdDebt()) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    int result = 1;
    result = 31 * result + getIdDebt();
    return result;
  }

  @Override
  public String toString() {
    return "ViewDebtFragmentArgs{"
        + "idDebt=" + getIdDebt()
        + "}";
  }

  public static final class Builder {
    private final HashMap arguments = new HashMap();

    @SuppressWarnings("unchecked")
    public Builder(@NonNull ViewDebtFragmentArgs original) {
      this.arguments.putAll(original.arguments);
    }

    @SuppressWarnings("unchecked")
    public Builder(int idDebt) {
      this.arguments.put("id_debt", idDebt);
    }

    @NonNull
    public ViewDebtFragmentArgs build() {
      ViewDebtFragmentArgs result = new ViewDebtFragmentArgs(arguments);
      return result;
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public Builder setIdDebt(int idDebt) {
      this.arguments.put("id_debt", idDebt);
      return this;
    }

    @SuppressWarnings({"unchecked","GetterOnBuilder"})
    public int getIdDebt() {
      return (int) arguments.get("id_debt");
    }
  }
}
