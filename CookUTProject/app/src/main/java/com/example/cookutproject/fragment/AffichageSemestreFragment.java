package com.example.cookutproject.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cookutproject.R;
import com.example.cookutproject.data.CookUTViewModel;
import com.example.cookutproject.data.adapters.SemestreAdapter;

public class AffichageSemestreFragment extends Fragment {

    public AffichageSemestreFragment() {
        // Required empty public constructor
    }

    @Override
    /**
     * Set le fragment qui affiche les données stockées dans la BDD concernant les semestres
     */
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_affichage_semestre, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerviewSemestre);
        SemestreAdapter adapter = new SemestreAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        CookUTViewModel cookUTViewModel = new ViewModelProvider(this).get(CookUTViewModel.class);

        cookUTViewModel.getReadAllSemestre().observe(getViewLifecycleOwner(),s ->{
            adapter.setSemestreList(s);
        });

        return view;
    }
}