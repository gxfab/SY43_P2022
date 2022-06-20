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

public class EditExpenseCatFragmentArgs implements NavArgs {
  private final HashMap arguments = new HashMap();

  private EditExpenseCatFragmentArgs() {
  }

  @SuppressWarnings("unchecked")
  private EditExpenseCatFragmentArgs(HashMap argumentsMap) {
    this.arguments.putAll(argumentsMap);
  }

  @NonNull
  @SuppressWarnings("unchecked")
  public static EditExpenseCatFragmentArgs fromBundle(@NonNull Bundle bundle) {
    EditExpenseCatFragmentArgs __result = new EditExpenseCatFragmentArgs();
    bundle.setClassLoader(EditExpenseCatFragmentArgs.class.getClassLoader());
    if (bundle.containsKey("id_expense_cat_edit")) {
      int idExpenseCatEdit;
      idExpenseCatEdit = bundle.getInt("id_expense_cat_edit");
      __result.arguments.put("id_expense_cat_edit", idExpenseCatEdit);
    } else {
      throw new IllegalArgumentException("Required argument \"id_expense_cat_edit\" is missing and does not have an android:defaultValue");
    }
    return __result;
  }

  @NonNull
  @SuppressWarnings("unchecked")
  public static EditExpenseCatFragmentArgs fromSavedStateHandle(
      @NonNull SavedStateHandle savedStateHandle) {
    EditExpenseCatFragmentArgs __result = new EditExpenseCatFragmentArgs();
    if (savedStateHandle.contains("id_expense_cat_edit")) {
      int idExpenseCatEdit;
      idExpenseCatEdit = savedStateHandle.get("id_expense_cat_edit");
      __result.arguments.put("id_expense_cat_edit", idExpenseCatEdit);
    } else {
      throw new IllegalArgumentException("Required argument \"id_expense_cat_edit\" is missing and does not have an android:defaultValue");
    }
    return __result;
  }

  @SuppressWarnings("unchecked")
  public int getIdExpenseCatEdit() {
    return (int) arguments.get("id_expense_cat_edit");
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public Bundle toBundle() {
    Bundle __result = new Bundle();
    if (arguments.containsKey("id_expense_cat_edit")) {
      int idExpenseCatEdit = (int) arguments.get("id_expense_cat_edit");
      __result.putInt("id_expense_cat_edit", idExpenseCatEdit);
    }
    return __result;
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public SavedStateHandle toSavedStateHandle() {
    SavedStateHandle __result = new SavedStateHandle();
    if (arguments.containsKey("id_expense_cat_edit")) {
      int idExpenseCatEdit = (int) arguments.get("id_expense_cat_edit");
      __result.set("id_expense_cat_edit", idExpenseCatEdit);
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
    EditExpenseCatFragmentArgs that = (EditExpenseCatFragmentArgs) object;
    if (arguments.containsKey("id_expense_cat_edit") != that.arguments.containsKey("id_expense_cat_edit")) {
      return false;
    }
    if (getIdExpenseCatEdit() != that.getIdExpenseCatEdit()) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    int result = 1;
    result = 31 * result + getIdExpenseCatEdit();
    return result;
  }

  @Override
  public String toString() {
    return "EditExpenseCatFragmentArgs{"
        + "idExpenseCatEdit=" + getIdExpenseCatEdit()
        + "}";
  }

  public static final class Builder {
    private final HashMap arguments = new HashMap();

    @SuppressWarnings("unchecked")
    public Builder(@NonNull EditExpenseCatFragmentArgs original) {
      this.arguments.putAll(original.arguments);
    }

    @SuppressWarnings("unchecked")
    public Builder(int idExpenseCatEdit) {
      this.arguments.put("id_expense_cat_edit", idExpenseCatEdit);
    }

    @NonNull
    public EditExpenseCatFragmentArgs build() {
      EditExpenseCatFragmentArgs result = new EditExpenseCatFragmentArgs(arguments);
      return result;
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public Builder setIdExpenseCatEdit(int idExpenseCatEdit) {
      this.arguments.put("id_expense_cat_edit", idExpenseCatEdit);
      return this;
    }

    @SuppressWarnings({"unchecked","GetterOnBuilder"})
    public int getIdExpenseCatEdit() {
      return (int) arguments.get("id_expense_cat_edit");
    }
  }
}
