package com.example.cookutproject.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.cookutproject.R;
import com.example.cookutproject.data.CookUTViewModel;
import com.example.cookutproject.data.adapters.SemestreAdapter;
import com.example.cookutproject.models.Semestre;

import java.util.ArrayList;
import java.util.List;

public class saisieEvenementFragment extends Fragment implements AdapterView.OnItemSelectedListener {

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
        Spinner spinner = (Spinner) view.findViewById(R.id.spinner_type_event);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(requireContext(),
                R.array.idSemestre_Array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        CookUTViewModel cookUTViewModel = new ViewModelProvider(this).get(CookUTViewModel.class);

        view.findViewById(R.id.button_validerEvenement).setOnClickListener(view1 -> {
            Navigation.findNavController(view).navigate(R.id.action_saisieEvenementFragment_to_saisieFragment);
        });

        view.findViewById(R.id.button_annulerEvenement).setOnClickListener(view1 -> {
            Navigation.findNavController(view).navigate(R.id.action_saisieEvenementFragment_to_saisieFragment);
        });

        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Object item = adapterView.getItemAtPosition(i);
        if (item != null) {
            Toast.makeText(getContext(), item.toString(),
                    Toast.LENGTH_SHORT).show();
        }
        Toast.makeText(getContext(), "Selected",
                Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}