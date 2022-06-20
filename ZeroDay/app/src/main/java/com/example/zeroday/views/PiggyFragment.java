package com.example.zeroday.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import com.example.zeroday.R;


public class PiggyFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_piggy, container, false);

        //Ici : chargement du fragment affichant les différentes catégories de dépenses avec l'argent dépensé acctuellement
        // et l'argent dépensé prévu par le budget prévisionnel ( manque de temps pour l'implémentation )

        return view;
    }
}
