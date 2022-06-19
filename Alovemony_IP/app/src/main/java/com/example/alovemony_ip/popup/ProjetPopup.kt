package com.example.alovemony_ip.popup

import android.app.Dialog
import android.net.Uri
import android.os.Bundle
import android.view.Window
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.alovemony_ip.R
import com.example.alovemony_ip.adapter.ProjetAdapter
import com.example.alovemony_ip.model.ProjetModel
import com.example.alovemony_ip.repository.ProjetRepository

class ProjetPopup(
    private val adapter: ProjetAdapter,
    private val currentProjet: ProjetModel
) : Dialog(adapter.context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.popup_projet_details)
        // on assigne les données de la popup en fonction de la dépense selectionné
        setupComponent()
        // on assigne le bouton close, permettant de fermer la popup
        setupCloseButton()
        // on assigne le bouton delete, permettant de supprimer la depense selectionné
        setupDeleteButton()
    }

    private fun setupDeleteButton() {
        findViewById<ImageView>(R.id.delete_button).setOnClickListener {
            //supprimer le projet de la base de donnee
            val repo = ProjetRepository()
            repo.deleteProjet(currentProjet)
            dismiss()
        }
    }

    private fun setupCloseButton() {
        findViewById<ImageView>(R.id.close_button).setOnClickListener{
            //fermer la fenetre
            dismiss()
        }
    }

    private fun setupComponent() {
        // actualiser l'image du projet
        val projetImage = findViewById<ImageView>(R.id.image_item)
        Glide.with(adapter.context).load(Uri.parse(currentProjet.imageUrl)).into(projetImage)

        // actualiser le nom du projet
        findViewById<TextView>(R.id.popup_projet_name).text = currentProjet.name

        // actualiser la description du projet
        findViewById<TextView>(R.id.popup_projet_desrcription_subtitle).text = currentProjet.description

        // actualiser le montant du projet
        findViewById<TextView>(R.id.popup_projet_montant_subtitle).text = currentProjet.montant.toString()

        findViewById<TextView>(R.id.popup_montant_global_item).text = currentProjet.montantGlobal.toString()

        findViewById<ProgressBar>(R.id.popup_progressBarProjet).progress = currentProjet.montant


    }
}