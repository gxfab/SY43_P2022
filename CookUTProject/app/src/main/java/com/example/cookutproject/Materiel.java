package com.example.cookutproject;

import java.util.ArrayList;

public class Materiel extends Budget{

    private int idMateriel;

    Materiel(float previsionnel, float depense, float recette, ArrayList<Facture> facture) {
        super(previsionnel, depense, recette, facture);
    }
}
