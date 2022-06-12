package com.example.noappnogain.ui.revenu

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.noappnogain.model.Category
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
    private lateinit var catArrayList: ArrayList<Category>

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
        catArrayList = arrayListOf<Category>()
        mouvementArrayList = arrayListOf<Data>()
        mAuth = FirebaseAuth.getInstance()
        mUser = mAuth?.currentUser
        val recyclerView: RecyclerView = binding.recyclerIdIncome


        val btnAjouter: Button = binding.btnAjouter

        btnAjouter.setOnClickListener(View.OnClickListener {
            dataInsert()
        })

        if (mAuth!!.currentUser != null) {
            val uid = mUser!!.uid
            mMouvementDatabase =
                FirebaseDatabase.getInstance().reference.child("MouvementData").child(uid)

        }

        mMouvementDatabase?.addValueEventListener(object : ValueEventListener {
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
        })


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
                val id: String? = mMouvementDatabase?.push()?.key
                val sdFormat: SimpleDateFormat = SimpleDateFormat("d/M/yyyy")
                val mDate = sdFormat.format(Date())
                val data = Data(ouramountinte, type, note, id, mDate)
                if (id != null) {
                    mMouvementDatabase?.child(id)?.setValue(data)
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