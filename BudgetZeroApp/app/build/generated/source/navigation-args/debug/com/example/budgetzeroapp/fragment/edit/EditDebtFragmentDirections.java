package com.example.budgetzeroapp.fragment.edit;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.example.budgetzeroapp.R;

public class EditDebtFragmentDirections {
  private EditDebtFragmentDirections() {
  }

  @NonNull
  public static NavDirections actionEditDebtFragmentToSavingsFragment() {
    return new ActionOnlyNavDirections(R.id.action_editDebtFragment_to_savingsFragment);
  }
}
