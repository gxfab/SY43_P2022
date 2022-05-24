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

import java.util.ArrayList;

import fr.sy43.studzero.R;

//récupération des catégories déjà existante : CTRL+F "récup data"
//remplir la BD : CTRL+F : "remplir la BD"

/**
 * 2nd screen of the creation of a new budget : select the categories you want to keep from the old budget
 */
public class New_Budget_2 extends AppCompatActivity {

    private CustomAdapter customAdapter;
    private ArrayList<String> category_name;
    private RecyclerView recyclerView;

    /**
     * enable the return button on the top bar
     * get the categories from the old budget
     * update the recycler view with these categories
     * setup a listener for the confirm button
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_budget2);

        //on viens forcément de l'écran settings
        getSupportActionBar().setTitle("Setup New Budget");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //init
        category_name = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);
        GetData();
        customAdapter = new CustomAdapter(this.getApplicationContext(), category_name);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getApplicationContext()));


        Button button = (Button) findViewById(R.id.NewBudget2_ConfirmButton);
        button.setOnClickListener(new View.OnClickListener() {
            /**
             * Add the categories the user want to keep to the DB
             * launch the intent of the next screen
             * @param v
             */
            public void onClick(View v) {
                //Ajouter les catégories que l'on garde à la BD
                SetSelectedCat(category_name, customAdapter.state);
                finish();
                //on viens forcement de settings pur cette page
                Intent intent = new Intent(getApplicationContext(), New_Budget_3.class);
                intent.putExtra("caller", "Settings"); //permet à la nouvelle activity de connaitre son lanceur
                startActivity(intent);
            }
        });

    }

    //récupérer les datas de la DB courrante

    /**
     * retrieve the name of the old budget's categories
     */
    private void GetData() {
        // récup data
        category_name.add("cat 1");
        category_name.add("cat 2");
        category_name.add("cat 3");
        category_name.add("cat 4");
        category_name.add("cat 5");
        category_name.add("cat 6");
        category_name.add("cat 7");
        category_name.add("cat 8");
        category_name.add("cat 9");
        category_name.add("cat 10");
        category_name.add("cat 11");
        category_name.add("cat 12");
    }

    /**
     * update the DB by adding the categories the user want to keep
     * display the selected categories (the one the user keep) in Log.I (debug)
     * @param category_name
     * @param state
     */
    private  void SetSelectedCat(ArrayList category_name,ArrayList state){
        //remplir la BD
        for(int i = 0; i < category_name.size(); ++i){
            if(state.get(i).equals(1)){//on garde cette catégorie
                Log.i("add cat", String.valueOf(category_name.get(i)));
            }
        }
    }


    //permet le retour vers la page précédente

    /**
     * handle the top bar inputs : if the user press the return button, we go back to the previous screen
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                //startActivity(new Intent(getApplicationContext(), New_Budget_1.class));
                //renvoye vers la page settings précédente
                Intent intent = new Intent(this, New_Budget_1.class);
                intent.putExtra("caller", "Settings"); //permet à la nouvelle activity de connaitre son lanceur
                startActivity(intent);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * return true if the top bar is sucessfully created
     * @param menu
     * @return
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    /**
     * override the onBackPressed method to "disable" it
     */
    @Override
    public void onBackPressed() {
    }
}