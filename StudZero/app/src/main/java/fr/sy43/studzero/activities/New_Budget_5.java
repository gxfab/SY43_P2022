package fr.sy43.studzero.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import fr.sy43.studzero.R;

public class New_Budget_5 extends AppCompatActivity {

    private CustomAdapter2 customAdapter2;
    private ArrayList<String> category_name;
    private ArrayList<Float> Allocated;
    private float Available;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_budget5);

        getSupportActionBar().setTitle("Review New Budget");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //init
        category_name = new ArrayList<>();
        Allocated = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);
        GetData();
        customAdapter2 = new CustomAdapter2(this, category_name, Allocated, Available);
        recyclerView.setAdapter(customAdapter2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getApplicationContext()));

        Button button = (Button) findViewById(R.id.NewBudget2_ConfirmButton);


        // here
        float allocated = 1000; //DB.budget.budgetamount ou somme de Allocated (arraylist)

        //apply
        TextView txtAllocated = (TextView) findViewById(R.id.TextViewAllocated);
        TextView txtAvailable = (TextView) findViewById(R.id.TextViewAvailable);

        txtAllocated.setText(getString(R.string.NewBudgetTextViewAllocated) + " " + String.valueOf(allocated) + getString(R.string.currency));
        txtAvailable.setText(getString(R.string.NewBudgetTextViewAvailable) + " " + String.valueOf(Available) + getString(R.string.currency));
        if(allocated != Available){
            txtAllocated.setTextColor(getResources().getColor(R.color.red));
            button.setTextColor(getResources().getColor(R.color.red));
            button.setEnabled(false);
        }
        else{
            txtAllocated.setTextColor(getResources().getColor(R.color.green));
            button.setTextColor(getResources().getColor(R.color.green));
            button.setEnabled(true);
        }


        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.i("button", "confirmed");
                finish();
                //go to home
                Intent intent = new Intent(getApplicationContext(), Home.class);
                intent.putExtra("caller", "New_Budget_5"); //permet à la nouvelle activity de connaitre son lanceur
                startActivity(intent);
            }
        });
    }

    //récupérer les datas de la DB courrante
    private void GetData() {
        // récup data
        category_name.add("cat 1");
        Allocated.add(10.0f);
        category_name.add("cat 2");
        Allocated.add(10.0f);
        category_name.add("cat 3");
        Allocated.add(10.0f);
        category_name.add("cat 4");
        Allocated.add(10.0f);
        category_name.add("cat 5");
        Allocated.add(10.0f);
        category_name.add("cat 6");
        Allocated.add(1000.0f);
        category_name.add("cat 7");
        Allocated.add(10.0f);
        category_name.add("cat 8");
        Allocated.add(50.0f);
        category_name.add("cat 9");
        Allocated.add(10.0f);
        category_name.add("cat 10");
        Allocated.add(10.0f);
        category_name.add("cat 11");
        Allocated.add(200.0f);
        category_name.add("cat 12");
        Allocated.add(100.0f);

        Available = 1200f; //this budget.allocated
    }

    public void OnCategoryClicked(int pos){ //appuis sur une catégorie du recycler view

        Intent intent = new Intent(this.getApplicationContext(), New_Budget_3.class);
        String caller     = getIntent().getStringExtra("caller"); //caller contient le nom de la class appelante
        intent.putExtra("caller", caller); //permet à la nouvelle activity de connaitre son lanceur
        intent.putExtra("subcaller", "NB5"); //permet à la nouvelle activity de connaitre son lanceur
        intent.putExtra("pos", String.valueOf(pos)); //envoie la position a présélectionée
        startActivity(intent);
    }

    //permet le retour vers la page précédente
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();

                String caller     = getIntent().getStringExtra("caller"); //caller contient le nom de la class appelante
                if(caller.equals("Settings")){
                    Intent intent = new Intent(this, New_Budget_3.class);
                    intent.putExtra("caller", "Settings"); //permet à la nouvelle activity de connaitre son lanceur
                    startActivity(intent);
                }
                else{
                    Intent intent = new Intent(this, New_Budget_3.class);
                    intent.putExtra("caller", "MAinActivity"); //permet à la nouvelle activity de connaitre son lanceur
                    startActivity(intent);
                }
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }


}