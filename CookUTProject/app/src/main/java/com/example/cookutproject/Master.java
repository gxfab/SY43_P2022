package com.example.cookutproject;

import java.util.ArrayList;
import java.util.List;

public class Master {

    private List<Atelier> atelierList = new ArrayList<Atelier>();
    private List<AutreEvenement> autreEventList = new ArrayList<AutreEvenement>();
    private List<Materiel> materielList = new ArrayList<Materiel>();
    private List<Repas> repasList = new ArrayList<Repas>();

    Master(){ //TODO : initialiser les valeurs en les récupérants dans la bdd
        atelierList = null;
        autreEventList = null;
        materielList = null;
        repasList = null;
    }

    public void AddAtelier(Atelier atelier) {
        this.atelierList.add(atelier);
    }

    public void AddAutreEvenement(AutreEvenement autreEvenement) {
        this.autreEventList.add(autreEvenement);
    }

    public void AddMateriel (Atelier atelier) {
        this.atelierList.add(atelier);
    }
}
