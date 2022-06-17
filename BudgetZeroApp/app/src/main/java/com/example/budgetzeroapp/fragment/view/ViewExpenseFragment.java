package com.example.budgetzeroapp.fragment.view;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.example.budgetzeroapp.MainActivity;
import com.example.budgetzeroapp.R;
import com.example.budgetzeroapp.fragment.DataBaseFragment;
import com.example.budgetzeroapp.fragment.HomeFragment;
import com.example.budgetzeroapp.fragment.HomeFragmentDirections;
import com.example.budgetzeroapp.tool.DBHelper;

import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class ViewExpenseFragment extends DataBaseFragment {

    private TextView name, date, amount, stable, type;
    private String nameVal;
    private Calendar dateVal;
    private float amountVal;
    private int stableVal, typeVal;
    private Button edit;

    public ViewExpenseFragment(){ super(); }
    public ViewExpenseFragment(int id){ super(id); }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_view_expense, parent, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        /**Listener edit button**/
        view.findViewById(R.id.editButton).setOnClickListener(new View.OnClickListener() {
            NavController navController = Navigation.findNavController(view);
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.navigate_to_editExpense_from_expense);
            }
        });

        /**Getting passed id**/
        id = ViewExpenseFragmentArgs.fromBundle(getArguments()).getIdExpense();
        //Toast.makeText(getActivity(),"id : " + id,Toast.LENGTH_SHORT).show();

        name = view.findViewById(R.id.textViewExpNameEntry);
        date = view.findViewById(R.id.textViewExpDateEntry);
        amount = view.findViewById(R.id.textViewExpAmountEntry);
        stable = view.findViewById(R.id.textViewExpStableEntry);
        type = view.findViewById(R.id.textViewExpType);
        edit = view.findViewById(R.id.editButton);
        getValues();
        setValues();
    }

    public void getValues() {
        Cursor exp = database.getExpenseFromID(id);
        exp.moveToFirst();
        if (exp.isAfterLast()) redirect(new HomeFragment(),id);
        else {
            nameVal = exp.getString(exp.getColumnIndexOrThrow(DBHelper.EXP_CAT_COL_NAME));
            amountVal = exp.getFloat(exp.getColumnIndexOrThrow(DBHelper.EXP_COL_AMOUNT));
            dateVal = Calendar.getInstance();
            dateVal.set(Calendar.DATE, exp.getInt(exp.getColumnIndexOrThrow(DBHelper.EXP_COL_DAY)));
            dateVal.set(Calendar.MONTH, exp.getInt(exp.getColumnIndexOrThrow(DBHelper.EXP_COL_MONTH)));
            dateVal.set(Calendar.YEAR, exp.getInt(exp.getColumnIndexOrThrow(DBHelper.EXP_COL_YEAR)));
            typeVal = exp.getInt(exp.getColumnIndexOrThrow(DBHelper.EXP_COL_TYPE));
            stableVal = exp.getInt(exp.getColumnIndexOrThrow(DBHelper.EXP_COL_AMOUNT));
        }
    }


    public void setValues() {
        name.setText(nameVal);
        amount.setText(String.valueOf(amountVal));
        Date dateString = dateVal.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE);
        date.setText(dateFormat.format(dateString));
        switch(typeVal) {
            case 1:
                type.setText("Expense");
                break;
            case 2:
                type.setText("Income");
                break;
            case 3:
                type.setText("Debt");
                break;
            case 4:
                type.setText("Saving");
                break;
            default:
                type.setText("Unknown type");
                break;
        }
        if (stableVal==0)   {
            stable.setText("No");
        }   else    {
            stable.setText("Yes");
        }
    }

    public static void redirectToEditExpense(int id, int type)
    {
        NavController navController= Navigation.findNavController(MainActivity.getActivity(), R.id.nav_host_fragment);
        NavDirections action = ViewExpenseFragmentDirections.navigateToEditExpenseFromExpense(id, type);
        navController.navigate(action);
    }
}