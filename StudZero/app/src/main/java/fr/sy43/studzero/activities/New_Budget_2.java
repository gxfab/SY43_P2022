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
public class New_Budget_2 extends AppCompatActivity {

    private CustomAdapter customAdapter;
    private ArrayList<String> category_name;
    private RecyclerView recyclerView;

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

    private  void SetSelectedCat(ArrayList category_name,ArrayList state){
        //remplir la BD
        for(int i = 0; i < category_name.size(); ++i){
            if(state.get(i).equals(1)){//on garde cette catégorie
                Log.i("add cat", String.valueOf(category_name.get(i)));
            }
        }
    }


    //permet le retour vers la page précédente
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

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public void onBackPressed() {
    }
}