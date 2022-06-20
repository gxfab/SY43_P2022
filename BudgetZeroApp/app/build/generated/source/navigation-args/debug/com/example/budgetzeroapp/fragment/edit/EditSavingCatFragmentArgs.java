package com.example.budgetzeroapp.fragment.edit;

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

public class EditSavingCatFragmentArgs implements NavArgs {
  private final HashMap arguments = new HashMap();

  private EditSavingCatFragmentArgs() {
  }

  @SuppressWarnings("unchecked")
  private EditSavingCatFragmentArgs(HashMap argumentsMap) {
    this.arguments.putAll(argumentsMap);
  }

  @NonNull
  @SuppressWarnings("unchecked")
  public static EditSavingCatFragmentArgs fromBundle(@NonNull Bundle bundle) {
    EditSavingCatFragmentArgs __result = new EditSavingCatFragmentArgs();
    bundle.setClassLoader(EditSavingCatFragmentArgs.class.getClassLoader());
    if (bundle.containsKey("id_saving_cat")) {
      int idSavingCat;
      idSavingCat = bundle.getInt("id_saving_cat");
      __result.arguments.put("id_saving_cat", idSavingCat);
    } else {
      throw new IllegalArgumentException("Required argument \"id_saving_cat\" is missing and does not have an android:defaultValue");
    }
    return __result;
  }

  @NonNull
  @SuppressWarnings("unchecked")
  public static EditSavingCatFragmentArgs fromSavedStateHandle(
      @NonNull SavedStateHandle savedStateHandle) {
    EditSavingCatFragmentArgs __result = new EditSavingCatFragmentArgs();
    if (savedStateHandle.contains("id_saving_cat")) {
      int idSavingCat;
      idSavingCat = savedStateHandle.get("id_saving_cat");
      __result.arguments.put("id_saving_cat", idSavingCat);
    } else {
      throw new IllegalArgumentException("Required argument \"id_saving_cat\" is missing and does not have an android:defaultValue");
    }
    return __result;
  }

  @SuppressWarnings("unchecked")
  public int getIdSavingCat() {
    return (int) arguments.get("id_saving_cat");
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public Bundle toBundle() {
    Bundle __result = new Bundle();
    if (arguments.containsKey("id_saving_cat")) {
      int idSavingCat = (int) arguments.get("id_saving_cat");
      __result.putInt("id_saving_cat", idSavingCat);
    }
    return __result;
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public SavedStateHandle toSavedStateHandle() {
    SavedStateHandle __result = new SavedStateHandle();
    if (arguments.containsKey("id_saving_cat")) {
      int idSavingCat = (int) arguments.get("id_saving_cat");
      __result.set("id_saving_cat", idSavingCat);
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
    EditSavingCatFragmentArgs that = (EditSavingCatFragmentArgs) object;
    if (arguments.containsKey("id_saving_cat") != that.arguments.containsKey("id_saving_cat")) {
      return false;
    }
    if (getIdSavingCat() != that.getIdSavingCat()) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    int result = 1;
    result = 31 * result + getIdSavingCat();
    return result;
  }

  @Override
  public String toString() {
    return "EditSavingCatFragmentArgs{"
        + "idSavingCat=" + getIdSavingCat()
        + "}";
  }

  public static final class Builder {
    private final HashMap arguments = new HashMap();

    @SuppressWarnings("unchecked")
    public Builder(@NonNull EditSavingCatFragmentArgs original) {
      this.arguments.putAll(original.arguments);
    }

    @SuppressWarnings("unchecked")
    public Builder(int idSavingCat) {
      this.arguments.put("id_saving_cat", idSavingCat);
    }

    @NonNull
    public EditSavingCatFragmentArgs build() {
      EditSavingCatFragmentArgs result = new EditSavingCatFragmentArgs(arguments);
      return result;
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public Builder setIdSavingCat(int idSavingCat) {
      this.arguments.put("id_saving_cat", idSavingCat);
      return this;
    }

    @SuppressWarnings({"unchecked","GetterOnBuilder"})
    public int getIdSavingCat() {
      return (int) arguments.get("id_saving_cat");
    }
  }
}
