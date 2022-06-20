package com.example.budgetzeroapp.fragment.edit;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.example.budgetzeroapp.R;

public class EditExpenseCatFragmentDirections {
  private EditExpenseCatFragmentDirections() {
  }

  @NonNull
  public static NavDirections actionEditExpenseCatFragmentToHomeFragment() {
    return new ActionOnlyNavDirections(R.id.action_editExpenseCatFragment_to_homeFragment);
  }
}
