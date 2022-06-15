package com.example.cookutproject;

import java.util.ArrayList;

public class AutreEvenement extends  Budget{

    private int idAutre;

    AutreEvenement(float previsionnel, float depense, float recette, ArrayList<Facture> facture) {
        super(previsionnel, depense, recette, facture);
    }


}
