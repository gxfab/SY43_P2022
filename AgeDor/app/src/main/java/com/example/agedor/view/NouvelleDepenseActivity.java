package com.example.agedor.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.agedor.MainActivity;
import com.example.agedor.R;

import java.util.Calendar;
import java.util.Date;

public class NouvelleDepenseActivity extends AppCompatActivity {

    private NouvelleDepenseActivity activity;

    public Button bValider;
    private Button bAbandonner;
    public EditText nom;
    private Spinner categorie;
    private EditText montant;
    private DatePicker date;

    // Variables à distribuer à la BD
    private String sNom;
    private String sCategorie;
    private String sDate;
    private String sMontant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nouvelle_depense);

        this.activity = this;

        this.bValider = (Button) findViewById(R.id.bValider);
        this.bAbandonner = (Button) findViewById(R.id.bAbandonner);

        this.nom = (EditText) findViewById(R.id.nomDepense);
        this.categorie = (Spinner) findViewById(R.id.spinnerCategorie);
        this.montant = (EditText) findViewById(R.id.montant);
        this.date = (DatePicker) findViewById(R.id.date);


        String[] categories = new String[]{"nourriture","loisirs","voiture"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,categories);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        this.categorie.setAdapter(adapter);


        bValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // On récupère les données dans le formulaire
                extractData();
                String s = nom + " " + categorie + " " + montant + " " + date;
                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();
                //showPopup();
                addToDB();
            }
        });

        bAbandonner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    public void showPopup(){

        AlertDialog.Builder popup = new AlertDialog.Builder(activity);
        popup.setTitle(this.sNom);
        popup.setMessage(this.sMontant + this.sDate + this.sCategorie);

        popup.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        popup.setNegativeButton("quitter", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        popup.show();
    }

    public void extractData(){

        this.sNom = new String(String.valueOf(nom.getText()));
        this.sCategorie = new String(String.valueOf(categorie.getSelectedItem()));
        this.sMontant = new String(String.valueOf(montant.getText()));

        int day = date.getDayOfMonth();
        int month = date.getMonth();
        int year = date.getYear();

        //Calendar calendar = Calendar.getInstance();
        //calendar.set(year,month,day);

        //this.sDate = new String(String.valueOf(calendar.getTime()));
        this.sDate = (day+""+month+""+year);
    }

    public void addToDB() {




    }



    public void valider(){

    }

    public void abandonner(){

    }


}
