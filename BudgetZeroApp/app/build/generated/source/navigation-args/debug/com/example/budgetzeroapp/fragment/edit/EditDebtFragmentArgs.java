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

public class EditDebtFragmentArgs implements NavArgs {
  private final HashMap arguments = new HashMap();

  private EditDebtFragmentArgs() {
  }

  @SuppressWarnings("unchecked")
  private EditDebtFragmentArgs(HashMap argumentsMap) {
    this.arguments.putAll(argumentsMap);
  }

  @NonNull
  @SuppressWarnings("unchecked")
  public static EditDebtFragmentArgs fromBundle(@NonNull Bundle bundle) {
    EditDebtFragmentArgs __result = new EditDebtFragmentArgs();
    bundle.setClassLoader(EditDebtFragmentArgs.class.getClassLoader());
    if (bundle.containsKey("id_debt_edit")) {
      int idDebtEdit;
      idDebtEdit = bundle.getInt("id_debt_edit");
      __result.arguments.put("id_debt_edit", idDebtEdit);
    } else {
      throw new IllegalArgumentException("Required argument \"id_debt_edit\" is missing and does not have an android:defaultValue");
    }
    return __result;
  }

  @NonNull
  @SuppressWarnings("unchecked")
  public static EditDebtFragmentArgs fromSavedStateHandle(
      @NonNull SavedStateHandle savedStateHandle) {
    EditDebtFragmentArgs __result = new EditDebtFragmentArgs();
    if (savedStateHandle.contains("id_debt_edit")) {
      int idDebtEdit;
      idDebtEdit = savedStateHandle.get("id_debt_edit");
      __result.arguments.put("id_debt_edit", idDebtEdit);
    } else {
      throw new IllegalArgumentException("Required argument \"id_debt_edit\" is missing and does not have an android:defaultValue");
    }
    return __result;
  }

  @SuppressWarnings("unchecked")
  public int getIdDebtEdit() {
    return (int) arguments.get("id_debt_edit");
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public Bundle toBundle() {
    Bundle __result = new Bundle();
    if (arguments.containsKey("id_debt_edit")) {
      int idDebtEdit = (int) arguments.get("id_debt_edit");
      __result.putInt("id_debt_edit", idDebtEdit);
    }
    return __result;
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public SavedStateHandle toSavedStateHandle() {
    SavedStateHandle __result = new SavedStateHandle();
    if (arguments.containsKey("id_debt_edit")) {
      int idDebtEdit = (int) arguments.get("id_debt_edit");
      __result.set("id_debt_edit", idDebtEdit);
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
    EditDebtFragmentArgs that = (EditDebtFragmentArgs) object;
    if (arguments.containsKey("id_debt_edit") != that.arguments.containsKey("id_debt_edit")) {
      return false;
    }
    if (getIdDebtEdit() != that.getIdDebtEdit()) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    int result = 1;
    result = 31 * result + getIdDebtEdit();
    return result;
  }

  @Override
  public String toString() {
    return "EditDebtFragmentArgs{"
        + "idDebtEdit=" + getIdDebtEdit()
        + "}";
  }

  public static final class Builder {
    private final HashMap arguments = new HashMap();

    @SuppressWarnings("unchecked")
    public Builder(@NonNull EditDebtFragmentArgs original) {
      this.arguments.putAll(original.arguments);
    }

    @SuppressWarnings("unchecked")
    public Builder(int idDebtEdit) {
      this.arguments.put("id_debt_edit", idDebtEdit);
    }

    @NonNull
    public EditDebtFragmentArgs build() {
      EditDebtFragmentArgs result = new EditDebtFragmentArgs(arguments);
      return result;
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public Builder setIdDebtEdit(int idDebtEdit) {
      this.arguments.put("id_debt_edit", idDebtEdit);
      return this;
    }

    @SuppressWarnings({"unchecked","GetterOnBuilder"})
    public int getIdDebtEdit() {
      return (int) arguments.get("id_debt_edit");
    }
  }
}
