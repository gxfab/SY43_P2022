package com.example.cookutproject.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cookutproject.R;
import com.example.cookutproject.data.CookUTViewModel;
import com.example.cookutproject.models.Semestre;

public class SaisieSemestreFragment extends Fragment {

    View view;
    CookUTViewModel cookUTViewModel;
    public SaisieSemestreFragment() {
        // Required empty public constructor
    }

    @Override
    /**
     * affiche les éléments de la saisie d'un semestre dans un fragment
     */
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_saisie_semestre, container, false);
        cookUTViewModel = new ViewModelProvider(this).get(CookUTViewModel.class);

        view.findViewById(R.id.button_validerSemestre).setOnClickListener(view1 -> {
            insertDatatoDatabase();
        });

        view.findViewById(R.id.button_annulerSemestre).setOnClickListener(view1 -> {
            Navigation.findNavController(view).navigate(R.id.action_saisieSemestreFragment_to_saisieFragment);
        });

        return view;
    }

    /**
     * Ajoute les valeurs saisies dans la base de données
     */
    private void insertDatatoDatabase() {
        String nom = ((EditText) view.findViewById(R.id.nomSemestre)).getText().toString();
        String montantPrev = ((EditText) view.findViewById(R.id.montantPrevSemestre)).getText().toString();

        if(inputCheck(nom,montantPrev)) {
            Semestre s = new Semestre(nom,Float.parseFloat(String.valueOf(montantPrev)),0,0);
            cookUTViewModel.addSemestre(s);
            Toast.makeText(requireContext(),"Succesfully added!", Toast.LENGTH_LONG).show();
            Navigation.findNavController(view).navigate(R.id.action_saisieSemestreFragment_to_saisieFragment);
        }
        else
            Toast.makeText(requireContext(),"Please fill out all fields!", Toast.LENGTH_LONG).show();
    }

    /**
     * vérifie que les champs du formulaire sont bien remplis
     * @param nom
     * @param montantPrev
     * @return
     */
    private boolean inputCheck(String nom,String montantPrev){
        return !(TextUtils.isEmpty(nom) && TextUtils.isEmpty(montantPrev));
    }
}