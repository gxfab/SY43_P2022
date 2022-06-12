package com.example.noappnogain.ui.budget

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.noappnogain.adapter.BudgetAdapter
import com.example.noappnogain.model.Budget
import com.example.noappnogain.R
import com.example.noappnogain.databinding.FragmentBudgetBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import kotlin.collections.ArrayList

class BudgetFragment : Fragment() {

    private var mAuth: FirebaseAuth? = null
    private var mUser: FirebaseUser? = null
    private var mBudgetDatabase: DatabaseReference? = null
    private var mCategoryDatabase: DatabaseReference? = null
    private var _binding: FragmentBudgetBinding? = null

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
        budgetArrayList = arrayListOf()

        mCategoryDatabase = FirebaseDatabase.getInstance().reference.child("Category")

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

        }

        mBudgetDatabase?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                budgetArrayList = arrayListOf()
                if (snapshot.exists()) {
                    for (userSnapshot in snapshot.children) {
                        val data = userSnapshot.getValue(Budget::class.java)
                        budgetArrayList.add(data!!)
                    }
                    recyclerView.adapter = BudgetAdapter(budgetArrayList)
                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })


        return root
    }

    private fun budgetInsert() {
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
            R.array.categorie,
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
        val btnSave = myviewm.findViewById<Button>(R.id.btnSave)
        val btnCancel = myviewm.findViewById<Button>(R.id.btnCancel)

        btnSave.setOnClickListener(View.OnClickListener {
            val type = edtType.toString().trim { it <= ' ' }
            val amount = edtAmount.text.toString().trim { it <= ' ' }
            if (TextUtils.isEmpty(amount)) {
                edtAmount.error
                return@OnClickListener
            }
            val ouramountinte = amount.toInt()
            if (mAuth?.currentUser != null) {
                val uid = mUser!!.uid
                val id: String? = mBudgetDatabase?.push()?.key
                val data = Budget(ouramountinte, type)
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


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
