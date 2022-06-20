package com.example.zeroday.views;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.zeroday.R;
import com.example.zeroday.models.Expense;
import com.example.zeroday.services.ExpenseService;


public class CategoryActivity extends AppCompatActivity {

    private CategoryActivity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        this.activity = this;

        Boolean itemType = getIntent().getBooleanExtra("itemType", true);
        String itemCode = getIntent().getStringExtra("itemCode");
        String itemLabel = getIntent().getStringExtra("itemLabel");

        TextView title = findViewById(R.id.category_title);
        TextView subtitle = findViewById(R.id.category_subtitle);
        Button add = findViewById(R.id.add_item_button);

        title.setText(itemLabel);

        //On adapte l'activité ( income / expense )
        //Cas "expense"
        if(itemType==true)
        {
            subtitle.setText("Add one or more expenses");

            //On assigne la création d'une popup d'ajout d'une dépense au bouton "Add"
            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Création de la popup
                    ItemPopup itemPopup = new ItemPopup(activity);
                    //Affectation de l'action au bouton "cancel" de la popup
                    itemPopup.getCancelButton().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            itemPopup.dismiss();
                        }
                    });

                    itemPopup.getAddButton().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //new ExpenseService(getApplicationContext()).create(new Expense("label","amount","date","frequency",));
                            itemPopup.dismiss();
                        }
                    });
                }
            });


        }
        else
        //Cas "income"
        {
            subtitle.setText("Add one or more incomes");
        }



    }
}