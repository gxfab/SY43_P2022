package com.example.alovemony_ip.fragments

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.alovemony_ip.*
import com.example.alovemony_ip.model.DepenseModel
import com.example.alovemony_ip.repository.CategorieRepository
import com.example.alovemony_ip.repository.CategorieRepository.Singleton.categorieList
import com.example.alovemony_ip.repository.DepenseRepository
import com.example.alovemony_ip.repository.UserRepository
import com.example.alovemony_ip.repository.UserRepository.Singleton.userList
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class AddDepenseFragment(private val context: MainActivity): Fragment() {

    private var uploadedImage: ImageView?= null
    private var imageDepense : Uri?= null
    var calendar = Calendar.getInstance()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater?.inflate(R.layout.fragment_add_depense, container, false)

        val depenseName = view.findViewById<EditText>(R.id.popup_add_name_edit)
        val depenseMontantValue = view.findViewById<EditText>(R.id.popup_add_montant_edit)
        val depenseDate = view.findViewById<EditText>(R.id.popup_add_date_edit)

        // On crée la liste de mots correspondant aux catégories pour l'associer au Spinner lors de l'ajout des dépenses
        val categorieName  = ArrayList<String>()
        for(i in categorieList)
        {
            categorieName.add(i.name)
        }
        // on assigne la liste au spinner
        val spinnerCategorie  = view.findViewById<Spinner>(R.id.popup_add_categorie_spinner)
        val  adapter = ArrayAdapter<String>(context, android.R.layout.simple_spinner_item,categorieName)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCategorie.adapter = adapter


        // On paramètre le calendrier pour selectionner une date
        val dateDepense = view.findViewById<EditText>(R.id.popup_add_date_edit)

        val dateSetListener = object: DatePickerDialog.OnDateSetListener{
            override fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int) {
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, month)
                calendar.set(Calendar.DAY_OF_MONTH, day)

                val myFormat = "dd/MM/yyyy"
                val dateFormat = SimpleDateFormat(myFormat, Locale.UK)

                dateDepense.setText(dateFormat.format(calendar.time))
            }
        }

        dateDepense.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View)
            {
                DatePickerDialog(context, dateSetListener,
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)).show()
            }
        })

        //on assigne le changement d'activité au bouton "Back"
        val buttonBack = view.findViewById<Button>(R.id.popup_add_depense_backbutton)
        buttonBack.setOnClickListener{
            //fermer la fenetre
            context.loadFragment(DepenseListFragment(context),R.string.page_liste_depense_tire)
        }
        // On permeet à l'utilisateur d'accéder à ses photos en cliquant sur "piclUpImageButton"
        uploadedImage = view.findViewById(R.id.popup_add_preview)
        val pickUpImageButton = view.findViewById<Button>(R.id.upload_image)
        pickUpImageButton.setOnClickListener {
            pickUpImage()
        }

        //on assigne le changement d'activité au bouton "Finish" , pour ajouter , avec une vérification si tous les champs ont été remplis
        val finishButton = view.findViewById<Button>(R.id.popup_add_depense_finishbutton)
        finishButton.setOnClickListener {
            if( depenseMontantValue.text.toString() =="" || depenseName.text.toString() == "" ||depenseDate.text.toString() == "" ) {
                depenseName.error = ("Vous n'avez pas donné toutes les informations")
            }else {
                sendForm(view)
                context.loadFragment(DepenseListFragment(context), R.string.page_liste_depense_tire)
            }
        }

        return view
    }

    /*
     Fonction permettant d'actualiser les données de depenseList en fonction de ce que rentre l'utlisateur
    */
    private fun sendForm(view : View)
    {
        val repoDepense = DepenseRepository()
        val repoUser = UserRepository()

        val depenseName = view.findViewById<EditText>(R.id.popup_add_name_edit).text.toString()
        val depenseMontantValue = view.findViewById<EditText>(R.id.popup_add_montant_edit).text.toString()
        val depenseDate = view.findViewById<EditText>(R.id.popup_add_date_edit).text.toString()

        val categorie = view.findViewById<Spinner>(R.id.popup_add_categorie_spinner)

        val depenseMontant = Integer.parseInt(depenseMontantValue)

        val depenseCategorie = categorie.selectedItem.toString()
        val repoCategorie = CategorieRepository()

        // on modifie le montant de la catégorie correspondant à la dépense
        for(i in categorieList)
        {
            if(i.name == depenseCategorie)
            {
                i.montant = i.montant + depenseMontant
                repoCategorie.updateCategorie(i)
            }
        }

        // on modifie le montant globale de l'utilisateur
        userList[0].currentMoney -= depenseMontant
        repoUser.updateUser(userList[0])

        // on ajoute la dépense à la base de donnée
        val depense = DepenseModel(
            UUID.randomUUID().toString(),
            depenseName,
            depenseCategorie,
            depenseDate,
            "https://cdn.pixabay.com/photo/2016/12/02/02/10/idea-1876659_960_720.jpg",
            100f
        )
        repoDepense.insertDepense(depense)
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