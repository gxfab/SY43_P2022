package com.example.agedor.view.introduction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.agedor.MainActivity;
import com.example.agedor.R;
import com.example.agedor.data.DBHandler;

public class Fragment5 extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_5);
    }

    public void suivant(View view){
        EditText nom = findViewById(R.id.editTextNom);
        EditText montant = findViewById(R.id.editTextMontant);
        String nom_txt = nom.getText().toString();
        String montant_txt = montant.getText().toString();
        if (nom_txt.equals("") || montant_txt.equals("")) {
            Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_LONG).show();
        }
        else {
            DBHandler db = new DBHandler(this);
            db.addNewCategorie(nom_txt, Double.parseDouble(montant_txt));
            Toast.makeText(this, "Ajouté avec succés !", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    public void precedent(View view){
        DBHandler db = new DBHandler(this);
        db.deleteRow("PROJETS", db.getRevenus().get(0).nom);
        Intent intent = new Intent(this, Fragment4.class);
        startActivity(intent);
    }
}