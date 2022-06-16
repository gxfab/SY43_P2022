package com.example.nomoola.fragment.dialog;

import android.content.ContextWrapper;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.nomoola.R;
import com.example.nomoola.database.converter.Converters;
import com.example.nomoola.database.entity.Category;
import com.example.nomoola.database.entity.InOutCome;
import com.example.nomoola.database.entity.SubCategory;
import com.example.nomoola.viewModel.CategoryViewModel;
import com.example.nomoola.viewModel.InOutComeViewModel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AddInOutComeDialog extends DialogFragment {

    private ArrayList<String> listSubCatNames;

    private TextView view_InOutComeTitle;
    private EditText edit_InOutComeName, edit_InOutComeAmount;
    private ImageButton exitButton, deleteButton, confirmEditButton;
    private Spinner spinner;
    private DatePicker datePicker;

    private InOutComeViewModel inOutComeViewModel;
    private InOutCome inOutCome;

    public AddInOutComeDialog(){
        this.inOutCome = new InOutCome();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        this.inOutComeViewModel = new ViewModelProvider(this).get(InOutComeViewModel.class);
        View view = inflater.inflate(R.layout.dialog_in_out_come, container, false);

        this.view_InOutComeTitle = view.findViewById(R.id.dialog_come_title);
        this.edit_InOutComeName = view.findViewById(R.id.dialog_come_editName);
        this.edit_InOutComeAmount = view.findViewById(R.id.dialog_come_editAmount);
        this.exitButton = view.findViewById(R.id.dialog_come_exitbutton);
        this.deleteButton = view.findViewById(R.id.dialog_come_delete);
        this.confirmEditButton = view.findViewById(R.id.dialog_come_confirmEdit);
        this.spinner = view.findViewById(R.id.dialog_come_spinnerType);
        this.datePicker = view.findViewById(R.id.dialog_come_datePicker);

        this.view_InOutComeTitle.setText("New InOutCome");
        this.deleteButton.setVisibility(View.GONE);

        this.listSubCatNames = new ArrayList<>();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, listSubCatNames);
        this.inOutComeViewModel.getAllSubCategoriesNames().observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {
                listSubCatNames.addAll(strings);
                adapter.notifyDataSetChanged();
            }
        });
        adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        this.spinner.setAdapter(adapter);

        this.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spinner.setSelection(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                spinner.setSelection(0);
            }
        });

        this.exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        this.confirmEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edit_InOutComeName.getText().toString();
                double amount = Double.valueOf(edit_InOutComeAmount.getText().toString()); //TODO check if the amount is null
                LocalDate date = LocalDate.of(datePicker.getYear(), datePicker.getMonth()+1, datePicker.getDayOfMonth());
                String subCatName = spinner.getSelectedItem().toString();
                SubCategory subcat = inOutComeViewModel.getSubCategoriesNamed(subCatName);

                inOutCome.setM_INOUTCOME_NAME(name);
                inOutCome.setM_INOUTCOME_AMOUNT(amount);
                inOutCome.setM_INOUTCOME_DATE(date);
                inOutCome.setM_SUBCAT_ID(subcat.getM_SUBCAT_ID());
                inOutCome.setM_CAT_ID(subcat.getM_CAT_ID());

                inOutComeViewModel.insert(inOutCome);
                dismiss();
            }
        });



        return view;
    }
}
