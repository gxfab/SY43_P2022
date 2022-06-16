package com.example.agedor.view.enveloppes.depenses;

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
import com.example.agedor.data.DBHandler;
import com.example.agedor.data.StorageCategories;
import com.example.agedor.data.StorageDepenses;

import java.util.ArrayList;

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
                nomModifie = editNom.getText().toString();
                montantModifie = editMontant.getText().toString();
                categorieModifie = editCategorie.getSelectedItem().toString();
                int day = editDate.getDayOfMonth();
                int month = editDate.getMonth();
                String s = String.valueOf(month);
                if(month<10){s = "0".concat(String.valueOf(month));}

                dateModifie = new String(day + "/" + s);

                boolean exeptionnel = false;
                if(valeursInitiale.categorie == "Dépense exceptionnelle"){
                    exeptionnel = true;
                }

                listener.modifier(nomModifie,categorieModifie,dateModifie,montantModifie, valeursInitiale.nom,exeptionnel);
            }
        });

        builder.setNeutralButton("Supprimer", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                boolean exeptionnel = false;
                if(valeursInitiale.categorie == "Dépense exceptionnelle"){
                    exeptionnel = true;
                }
                listener.supprimer(valeursInitiale.nom,exeptionnel);
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


        DBHandler db = new DBHandler(getContext());
        ArrayList<StorageCategories> cat = db.getCategories();

        ArrayList<String> cat2 = new ArrayList<>();
        for(int i = 0; i < cat.size(); i++){
            cat2.add(cat.get(i).nom);
        }


        int index = 0;
        for(int i = 0; i < cat.size(); i++){
            if(cat2.get(i).equals(valeursInitiale.categorie)){
                index = i;
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item,cat2);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        editCategorie.setAdapter(adapter);

        editCategorie.setSelection(index);




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
        void modifier(String nom, String categorie, String date, String montant, String ancienNom, boolean exep);

        void supprimer(String name, boolean exep);
    }
}
