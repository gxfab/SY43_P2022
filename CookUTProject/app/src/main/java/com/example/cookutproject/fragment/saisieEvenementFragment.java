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
import com.example.cookutproject.models.Evenement;
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
        Spinner spinner = (Spinner) view.findViewById(R.id.spinner_id_semestre);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(requireContext(),
                R.array.typeEvent_Array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Spinner spinner1 = (Spinner) view.findViewById(R.id.spinner_type_event);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(requireContext(),
                R.array.idSemestre_Array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);

        CookUTViewModel cookUTViewModel = new ViewModelProvider(this).get(CookUTViewModel.class);

        view.findViewById(R.id.button_validerEvenement).setOnClickListener(view1 -> {
            insertDatatoDatabase();
        });

        view.findViewById(R.id.button_annulerEvenement).setOnClickListener(view1 -> {
            Navigation.findNavController(view).navigate(R.id.action_saisieEvenementFragment_to_saisieFragment);
        });

        return view;
    }

    private void insertDatatoDatabase() {
        String nom = ((EditText) view.findViewById(R.id.nomEvenement)).getText().toString();
        String montantPrev = ((EditText) view.findViewById(R.id.montantPrevEvenement)).getText().toString();
        String type =((EditText) view.findViewById(R.id.typeEvenement)).getText().toString();
        String date = ((EditText) view.findViewById(R.id.dateEvenement)).getText().toString();
        String idSemestre = ((EditText) view.findViewById(R.id.idSemestreEvenement)).getText().toString();
        if(inputCheck(nom,montantPrev,type,date,idSemestre)) {
            Evenement e = new Evenement(type,nom,date,Float.parseFloat(String.valueOf(montantPrev)),0,0);
            cookUTViewModel.addEvenement(e);
            Toast.makeText(requireContext(),"Succesfully added!", Toast.LENGTH_LONG).show();
            Navigation.findNavController(view).navigate(R.id.action_saisieEvenementFragment_to_saisieFragment);
        }
        else
            Toast.makeText(requireContext(),"Please fill out all fields!", Toast.LENGTH_LONG).show();
    }

    private boolean inputCheck(String nom,String montantPrev,String type,String date,String idSemestre){
        return !(TextUtils.isEmpty(nom) && TextUtils.isEmpty(montantPrev) && TextUtils.isEmpty(type) && TextUtils.isEmpty(date) && TextUtils.isEmpty(idSemestre));
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