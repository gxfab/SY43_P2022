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
import java.util.List;

import fr.sy43.studzero.R;
import fr.sy43.studzero.sqlite.helper.DatabaseHelper;
import fr.sy43.studzero.sqlite.model.Category;
import fr.sy43.studzero.sqlite.model.CategoryType;

/**
 * 5th screen of a new budget creation
 * Display all the information : allocated amount, budget amount, categories with their allocated amount and a progress bar to show the %of the total budget
 * A confirm button to finish the creation of the budget
 */
public class New_Budget_5 extends AppCompatActivity {

    private CustomAdapter2 customAdapter2;
    private ArrayList<String> category_name;
    private ArrayList<Float> Allocated;
    private float Available;
    private RecyclerView recyclerView;
    private long idNewBudget;

    /**
     * setup the text fields with the correct value
     * setup the top bar
     * setup the confirm button listener
     * setup the recycler view (customAdapter2 and arraylists)
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_budget5);

        idNewBudget = getIntent().getLongExtra(New_Budget_1.ID_NEW_BUDGET, -1);
        if(idNewBudget == -1) {
            Intent intent = new Intent(this, New_Budget_1.class);
            intent.putExtra("caller", "Settings"); //permet à la nouvelle activity de connaitre son lanceur
            intent.putExtra(New_Budget_1.ID_NEW_BUDGET, idNewBudget);
            startActivity(intent);
        }

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

        DatabaseHelper db = new DatabaseHelper(getApplicationContext());
        float allocated = 0f;
        List<Category> listCategories = db.getAllCategories((int) idNewBudget);
        String[] category = new String[listCategories.size()];
        for(int i = 0; i < listCategories.size(); ++i) {
            allocated += listCategories.get(i).getTheoreticalAmount();
            category[i] = db.getTypeCategory(listCategories.get(i).getType()).getNameCategory();
        }
        allocated = Float.parseFloat(String.format("%.2f", allocated).replace(",", "."));
        db.closeDB();
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
            /**
             * Confirm the budget creation and go to home page
             * update the budget id of the user in the DB
             * @param v
             */
            public void onClick(View v) {
                Log.i("button", "confirmed");
                DatabaseHelper db = new DatabaseHelper(getApplicationContext());
                db.updateUserCurrentBudget((int) idNewBudget);
                db.closeDB();
                finish();
                //go to home
                Intent intent = new Intent(getApplicationContext(), Home.class);
                intent.putExtra(New_Budget_1.ID_NEW_BUDGET, idNewBudget);
                intent.putExtra("caller", "New_Budget_5"); //permet à la nouvelle activity de connaitre son lanceur
                startActivity(intent);
            }
        });
    }

    //récupérer les datas de la DB courrante

    /**
     * Get the data from the database and put it into the arraylists (category_name and allocated)
     */
    private void GetData() {
        // récup data

        DatabaseHelper db = new DatabaseHelper(getApplicationContext());
        List<CategoryType> categoryTypes = db.getAllCategoriesTypes();
        Available = 0f;
        for(int i = 0; i < categoryTypes.size(); ++i) {
            category_name.add(categoryTypes.get(i).getNameCategory());
            Allocated.add(Float.parseFloat(String.format("%.2f", db.getCategoryOfCategoryType((int) idNewBudget, categoryTypes.get(i).getNameCategory()).getTheoreticalAmount()).replace(",", ".")));
        }
        Available = Float.parseFloat(String.format("%.2f", db.getBudget((int) idNewBudget).getBudgetAmount()).replace(",", "."));
        db.closeDB();
    }

    /**
     * When a category is clicked, go back to the new budget 3 screen (setup allocated amount for each category)
     * @param pos
     */
    public void OnCategoryClicked(int pos){ //appuis sur une catégorie du recycler view

        Intent intent = new Intent(this.getApplicationContext(), New_Budget_3.class);
        String caller     = getIntent().getStringExtra("caller"); //caller contient le nom de la class appelante
        intent.putExtra("caller", caller); //permet à la nouvelle activity de connaitre son lanceur
        intent.putExtra("subcaller", "NB5"); //permet à la nouvelle activity de connaitre son lanceur
        intent.putExtra("pos", String.valueOf(pos)); //envoie la position a présélectionée
        intent.putExtra(New_Budget_1.ID_NEW_BUDGET, idNewBudget);
        startActivity(intent);
    }

    //permet le retour vers la page précédente

    /**
     * go back to the previous screen when the return button of the top bar is pressed
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();

                String caller     = getIntent().getStringExtra("caller"); //caller contient le nom de la class appelante
                if(caller.equals("Settings")){
                    Intent intent = new Intent(this, New_Budget_3.class);
                    intent.putExtra(New_Budget_1.ID_NEW_BUDGET, idNewBudget);
                    intent.putExtra("caller", "Settings"); //permet à la nouvelle activity de connaitre son lanceur
                    startActivity(intent);
                }
                else{
                    Intent intent = new Intent(this, New_Budget_3.class);
                    intent.putExtra("caller", "MAinActivity"); //permet à la nouvelle activity de connaitre son lanceur
                    intent.putExtra(New_Budget_1.ID_NEW_BUDGET, idNewBudget);
                    startActivity(intent);
                }
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * return true if the top bar is created successfully
     * @param menu
     * @return
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }


}