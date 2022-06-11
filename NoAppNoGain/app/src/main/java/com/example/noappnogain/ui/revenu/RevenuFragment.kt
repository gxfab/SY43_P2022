package com.example.noappnogain.ui.revenu

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.noappnogain.R
import com.example.noappnogain.adapter.IncomeAdapter
import com.example.noappnogain.databinding.FragmentRevenuBinding
import com.example.noappnogain.model.Data
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import java.text.DateFormat
import java.util.*

class RevenuFragment : Fragment() {

    private var _binding: FragmentRevenuBinding? = null
    private var mAuth: FirebaseAuth? = null
    private var mIncomeDatabase: DatabaseReference? = null
    private var mUser: FirebaseUser? = null

    private lateinit var incomeArrayList: ArrayList<Data>

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
        incomeArrayList = arrayListOf<Data>()

        mAuth = FirebaseAuth.getInstance()
        mUser = mAuth?.currentUser

        val btnAjouter: Button = binding.btnAjouter

        btnAjouter.setOnClickListener(View.OnClickListener {
            incomeDataInsert()
        })

        val recyclerView: RecyclerView = binding.recyclerIdIncome

        if (mAuth!!.currentUser != null) {
            val uid = mUser!!.uid
            mIncomeDatabase =
                FirebaseDatabase.getInstance().reference.child("IncomeData").child(uid)

        }

        mIncomeDatabase?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()) {
                    for (userSnapshot in snapshot.children) {
                        val data = userSnapshot.getValue(Data::class.java)
                        incomeArrayList.add(data!!)
                    }

                    recyclerView.adapter = IncomeAdapter(incomeArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

        return root
    }

    fun incomeDataInsert() {
        val mydialog = AlertDialog.Builder(activity)
        val inflater = LayoutInflater.from(activity)
        val myviewm: View = inflater.inflate(R.layout.ajouter_revenu, null)
        mydialog.setView(myviewm)
        val dialog = mydialog.create()
        dialog.setCancelable(false)
        val edtAmount = myviewm.findViewById<EditText>(R.id.montant_edt)
        val edtCat = myviewm.findViewById<Spinner>(R.id.categorie_edt)
        val edtType: String = edtCat.selectedItem.toString()
        val edtNote = myviewm.findViewById<EditText>(R.id.nom_edt)
        val btnSave = myviewm.findViewById<Button>(R.id.btnSave)
        val btnCancel = myviewm.findViewById<Button>(R.id.btnCancel)
        btnSave.setOnClickListener(View.OnClickListener {
            val type = edtType.toString().trim { it <= ' ' }
            val amount = edtAmount.text.toString().trim { it <= ' ' }
            val note = edtNote.text.toString().trim { it <= ' ' }
            if (TextUtils.isEmpty(amount)) {
                edtAmount.error
                return@OnClickListener
            }
            val ouramountinte = amount.toInt()
            if (TextUtils.isEmpty(note)) {
                edtNote.error
                return@OnClickListener
            }
            if (mAuth?.currentUser != null) {
                val id: String? = mIncomeDatabase?.push()?.key
                val mDate = DateFormat.getDateInstance().format(Date())
                val data = Data(ouramountinte, type, note, id, mDate)
                if (id != null) {
                    mIncomeDatabase?.child(id)?.setValue(data)
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