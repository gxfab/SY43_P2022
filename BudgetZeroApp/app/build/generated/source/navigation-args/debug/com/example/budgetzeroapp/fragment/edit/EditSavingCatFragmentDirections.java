package com.example.budgetzeroapp.fragment.edit;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.example.budgetzeroapp.R;

public class EditSavingCatFragmentDirections {
  private EditSavingCatFragmentDirections() {
  }

  @NonNull
  public static NavDirections actionEditSavingCatFragmentToSavingsFragment() {
    return new ActionOnlyNavDirections(R.id.action_editSavingCatFragment_to_savingsFragment);
  }
}
