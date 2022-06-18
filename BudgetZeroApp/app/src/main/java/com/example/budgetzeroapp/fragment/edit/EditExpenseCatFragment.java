package com.example.budgetzeroapp.fragment.edit;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.budgetzeroapp.MainActivity;
import com.example.budgetzeroapp.fragment.HomeFragmentDirections;
import com.example.budgetzeroapp.tool.DBHelper;
import com.example.budgetzeroapp.R;
import com.example.budgetzeroapp.tool.adapter.SimpleListAdapter;
import com.example.budgetzeroapp.tool.item.ListItem;

import java.util.List;

public class EditExpenseCatFragment extends EditDataBaseFragment {
    private EditText name, budget;
    private CheckBox sub;
    private Spinner parentCat;
    private Button save;
    private String defaultName;
    private float defaultBudget;
    private int defaultSub, defaultParentCat;

    List<ListItem> list;


    public EditExpenseCatFragment(){ super(); }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup parent) {
        View view= inflater.inflate(R.layout.fragment_edit_expense_cat, parent, false);

        /**Getting passed id**/
        id = EditExpenseCatFragmentArgs.fromBundle(getArguments()).getIdExpenseCatEdit();


        save = view.findViewById(R.id.buttonSave);
        name = view.findViewById(R.id.editTextCatName);
        budget = view.findViewById(R.id.editTextCatBudget);
        sub = view.findViewById(R.id.checkBoxCatSub);
        parentCat = view.findViewById(R.id.spinnerCatParent);
        list = ListItem.initList(database.getAllExpenseCat());

        sub.setOnClickListener(view1 -> parentCat.setEnabled(sub.isChecked()));
        return view;
    }

    @Override
    public void initDefaultValues() {
        defaultName = "";
        defaultBudget = 0;
        defaultSub = 0;
        defaultParentCat = 0;
        parentCat.setAdapter(new SimpleListAdapter(list));

    }

    @Override
    public void changeDefaultValues() {
        Cursor cat = database.getData("select * from "+DBHelper.EXP_CAT_TABLE_NAME+
                " where "+DBHelper.EXP_CAT_COL_ID+" = "+id);
        cat.moveToFirst();
        if (cat.isAfterLast()) id = 0;
        else {
            defaultName = cat.getString(cat.getColumnIndexOrThrow(DBHelper.EXP_CAT_COL_NAME));
            defaultBudget = cat.getFloat(cat.getColumnIndexOrThrow(DBHelper.EXP_CAT_COL_BUDGET));
            defaultSub = cat.getInt(cat.getColumnIndexOrThrow(DBHelper.EXP_CAT_COL_IS_SUB));
            defaultParentCat = cat.getInt(cat.getColumnIndexOrThrow(DBHelper.EXP_CAT_COL_ID_PARENT));
            int it = 0;
            for (ListItem i : list){
                if(i.getId() == defaultParentCat) parentCat.setSelection(it);
                it++;
            }
            parentCat.setEnabled(sub.isChecked());
        }
    }

    @Override
    public void setDefaultValues() {
        name.setText(defaultName);
        budget.setText(String.valueOf(defaultBudget));
        sub.setChecked(defaultSub!=0);
        parentCat.setEnabled(sub.isChecked());
        parentCat.setSelection(0);
    }

    @Override
    public void setButtons() {
        save.setOnClickListener(v -> {

            float newBudget = Float.parseFloat(budget.getText().toString());
            if(newBudget <= 0){
                message("Budget must have a positive value");
                return;
            }
            String newName = name.getText().toString();
            if(newName.equals("")){
                message("Name can't be empty");
            }
            boolean isSub = sub.isChecked();
            int idCat = ((ListItem) parentCat.getSelectedItem()).getId();

            if(id == 0) id = database.insertExpenseCat(newName,newBudget,isSub,idCat);
            else database.updateExpenseCat(id, newName,newBudget,isSub, idCat);

            NavController navController= Navigation.findNavController(MainActivity.getActivity(), R.id.nav_host_fragment);
            navController.navigate(EditExpenseCatFragmentDirections.actionEditExpenseCatFragmentToHomeFragment());
            navController.navigate(HomeFragmentDirections.actionHomeFragmentToViewExpenseCatFragment(id));
        });
    }

}