package com.example.zeroday.views;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.example.zeroday.R;

public class ExpenseActivity extends AppCompatActivity {

    private ExpenseActivity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);

        this.activity = this;

        Boolean inputType = getIntent().getBooleanExtra("inputType", true);
        Boolean outputType = getIntent().getBooleanExtra("outputType", false);

        TextView title = findViewById(R.id.expenses_title);
        TextView subTitle = findViewById(R.id.expenses_subtitle);

        Button add = findViewById(R.id.add_category_button);


        //Cas "income"
        if(inputType == false){
            title.setText("Add Incomes");
            subTitle.setText("Add one or more incomes");
            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CategoryPopup categoryPopup = new CategoryPopup(activity);
                    categoryPopup.build();
                }
            });
        }
        //Cas "expense"
        else
        {
            title.setText("Add Expenses");
            subTitle.setText("Add one or more expenses");
            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CategoryPopup categoryPopup = new CategoryPopup(activity);
                    categoryPopup.getCancelButton().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            categoryPopup.dismiss();
                        }
                    });
                    categoryPopup.getAddButton().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            // Ajouter la nouvelle cat de Expense à la base de données ICI
                            categoryPopup.dismiss();
                        }
                    });
                    categoryPopup.build();
                }
            });
        }




    }

}
