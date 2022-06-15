package com.example.sy43;

import static java.lang.Float.parseFloat;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.sy43.adapters.CategoryAdapter;
import com.example.sy43.db.entity.Categorydb;
import com.example.sy43.db.entity.SubCategory;
import com.example.sy43.viewmodels.CategoryViewModel;
import com.example.sy43.viewmodels.SubCategoryViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CreateActivity extends AppCompatActivity {

    private DatePicker datePicker;
    private Calendar calendar;
    private TextView dateView;
    private int year, month, day;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        Context context = this.getApplicationContext();
        dateView = (TextView) findViewById(R.id.textView3);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);


        CategoryViewModel categoryViewModel = new ViewModelProvider(this).get(CategoryViewModel.class);
        categoryViewModel.init();
        SubCategoryViewModel subCategoryViewModel = new ViewModelProvider(this).get(SubCategoryViewModel.class);
        subCategoryViewModel.init();

        Spinner spinnerChooseCategory = (Spinner) findViewById(R.id.spinnerChooseCategory);
        categoryViewModel.getCategories().observe(this, new Observer<List<Categorydb>>() {
            @Override
            public void onChanged(List<Categorydb> receivedCategories) {
                final List<String> list = new ArrayList<String>();
                for (Categorydb cat : receivedCategories) {
                    list.add(cat.getCatName());
                }
                ArrayAdapter<String> adp1 = new ArrayAdapter<String>(context,
                        android.R.layout.simple_list_item_1,
                        list);
                adp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerChooseCategory.setAdapter(adp1);
            }
        });


        Switch subSwitch = (Switch) findViewById(R.id.switchIsSubCat);

        Button cat = (Button) findViewById(R.id.btnCatConfirm);
        cat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isCategory = !subSwitch.isChecked();
                if (isCategory) {
                    Categorydb cat = new Categorydb();
                    EditText name = (EditText) findViewById(R.id.txtCategory);
                    if (name.getText().toString().length() == 0) return;
                    cat.setCatName(name.getText().toString());
                    cat.setObjective(false);
                    categoryViewModel.createCategory(cat);

                } else {
                    SubCategory cat = new SubCategory();
                    TextView name = (TextView) findViewById(R.id.txtCategory);
                    cat.setSubCatName(name.getText().toString());
                    //cat.setCategory();
                    List<Categorydb> categories = categoryViewModel.getCategories().getValue();
                    Categorydb parentCat = null;
                    for (Categorydb _cat : categories) {
                        if (_cat.getCatName() == spinnerChooseCategory.getSelectedItem().toString()) {
                            parentCat = _cat;
                        }
                    }
                    EditText montant = (EditText) findViewById(R.id.editMontant);
                    if (montant.getText().toString().length() == 0) return;
                    cat.setMaxValue(Float.parseFloat(montant.getText().toString()));
                    cat.setCurrentValue(0);
                    cat.setCategory(parentCat.getCatID());
                    subCategoryViewModel.createSubCategory(cat);
                }
                Intent intent = new Intent(v.getContext(), CategoryActivity.class);
                v.getContext().startActivity(intent);
            }
        });
        Button obj = (Button) findViewById(R.id.btnObjConfirm);
        obj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Categorydb cat = new Categorydb();
                EditText name = (EditText) findViewById(R.id.objName);
                cat.setCatName(name.getText().toString());

                EditText value = (EditText) findViewById(R.id.objValue);
                if (name.getText().toString().length() == 0 || value.getText().toString().length() == 0) return;
                Log.d("Test", String.valueOf(value.getText()));
                cat.setMaxValue(parseFloat(value.getText().toString()));

                cat.setCurrentValue(0);
                cat.setObjective(true);
                Date date = showDate(year, month, day);
                if (date == null) {
                    return;
                }
                cat.setDate(date.getTime());

                categoryViewModel.createCategory(cat);
                Intent intent = new Intent(v.getContext(), CategoryActivity.class);
                v.getContext().startActivity(intent);
            }
        });

        visibilitySubcategoryFields(View.INVISIBLE);
        subSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    visibilitySubcategoryFields(View.VISIBLE);
                } else {
                    visibilitySubcategoryFields(View.INVISIBLE);
                }
            }
        });
    }

    public void visibilitySubcategoryFields(int visibility) {
        EditText editText = (EditText) findViewById(R.id.editMontant);
        Spinner spinner = (Spinner) findViewById(R.id.spinnerChooseCategory);
        editText.setVisibility(visibility);
        spinner.setVisibility(visibility);


    }

    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        showDialog(999);
        Toast.makeText(getApplicationContext(), "ca",
                        Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this,
                    myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    // TODO Auto-generated method stub
                    // arg1 = year
                    // arg2 = month
                    // arg3 = day
                    showDate(arg1, arg2 + 1, arg3);
                }
            };

    private Date showDate(int year, int month, int day) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MM-dd-YY");
        Date d = new Date(year, month, day);
        String strDate = dateFormatter.format(d);
        dateView.setText(strDate);
        return d;
    }


}
