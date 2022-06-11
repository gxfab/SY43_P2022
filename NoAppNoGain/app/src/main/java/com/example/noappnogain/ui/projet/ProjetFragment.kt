package com.example.noappnogain.ui.projet

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.noappnogain.Adapter.ProjetAdapter
import com.example.noappnogain.R
import com.example.noappnogain.databinding.FragmentProjetBinding
import com.example.noappnogain.model.Data
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import java.text.DateFormat
import java.util.*

class ProjetFragment : Fragment() {

    private var mAuth: FirebaseAuth? = null
    private var mProjetDatabase: DatabaseReference? = null
    private var mUser: FirebaseUser? = null
    private var _binding: FragmentProjetBinding? = null

    private lateinit var projetArrayList: ArrayList<Data>

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

        btnAjouter.setOnClickListener(View.OnClickListener {
            projetDataInsert()
        })

        projetArrayList = arrayListOf<Data>()

        mAuth = FirebaseAuth.getInstance()
        mUser = mAuth?.currentUser

        if (mAuth!!.currentUser != null) {
            val uid = mUser!!.uid

            mProjetDatabase =
                FirebaseDatabase.getInstance().reference.child("ProjetData").child(uid)

        }

        mProjetDatabase?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()) {
                    for (userSnapshot in snapshot.children) {
                        val data = userSnapshot.getValue(Data::class.java)
                        if (data != null) {

                        }
                        projetArrayList.add(data!!)
                    }

                    recyclerView.adapter = ProjetAdapter(projetArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })


        return root
    }

    fun projetDataInsert() {

        val mydialog = AlertDialog.Builder(activity)
        val inflater = LayoutInflater.from(activity)
        val myviewm: View = inflater.inflate(R.layout.ajouter_projet, null)
        mydialog.setView(myviewm)
        val dialog = mydialog.create()
        dialog.setCancelable(false)
        val edtAmount = myviewm.findViewById<EditText>(R.id.montant_edt)
        val edtDateLimite = myviewm.findViewById<EditText>(R.id.date_limite_edt)
        val edtNote = myviewm.findViewById<EditText>(R.id.nom_edt)
        val btnSave = myviewm.findViewById<Button>(R.id.btnSave)
        val btnCancel = myviewm.findViewById<Button>(R.id.btnCancel)

        btnSave.setOnClickListener(View.OnClickListener {
            val note = edtDateLimite.text.toString().trim { it <= ' ' }
            val amount = edtAmount.text.toString().trim { it <= ' ' }
            val type = edtNote.text.toString().trim { it <= ' ' }
            if (TextUtils.isEmpty(amount)) {
                edtAmount.error
                return@OnClickListener
            }
            val ouramountinte = amount.toInt()
            if (TextUtils.isEmpty(type)) {
                edtNote.error
                return@OnClickListener
            }
            if (TextUtils.isEmpty(note)) {
                edtNote.error
                return@OnClickListener
            }
            if (mAuth?.currentUser != null) {
                val id: String? = mProjetDatabase?.push()?.key
                val mDate = DateFormat.getDateInstance().format(Date())
                val data = Data(ouramountinte, type, note, id, mDate)
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