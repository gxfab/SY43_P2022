package com.example.noappnogain.ui.revenu

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.noappnogain.R
import com.example.noappnogain.adapter.HomeAdapter
import com.example.noappnogain.databinding.FragmentRevenuBinding
import com.example.noappnogain.model.Data
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import java.util.*

class RevenuFragment : Fragment() {

    private var _binding: FragmentRevenuBinding? = null
    private var mAuth: FirebaseAuth? = null
    private var mMouvementDatabase: DatabaseReference? = null
    private var mUser: FirebaseUser? = null

    private lateinit var mouvementArrayList: ArrayList<Data>

    private var spinnerAnnee : Spinner? = null
    private var spinnerMois : Spinner? = null
    private var spinnerCat : Spinner? = null

    var annee = "0"
    var mois =  "0"
    var categorie = "0"
    var posAnnee = 0
    var posMois = 0
    var posCat = 0

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentRevenuBinding.inflate(inflater, container, false)
        val root: View = binding.root
        mouvementArrayList = arrayListOf<Data>()
        mAuth = FirebaseAuth.getInstance()
        mUser = mAuth?.currentUser
        val recyclerView = binding.recyclerIdIncome

        if (mAuth!!.currentUser != null) {
            val uid = mUser!!.uid
            mMouvementDatabase =
                FirebaseDatabase.getInstance().reference.child("MouvementData").child(uid)
        }

