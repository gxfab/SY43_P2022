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

public class saisieEvenementFragment extends Fragment {

    private View view;
    private CookUTViewModel cookUTViewModel;
    public saisieEvenementFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_saisie_evenement, container, false);
        cookUTViewModel = new ViewModelProvider(this).get(CookUTViewModel.class);

        view.findViewById(R.id.button_validerEvenement).setOnClickListener(view1 -> {
            Navigation.findNavController(view).navigate(R.id.action_saisieEvenementFragment_to_saisieFragment);
        });

        view.findViewById(R.id.button_annulerEvenement).setOnClickListener(view1 -> {
            Navigation.findNavController(view).navigate(R.id.action_saisieEvenementFragment_to_saisieFragment);
        });

        return view;
    }
}