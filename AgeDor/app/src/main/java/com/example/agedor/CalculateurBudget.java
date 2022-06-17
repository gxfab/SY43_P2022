package com.example.agedor;

import android.util.Log;

import com.example.agedor.data.DBHandler;
import com.example.agedor.data.StorageCategories;

import java.util.ArrayList;

public class CalculateurBudget {
    public static String calculerBudget() {
        String phrase;
        DBHandler db = new DBHandler(MainActivity.context);
        Double revenus = db.getSum("REVENUS");
        Double depenses = db.getSum("DEPENSES");
        Double projets = db.getSum("PROJETS");
        Double extra = db.getSum("EXTRA");
        Double facture = db.getSum("FACTURES");
        Double dettes = db.getSum("DETTES");
        Double categories = db.getSum("CATEGORIES");
        Double restant = revenus - depenses - projets - extra - facture - dettes - categories;
        Log.i("MainActivity", "restant : " + restant);
        if (restant < 0.0) {
            phrase = "Vous êtes au dessus du budget de "+ -1 * restant + "€, veuillez éditer certains budgets.";
        }
        else if (restant == 0.0) {
            phrase = "Tout est bon pour vous !";
        }
        else {
            phrase = "Vous avez " + restant + "€ de surplus non attribué qui a été attribué à la catégorie \"épargnes\".";
            processusEpargnes(restant);
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
}
