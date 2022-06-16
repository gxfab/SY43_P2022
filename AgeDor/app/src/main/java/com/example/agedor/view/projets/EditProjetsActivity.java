package com.example.agedor.view.projets;

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
import com.example.agedor.data.StorageProjets;
import com.example.agedor.view.dettes.DettesAdapter;
import com.example.agedor.view.dettes.EditDetteDialog;

import java.util.ArrayList;

public class EditProjetsActivity extends AppCompatActivity implements ProjetsDialog.DialogListener{
    private RecyclerView listeProjets;
    private ArrayList<StorageProjets> projets;
    private ProjetsAdapter monAdapter;
    private ProjetsAdapter.RecyclerViewClickListener listenerRecycler;

    private EditText m_nom;
    private EditText m_montant;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_projets);
        this.m_nom = findViewById(R.id.editTextNom);
        this.m_montant = findViewById(R.id.editTextMontant);

        listeProjets = findViewById(R.id.recycler_projets);
        setData();
        setAdapter();
    }

    private void setData(){
        DBHandler db = new DBHandler(this);
        this.projets = db.getProjets();

    }

    private void setAdapter(){

        setOnClickListener();
        monAdapter = new ProjetsAdapter(projets, listenerRecycler);
        listeProjets.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        listeProjets.setAdapter(monAdapter);

    }

    private void setOnClickListener() {
        listenerRecycler = new ProjetsAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                //Toast.makeText(getApplicationContext(), depenses.get(position).nom,Toast.LENGTH_SHORT).show();
                StorageProjets projet = projets.get(position);
                modifierProjet(projet);
            }
        };
    }

    private void modifierProjet(StorageProjets projet){
        ProjetsDialog dialog = new ProjetsDialog(projet);
        dialog.show(getSupportFragmentManager(),"dialog");
    }

    @Override
    public void modifier(String nom, String montant, String ancienNom) {

        DBHandler db = new DBHandler(this);

        db.deleteRow("PROJETS",ancienNom);
        db.addNewProjet(nom,Double.parseDouble(montant));
        projets = db.getProjets();
        setAdapter();

    }

    @Override
    public void supprimer(String nom){
        DBHandler db = new DBHandler(this);
        db.deleteRow("PROJETS",nom);

        projets = db.getProjets();
        setAdapter();
    }

    public void addProjet(View view) {
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
        db.addNewProjet(nom,montant);

        projets = db.getProjets();
        setAdapter();

    }


}