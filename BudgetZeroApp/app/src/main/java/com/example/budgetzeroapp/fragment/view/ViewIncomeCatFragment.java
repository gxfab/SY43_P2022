package com.example.budgetzeroapp.fragment.view;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.budgetzeroapp.R;
import com.example.budgetzeroapp.fragment.DataBaseFragment;
import com.example.budgetzeroapp.fragment.HomeFragment;
import com.example.budgetzeroapp.tool.ClickableListManager;
import com.example.budgetzeroapp.tool.DBHelper;
import com.example.budgetzeroapp.tool.item.CategoryItem;
import com.example.budgetzeroapp.tool.item.ExpenseItem;

import java.util.List;

// nom, liste des revenus associés à la catégorie
public class ViewIncomeCatFragment extends DataBaseFragment {

    private TextView name;
    private TextView listTextView;
    private ListView incList;
    private String nameVal;
    private List<ExpenseItem> incListVal;
    private Button edit;

    public ViewIncomeCatFragment(){ super(); }
    public ViewIncomeCatFragment(int id){ super(id); }

    public View onCreateView(LayoutInflater inflater, ViewGroup parent) {
        View view= inflater.inflate(R.layout.fragment_view_income_cat, parent, false);
        name = view.findViewById(R.id.textViewIncNameEntry);
        incList = view.findViewById(R.id.listViewCatInc);
        listTextView = view.findViewById(R.id.textViewCatInc);
        edit = view.findViewById(R.id.editButton);
        getValues();
        setValues();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.editButton).setOnClickListener(new View.OnClickListener() {
            NavController navController = Navigation.findNavController(view);
            @Override
            public void onClick(View view) {
            }
        });
    }

    public void getValues() {
        Cursor inc = database.getCatFromType(id, DBHelper.TYPE_INC);
        inc.moveToFirst();
        if (!inc.isAfterLast()) {
            nameVal = inc.getString(inc.getColumnIndexOrThrow(DBHelper.INC_CAT_COL_NAME));
            incListVal = ExpenseItem.ExpensesToList(database.getExpensesFromCat(id, DBHelper.TYPE_INC));
        }
    }

    public void setValues() {
        name.setText(nameVal);
        ClickableListManager.clickableExpenseList(incList,incListVal);
        if (incListVal.isEmpty()){
            incList.setVisibility(View.GONE);
            listTextView.setVisibility(View.GONE);
        } else if(incList.getVisibility()==View.GONE) {
            incList.setVisibility(View.VISIBLE);
            listTextView.setVisibility(View.GONE);
        }
    }
}