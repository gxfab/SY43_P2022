package com.example.agedor;

import com.example.agedor.data.DBHandler;
import com.example.agedor.data.StorageCategories;
import com.example.agedor.data.StorageDepenses;

import java.util.ArrayList;

public class CalculateurBudget {
    public static String calculerBudget() {
        String phrase;
        DBHandler db = new DBHandler(MainActivity.context);
        Double revenus = db.getSum("REVENUS");
        Double projets = db.getSum("PROJETS");
        Double extra = db.getSum("EXTRA");
        Double facture = db.getSum("FACTURES");
        Double dettes = db.getSum("DETTES");
        Double categories = db.getSum("CATEGORIES");
        Double restant = revenus - projets - extra - facture - dettes - categories;
        if (restant < 0.0) {
            phrase = "Vous êtes au dessus du budget de "+ -1 * restant + "€, veuillez éditer certains budgets.";
        }
        else if (restant == 0.0) {
            phrase = "Tout est bon pour vous !";
        }
        else {
            phrase = "Vous avez " + restant + "€ de surplus qui a été attribué à la catégorie \"épargnes\".";
            processusEpargnes(restant);
        }
        String phrase_diff = diffCategorie();
        if (phrase.startsWith("Tout est bon")) {
            phrase = phrase_diff;
        }
        return phrase;
    }

    public static void processusEpargnes(Double restant) {
        boolean entree = false;
        DBHandler db = new DBHandler(MainActivity.context);
        ArrayList<StorageCategories> epargnes = db.getCategories();
        for (StorageCategories epargne : epargnes) {
            if (epargne.nom.equals("épargnes")) {
                db.deleteRow("CATEGORIES","épargnes");
                db.addNewCategorie("épargnes", restant + epargne.montant);
                entree = true;
                break;
            }
        }
        if (!entree) {
            db.addNewCategorie("épargnes", restant);
        }
    }

    public static String diffCategorie() {
        String where;
        Double somme_depenses = 0.0;
        DBHandler db = new DBHandler(MainActivity.context);
        ArrayList<StorageCategories> categories = db.getCategories();
        for (StorageCategories categorie : categories) {
            where = "WHERE CAT = '" + categorie.nom + "'";
            ArrayList<StorageDepenses> depenses = db.getDepenses(where);
            for (StorageDepenses depense : depenses) {
                somme_depenses += depense.montant;
            }
            Double diff = somme_depenses - categorie.montant;
            if (diff > 0.0) {
                return "Vous dépassez de " + diff + "€ le budget alloué à l'enveloppe \"" + categorie.nom + "\"." ;
            }
        }
        return "Tout est bon pour vous !";
    }
}
