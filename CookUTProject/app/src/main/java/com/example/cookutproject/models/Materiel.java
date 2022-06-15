package com.example.cookutproject.models;

import java.util.ArrayList;

public class Materiel extends Budget{

    private int idMateriel;

    Materiel(float previsionnel, float depense, float recette, ArrayList<Operation> operation) {
        super(previsionnel, depense, recette, operation);
    }
}
