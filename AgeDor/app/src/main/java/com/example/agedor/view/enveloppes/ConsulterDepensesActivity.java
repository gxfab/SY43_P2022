package com.example.agedor.view.enveloppes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.agedor.R;
import com.example.agedor.data.DBHandler;
import com.example.agedor.data.StorageDepenses;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
        this.depenses = db.getDepenses();

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
    public void modifier(String nom, String categorie, String date, String montant, String ancienNom) {
        String s = nom + categorie + date + montant;
        //Toast.makeText(getApplicationContext(), s,Toast.LENGTH_SHORT).show();

        DBHandler db = new DBHandler(this);

        int index = 0;
        for(int i = 0; i < depenses.size(); i++){
            if(depenses.get(i).equals(ancienNom)){
                index = i;
            }
        }

        db.deleteRow("DEPENSES",ancienNom);
        db.addNewDepense(categorie,date,nom,Double.parseDouble(montant));

        // Mise à jour graphique
        depenses = db.getDepenses();
        setAdapter();
    }

    @Override
    public void supprimer(String nom){
        DBHandler db = new DBHandler(this);
        db.deleteRow("DEPENSES",nom);

        // Mise à jour graphique
        depenses = db.getDepenses();
        setAdapter();
    }
}