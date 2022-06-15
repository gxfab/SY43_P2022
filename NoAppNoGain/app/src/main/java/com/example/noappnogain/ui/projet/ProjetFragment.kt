package com.example.noappnogain.ui.projet

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.noappnogain.R
import com.example.noappnogain.adapter.ProjetAdapter
import com.example.noappnogain.databinding.FragmentProjetBinding
import com.example.noappnogain.model.Projet
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import java.util.*

class ProjetFragment : Fragment() {

    private var mAuth: FirebaseAuth? = null
    private var mProjetDatabase: DatabaseReference? = null
    private var mUser: FirebaseUser? = null
    private var _binding: FragmentProjetBinding? = null

    private lateinit var projetArrayList: ArrayList<Projet>

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentProjetBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val recyclerView: RecyclerView = binding.verticalRecyclerView

        mAuth = FirebaseAuth.getInstance()
        mUser = mAuth?.currentUser

        val btnAjouter: Button = binding.btnAjouter

        btnAjouter.setOnClickListener {
            projetDataInsert()
        }
        projetArrayList = arrayListOf()

        mAuth = FirebaseAuth.getInstance()
        mUser = mAuth?.currentUser

        if (mAuth!!.currentUser != null) {
            val uid = mUser!!.uid

            mProjetDatabase =
                FirebaseDatabase.getInstance().reference.child("ProjetData").child(uid)

        }

        mProjetDatabase?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                projetArrayList = arrayListOf()
                if (snapshot.exists()) {
                    for (userSnapshot in snapshot.children) {
                        val data = userSnapshot.getValue(Projet::class.java)
                        projetArrayList.add(data!!)
                    }
                    projetArrayList.reverse()
                    recyclerView.adapter = ProjetAdapter(projetArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })


        return root
    }

    private fun projetDataInsert() {

        val mydialog = AlertDialog.Builder(activity)
        val inflater = LayoutInflater.from(activity)
        val myviewm: View = inflater.inflate(R.layout.ajouter_projet, null)
        mydialog.setView(myviewm)
        val dialog = mydialog.create()
        dialog.setCancelable(false)
        val edtAmountActual = myviewm.findViewById<EditText>(R.id.montantActuel_edt)
        val edtAmountTotal = myviewm.findViewById<EditText>(R.id.montantTotal_edt)
        val edtDateLimite = myviewm.findViewById<DatePicker>(R.id.date_limite_edt)
        val edtNom = myviewm.findViewById<EditText>(R.id.nom_edt)
        val btnSave = myviewm.findViewById<Button>(R.id.btnSave)
        val btnCancel = myviewm.findViewById<Button>(R.id.btnCancel)
        Calendar.getInstance()

        btnSave.setOnClickListener(View.OnClickListener {

            val isFinished = false
            val day = edtDateLimite.dayOfMonth.toString()
            val emonth = edtDateLimite.month.toString()
            val month = emonth.toInt() + 1
            val year = edtDateLimite.year.toString()
            val date = day.plus("/").plus(month).plus("/").plus(year)
            val actualAmount = edtAmountActual.text.toString().trim { it <= ' ' }
            val totalAmount = edtAmountTotal.text.toString().trim { it <= ' ' }
            val myTotalAmount = totalAmount.toInt()
            val name = edtNom.text.toString().trim { it <= ' ' }
            if (TextUtils.isEmpty(actualAmount)) {
                edtAmountActual.error
                Toast.makeText(activity, "Echec de l'enregistrement...", Toast.LENGTH_SHORT).show()
                return@OnClickListener
            }
            val myActualAmount = actualAmount.toInt()
            if (TextUtils.isEmpty(totalAmount)) {
                edtAmountTotal.error
                Toast.makeText(activity, "Echec de l'enregistrement...", Toast.LENGTH_SHORT).show()
                return@OnClickListener
            }
            if (TextUtils.isEmpty(name)) {
                edtNom.error
                Toast.makeText(activity, "Echec de l'enregistrement...", Toast.LENGTH_SHORT).show()
                return@OnClickListener
            }
            if (mAuth?.currentUser != null) {

                val id: String? = mProjetDatabase?.push()?.key
                val data = Projet(myActualAmount, myTotalAmount, isFinished, name, id, date)
                if (id != null) {
                    mProjetDatabase?.child(id)?.setValue(data)
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