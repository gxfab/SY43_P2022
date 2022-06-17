package com.example.agedor.view.enveloppes.categories;

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
import com.example.agedor.view.enveloppes.depenses.ListeDepensesAdapter;

import java.util.ArrayList;

public class EditCategoriesActivity extends AppCompatActivity implements EditCategorieDialog.DialogListener {

    private RecyclerView listeCategories;
    private ArrayList<StorageCategories> categories;
    private CategoriesAdapter monAdapter;
    private CategoriesAdapter.RecyclerViewClickListener listenerRecycler;

    private EditText m_nom;
    private EditText m_montant;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_categories);
        this.m_nom = findViewById(R.id.editTextNom);
        this.m_montant = findViewById(R.id.editTextMontant);

        listeCategories = findViewById(R.id.recycler_categories);
        setData();
        setAdapter();
        getSupportActionBar().setTitle("Editer les enveloppes");
    }

    private void setData(){
        DBHandler db = new DBHandler(this);
        this.categories = db.getCategories();

    }

    private void setAdapter(){

        setOnClickListener();
        monAdapter = new CategoriesAdapter(categories, listenerRecycler);
        listeCategories.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        listeCategories.setAdapter(monAdapter);

    }

    private void setOnClickListener() {
        listenerRecycler = new CategoriesAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                //Toast.makeText(getApplicationContext(), depenses.get(position).nom,Toast.LENGTH_SHORT).show();
                StorageCategories categorie = categories.get(position);
                modifierCategorie(categorie);
            }
        };
    }

    private void modifierCategorie(StorageCategories categorie){
        EditCategorieDialog dialog = new EditCategorieDialog(categorie);
        dialog.show(getSupportFragmentManager(),"dialog");
    }

    @Override
    public void modifier(String nom, String montant, String ancienNom) {

        DBHandler db = new DBHandler(this);

        db.deleteRow("CATEGORIES",ancienNom);
        db.addNewCategorie(nom,Double.parseDouble(montant));
        categories = db.getCategories();
        setAdapter();

    }

    @Override
    public void supprimer(String nom){
        DBHandler db = new DBHandler(this);
        db.deleteRow("CATEGORIES",nom);

        categories = db.getCategories();
        setAdapter();
    }

    public void addCategorie(View view) {
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
        db.addNewCategorie(nom,montant);

        categories = db.getCategories();
        setAdapter();

    }

}