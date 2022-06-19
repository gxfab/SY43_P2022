package com.example.alovemony_ip.repository

import android.app.Dialog
import android.net.Uri
import android.os.Bundle
import android.view.Window
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.alovemony_ip.R
import com.example.alovemony_ip.adapter.ProjetGlobalAdapter
import com.example.alovemony_ip.model.ProjetGlobalModel

class ProjetGlobalPopup(
    private val adapter: ProjetGlobalAdapter,
    private val currentProjetGlobal: ProjetGlobalModel
) : Dialog(adapter.context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.popup_projet_details)
        setupComponent()
        setupCloseButton()
        setupDeleteButton()
    }

    private fun setupDeleteButton() {
        findViewById<ImageView>(R.id.delete_button).setOnClickListener {
            //supprimer le projet de la base de donnee
            val repo = ProjetGlobalRepository()
            repo.deleteProjetGlobal(currentProjetGlobal)
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
        Glide.with(adapter.context).load(Uri.parse(currentProjetGlobal.imageUrl)).into(projetImage)

        // actualiser le nom du projet
        findViewById<TextView>(R.id.popup_projet_name).text = currentProjetGlobal.name

        // actualiser la description du projet
        findViewById<TextView>(R.id.popup_projet_desrcription_subtitle).text = currentProjetGlobal.description

        // actualiser le montant du projet
        findViewById<TextView>(R.id.popup_projet_montant_subtitle).text = currentProjetGlobal.montant.toString()

        findViewById<TextView>(R.id.popup_montant_global_item).text = currentProjetGlobal.montantGlobal.toString()

        findViewById<ProgressBar>(R.id.popup_progressBarProjet).progress = currentProjetGlobal.montant


    }
}