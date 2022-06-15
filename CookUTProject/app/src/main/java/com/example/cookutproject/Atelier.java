package com.example.cookutproject;

import java.util.ArrayList;

public class Atelier extends Budget{

    private int idAtelier;

    Atelier(float previsionnel, float depense, float recette, ArrayList<Operation> operation) {
        super(previsionnel, depense, recette, operation);
    }
}
