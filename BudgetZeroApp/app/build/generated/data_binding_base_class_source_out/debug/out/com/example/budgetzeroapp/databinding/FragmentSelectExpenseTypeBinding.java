// Generated by view binder compiler. Do not edit!
package com.example.budgetzeroapp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.budgetzeroapp.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentSelectExpenseTypeBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final RadioGroup groupRadioType;

  @NonNull
  public final Button submit;

  @NonNull
  public final TextView textRadio;

  @NonNull
  public final RadioButton typeDebt;

  @NonNull
  public final RadioButton typeExp;

  @NonNull
  public final RadioButton typeInc;

  @NonNull
  public final RadioButton typeSav;

  private FragmentSelectExpenseTypeBinding(@NonNull RelativeLayout rootView,
      @NonNull RadioGroup groupRadioType, @NonNull Button submit, @NonNull TextView textRadio,
      @NonNull RadioButton typeDebt, @NonNull RadioButton typeExp, @NonNull RadioButton typeInc,
      @NonNull RadioButton typeSav) {
    this.rootView = rootView;
    this.groupRadioType = groupRadioType;
    this.submit = submit;
    this.textRadio = textRadio;
    this.typeDebt = typeDebt;
    this.typeExp = typeExp;
    this.typeInc = typeInc;
    this.typeSav = typeSav;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentSelectExpenseTypeBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentSelectExpenseTypeBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_select_expense_type, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentSelectExpenseTypeBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.group_radio_type;
      RadioGroup groupRadioType = ViewBindings.findChildViewById(rootView, id);
      if (groupRadioType == null) {
        break missingId;
      }

      id = R.id.submit;
      Button submit = ViewBindings.findChildViewById(rootView, id);
      if (submit == null) {
        break missingId;
      }

      id = R.id.text_radio;
      TextView textRadio = ViewBindings.findChildViewById(rootView, id);
      if (textRadio == null) {
        break missingId;
      }

      id = R.id.type_debt;
      RadioButton typeDebt = ViewBindings.findChildViewById(rootView, id);
      if (typeDebt == null) {
        break missingId;
      }

      id = R.id.type_exp;
      RadioButton typeExp = ViewBindings.findChildViewById(rootView, id);
      if (typeExp == null) {
        break missingId;
      }

      id = R.id.type_inc;
      RadioButton typeInc = ViewBindings.findChildViewById(rootView, id);
      if (typeInc == null) {
        break missingId;
      }

      id = R.id.type_sav;
      RadioButton typeSav = ViewBindings.findChildViewById(rootView, id);
      if (typeSav == null) {
        break missingId;
      }

      return new FragmentSelectExpenseTypeBinding((RelativeLayout) rootView, groupRadioType, submit,
          textRadio, typeDebt, typeExp, typeInc, typeSav);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
