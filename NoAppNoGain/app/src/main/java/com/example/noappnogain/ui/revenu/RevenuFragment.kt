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
import java.text.SimpleDateFormat
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

        val btnFiltre: Button = binding.btnFiltre
        btnFiltre.setOnClickListener(View.OnClickListener {
            filtreData()
        })

        val btnAjouter: Button = binding.btnAjouter
        btnAjouter.setOnClickListener(View.OnClickListener {
            dataInsert()
            updateRecylerView()

        })

        updateRecylerView()


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
        edtCat.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                edtType = edtCat.selectedItem.toString()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        })

        val edtNote = myviewm.findViewById<EditText>(R.id.nom_edt)
        val btnSave = myviewm.findViewById<Button>(R.id.btnSave)
        val btnCancel = myviewm.findViewById<Button>(R.id.btnCancel)
        btnSave.setOnClickListener(View.OnClickListener {
            val type = edtType.toString().trim { it <= ' ' }
            val amount = edtAmount.text.toString().trim { it <= ' ' }
            val note = edtNote.text.toString().trim { it <= ' ' }
            if (TextUtils.isEmpty(amount) || amount.toInt() < 0) {
                edtAmount.error
                return@OnClickListener
            }
            val ouramountinte = amount.toInt()
            if (TextUtils.isEmpty(note)) {
                edtNote.error
                return@OnClickListener
            }
            if (mAuth?.currentUser != null) {
                val id: String? = mMouvementDatabase?.push()?.key
                val sdFormat: SimpleDateFormat = SimpleDateFormat("d/M/yyyy")
                val mDate = sdFormat.format(Date())
                val data = Data(ouramountinte, type, note, id, mDate)
                if (id != null) {
                    mMouvementDatabase?.child(id)?.setValue(data)
                }
            }
            dialog.dismiss()
            updateRecylerView()
        })
        btnCancel.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }

    fun updateRecylerView(){

        val recyclerView = binding.recyclerIdIncome

        val valueEventListener: ValueEventListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                mouvementArrayList = arrayListOf<Data>()
                if (snapshot.exists()) {
                    for (userSnapshot in snapshot.children) {
                        val data: Data? = userSnapshot.getValue(Data::class.java)
                        if (data != null) {
                            if (data.amount > 0) {
                                mouvementArrayList.add(data!!)
                            }
                        }
                    }
                    recyclerView.adapter = HomeAdapter(mouvementArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        }

        mMouvementDatabase?.addListenerForSingleValueEvent(valueEventListener)
    }

    fun filtreData(){

        spinnerAnnee?.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                annee = spinnerAnnee!!.selectedItem.toString().trim { it <= ' ' }
                posAnnee = position
                println("posAnnee" + position)
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                annee = "0"
            }
        })

        spinnerMois?.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
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
        })

        spinnerCat?.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                categorie = spinnerCat!!.selectedItem.toString().trim { it <= ' ' }
                posCat = position
                println("cat : " + categorie)
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                categorie = "---"
            }
        })

        var tous : Boolean = false
        var month : Boolean = false
        var year : Boolean = false
        var tousCat : Boolean = true

        if(posAnnee == 0 && posMois == 0){
            tous = true
            year = true
            month = true
        }
        if(posAnnee > 0 && posMois == 0){
            tous = true
            year = false
            month = true
        }
        if(posAnnee == 0 && posMois > 0){
            tous = true
            year = true
            month = false
        }
        if(posAnnee > 0 && posMois > 0){
            tous = false
            year = false
            month = false
        }
        if(posCat > 0){
            tousCat = false
        }else{
            tous = true
        }

        val recyclerView = binding.recyclerIdIncome
        val valueEventListener: ValueEventListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                mouvementArrayList = arrayListOf<Data>()
                if (snapshot.exists()) {
                    for (userSnapshot in snapshot.children) {
                        val data: Data? = userSnapshot.getValue(Data::class.java)
                        if (data != null) {
                            if (data.amount > 0) {
                                var mDate = ""
                                if(!year && !month){
                                    mDate = "/".plus(mois).plus("/").plus(annee)
                                    println("mDate" + mDate)
                                }
                                if(!tous){
                                    if(!year){
                                        if(!month){
                                            if(data.date!!.endsWith(mDate)){
                                                if (!tousCat) {
                                                    if (data.type == categorie) {
                                                        mouvementArrayList.add(data!!)
                                                    }
                                                }else{
                                                    mouvementArrayList.add(data!!)
                                                }
                                            }
                                        }
                                    }else{
                                        if(data.date!!.endsWith(annee)){
                                            if (!tousCat) {
                                                if (data.type == categorie) {
                                                    mouvementArrayList.add(data!!)
                                                }
                                            }else{
                                                mouvementArrayList.add(data!!)
                                            }
                                        }
                                    }
                                }else{
                                    if (!tousCat) {
                                        if (data.type == categorie) {
                                            mouvementArrayList.add(data!!)
                                        }
                                    }else{
                                        mouvementArrayList.add(data!!)
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
        }

        mMouvementDatabase?.addListenerForSingleValueEvent(valueEventListener)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}