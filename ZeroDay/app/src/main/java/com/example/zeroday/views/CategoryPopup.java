package com.example.zeroday.views;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.zeroday.R;

public class CategoryPopup extends Dialog {

    private String title;
    private EditText label;
    private Button cancel;
    private Button add;

    public CategoryPopup(Activity activity)
    {

        super(activity, androidx.appcompat.R.style.Theme_AppCompat_Dialog);

        this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.category_popup_template);
        this.title = "Add Category";
        this.label = findViewById(R.id.category_popup_label);


        this.cancel = findViewById(R.id.category_popup_cancel);
        this.add = findViewById(R.id.category_popup_add);



    }

    public Button getCancelButton(){
        return this.cancel;
    }

    public Button getAddButton(){
        return this.add;
    }

    public void build(){
        show();
    }


}
