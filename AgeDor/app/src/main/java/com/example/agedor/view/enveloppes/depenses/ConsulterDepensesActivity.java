package com.example.agedor.view.enveloppes.depenses;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.agedor.R;
import com.example.agedor.data.DBHandler;
import com.example.agedor.data.StorageDepenses;
import com.example.agedor.data.StorageExtra;

import java.util.ArrayList;

public class ConsulterDepensesActivity extends AppCompatActivity implements ModifierDepenseDialog.DialogListener {

    private RecyclerView listeDepenses;
    private ArrayList<StorageDepenses> depenses;
    private ListeDepensesAdapter monAdapter;
    private ListeDepensesAdapter.RecyclerViewClickListener listenerRecycler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consulter_depenses);
        listeDepenses = findViewById(R.id.consulter_depenses);
        setDepenses();
        setAdapter();

    }

    private void setDepenses(){

        DBHandler db = new DBHandler(this);

        ArrayList<StorageExtra> listeExtras = db.getExtra();

        ArrayList<StorageDepenses> extras = new ArrayList<>();

        for(int i = 0;i<listeExtras.size();i++){
            extras.add(new StorageDepenses("Dépense exeptionnelle",listeExtras.get(i).date_extra,listeExtras.get(i).nom,listeExtras.get(i).montant));
        }
        
        this.depenses = db.getDepenses();
        this.depenses.addAll(extras);


    }

    private void setAdapter(){

        setOnClickListener();
        monAdapter = new ListeDepensesAdapter(depenses, listenerRecycler);
        listeDepenses.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        listeDepenses.setAdapter(monAdapter);

    }

    private void setOnClickListener() {
        listenerRecycler = new ListeDepensesAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                //Toast.makeText(getApplicationContext(), depenses.get(position).nom,Toast.LENGTH_SHORT).show();
                StorageDepenses depense = depenses.get(position);
                modifierDepense(depense);
            }
        };
    }

    private void modifierDepense(StorageDepenses depense){
        ModifierDepenseDialog dialog = new ModifierDepenseDialog(depense);
        dialog.show(getSupportFragmentManager(),"dialog");
    }

    @Override
    public void modifier(String nom, String categorie, String date, String montant, String ancienNom, boolean exep) {

        if(exep == false) {
            DBHandler db = new DBHandler(this);

            int index = 0;
            for (int i = 0; i < depenses.size(); i++) {
                if (depenses.get(i).equals(ancienNom)) {
                    index = i;
                }
            }

            db.deleteRow("DEPENSES", ancienNom);
            db.addNewDepense(categorie, date, nom, Double.parseDouble(montant));

        }
        else {
            DBHandler db = new DBHandler(this);

            int index = 0;
            for (int i = 0; i < db.getExtra().size(); i++) {
                if (db.getExtra().get(i).equals(ancienNom)) {
                    index = i;
                }
            }

            db.deleteRow("EXTRAS", ancienNom);

            if(categorie.equals("extras")) {
                db.addNewExtra(date, nom, Double.parseDouble(montant));
            }
            else{
                db.addNewDepense(categorie, date, nom, Double.parseDouble(montant));
            }

        }

        // Mise à jour graphique
        setDepenses();
        setAdapter();

    }

    @Override
    public void supprimer(String nom, boolean exep) {
        if (exep == false) {
            DBHandler db = new DBHandler(this);
            db.deleteRow("DEPENSES", nom);
        }
        else{
            DBHandler db = new DBHandler(this);
            db.deleteRow("EXTRAS", nom);

        }
        // Mise à jour graphique
        setDepenses();
        setAdapter();
    }
}