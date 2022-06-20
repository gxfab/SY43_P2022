package com.example.zeroday.views;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.zeroday.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //On récupère la barre de navigation
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //On séléctionne l'item "chart" par défaut
        bottomNavigationView.setSelectedItemId(R.id.chart);
        //On charge le frament correspondant à l'item "chart" par défaut
        loadFragment( new ChartFragment());

        //On définit le OnItemSelectedListener de façon à charger le bon fragment en fonction de l'item selectionné
        bottomNavigationView.setOnItemSelectedListener(item -> {

            switch (item.getItemId()){

                case R.id.chart:
                    loadFragment(new ChartFragment());
                    break;
                case R.id.add:
                    loadFragment(new AddFragment());
                    break;
                case R.id.piggy:
                    loadFragment(new PiggyFragment());
                    break;
            }
            return true;
        });

    }

    //Fonction permettant de charger le fragment passé en paramètres dans le conteneur central de l'activité
    private void loadFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container,fragment);
        transaction.disallowAddToBackStack();
        transaction.commit();
    }
}
