package com.example.agedor.view.factures;

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
import com.example.agedor.data.StorageDettes;
import com.example.agedor.data.StorageFactures;
import com.example.agedor.view.dettes.DettesAdapter;
import com.example.agedor.view.dettes.EditDetteDialog;

import java.util.ArrayList;

public class EditFacturesActivity extends AppCompatActivity implements FactureDialog.DialogListener {
    private RecyclerView listeFactures;
    private ArrayList<StorageFactures> factures;
    private FacturesAdapter monAdapter;
    private FacturesAdapter.RecyclerViewClickListener listenerRecycler;

    private EditText m_nom;
    private EditText m_montant;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_factures);
        this.m_nom = findViewById(R.id.editTextNom);
        this.m_montant = findViewById(R.id.editTextMontant);

        listeFactures = findViewById(R.id.recycler_factures);
        setData();
        setAdapter();
    }

    private void setData(){
        DBHandler db = new DBHandler(this);
        this.factures = db.getFactures();

    }

    private void setAdapter(){

        setOnClickListener();
        monAdapter = new FacturesAdapter(factures, listenerRecycler);
        listeFactures.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        listeFactures.setAdapter(monAdapter);

    }

    private void setOnClickListener() {
        listenerRecycler = new FacturesAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                //Toast.makeText(getApplicationContext(), depenses.get(position).nom,Toast.LENGTH_SHORT).show();
                StorageFactures facture = factures.get(position);
                modifierFacture(facture);
            }
        };
    }

    private void modifierFacture(StorageFactures facture){
        FactureDialog dialog = new FactureDialog(facture);
        dialog.show(getSupportFragmentManager(),"dialog");
    }

    @Override
    public void modifier(String nom, String montant, String ancienNom) {

        DBHandler db = new DBHandler(this);

        db.deleteRow("FACTURES",ancienNom);
        db.addNewFacture(nom,Double.parseDouble(montant));
        factures = db.getFactures();
        setAdapter();

    }

    @Override
    public void supprimer(String nom){
        DBHandler db = new DBHandler(this);
        db.deleteRow("FACTURES",nom);

        factures = db.getFactures();
        setAdapter();
    }

    public void addFacture(View view) {
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
        db.addNewFacture(nom,montant);

        factures = db.getFactures();
        setAdapter();

    }


}