        spinnerAnnee = binding.spinnerAnnee
        ArrayAdapter.createFromResource(
            activity!!,
            R.array.annee,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerAnnee!!.adapter = adapter
        }
        spinnerMois = binding.spinnerMois
        ArrayAdapter.createFromResource(
            activity!!,
            R.array.mois,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerMois!!.adapter = adapter
        }
        spinnerCat = binding.spinnerCategorie
        ArrayAdapter.createFromResource(
            activity!!,
            R.array.categorie_revenu,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerCat!!.adapter = adapter
        }
        spinnerAnnee?.setSelection(Adapter.NO_SELECTION, true)
        spinnerAnnee?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                annee = spinnerAnnee?.selectedItem.toString().trim { it <= ' ' }
                posAnnee = position
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                annee = "0"
            }
        }
        spinnerMois?.setSelection(Adapter.NO_SELECTION, true)
        spinnerMois?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                mois = position.toString()
                posMois = position
                println("posMois" + position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                mois = "0"
            }
        }
        spinnerCat?.setSelection(Adapter.NO_SELECTION, true)
        spinnerCat?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                categorie = spinnerCat?.selectedItem.toString().trim { it <= ' ' }
                posCat = position
                println("cat : " + categorie)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                categorie = "---"
            }
        }

        val valueEventListener = mMouvementDatabase?.orderByChild("date")?.addValueEventListener( object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                mouvementArrayList = arrayListOf<Data>()
                if (snapshot.exists()) {
                    for (userSnapshot in snapshot.children) {
                        val data: Data? = userSnapshot.getValue(Data::class.java)
                        if (data != null) {
                            if (data.amount > 0) {
                                mouvementArrayList.add(data)
                            }
                        }
                    }
                    recyclerView.adapter = HomeAdapter(mouvementArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

        val btnAjouter: Button = binding.btnAjouter
        btnAjouter.setOnClickListener(View.OnClickListener {
            dataInsert()

        })

        val btnFiltre: Button = binding.btnFiltre
        btnFiltre.setOnClickListener(View.OnClickListener {
            filtreData()
            mMouvementDatabase?.removeEventListener(valueEventListener!!)
        })

        return root
    }

    fun dataInsert() {
        val mydialog = AlertDialog.Builder(activity)
        val inflater = LayoutInflater.from(activity)
        val myviewm: View = inflater.inflate(R.layout.ajouter_revenu, null)
        mydialog.setView(myviewm)
        val dialog = mydialog.create()
        dialog.setCancelable(false)
        val edtAmount = myviewm.findViewById<EditText>(R.id.montant_edt)
        val edtCat = myviewm.findViewById<Spinner>(R.id.categorie_edt)
        ArrayAdapter.createFromResource(
            activity!!,
            R.array.categorie_revenu,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            edtCat.adapter = adapter
        }
        var edtType: String? = null
        edtCat.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                edtType = edtCat.selectedItem.toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        val edtNote = myviewm.findViewById<EditText>(R.id.nom_edt)
        val edtDate = myviewm.findViewById<DatePicker>(R.id.date_edt)
        val btnSave = myviewm.findViewById<Button>(R.id.btnSave)
        val btnCancel = myviewm.findViewById<Button>(R.id.btnCancel)
        btnSave.setOnClickListener(View.OnClickListener {
            val day = edtDate.dayOfMonth.toString()
            val emonth = edtDate.month.toString()
            val month = emonth.toInt() + 1
            val year = edtDate.year.toString()
            val mDate = year.plus("/").plus(month).plus("/").plus(day)
            val type = edtType.toString().trim { it <= ' ' }
            val amount = edtAmount.text.toString().trim { it <= ' ' }
            val note = edtNote.text.toString().trim { it <= ' ' }
            if (TextUtils.isEmpty(amount) || amount.toInt() < 0) {
                edtAmount.error
                Toast.makeText(activity, "Echec de l'enregistrement...", Toast.LENGTH_SHORT).show()
                return@OnClickListener
            }
            val ouramountinte = amount.toInt()
            if (TextUtils.isEmpty(note)) {
                edtNote.error
                Toast.makeText(activity, "Echec de l'enregistrement...", Toast.LENGTH_SHORT).show()
                return@OnClickListener
            }
            if (mAuth?.currentUser != null) {
                val id: String? = mMouvementDatabase?.push()?.key
                val data = Data(ouramountinte, type, note, id, mDate)
                if (id != null) {
                    mMouvementDatabase?.child(id)?.setValue(data)
                }
            }
            dialog.dismiss()
        })
        btnCancel.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }

    fun filtreData(){

        var withMonth : Boolean = false
        var onlyYear : Boolean = false
        var tousCat : Boolean = true

        if(posAnnee == 0 && posMois == 0){
            onlyYear = false
            withMonth = false
        }
        if(posAnnee > 0 && posMois == 0){
            onlyYear = true
            withMonth = false
        }
        if(posAnnee == 0 && posMois > 0){
            onlyYear = false
            withMonth = false
        }
        if(posAnnee > 0 && posMois > 0){
            onlyYear = true
            withMonth = true
        }
        tousCat = posCat <= 0

        val recyclerView = binding.recyclerIdIncome
        mMouvementDatabase?.orderByChild("date")?.addValueEventListener( object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                mouvementArrayList = arrayListOf<Data>()
                if (snapshot.exists()) {
                    for (userSnapshot in snapshot.children) {
                        val data: Data? = userSnapshot.getValue(Data::class.java)
                        if (data != null) {
                            if (data.amount > 0) {
                                var mDate = ""
                                if(onlyYear && withMonth){
                                    mDate = "".plus(annee).plus("/").plus(mois)
                                    println("mDate" + mDate)
                                }
                                if(onlyYear && !withMonth){
                                    mDate = "".plus(annee)
                                    println("mDate" + mDate)
                                }
                                if(onlyYear){
                                    if(withMonth){
                                        if(data.date!!.startsWith(mDate)){
                                            if (!tousCat) {
                                                if (data.type == categorie) {
                                                    mouvementArrayList.add(data)
                                                }
                                            }else{
                                                mouvementArrayList.add(data)
                                            }
                                        }
                                    }else{
                                        if(data.date!!.startsWith(mDate)){
                                            if (!tousCat) {
                                                if (data.type == categorie) {
                                                    mouvementArrayList.add(data)
                                                }
                                            }else{
                                                mouvementArrayList.add(data)
                                            }
                                        }
                                    }
                                }else{
                                    if (!tousCat) {
                                        if (data.type == categorie) {
                                            mouvementArrayList.add(data)
                                        }
                                    }else{
                                        mouvementArrayList.add(data)
                                    }
                                }
                            }
                        }
                    }
                    recyclerView.adapter = HomeAdapter(mouvementArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}