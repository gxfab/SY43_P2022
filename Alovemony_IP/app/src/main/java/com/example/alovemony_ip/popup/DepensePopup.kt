package com.example.alovemony_ip.popup
import android.app.Dialog
import android.net.Uri
import android.os.Bundle
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.alovemony_ip.R
import com.example.alovemony_ip.adapter.DepenseAdapter
import com.example.alovemony_ip.model.DepenseModel
import com.example.alovemony_ip.repository.DepenseRepository

class DepensePopup(
    private val adapter: DepenseAdapter,
    private val currentDepense: DepenseModel
) : Dialog(adapter.context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.popup_depense_details)
        // on assigne les données de la popup en fonction de la dépense selectionné
        setupComponent()
        // on assigne le bouton close, permettant de fermer la popup
        setupCloseButton()
        // on assigne le bouton delete, permettant de supprimer la depense selectionné
        setupDeleteButton()
    }

    private fun setupDeleteButton() {
        findViewById<ImageView>(R.id.popup_depense_delete_button).setOnClickListener {
            //supprimer la depense de la base de donnee
            val repo = DepenseRepository()
            repo.deleteDepense(currentDepense)
            dismiss()
        }
    }

    private fun setupCloseButton() {
        findViewById<ImageView>(R.id.popup_depense_close_button).setOnClickListener{
            //fermer la fenetre
            dismiss()
        }
    }

    private fun setupComponent() {
        // actualiser l'image de la depense
        val projetImage = findViewById<ImageView>(R.id.image_item)
        Glide.with(adapter.context).load(Uri.parse(currentDepense.imageUrl)).into(projetImage)

        // actualiser le nom de la depense
        findViewById<TextView>(R.id.popup_depense_name).text = currentDepense.name

        // actualiser la description de la depense
        findViewById<TextView>(R.id.popup_depense_desrcription_subtitle).text = currentDepense.description

        // actualiser le montant de la depense
        findViewById<TextView>(R.id.popup_depense_montant_subtitle).text = currentDepense.montant.toString()
    }
}