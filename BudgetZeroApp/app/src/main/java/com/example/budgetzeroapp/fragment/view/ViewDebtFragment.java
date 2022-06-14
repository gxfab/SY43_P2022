package com.example.budgetzeroapp.fragment.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.budgetzeroapp.R;
import com.example.budgetzeroapp.fragment.DataBaseFragment;

//champs : name (string), month_left (int), total_amount(float)
// montant déjà remboursé
// liste de tous les paiements de cette dette
public class ViewDebtFragment extends DataBaseFragment {

    private TextView name, monthLeft, totalAmount, refundedAmount;
    private TextView [] exp;
    private LinearLayout expLayout;
    private String nameVal;
    private int monthLeftVal;
    private float totalAmountVal, refundedAmountVal;
    private String [] expVal;

    public ViewDebtFragment(){ super(); }
    public ViewDebtFragment(int id){ super(id); }

    public View initView(LayoutInflater inflater, ViewGroup parent) {
        View view= inflater.inflate(R.layout.fragment_view_debt, parent, false);
        name = view.findViewById(R.id.textViewDebtNameEntry);
        monthLeft = view.findViewById(R.id.textViewDebtMonthEntry);
        totalAmount = view.findViewById(R.id.textViewDebtTotalAmountEntry);
        refundedAmount = view.findViewById(R.id.textViewDebtRefundedEntry);
        expLayout = view.findViewById(R.id.layoutDebtExpenses);
        getValues();
        setValues();
        return view;
    }

    public void getValues() {
        //Get values
    }

    public void setValues() {
        name.setText(nameVal);
        monthLeft.setText(String.valueOf(monthLeftVal));
        totalAmount.setText(String.valueOf(totalAmountVal));
        refundedAmount.setText(String.valueOf(refundedAmountVal));
        for(int i=0;i<expVal.length;i++) {
            exp[i] = new TextView(expLayout.getContext());
            exp[i].setText(expVal[i]);
            expLayout.addView(exp[i]);
        }
    }



}
