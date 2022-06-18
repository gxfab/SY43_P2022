package com.example.budgetzeroapp.fragment.edit;

import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.budgetzeroapp.MainActivity;
import com.example.budgetzeroapp.R;
import com.example.budgetzeroapp.fragment.SavingsFragmentDirections;
import com.example.budgetzeroapp.tool.DBHelper;
import com.example.budgetzeroapp.tool.item.ListItem;

// name, max amount, current amount
//liste des expenses associées
public class EditSavingCatFragment extends EditDataBaseFragment{

    private EditText name, goal;
    private Button save;
    private String defaultName;
    private float defaultGoal;

    public EditSavingCatFragment(){ super(); }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup parent) {
        View view= inflater.inflate(R.layout.fragment_edit_saving_cat, parent, false);

        /**Getting passed id**/
        id = EditSavingCatFragmentArgs.fromBundle(getArguments()).getIdSavingCat();


        save = view.findViewById(R.id.buttonSave);
        name = view.findViewById(R.id.editTextSaveName);
        goal = view.findViewById(R.id.editTextSaveGoal);
        return view;
    }

    @Override
    public void initDefaultValues() {
        defaultName = "";
        defaultGoal = 0;
    }

    @Override
    public void changeDefaultValues() {
        Cursor cat = database.getData("select * from "+ DBHelper.SAV_CAT_TABLE_NAME+
                " where "+DBHelper.SAV_CAT_COL_ID+"="+id);
        cat.moveToFirst();
        if (cat.isAfterLast()) id = 0;
        else {
            defaultName = cat.getString(cat.getColumnIndexOrThrow(DBHelper.SAV_CAT_COL_NAME));
            defaultGoal = cat.getFloat(cat.getColumnIndexOrThrow(DBHelper.SAV_CAT_COL_MAX_AMOUNT));
        }
    }

    @Override
    public void setDefaultValues() {
        name.setText(defaultName);
        goal.setText(String.valueOf(defaultGoal));
    }

    @Override
    public void setButtons() {
        save.setOnClickListener(v -> {

            float newBudget = Float.parseFloat(goal.getText().toString());
            if(newBudget <= 0){
                message("Objective must have a positive value");
                return;
            }
            String newName = name.getText().toString();
            if(newName.equals("")){
                message("Name can't be empty");
            }

            if(id == 0) id = database.insertSavingsCat(newName,newBudget);
            else database.updateSavingsCat(id, newName,newBudget);

            NavController navController= Navigation.findNavController(MainActivity.getActivity(), R.id.nav_host_fragment);
            navController.navigate(EditSavingCatFragmentDirections.actionEditSavingCatFragmentToSavingsFragment());
            navController.navigate(SavingsFragmentDirections.navigateToViewSavingCatFromSavings(id));
        });
    }
}
