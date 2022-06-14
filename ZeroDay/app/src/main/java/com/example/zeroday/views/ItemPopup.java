package com.example.zeroday.views;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.zeroday.R;

public class ItemPopup extends Dialog {

    private String title;
    private EditText label;
    private Spinner spinner;
    private Button cancel;
    private Button add;
    String[] cycles;

    public ItemPopup(Activity activity)
    {
        super(activity, androidx.appcompat.R.style.Theme_AppCompat_Dialog);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.item_popup_template);


        this.title = "Add Item";
        this.spinner = findViewById(R.id.item_popup_spinner);
        this.label = findViewById(R.id.item_popup_label);
        this.cancel = findViewById(R.id.item_popup_cancel);
        this.add = findViewById(R.id.item_popup_add);
        this.cycles = new String[]{"cycle1", "cycle2", "cycle3", "cycle4"};

        ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(), R.layout.black_spinner_item, cycles);
        arrayAdapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

    }

    public void setText(String string){
        this.title = string;
    }

    public Button getCancelButton(){
        return this.cancel;
    }

    public Button getAddButton(){
        return this.add;
    }

    public void build()
    {

        TextView textView = findViewById(R.id.item_popup_title);
        textView.setText(this.title);
        show();
    }

}
