package com.example.noappnogain.adapter

import android.app.AlertDialog
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.noappnogain.R
import com.example.noappnogain.model.Budget
import com.example.noappnogain.model.Projet
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.ArrayList

class BudgetAdapter(private val budgetList: ArrayList<Budget>) :
    RecyclerView.Adapter<BudgetAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.budget_recycler_view,
            parent, false
        )
        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentitem = budgetList[position]

        holder.type.text = currentitem.category
        holder.amount.text = currentitem.montant.toString()

        holder.itemView.setOnClickListener { view ->
            var mAuth: FirebaseAuth? = null
            var mUser: FirebaseUser? = null
            var mBudgetDatabase: DatabaseReference? = null
            mAuth = FirebaseAuth.getInstance()
            mUser = mAuth?.currentUser

            if (mAuth!!.currentUser != null) {
                val uid = mUser!!.uid
                mBudgetDatabase =
                    FirebaseDatabase.getInstance().reference.child("BudgetData").child(uid)
            }

            val mydialog = AlertDialog.Builder(view.context)
            val inflater = LayoutInflater.from(view.context)
            val myviewm: View = inflater.inflate(R.layout.modifier_budget, null)
            mydialog.setView(myviewm)
            val edtAmount = myviewm.findViewById<EditText>(R.id.montant_edt)
            val edtCat = myviewm.findViewById<TextView>(R.id.categorie_edt)

            val type = budgetList[position].category
            val post_key = budgetList[position].id
            val amount = budgetList[position].montant
            edtCat.setText(type)
            edtAmount.setText(amount.toString())

            val btnUpdate = myviewm.findViewById<Button>(R.id.btn_upd_Update)
            val dialog = mydialog.create()
            btnUpdate.setOnClickListener(View.OnClickListener {
                val amount = edtAmount.text.toString().trim { it <= ' ' }
                if (TextUtils.isEmpty(amount)) {
                    edtAmount.error
                    Toast.makeText(view.context, "Echec de l'enregistrement...", Toast.LENGTH_SHORT).show()
                    return@OnClickListener
                }
                val myAmount = amount.toInt()
                if (mAuth?.currentUser != null) {
                    val data = Budget(myAmount, type, post_key)
                    mBudgetDatabase?.child(post_key.toString())?.setValue(data)
                    Toast.makeText(view.context, "Enregistrement réussi...", Toast.LENGTH_SHORT).show()
                }
                dialog.dismiss()
            })
            dialog.show()
        }

    }

    override fun getItemCount(): Int {
        return budgetList.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val type: TextView = itemView.findViewById(R.id.type_txt_budget)
        val amount: TextView = itemView.findViewById(R.id.amount_txt_budget)

    }

}