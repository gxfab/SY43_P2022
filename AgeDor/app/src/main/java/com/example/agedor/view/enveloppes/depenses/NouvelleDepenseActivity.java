package com.example.agedor.view.enveloppes.depenses;

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

import com.example.agedor.R;
import com.example.agedor.data.DBHandler;
import com.example.agedor.data.StorageCategories;

import java.util.ArrayList;

public class NouvelleDepenseActivity extends AppCompatActivity {

    private NouvelleDepenseActivity activity;

    private final String EXTRAS = "extras";


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

        DBHandler db = new DBHandler(this);

        ArrayList<StorageCategories> categories = db.getCategories();

        ArrayList<String> cat2 = new ArrayList<>();
        //String[] categories2 = new String[categories.size()];

        for (int i = 0; i < categories.size(); i++) {
            cat2.add(categories.get(i).nom);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, cat2);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        this.categorie.setAdapter(adapter);


        bValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // On récupère les données dans le formulaire
                extractData();
                Toast.makeText(getApplicationContext(), "Dépense aoutée avec succès !", Toast.LENGTH_SHORT).show();
                //showPopup();


                if ((sCategorie).equals(EXTRAS)) { // Si la catégorie est de type dépense exeptionnelle (hors d'une enveloppe)
                    addExtraToDB();
                } else {
                    addToDB();
                }
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

        String day = String.valueOf(date.getDayOfMonth());
        int month = date.getMonth()+1;
        String s = String.valueOf(month);
        if(month<10){s = "0".concat(String.valueOf(month));}

        this.sDate = new String(day + "/" + s);
    }

    public void addToDB() {

        DBHandler db = new DBHandler(this);

        db.addNewDepense(sCategorie,sDate,sNom,Double.parseDouble(sMontant));

    }

    public void addExtraToDB(){

        DBHandler db = new DBHandler(this);

        db.addNewExtra(sDate,sNom,Double.parseDouble(sMontant));

    }



    public void valider(){

    }

    public void abandonner(){

    }


}
