package com.example.agedor.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.agedor.R;
import com.example.agedor.data.StorageDepenses;

public class ModifierDepenseDialog extends AppCompatDialogFragment {

    private StorageDepenses valeursInitiale;

    private EditText editNom;
    private EditText editMontant;
    private Spinner editCategorie;
    private DatePicker editDate;

    private String nomModifie;
    private String montantModifie;
    private String categorieModifie;
    private String dateModifie;

    private DialogListener listener;

    ModifierDepenseDialog(StorageDepenses depense){
        this.valeursInitiale = depense;
    }



    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.popup_modifier_depense,null);

        builder.setView(view);
        builder.setTitle("Modifier la dépense");
        builder.setNegativeButton("Abandonner", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });

        builder.setPositiveButton("Modifier", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        builder.setNeutralButton("Supprimer", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                    nomModifie = editNom.getText().toString();
                    montantModifie = editMontant.getText().toString();
                    categorieModifie = editCategorie.getSelectedItem().toString();
                    int day = editDate.getDayOfMonth();
                    int month = editDate.getMonth();
                    int year = editDate.getYear();
                    dateModifie = (day+""+month+""+year);

                    listener.applyTexts(nomModifie,categorieModifie,dateModifie,montantModifie);
            }
        });


        editNom = view.findViewById(R.id.modifierNom);
        editNom.setText(valeursInitiale.nom);

        editMontant = view.findViewById(R.id.modifierMontant);
        editMontant.setText(String.valueOf(valeursInitiale.montant));

        editCategorie = view.findViewById(R.id.modifierCategorie);
        /*
        ArrayAdapter<String> array_spinner=(ArrayAdapter<String>)editCategorie.getAdapter();
        editCategorie.setSelection(array_spinner.getPosition(valeursInitiale.));*/



        String[] categories = new String[]{"toto","tata","tutu"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item,categories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        editCategorie.setAdapter(adapter);

        editCategorie.setSelection(2);
        editDate = view.findViewById(R.id.modifierDate);
        editDate.init(2022,06,15,null);

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        listener = (DialogListener) context;

    }

    public interface DialogListener{
        void applyTexts(String nom, String categorie, String date, String montant);
    }
}
