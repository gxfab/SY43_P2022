package com.example.agedor.view.enveloppes.categories;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import android.widget.EditText;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.agedor.R;
import com.example.agedor.data.DBHandler;
import com.example.agedor.data.StorageCategories;


import java.util.ArrayList;

public class EditCategorieDialog extends AppCompatDialogFragment {

    private StorageCategories valeursInitiale;

    private EditText editNom;
    private EditText editMontant;

    private String nomModifie;
    private String montantModifie;

    private EditCategorieDialog.DialogListener listener;

    EditCategorieDialog(StorageCategories categorie){
        this.valeursInitiale = categorie;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_edit_categorie,null);

        builder.setView(view);
        builder.setTitle("Modifier la catégorie");
        builder.setNegativeButton("Abandonner", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });

        builder.setPositiveButton("Modifier", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                nomModifie = editNom.getText().toString();
                montantModifie = editMontant.getText().toString();

                listener.modifier(nomModifie, montantModifie, valeursInitiale.nom);
            }
        });

        builder.setNeutralButton("Supprimer", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                listener.supprimer(valeursInitiale.nom);
            }
        });


        editNom = view.findViewById(R.id.editTextNom);
        editNom.setText(valeursInitiale.nom);

        editMontant = view.findViewById(R.id.editTextMontant);
        editMontant.setText(String.valueOf(valeursInitiale.montant));

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        listener = (DialogListener) context;

    }

    public interface DialogListener{
        void modifier(String nom, String montant, String ancienNom);

        void supprimer(String name);
    }
}
