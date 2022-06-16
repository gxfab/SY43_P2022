package com.example.agedor.view.introduction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.agedor.MainActivity;
import com.example.agedor.R;
import com.example.agedor.data.DBHandler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Fragment1 extends AppCompatActivity {

    public String nom = ReadTextFile("nom.txt");

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_1);
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
            db.addNewRevenus(nom_txt, Double.parseDouble(montant_txt));
            Toast.makeText(this, "Ajouté avec succés !", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, Fragment2.class);
            startActivity(intent);
        }
    }

    public void precedent(View view){
        deleteFichier("nom.txt");
        Intent intent = new Intent(this, IntroActivity.class);
        // Supprimer le fichier texte contenant le nom de la personne
        startActivity(intent);
    }

    public void deleteFichier(String fileName) {
        File dir = getFilesDir();
        File file = new File(dir, fileName);
        file.delete();
    }

    public String ReadTextFile(String fileName) {
        File path = MainActivity.getContext().getFilesDir();
        File readFrom = new File(path, fileName);
        byte[] content = new byte[(int) readFrom.length()];
        try {
            FileInputStream stream = new FileInputStream(readFrom);
            stream.read(content);
            String text = new String(content);
            stream.close();
            return text;
        } catch (IOException e) {
            e.printStackTrace();
            return e.toString();
        }
    }
}