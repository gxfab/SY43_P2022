package com.example.agedor.view.dettes;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.agedor.R;
import com.example.agedor.data.DBHandler;
import com.example.agedor.data.StorageCategories;
import com.example.agedor.data.StorageDettes;
import com.example.agedor.view.enveloppes.categories.CategoriesAdapter;
import com.example.agedor.view.enveloppes.categories.EditCategorieDialog;

import java.util.ArrayList;

public class EditDettesActivity extends AppCompatActivity implements EditDetteDialog.DialogListener{

    private RecyclerView listeDettes;
    private ArrayList<StorageDettes> dettes;
    private DettesAdapter monAdapter;
    private DettesAdapter.RecyclerViewClickListener listenerRecycler;

    private EditText m_nom;
    private EditText m_montant;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_dettes);
        this.m_nom = findViewById(R.id.editTextNom);
        this.m_montant = findViewById(R.id.editTextMontant);

        listeDettes = findViewById(R.id.recycler_dettes);
        setData();
        setAdapter();
        getSupportActionBar().setTitle("Editer les dettes");
    }

    private void setData(){
        DBHandler db = new DBHandler(this);
        this.dettes = db.getDettes();

    }

    private void setAdapter(){

        setOnClickListener();
        monAdapter = new DettesAdapter(dettes, listenerRecycler);
        listeDettes.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        listeDettes.setAdapter(monAdapter);

    }

    private void setOnClickListener() {
        listenerRecycler = new DettesAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                //Toast.makeText(getApplicationContext(), depenses.get(position).nom,Toast.LENGTH_SHORT).show();
                StorageDettes dette = dettes.get(position);
                modifierDette(dette);
            }
        };
    }

    private void modifierDette(StorageDettes dette){
        EditDetteDialog dialog = new EditDetteDialog(dette);
        dialog.show(getSupportFragmentManager(),"dialog");
    }

    @Override
    public void modifier(String nom, String montant, String ancienNom) {

        DBHandler db = new DBHandler(this);

        db.deleteRow("DETTES",ancienNom);
        db.addNewDette(nom,Double.parseDouble(montant));
        dettes = db.getDettes();
        setAdapter();

    }

    @Override
    public void supprimer(String nom){
        DBHandler db = new DBHandler(this);
        db.deleteRow("DETTES",nom);

        dettes = db.getDettes();
        setAdapter();
    }

    public void addDette(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();

        view = inflater.inflate(R.layout.dialog_edit_categorie,null);
        builder.setView(view);

        m_nom = view.findViewById(R.id.editTextNom);
        m_montant = view.findViewById(R.id.editTextMontant);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                String sNom = m_nom.getText().toString();
                Double dMontant = Double.parseDouble(String.valueOf(m_montant.getText()));
                Toast.makeText(getApplicationContext(),"Ajoutée avec succès !",Toast.LENGTH_LONG).show();
                addToDB(sNom,dMontant);
            }
        });

        builder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });




        builder.create();
        builder.show();

    }

    void addToDB(String nom, Double montant){

        DBHandler db = new DBHandler(this);
        db.addNewDette(nom,montant);

        dettes = db.getDettes();
        setAdapter();

    }


}