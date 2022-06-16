package com.example.agedor.view.enveloppes;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewStub;
import android.widget.EditText;

import com.example.agedor.R;
import com.example.agedor.data.DBHandler;
import com.example.agedor.data.StorageDepenses;

import java.util.ArrayList;

public class EditCategoriesActivity extends AppCompatActivity {

    public String m_Text = "";
    public Double m_Montant = 0.0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_categories);
    }



    public void changeCategorie(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();

        builder.setView(inflater.inflate(R.layout.dialog_edit_categorie, null))
                // Add action buttons
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        m_Text = ((EditText) ((AlertDialog) dialog).findViewById(R.id.nom)).getText().toString();
                        m_Montant = Double.parseDouble(((EditText) ((AlertDialog) dialog).findViewById(R.id.montant)).getText().toString());
                    }
                })
                .setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        builder.create();
        builder.show();
    }


    public void addCategorie(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();

        builder.setView(inflater.inflate(R.layout.dialog_edit_categorie, null))
                // Add action buttons
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        m_Text = ((EditText) ((AlertDialog) dialog).findViewById(R.id.nom)).getText().toString();
                        m_Montant = Double.parseDouble(((EditText) ((AlertDialog) dialog).findViewById(R.id.montant)).getText().toString());
                        addToDB();
                    }
                })
                .setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        builder.create();
        builder.show();

    }

    void addToDB(){

        DBHandler db = new DBHandler(this);
        db.addNewCategorie(m_Text,m_Montant);

    }




}