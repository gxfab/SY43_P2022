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

    public List<Atelier> getAtelierList() {
        return atelierList;
    }

    public List<AutreEvenement> getAutreEventList() {
        return autreEventList;
    }

    public List<Materiel> getMaterielList() {
        return materielList;
    }

    public List<Repas> getRepasList() {
        return repasList;
    }

    public  <T extends Budget> float sumRecetteEvenement(List<T> t){
        float sommeRecette = 0;
        int i=0;
        while (t.get(i)!=null){
            sommeRecette += t.get(i).getRecette();
        }
        return sommeRecette;
    }

    public  <T extends Budget> float sumDepenseEvenement(List<T> t){
        float sommeDepense = 0;
        int i=0;
        while (t.get(i)!=null){
            sommeDepense += t.get(i).getRecette();
        }
        return sommeDepense;
    }
}
