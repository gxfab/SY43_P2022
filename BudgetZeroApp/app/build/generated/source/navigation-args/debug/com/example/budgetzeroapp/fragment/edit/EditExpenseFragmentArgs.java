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

public class EditExpenseFragmentArgs implements NavArgs {
  private final HashMap arguments = new HashMap();

  private EditExpenseFragmentArgs() {
  }

  @SuppressWarnings("unchecked")
  private EditExpenseFragmentArgs(HashMap argumentsMap) {
    this.arguments.putAll(argumentsMap);
  }

  @NonNull
  @SuppressWarnings("unchecked")
  public static EditExpenseFragmentArgs fromBundle(@NonNull Bundle bundle) {
    EditExpenseFragmentArgs __result = new EditExpenseFragmentArgs();
    bundle.setClassLoader(EditExpenseFragmentArgs.class.getClassLoader());
    if (bundle.containsKey("id_expense_edit")) {
      int idExpenseEdit;
      idExpenseEdit = bundle.getInt("id_expense_edit");
      __result.arguments.put("id_expense_edit", idExpenseEdit);
    } else {
      throw new IllegalArgumentException("Required argument \"id_expense_edit\" is missing and does not have an android:defaultValue");
    }
    if (bundle.containsKey("type_expense")) {
      int typeExpense;
      typeExpense = bundle.getInt("type_expense");
      __result.arguments.put("type_expense", typeExpense);
    } else {
      throw new IllegalArgumentException("Required argument \"type_expense\" is missing and does not have an android:defaultValue");
    }
    return __result;
  }

  @NonNull
  @SuppressWarnings("unchecked")
  public static EditExpenseFragmentArgs fromSavedStateHandle(
      @NonNull SavedStateHandle savedStateHandle) {
    EditExpenseFragmentArgs __result = new EditExpenseFragmentArgs();
    if (savedStateHandle.contains("id_expense_edit")) {
      int idExpenseEdit;
      idExpenseEdit = savedStateHandle.get("id_expense_edit");
      __result.arguments.put("id_expense_edit", idExpenseEdit);
    } else {
      throw new IllegalArgumentException("Required argument \"id_expense_edit\" is missing and does not have an android:defaultValue");
    }
    if (savedStateHandle.contains("type_expense")) {
      int typeExpense;
      typeExpense = savedStateHandle.get("type_expense");
      __result.arguments.put("type_expense", typeExpense);
    } else {
      throw new IllegalArgumentException("Required argument \"type_expense\" is missing and does not have an android:defaultValue");
    }
    return __result;
  }

  @SuppressWarnings("unchecked")
  public int getIdExpenseEdit() {
    return (int) arguments.get("id_expense_edit");
  }

  @SuppressWarnings("unchecked")
  public int getTypeExpense() {
    return (int) arguments.get("type_expense");
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public Bundle toBundle() {
    Bundle __result = new Bundle();
    if (arguments.containsKey("id_expense_edit")) {
      int idExpenseEdit = (int) arguments.get("id_expense_edit");
      __result.putInt("id_expense_edit", idExpenseEdit);
    }
    if (arguments.containsKey("type_expense")) {
      int typeExpense = (int) arguments.get("type_expense");
      __result.putInt("type_expense", typeExpense);
    }
    return __result;
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public SavedStateHandle toSavedStateHandle() {
    SavedStateHandle __result = new SavedStateHandle();
    if (arguments.containsKey("id_expense_edit")) {
      int idExpenseEdit = (int) arguments.get("id_expense_edit");
      __result.set("id_expense_edit", idExpenseEdit);
    }
    if (arguments.containsKey("type_expense")) {
      int typeExpense = (int) arguments.get("type_expense");
      __result.set("type_expense", typeExpense);
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
    EditExpenseFragmentArgs that = (EditExpenseFragmentArgs) object;
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
    return true;
  }

  @Override
  public int hashCode() {
    int result = 1;
    result = 31 * result + getIdExpenseEdit();
    result = 31 * result + getTypeExpense();
    return result;
  }

  @Override
  public String toString() {
    return "EditExpenseFragmentArgs{"
        + "idExpenseEdit=" + getIdExpenseEdit()
        + ", typeExpense=" + getTypeExpense()
        + "}";
  }

  public static final class Builder {
    private final HashMap arguments = new HashMap();

    @SuppressWarnings("unchecked")
    public Builder(@NonNull EditExpenseFragmentArgs original) {
      this.arguments.putAll(original.arguments);
    }

    @SuppressWarnings("unchecked")
    public Builder(int idExpenseEdit, int typeExpense) {
      this.arguments.put("id_expense_edit", idExpenseEdit);
      this.arguments.put("type_expense", typeExpense);
    }

    @NonNull
    public EditExpenseFragmentArgs build() {
      EditExpenseFragmentArgs result = new EditExpenseFragmentArgs(arguments);
      return result;
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public Builder setIdExpenseEdit(int idExpenseEdit) {
      this.arguments.put("id_expense_edit", idExpenseEdit);
      return this;
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public Builder setTypeExpense(int typeExpense) {
      this.arguments.put("type_expense", typeExpense);
      return this;
    }

    @SuppressWarnings({"unchecked","GetterOnBuilder"})
    public int getIdExpenseEdit() {
      return (int) arguments.get("id_expense_edit");
    }

    @SuppressWarnings({"unchecked","GetterOnBuilder"})
    public int getTypeExpense() {
      return (int) arguments.get("type_expense");
    }
  }
}
