package com.example.cookutproject.models;

import java.util.ArrayList;
import java.util.List;

public class Master {

    private List<Evenement> evenementList = new ArrayList<Evenement>();

    Master(){ //TODO : initialiser les valeurs en les récupérants dans la bdd
        evenementList = null;
    }

    /**
     * récupération de la liste d'évènements
     * @return
     */
    public List<Evenement> getEvenementList() {
        return evenementList;
    }

    /**
     * Setter pour la liste d'évènements
     * @param evenementList
     */
    public void setEvenementList(List<Evenement> evenementList) {
        this.evenementList = evenementList;
    }

    /**
     * Fonction permettant le calcul de la totalité des recettes faites sur un évènement
     * @return somme des recettes
     */
    public float sumRecetteEvenement(){
        float sommeRecette = 0;
        int i=0;
        while (this.evenementList.get(i)!=null){
            sommeRecette += this.evenementList.get(i).getAmountRecipe();
        }
        return sommeRecette;
    }

    /**
     * Fonction permettant le calcul de la totalité des dépenses effectuée lors d'un évènement
     * @param t
     * @param <T>
     * @return somme des dépenses
     */
    public  <T extends Budget> float sumDepenseEvenement(List<T> t){
        float sommeDepense = 0;
        int i=0;
        while (t.get(i)!=null){
            sommeDepense += t.get(i).getRecette();
        }
        return sommeDepense;
    }
}
