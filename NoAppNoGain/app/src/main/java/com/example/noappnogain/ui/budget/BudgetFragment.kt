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
import com.example.noappnogain.adapter.HomeAdapter
import com.example.noappnogain.databinding.FragmentBudgetBinding
import com.example.noappnogain.model.Budget
import com.example.noappnogain.model.Data
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

    var budgetPlan : Int = 0
    var budgetRest : Int = 0

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

        mBudgetDatabase?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                budgetArrayList = arrayListOf()
                if (snapshot.exists()) {
                    for (userSnapshot in snapshot.children) {
                        val data = userSnapshot.getValue(Budget::class.java)
                        if (data != null) {
                            budgetRest += data.montant.toInt()
                        }
                        budgetArrayList.add(data!!)
                    }
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
        })

        mMouvementDatabase?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                budgetPlan = 0
                budgetRest = 0
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

            }
            dialog.dismiss()
        })
        btnCancel.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }

    fun budgetUpdate() {
        val mydialog = AlertDialog.Builder(activity)
        val inflater = LayoutInflater.from(activity)
        val myviewm: View = inflater.inflate(R.layout.modifier_budget, null)
        mydialog.setView(myviewm)
        val edtAmount = myviewm.findViewById<EditText>(R.id.montant_edt)
        val edtCat = myviewm.findViewById<Spinner>(R.id.categorie_edt)
        // spinner
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
        val btnUpdate = myviewm.findViewById<Button>(R.id.btn_upd_Update)
        val btnDelete = myviewm.findViewById<Button>(R.id.btnuPD_Delete)
        val dialog = mydialog.create()
        btnUpdate.setOnClickListener(View.OnClickListener {
            val type = edtType.toString().trim { it <= ' ' }
            val amount = edtAmount.text.toString().trim { it <= ' ' }
            val myAmount = amount.toInt()
            //val data = Budget(myAmount, type, post_key)
            //mBudgetDatabase?.child(post_key)?.setValue(data)
            dialog.dismiss()
        })
        btnDelete.setOnClickListener(View.OnClickListener {
            //mBudgetDatabase.child(post_key).removeValue()
            dialog.dismiss()
        })
        dialog.show()
    }




    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
