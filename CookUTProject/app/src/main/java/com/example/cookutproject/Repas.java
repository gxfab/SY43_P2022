package com.example.cookutproject;

import java.util.ArrayList;

public class Repas extends Budget{

    private int idRepas;

    Repas(float previsionnel, float depense, float recette, ArrayList<Facture> facture) {
        super(previsionnel, depense, recette, facture);
    }

}
