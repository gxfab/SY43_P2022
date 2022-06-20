// Generated by view binder compiler. Do not edit!
package com.example.budgetzeroapp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.budgetzeroapp.R;
import com.google.android.material.appbar.AppBarLayout;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentViewDebtBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final AppBarLayout appBarLayout;

  @NonNull
  public final Button editButton;

  @NonNull
  public final ImageView imageDummy;

  @NonNull
  public final ListView listViewDebtExpenses;

  @NonNull
  public final ConstraintLayout scrollView1;

  @NonNull
  public final TextView textViewDebtExpenses;

  @NonNull
  public final TextView textViewDebtMonth;

  @NonNull
  public final TextView textViewDebtMonthEntry;

  @NonNull
  public final TextView textViewDebtName;

  @NonNull
  public final TextView textViewDebtNameEntry;

  @NonNull
  public final TextView textViewDebtRefunded;

  @NonNull
  public final TextView textViewDebtRefundedEntry;

  @NonNull
  public final TextView textViewDebtTotalAmount;

  @NonNull
  public final TextView textViewDebtTotalAmountEntry;

  private FragmentViewDebtBinding(@NonNull ConstraintLayout rootView,
      @NonNull AppBarLayout appBarLayout, @NonNull Button editButton, @NonNull ImageView imageDummy,
      @NonNull ListView listViewDebtExpenses, @NonNull ConstraintLayout scrollView1,
      @NonNull TextView textViewDebtExpenses, @NonNull TextView textViewDebtMonth,
      @NonNull TextView textViewDebtMonthEntry, @NonNull TextView textViewDebtName,
      @NonNull TextView textViewDebtNameEntry, @NonNull TextView textViewDebtRefunded,
      @NonNull TextView textViewDebtRefundedEntry, @NonNull TextView textViewDebtTotalAmount,
      @NonNull TextView textViewDebtTotalAmountEntry) {
    this.rootView = rootView;
    this.appBarLayout = appBarLayout;
    this.editButton = editButton;
    this.imageDummy = imageDummy;
    this.listViewDebtExpenses = listViewDebtExpenses;
    this.scrollView1 = scrollView1;
    this.textViewDebtExpenses = textViewDebtExpenses;
    this.textViewDebtMonth = textViewDebtMonth;
    this.textViewDebtMonthEntry = textViewDebtMonthEntry;
    this.textViewDebtName = textViewDebtName;
    this.textViewDebtNameEntry = textViewDebtNameEntry;
    this.textViewDebtRefunded = textViewDebtRefunded;
    this.textViewDebtRefundedEntry = textViewDebtRefundedEntry;
    this.textViewDebtTotalAmount = textViewDebtTotalAmount;
    this.textViewDebtTotalAmountEntry = textViewDebtTotalAmountEntry;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentViewDebtBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentViewDebtBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_view_debt, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentViewDebtBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.appBarLayout;
      AppBarLayout appBarLayout = ViewBindings.findChildViewById(rootView, id);
      if (appBarLayout == null) {
        break missingId;
      }

      id = R.id.editButton;
      Button editButton = ViewBindings.findChildViewById(rootView, id);
      if (editButton == null) {
        break missingId;
      }

      id = R.id.imageDummy;
      ImageView imageDummy = ViewBindings.findChildViewById(rootView, id);
      if (imageDummy == null) {
        break missingId;
      }

      id = R.id.listViewDebtExpenses;
      ListView listViewDebtExpenses = ViewBindings.findChildViewById(rootView, id);
      if (listViewDebtExpenses == null) {
        break missingId;
      }

      ConstraintLayout scrollView1 = (ConstraintLayout) rootView;

      id = R.id.textViewDebtExpenses;
      TextView textViewDebtExpenses = ViewBindings.findChildViewById(rootView, id);
      if (textViewDebtExpenses == null) {
        break missingId;
      }

      id = R.id.textViewDebtMonth;
      TextView textViewDebtMonth = ViewBindings.findChildViewById(rootView, id);
      if (textViewDebtMonth == null) {
        break missingId;
      }

      id = R.id.textViewDebtMonthEntry;
      TextView textViewDebtMonthEntry = ViewBindings.findChildViewById(rootView, id);
      if (textViewDebtMonthEntry == null) {
        break missingId;
      }

      id = R.id.textViewDebtName;
      TextView textViewDebtName = ViewBindings.findChildViewById(rootView, id);
      if (textViewDebtName == null) {
        break missingId;
      }

      id = R.id.textViewDebtNameEntry;
      TextView textViewDebtNameEntry = ViewBindings.findChildViewById(rootView, id);
      if (textViewDebtNameEntry == null) {
        break missingId;
      }

      id = R.id.textViewDebtRefunded;
      TextView textViewDebtRefunded = ViewBindings.findChildViewById(rootView, id);
      if (textViewDebtRefunded == null) {
        break missingId;
      }

      id = R.id.textViewDebtRefundedEntry;
      TextView textViewDebtRefundedEntry = ViewBindings.findChildViewById(rootView, id);
      if (textViewDebtRefundedEntry == null) {
        break missingId;
      }

      id = R.id.textViewDebtTotalAmount;
      TextView textViewDebtTotalAmount = ViewBindings.findChildViewById(rootView, id);
      if (textViewDebtTotalAmount == null) {
        break missingId;
      }

      id = R.id.textViewDebtTotalAmountEntry;
      TextView textViewDebtTotalAmountEntry = ViewBindings.findChildViewById(rootView, id);
      if (textViewDebtTotalAmountEntry == null) {
        break missingId;
      }

      return new FragmentViewDebtBinding((ConstraintLayout) rootView, appBarLayout, editButton,
          imageDummy, listViewDebtExpenses, scrollView1, textViewDebtExpenses, textViewDebtMonth,
          textViewDebtMonthEntry, textViewDebtName, textViewDebtNameEntry, textViewDebtRefunded,
          textViewDebtRefundedEntry, textViewDebtTotalAmount, textViewDebtTotalAmountEntry);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
