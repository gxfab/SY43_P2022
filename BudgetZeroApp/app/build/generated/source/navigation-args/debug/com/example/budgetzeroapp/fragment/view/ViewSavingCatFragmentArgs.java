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

public class ViewSavingCatFragmentArgs implements NavArgs {
  private final HashMap arguments = new HashMap();

  private ViewSavingCatFragmentArgs() {
  }

  @SuppressWarnings("unchecked")
  private ViewSavingCatFragmentArgs(HashMap argumentsMap) {
    this.arguments.putAll(argumentsMap);
  }

  @NonNull
  @SuppressWarnings("unchecked")
  public static ViewSavingCatFragmentArgs fromBundle(@NonNull Bundle bundle) {
    ViewSavingCatFragmentArgs __result = new ViewSavingCatFragmentArgs();
    bundle.setClassLoader(ViewSavingCatFragmentArgs.class.getClassLoader());
    if (bundle.containsKey("id_savings")) {
      int idSavings;
      idSavings = bundle.getInt("id_savings");
      __result.arguments.put("id_savings", idSavings);
    } else {
      throw new IllegalArgumentException("Required argument \"id_savings\" is missing and does not have an android:defaultValue");
    }
    return __result;
  }

  @NonNull
  @SuppressWarnings("unchecked")
  public static ViewSavingCatFragmentArgs fromSavedStateHandle(
      @NonNull SavedStateHandle savedStateHandle) {
    ViewSavingCatFragmentArgs __result = new ViewSavingCatFragmentArgs();
    if (savedStateHandle.contains("id_savings")) {
      int idSavings;
      idSavings = savedStateHandle.get("id_savings");
      __result.arguments.put("id_savings", idSavings);
    } else {
      throw new IllegalArgumentException("Required argument \"id_savings\" is missing and does not have an android:defaultValue");
    }
    return __result;
  }

  @SuppressWarnings("unchecked")
  public int getIdSavings() {
    return (int) arguments.get("id_savings");
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public Bundle toBundle() {
    Bundle __result = new Bundle();
    if (arguments.containsKey("id_savings")) {
      int idSavings = (int) arguments.get("id_savings");
      __result.putInt("id_savings", idSavings);
    }
    return __result;
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public SavedStateHandle toSavedStateHandle() {
    SavedStateHandle __result = new SavedStateHandle();
    if (arguments.containsKey("id_savings")) {
      int idSavings = (int) arguments.get("id_savings");
      __result.set("id_savings", idSavings);
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
    ViewSavingCatFragmentArgs that = (ViewSavingCatFragmentArgs) object;
    if (arguments.containsKey("id_savings") != that.arguments.containsKey("id_savings")) {
      return false;
    }
    if (getIdSavings() != that.getIdSavings()) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    int result = 1;
    result = 31 * result + getIdSavings();
    return result;
  }

  @Override
  public String toString() {
    return "ViewSavingCatFragmentArgs{"
        + "idSavings=" + getIdSavings()
        + "}";
  }

  public static final class Builder {
    private final HashMap arguments = new HashMap();

    @SuppressWarnings("unchecked")
    public Builder(@NonNull ViewSavingCatFragmentArgs original) {
      this.arguments.putAll(original.arguments);
    }

    @SuppressWarnings("unchecked")
    public Builder(int idSavings) {
      this.arguments.put("id_savings", idSavings);
    }

    @NonNull
    public ViewSavingCatFragmentArgs build() {
      ViewSavingCatFragmentArgs result = new ViewSavingCatFragmentArgs(arguments);
      return result;
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public Builder setIdSavings(int idSavings) {
      this.arguments.put("id_savings", idSavings);
      return this;
    }

    @SuppressWarnings({"unchecked","GetterOnBuilder"})
    public int getIdSavings() {
      return (int) arguments.get("id_savings");
    }
  }
}
