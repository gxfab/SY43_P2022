package com.example.alovemony_ip.fragments

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.alovemony_ip.*
import com.example.alovemony_ip.model.DetteModel
import com.example.alovemony_ip.model.ProjetGlobalModel
import com.example.alovemony_ip.model.ProjetModel
import com.example.alovemony_ip.repository.DetteRepository
import com.example.alovemony_ip.repository.ProjetGlobalRepository
import com.example.alovemony_ip.repository.ProjetRepository
import java.util.*

class AddProjectFragment(private val context: MainActivity): Fragment() {

    private var uploadedImage: ImageView?= null
    private var imageDepense : Uri?= null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater?.inflate(R.layout.fragment_add_projet, container, false)

        val projetName = view.findViewById<EditText>(R.id.popup_add_name_edit)
        val projetMontant = view.findViewById<EditText>(R.id.popup_add_montant_edit)
        val projetMontantGlobal = view.findViewById<EditText>(R.id.popup_add_montant_global_edit)
        val projetDate = view.findViewById<EditText>(R.id.popup_add_date_edit)

        //on assigne le changement d'activité au bouton "Back"
        val buttonBack = view.findViewById<Button>(R.id.popup_add_projet_backbutton)
        buttonBack.setOnClickListener{
            //fermer la fenetre
            context.loadFragment(ProjetFragment(context),R.string.page_projet_titre)
        }

        // On permeet à l'utilisateur d'accéder à ses photos en cliquant sur "piclUpImageButton"
        uploadedImage = view.findViewById(R.id.popup_add_projet_preview)
        val pickUpImageButton = view.findViewById<Button>(R.id.upload_image_projet)
        pickUpImageButton.setOnClickListener {
            pickUpImage()
        }
        //on assigne le changement d'activité au bouton "Finish", pour ajouter , avec une vérification si tous les champs ont été remplis
        val finishButton = view.findViewById<Button>(R.id.popup_add_projet_finishbutton)
        finishButton.setOnClickListener {
            if( projetMontant.text.toString() =="" || projetName.text.toString() == "" ||projetDate.text.toString() == "" || projetMontantGlobal.text.toString() =="" )
            {
                projetName.error = ("Vous n'avez pas donné toutes les informations")
            }else {
                sendForm(view)
                context.loadFragment(ProjetFragment(context), R.string.page_projet_titre)
            }
        }
        //recuperer le recyclerview vertical

        return view
    }
    /*
        Fonction permettant d'actualiser les données de depenseList en fonction de ce que rentre l'utlisateur
       */
    private fun sendForm(view : View)
    {
        val repoProjet = ProjetRepository()
        val repoProjetGlobal  = ProjetGlobalRepository()
        val repoDette = DetteRepository()

        val projetName = view.findViewById<EditText>(R.id.popup_add_name_edit).text.toString()
        val projetMontant = view.findViewById<EditText>(R.id.popup_add_montant_edit).text.toString()
        val projetMontantGlobal = view.findViewById<EditText>(R.id.popup_add_montant_global_edit).text.toString()
        val projetDate = view.findViewById<EditText>(R.id.popup_add_date_edit).text.toString()
        val projetType = view.findViewById<Spinner>(R.id.popup_add_projet_type_spinner).selectedItem.toString()

        // on affecte le projet à la bonne structure de la base de donnée en fonction de la selection de Spinner
        if(projetType == "Dette")
        {
            val dette = DetteModel(
                UUID.randomUUID().toString(),
                projetName,
                projetDate,
                "https://cdn.pixabay.com/photo/2016/12/02/02/10/idea-1876659_960_720.jpg",
                projetMontant.toInt(),
                projetMontantGlobal.toInt()
            )

            repoDette.insertDette(dette)
        }else {
            if (projetType == "ProjetGlobal") {
                val projetGlobal = ProjetGlobalModel(
                    UUID.randomUUID().toString(),
                    projetName,
                    projetDate,
                    "https://cdn.pixabay.com/photo/2016/12/02/02/10/idea-1876659_960_720.jpg",
                    projetMontant.toInt(),
                    projetMontantGlobal.toInt()
                )
            repoProjetGlobal.insertProjetGlobal(projetGlobal)

            } else {
                val projet = ProjetModel(
                    UUID.randomUUID().toString(),
                    projetName,
                    projetDate,
                    "https://cdn.pixabay.com/photo/2016/12/02/02/10/idea-1876659_960_720.jpg",
                    projetMontant.toInt(),
                    projetMontantGlobal.toInt()
                )
                repoProjet.insertProjet(projet)
            }
        }
    }
    /*
            Fonctions permettant d'accéder au dossier photo de l'utilisateur
     */

    private fun pickUpImage(){

        val intent = Intent()
        intent.type = "image/"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent,"Select Picture"),47)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 47 && resultCode == Activity.RESULT_OK){
            if(data == null || data.data == null) return
            imageDepense = data.data
            uploadedImage?.setImageURI(imageDepense)
        }
    }

}