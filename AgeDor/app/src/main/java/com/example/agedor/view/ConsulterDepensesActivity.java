package com.example.agedor.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.agedor.R;
import com.example.agedor.data.StorageDepenses;

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
        this.depenses = new ArrayList<>();
        depenses.add(new StorageDepenses(0,"14/06/22","name1",(double)25));
        depenses.add(new StorageDepenses(0,"12/05/22","name2",(double)8));
        depenses.add(new StorageDepenses(0,"08/04/22","name3",(double)12));
        depenses.add(new StorageDepenses(0,"14/06/22","name4",(double)25));
        depenses.add(new StorageDepenses(0,"12/05/22","name5",(double)8));
        depenses.add(new StorageDepenses(0,"08/04/22","name6",(double)12));
        depenses.add(new StorageDepenses(0,"14/06/22","name7",(double)25));
        depenses.add(new StorageDepenses(0,"12/05/22","name8",(double)8));
        depenses.add(new StorageDepenses(0,"08/04/22","name9",(double)12));
        depenses.add(new StorageDepenses(0,"14/06/22","name10",(double)25));
        depenses.add(new StorageDepenses(0,"12/05/22","name11",(double)8));
        depenses.add(new StorageDepenses(0,"08/04/22","name12",(double)12));
        depenses.add(new StorageDepenses(0,"14/06/22","name13",(double)25));
        depenses.add(new StorageDepenses(0,"12/05/22","name14",(double)8));
        depenses.add(new StorageDepenses(0,"08/04/22","name15",(double)12));
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
    public void applyTexts(String nom, String categorie, String date, String montant) {
        String s = nom + categorie + date + montant;
        Toast.makeText(getApplicationContext(), s,Toast.LENGTH_SHORT).show();
    }
}