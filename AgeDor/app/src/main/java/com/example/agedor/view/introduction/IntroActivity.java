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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class IntroActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("IntroActivity", "onCreate");
        super.onCreate(savedInstanceState);
        Log.i("IntroActivity", "onCreate");
        setContentView(R.layout.intro);
        Log.i("IntroActivity", "onCreate");
    }

    public void suivant(View view){
        EditText nom = findViewById(R.id.editTextNom);
        String nom_txt = nom.getText().toString();
        if (nom_txt.equals("")) {
            Toast.makeText(this, "Veuillez entrer un nom", Toast.LENGTH_LONG).show();
        }
        else {
            // créer un fichier avec le prénom de la personne
            writeToFile("nom.txt", nom_txt);
            Intent intent = new Intent(this, Fragment1.class);
            startActivity(intent);
        }
    }

    public void writeToFile(String fileName, String content) {
        File path = MainActivity.getContext().getFilesDir();
        try {
            // Create file
            FileOutputStream writer = new FileOutputStream(new File(path, fileName));
            writer.write(content.getBytes());
            writer.close();
        } catch (IOException e) {
            Log.e("writeToFile", "File write failed: " + e.toString());
        }
    }


}