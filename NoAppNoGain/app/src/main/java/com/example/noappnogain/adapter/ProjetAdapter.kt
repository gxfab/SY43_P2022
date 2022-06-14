package com.example.noappnogain.adapter

import android.app.AlertDialog
import android.graphics.Color
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.noappnogain.model.Projet
import com.example.noappnogain.R
import com.example.noappnogain.model.Data
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.text.SimpleDateFormat
import java.util.*

class ProjetAdapter(private val projetList: ArrayList<Projet>) :
    RecyclerView.Adapter<ProjetAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.projet_recycler_view,
            parent, false
        )
        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentitem = projetList[position]

        val SDFormat: SimpleDateFormat = SimpleDateFormat("d/M/yyyy")
        val mDate = SDFormat.format(Date())
        val todayDate = SDFormat.parse(mDate)
        val projetDate = SDFormat.parse(currentitem.date)

        val compare = todayDate.compareTo(projetDate)
        when{
            compare < 0 -> {
                holder.datePicker.setTextColor(Color.parseColor("#0dff00"));
            }
            compare < 0 -> {
                holder.datePicker.setTextColor(Color.parseColor("#ff0000"));
            }
            else -> {
                holder.datePicker.setTextColor(Color.parseColor("#ff0000"));
            }

        }

        if(currentitem.totalAmount > currentitem.actualAmount){
            holder.totalAmount.setTextColor(Color.parseColor("#ff0000"));
        }else{
            holder.actualAmount.setTextColor(Color.parseColor("#0dff00"));
        }

        holder.datePicker.text = currentitem.date
        holder.name.text = currentitem.name
        holder.totalAmount.text = currentitem.totalAmount.toString()
        holder.actualAmount.text = currentitem.actualAmount.toString()

        holder.itemView.setOnClickListener { view ->
            var mAuth: FirebaseAuth? = null
            var mUser: FirebaseUser? = null
            var mProjetDatabase: DatabaseReference? = null
            mAuth = FirebaseAuth.getInstance()
            mUser = mAuth?.currentUser

            if (mAuth!!.currentUser != null) {
                val uid = mUser!!.uid
                mProjetDatabase =
                    FirebaseDatabase.getInstance().reference.child("ProjetData").child(uid)
            }

            val mydialog = AlertDialog.Builder(view.context)
            val inflater = LayoutInflater.from(view.context)
            val myviewm: View = inflater.inflate(R.layout.modifier_projet, null)
            mydialog.setView(myviewm)
            val edtAmountActual = myviewm.findViewById<EditText>(R.id.montantActuel_edt)
            val edtAmountTotal = myviewm.findViewById<EditText>(R.id.montantTotal_edt)
            val edtName= myviewm.findViewById<EditText>(R.id.nom_edt)
            val edtDateLimite = myviewm.findViewById<DatePicker>(R.id.date_limite_edt)

            val post_key = projetList[position].id
            val name = projetList[position].name
            val amountActual = projetList[position].actualAmount
            val amountTotal = projetList[position].totalAmount

            edtAmountActual.setText(amountActual.toString())
            edtAmountTotal.setText(amountTotal.toString())
            edtName.setText(name)

            val btnUpdate = myviewm.findViewById<Button>(R.id.btn_upd_Update)
            val btnDelete = myviewm.findViewById<Button>(R.id.btnuPD_Delete)
            val dialog = mydialog.create()
            dialog.show()
            btnUpdate.setOnClickListener {
                val isFinished = false
                val actualAmount = edtAmountActual.text.toString().trim { it <= ' ' }
                if (TextUtils.isEmpty(actualAmount)) {
                    edtAmountActual.error
                    Toast.makeText(view.context, "Echec de l'enregistrement...", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                val myActualAmount = actualAmount.toInt()
                val totalAmount = edtAmountTotal.text.toString().trim { it <= ' ' }
                if (TextUtils.isEmpty(totalAmount)) {
                    edtAmountTotal.error
                    Toast.makeText(view.context, "Echec de l'enregistrement...", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                val myTotalAmount = totalAmount.toInt()
                val day = edtDateLimite.dayOfMonth.toString()
                val emonth = edtDateLimite.month.toString()
                val month = emonth.toInt() + 1
                val year = edtDateLimite.year.toString()
                val date = day.plus("/").plus(month).plus("/").plus(year)
                val myName = edtName.text.toString()
                if (TextUtils.isEmpty(name)) {
                    edtName.error
                    Toast.makeText(view.context, "Echec de l'enregistrement...", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                if (mAuth?.currentUser != null) {
                    val data = Projet(myActualAmount, myTotalAmount, isFinished, myName, post_key, date)
                    mProjetDatabase?.child(post_key.toString())?.setValue(data)
                    Toast.makeText(view.context, "Enregistrement réussi...", Toast.LENGTH_SHORT).show()
                }
                dialog.dismiss()
            }

            btnDelete.setOnClickListener {
                mProjetDatabase?.child(post_key.toString())?.removeValue()
                dialog.dismiss()
            }
        }



    }

    override fun getItemCount(): Int {
        return projetList.size
    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val name: TextView = itemView.findViewById(R.id.nom_projet)
        val totalAmount: TextView = itemView.findViewById(R.id.totalMontant_projet)
        val actualAmount: TextView = itemView.findViewById(R.id.actualMontant_projet)
        val datePicker: TextView = itemView.findViewById(R.id.date_limite_projet)


    }

}