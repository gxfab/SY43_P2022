package com.example.cookutproject.models;

import java.util.ArrayList;

public class AutreEvenement extends  Budget{

    private int idAutre;

    AutreEvenement(float previsionnel, float depense, float recette, ArrayList<Operation> operation) {
        super(previsionnel, depense, recette, operation);
    }


}
