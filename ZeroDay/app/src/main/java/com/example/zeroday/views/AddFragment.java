package com.example.zeroday.views;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.zeroday.R;

/** Fragment permettant d'ajouter une dépense ou un revenu de manière spontanée.
 * @author Zero Day
 * @version 1.0
 */
public class AddFragment extends Fragment {

    /** Fonction appelée à la création du fragment.
     * @param inflater Inflater du layout
     * @param container Conteneur du fragment.
     * @param savedInstanceState
     * @return Le fragment
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add, container, false);

        //On récupère le boutton d'ajout d'un revenu et on lui affecte un OnClickListener qui lance l'activité correspondante
        Button incomeButton = view.findViewById(R.id.add_income_button);
        incomeButton.setOnClickListener( new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(getActivity(),ExpenseActivity.class);
                intent.putExtra("inputType", false); //On lance l'activité en mode income
                intent.putExtra("outputType",false);
                startActivity(intent);
            }
        });

        //On récupère le boutton d'ajout d'une dépense et on lui affecte un OnClickListener qui lance l'activité correspondante
        Button expenseButton = view.findViewById(R.id.add_expense_button);
        expenseButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),ExpenseActivity.class);
                intent.putExtra("inputType", true); //On lance l'activité en mode expense
                intent.putExtra("outputType",false);
                startActivity(intent);
            }
        });


        return view;
    }
}
