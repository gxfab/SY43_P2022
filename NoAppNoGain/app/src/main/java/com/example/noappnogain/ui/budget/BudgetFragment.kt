package com.example.noappnogain.ui.budget

import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.noappnogain.R
import com.example.noappnogain.adapter.BudgetAdapter
import com.example.noappnogain.databinding.FragmentBudgetBinding
import com.example.noappnogain.model.Budget
import com.example.noappnogain.model.Data
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*

import java.util.*

class BudgetFragment : Fragment() {

    private var mAuth: FirebaseAuth? = null
    private var mUser: FirebaseUser? = null
    private var mBudgetDatabase: DatabaseReference? = null
    private var mMouvementDatabase: DatabaseReference? = null
    private var _binding: FragmentBudgetBinding? = null

    // budget update key
    private var post_key: String? = null
    private var categorie: String? = null
    private var montant = 0
    var budgetPlanSetResult : TextView? = null
    var budgetRestSetResult : TextView? = null

    private var spinnerAnnee : Spinner? = null
    private var spinnerMois : Spinner? = null

    var budgetPlan : Int = 0
    var budgetRest : Int = 0
    var annee = "0"
    var mois =  "0"
    var posAnnee = 0
    var posMois = 0

    private lateinit var budgetArrayList: ArrayList<Budget>
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentBudgetBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val recyclerView: RecyclerView = binding.recyclerIdBudget
        budgetPlanSetResult = binding.budgetPlan
        budgetRestSetResult = binding.budgetRestant
        budgetArrayList = arrayListOf()
        mAuth = FirebaseAuth.getInstance()
        mUser = mAuth?.currentUser

        val btnAjouter: Button = binding.btnAjouter
        btnAjouter.setOnClickListener {
            budgetInsert()
        }

        if (mAuth!!.currentUser != null) {
            val uid = mUser!!.uid
            mBudgetDatabase =
                FirebaseDatabase.getInstance().reference.child("BudgetData").child(uid)
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

        val valueEventListenerBudget : ValueEventListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                budgetRest = 0
                budgetArrayList = arrayListOf()
                if (snapshot.exists()) {
                    for (userSnapshot in snapshot.children) {
                        val data = userSnapshot.getValue(Budget::class.java)
                        if (data != null) {
                            budgetRest += data.montant.toInt()
                        }
                        budgetArrayList.add(data!!)
                    }
                    budgetRest = budgetPlan - budgetRest
                    if(budgetRest < 0) {
                        budgetRestSetResult!!.setTextColor(Color.parseColor("#ff0000"));
                    }
                    budgetRestSetResult!!.setText(budgetRest.toString())
                    recyclerView.adapter = BudgetAdapter(budgetArrayList)
                }
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        }

        mBudgetDatabase?.addListenerForSingleValueEvent(valueEventListenerBudget)

        val valueEventListener: ValueEventListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                budgetPlan = 0
                if (snapshot.exists()) {
                    for (userSnapshot in snapshot.children) {
                        val data = userSnapshot.getValue(Data::class.java)
                        if (data != null) {
                            if (data.amount > 0) {
                                budgetPlan += data.amount.toInt()
                            }
                        }
                    }
                    budgetPlanSetResult!!.setText(budgetPlan.toString())
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        }

        mMouvementDatabase?.addListenerForSingleValueEvent(valueEventListener)

        val btnFiltre: Button = binding.btnFiltre
        btnFiltre.setOnClickListener(View.OnClickListener {
            filtreData()
        })

        return root
    }

    fun budgetInsert() {
        val mydialog = AlertDialog.Builder(activity)
        val inflater = LayoutInflater.from(activity)
        val myviewm: View = inflater.inflate(R.layout.ajouter_budget, null)
        mydialog.setView(myviewm)
        val dialog = mydialog.create()
        dialog.setCancelable(false)
        val edtAmount = myviewm.findViewById<EditText>(R.id.montant_edt)
        val edtCat = myviewm.findViewById<Spinner>(R.id.categorie_edt)
        ArrayAdapter.createFromResource(
            activity!!,
            R.array.categorie_depense,
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
        val btnSave = myviewm.findViewById<Button>(R.id.btnSave)
        val btnCancel = myviewm.findViewById<Button>(R.id.btnCancel)

        btnSave.setOnClickListener(View.OnClickListener {
            val type = edtType.toString().trim { it <= ' ' }
            val amount = edtAmount.text.toString().trim { it <= ' ' }
            if (TextUtils.isEmpty(amount) || amount.toInt() < 0) {
                edtAmount.error
                return@OnClickListener
            }
            val ouramountinte = amount.toInt()
            if (mAuth?.currentUser != null) {
                val id: String? = mBudgetDatabase?.push()?.key
                val data = Budget(ouramountinte, type, id.toString())
                if (id != null) {
                    mBudgetDatabase?.child(id)?.setValue(data)
                }
                filtreData()
            }
            dialog.dismiss()
        })
        btnCancel.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
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


        var tous : Boolean = false
        var month : Boolean = false
        var year : Boolean = false

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

        val valueEventListenerBudget : ValueEventListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                budgetRest = 0
                budgetArrayList = arrayListOf()
                if (snapshot.exists()) {
                    for (userSnapshot in snapshot.children) {
                        val data = userSnapshot.getValue(Budget::class.java)
                        if (data != null) {
                            budgetRest += data.montant.toInt()
                        }
                        budgetArrayList.add(data!!)
                    }
                    budgetRest = budgetPlan - budgetRest
                    if(budgetRest < 0) {
                        budgetRestSetResult!!.setTextColor(Color.parseColor("#ff0000"));
                    }
                    budgetRestSetResult!!.setText(budgetRest.toString())

                }
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        }

        mBudgetDatabase?.addListenerForSingleValueEvent(valueEventListenerBudget)


        val valueEventListener: ValueEventListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                budgetPlan = 0
                if (snapshot.exists()) {
                    for (userSnapshot in snapshot.children) {
                        val data = userSnapshot.getValue(Data::class.java)
                        if (data != null) {
                            if (data.amount < 0) {
                                var mDate = ""
                                if(!year && !month){
                                    mDate = "/".plus(mois).plus("/").plus(annee)
                                    println("mDate" + mDate)
                                }
                                if(!tous){
                                    if(!year){
                                        if(!month){
                                            if(data.date!!.endsWith(mDate)){
                                                budgetPlan += data.amount.toInt()
                                            }
                                        }
                                    }else{
                                        if(data.date!!.endsWith(annee)){
                                            budgetPlan += data.amount.toInt()
                                        }
                                    }
                                }else{
                                    budgetPlan += data.amount.toInt()
                                }
                            }
                        }
                    }
                    budgetPlanSetResult!!.setText(budgetPlan.toString())
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
