package fr.sy43.studzero;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class New_Budget_2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_budget2);

        //on viens forcément de l'écran settings
        getSupportActionBar().setTitle("Setup New Budget");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    //permet le retour vers la page settings sur l'appuis de la back arrow
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
}