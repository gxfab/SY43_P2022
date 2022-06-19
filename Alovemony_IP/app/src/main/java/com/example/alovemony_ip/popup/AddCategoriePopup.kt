package com.example.alovemony_ip.popup

import android.app.Dialog
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.example.alovemony_ip.MainActivity
import com.example.alovemony_ip.R
import com.example.alovemony_ip.model.CategorieModel
import com.example.alovemony_ip.repository.CategorieRepository
import java.util.*

class AddCategoriePopup(context : MainActivity) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.popup_add_categorie)

        // on assigne le bouton close, permettant de fermer la popup
        val closeButton = findViewById<ImageView>(R.id.popup_categorie_close_button)
        closeButton.setOnClickListener {
            dismiss()
        }
        // on assigne le bouton add, permettant d'ajouter la catégorie et de fermer la popup
        val addCategorie = findViewById<Button>(R.id.popup_add_categorie_finishbutton)
        addCategorie.setOnClickListener {
            sendForm()
            dismiss()
        }


    }
    /*
         Fonction permettant d'ajouter une catégorie à la base de donnée en fonction des données entrée par l'utilisateur
        */
    private fun sendForm()
    {
        val repo = CategorieRepository()

        val categorieName = findViewById<EditText>(R.id.popup_add_categorie_name_edit).text.toString()
        val categorieMontantValue = findViewById<EditText>(R.id.popup_add_categorie_montant_edit).text.toString()

        val categorieMontant = Integer.parseInt(categorieMontantValue)


        val categorie = CategorieModel(
            UUID.randomUUID().toString(),
            categorieName,
            0,
            categorieMontant
        )
        repo.insertCategorie(categorie)
    }

}