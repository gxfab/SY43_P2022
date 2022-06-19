package com.example.alovemony_ip.popup

import android.app.Dialog
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.alovemony_ip.R
import com.example.alovemony_ip.connexionactivities.ConnexionCategorieActivity
import com.example.alovemony_ip.model.CategorieModel
import com.example.alovemony_ip.repository.CategorieRepository

class CategorieModifMontant(context :AppCompatActivity,val currentCategorie : CategorieModel) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.popup_categorie_montant_prev)
        val textNameCategorie = findViewById<TextView>(R.id.popup_add_categorie_name)
        textNameCategorie.text  = currentCategorie.name


        setupFinishButton()
        setupCloseButton()
    }

    // on assigne le bouton  finish, permettant d'actualiser les données entrée
    private fun setupFinishButton() {
        findViewById<Button>(R.id.popup_connexion_add_categorie_finishbutton).setOnClickListener {
            sendForm()
            dismiss()
        }
    }
    // on assigne le bouton close, permettant de fermer la popup
    private fun setupCloseButton(){
        findViewById<ImageView>(R.id.popup_categorie_modif_close_button).setOnClickListener {
            dismiss()
        }
    }
     /*
         Fonction permettant de modifier le montant prévu d'une catégorie en fonction des données entrée par l'utilisateur
        */
    fun sendForm()
    {
        val repo_categorie = CategorieRepository()
        val montantPrevValue =  findViewById<EditText>(R.id.popup_add_categorie_montant_edit).text.toString()
        currentCategorie.montantPrev = montantPrevValue.toInt()

        repo_categorie.updateCategorie(currentCategorie)

    }

}