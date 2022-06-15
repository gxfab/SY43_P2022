package com.example.noappnogain.ui.budget

import android.graphics.Color
import android.os.Bundle
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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import java.text.SimpleDateFormat
import java.util.*

class BudgetFragment : Fragment() {

    private var mAuth: FirebaseAuth? = null
    private var mUser: FirebaseUser? = null
    private var mBudgetDatabase: DatabaseReference? = null
    private var mMouvementDatabase: DatabaseReference? = null
    private var _binding: FragmentBudgetBinding? = null

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
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                mois = "0"
            }
        }

        val valueEventListener: ValueEventListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                budgetPlan = 0
                if (snapshot.exists()) {
                    for (userSnapshot in snapshot.children) {
                        val data = userSnapshot.getValue(Data::class.java)
                        if (data != null) {
                            if (data.amount > 0) {
                                var mDate: String
                                val sdFormat = SimpleDateFormat("yyyy/M")
                                mDate = sdFormat.format(Date())
                                if(data.date!!.startsWith(mDate)){
                                    budgetPlan += data.amount
                                }
                            }
                        }
                    }
                    budgetPlanSetResult!!.text = budgetPlan.toString()
                }
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        }

        mMouvementDatabase?.addListenerForSingleValueEvent(valueEventListener)

        mBudgetDatabase?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                budgetRest = 0
                budgetArrayList = arrayListOf()
                if (snapshot.exists()) {
                    for (userSnapshot in snapshot.children) {
                        val data = userSnapshot.getValue(Budget::class.java)
                        if (data != null) {
                            budgetRest += data.montant
                        }
                        budgetArrayList.add(data!!)
                    }
                    budgetRest = budgetPlan - budgetRest
                    if(budgetRest < 0) {
                        budgetRestSetResult!!.setTextColor(Color.parseColor("#ff0000"))
                    }else{
                        budgetRestSetResult!!.setTextColor(Color.parseColor("#000000"))
                    }
                    budgetRestSetResult!!.text = budgetRest.toString()
                    recyclerView.adapter = BudgetAdapter(budgetArrayList)
                }else{
                    val categorie_depense : Array<String> = arrayOf("Alimentation", "Animaux", "Cadeaux offerts", "Education", "Enfants",
                        "Epargne", "Habillement", "Impôts", "Intérêts dette", "Inventissement", "Loisirs", "Ménage", "Santé", "Transport", "Logement")
                    for(cat in categorie_depense){
                        val amount = 0
                        val id: String? = mBudgetDatabase?.push()?.key
                        val data = Budget(amount, cat, id.toString())
                        if (id != null) {
                            mBudgetDatabase?.child(id)?.setValue(data)
                        }
                    }
                }
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

        val btnFiltre: Button = binding.btnFiltre
        btnFiltre.setOnClickListener({
            filtreData()
        })

        return root
    }

    fun filtreData(){

        var withMonth = false
        var onlyYear = false

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

        val valueEventListenerBudget: ValueEventListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                budgetRest = 0
                if (snapshot.exists()) {
                    for (userSnapshot in snapshot.children) {
                        val data = userSnapshot.getValue(Budget::class.java)
                        if (data != null) {
                            budgetRest += data.montant
                        }
                    }
                    budgetPlanSetResult!!.text = budgetPlan.toString()
                }
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        }

        val valueEventListener: ValueEventListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                budgetPlan = 0
                if (snapshot.exists()) {
                    for (userSnapshot in snapshot.children) {
                        val data = userSnapshot.getValue(Data::class.java)
                        if (data != null) {
                            if (data.amount > 0) {
                                var mDate: String
                                val sdFormat = SimpleDateFormat("yyyy/M")
                                mDate = sdFormat.format(Date())
                                if(onlyYear && withMonth){
                                    mDate = "".plus(annee).plus("/").plus(mois)
                                }
                                if(onlyYear){
                                    if(withMonth){
                                        if(data.date!!.startsWith(mDate)){
                                            budgetPlan += data.amount
                                        }
                                    }
                                }else{
                                    if(data.date!!.startsWith(mDate)){
                                        budgetPlan += data.amount
                                    }
                                }
                            }
                        }
                    }
                    budgetRest = budgetPlan - budgetRest
                    if(budgetRest < 0) {
                        budgetRestSetResult!!.setTextColor(Color.parseColor("#ff0000"))
                    }else{
                        budgetRestSetResult!!.setTextColor(Color.parseColor("#000000"))
                    }
                    budgetRestSetResult!!.text = budgetRest.toString()
                    budgetPlanSetResult!!.text = budgetPlan.toString()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        }
        mBudgetDatabase?.addListenerForSingleValueEvent(valueEventListenerBudget)
        mMouvementDatabase?.addListenerForSingleValueEvent(valueEventListener)

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
