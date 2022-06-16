package com.example.budgetzeroapp.fragment.view;

import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ListView;

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
    private ListView incList;
    private String nameVal;
    private List<ExpenseItem> incListVal;

    public ViewIncomeCatFragment(){ super(); }
    public ViewIncomeCatFragment(int id){ super(id); }

    public View onCreateView(LayoutInflater inflater, ViewGroup parent) {
        View view= inflater.inflate(R.layout.fragment_view_income_cat, parent, false);
        name = view.findViewById(R.id.textViewIncNameEntry);
        incList = view.findViewById(R.id.listViewCatInc);
        getValues();
        setValues();
        return view;
    }

    public void getValues() {
        Cursor inc = database.getCatFromType(id, DBHelper.TYPE_INC);
        inc.moveToFirst();
        if (inc.isAfterLast()) redirect(new HomeFragment(),id);
        else {
            nameVal = inc.getString(inc.getColumnIndexOrThrow(DBHelper.INC_CAT_COL_NAME));
            incListVal = ExpenseItem.catExpensesToList(database, id, DBHelper.TYPE_INC);
        }
    }

    public void setValues() {
        name.setText(nameVal);
        ClickableListManager.clickableExpenseList(incList,incListVal);
    }
}