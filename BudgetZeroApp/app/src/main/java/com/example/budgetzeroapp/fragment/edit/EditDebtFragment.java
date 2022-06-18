package com.example.budgetzeroapp.fragment.edit;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.database.Cursor;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.budgetzeroapp.MainActivity;
import com.example.budgetzeroapp.R;
import com.example.budgetzeroapp.tool.DBHelper;

// Champs à éditer : name (string), month_left(int) et total_amount (float)
//bouton save
public class EditDebtFragment extends EditDataBaseFragment{

    private Button save;
    private String defaultName;
    private int defaultMonthLeft;
    private float defaultTotalAmount;
    private EditText name, monthLeft, totalAmount;

    public EditDebtFragment(){ super(); }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup parent) {
        View view= inflater.inflate(R.layout.fragment_edit_debt, parent, false);

        /**Getting passed id**/
        id = EditDebtFragmentArgs.fromBundle(getArguments()).getIdDebtEdit();
        Toast.makeText(getActivity(),"id : " + id,Toast.LENGTH_SHORT).show();

        save = view.findViewById(R.id.buttonSave);
        name = view.findViewById(R.id.editTextDebtName);
        monthLeft = view.findViewById(R.id.editTextDebtMonth);
        totalAmount = view.findViewById(R.id.editTextDebtAmount);
        return view;
    }

    @Override
    public void initDefaultValues() {
        defaultName = "";
        defaultMonthLeft = 0;
        defaultTotalAmount = 0;
    }

    @Override
    public void changeDefaultValues() {
        Cursor debt = database.getData("select * from "+DBHelper.DEBT_TABLE_NAME+
                " where "+DBHelper.DEBT_COL_ID+"="+id);
        debt.moveToFirst();
        if (debt.isAfterLast()) id = 0;
        else {
            defaultName = debt.getString(debt.getColumnIndexOrThrow(DBHelper.DEBT_COL_NAME));
            defaultMonthLeft = debt.getInt(debt.getColumnIndexOrThrow(DBHelper.DEBT_COL_MONTH_LEFT));
            defaultTotalAmount = debt.getFloat(debt.getColumnIndexOrThrow(DBHelper.DEBT_COL_TOTAL_AMOUNT));
        }
    }

    @Override
    public void setDefaultValues() {
        name.setText(defaultName);
        monthLeft.setText(String.valueOf(defaultMonthLeft));
        totalAmount.setText(String.valueOf(defaultTotalAmount));
    }

    @Override
    public void setButtons() {
        save.setOnClickListener(v -> {

            float newAmount = Float.parseFloat(totalAmount.getText().toString());
            if(newAmount <= 0){
                message("Amount must have a positive value");
                return;
            }
            int newMonthLeft = Integer.parseInt(monthLeft.getText().toString());
            if(newMonthLeft <= 0){
                message("Months left must have a positive value");
                return;
            }
            String newName = name.getText().toString();
            if(newName.equals("")){
                message("Name can't be empty");
            }

            if(id == 0) id = database.insertDebtCat(newName, newMonthLeft, newAmount);
            else database.updateDebtCat(id, newName, newMonthLeft, newAmount);

            NavController navController= Navigation.findNavController(MainActivity.getActivity(), R.id.nav_host_fragment);
            navController.navigate(EditDebtFragmentDirections.navigateToViewDebtFromDebt(id));
        });
    }
}
