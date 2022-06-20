// Generated by view binder compiler. Do not edit!
package com.example.budgetzeroapp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.budgetzeroapp.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentCashFlowStableBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final TextView earningsLabel;

  @NonNull
  public final ListView earningsList;

  @NonNull
  public final TextView expensesLabel;

  @NonNull
  public final ListView expensesList;

  @NonNull
  public final ImageView imageDummy1;

  @NonNull
  public final ImageView imageDummy2;

  @NonNull
  public final ConstraintLayout scrollView1;

  private FragmentCashFlowStableBinding(@NonNull ConstraintLayout rootView,
      @NonNull TextView earningsLabel, @NonNull ListView earningsList,
      @NonNull TextView expensesLabel, @NonNull ListView expensesList,
      @NonNull ImageView imageDummy1, @NonNull ImageView imageDummy2,
      @NonNull ConstraintLayout scrollView1) {
    this.rootView = rootView;
    this.earningsLabel = earningsLabel;
    this.earningsList = earningsList;
    this.expensesLabel = expensesLabel;
    this.expensesList = expensesList;
    this.imageDummy1 = imageDummy1;
    this.imageDummy2 = imageDummy2;
    this.scrollView1 = scrollView1;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentCashFlowStableBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentCashFlowStableBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_cash_flow_stable, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentCashFlowStableBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.earnings_label;
      TextView earningsLabel = ViewBindings.findChildViewById(rootView, id);
      if (earningsLabel == null) {
        break missingId;
      }

      id = R.id.earnings_list;
      ListView earningsList = ViewBindings.findChildViewById(rootView, id);
      if (earningsList == null) {
        break missingId;
      }

      id = R.id.expenses_label;
      TextView expensesLabel = ViewBindings.findChildViewById(rootView, id);
      if (expensesLabel == null) {
        break missingId;
      }

      id = R.id.expenses_list;
      ListView expensesList = ViewBindings.findChildViewById(rootView, id);
      if (expensesList == null) {
        break missingId;
      }

      id = R.id.imageDummy1;
      ImageView imageDummy1 = ViewBindings.findChildViewById(rootView, id);
      if (imageDummy1 == null) {
        break missingId;
      }

      id = R.id.imageDummy2;
      ImageView imageDummy2 = ViewBindings.findChildViewById(rootView, id);
      if (imageDummy2 == null) {
        break missingId;
      }

      ConstraintLayout scrollView1 = (ConstraintLayout) rootView;

      return new FragmentCashFlowStableBinding((ConstraintLayout) rootView, earningsLabel,
          earningsList, expensesLabel, expensesList, imageDummy1, imageDummy2, scrollView1);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
