package com.example.noappnogain.ui.depense

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
import com.example.noappnogain.databinding.FragmentDepenseBinding
import com.example.noappnogain.model.Data
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import java.text.SimpleDateFormat
import java.util.*


class DepenseFragment : Fragment() {

    private var mAuth: FirebaseAuth? = null
    private var mMouvementDatabase: DatabaseReference? = null
    private var mUser: FirebaseUser? = null
    private var _binding: FragmentDepenseBinding? = null
    private var mCategoryDatabase: DatabaseReference? = null
    private var mPieChartDatabase: DatabaseReference? = null

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

        _binding = FragmentDepenseBinding.inflate(inflater, container, false)
        val root: View = binding.root
        mouvementArrayList = arrayListOf<Data>()
        catArrayList = arrayListOf<Category>()
        val recyclerView: RecyclerView = binding.recyclerIdExpense
        mAuth = FirebaseAuth.getInstance()
        mUser = mAuth?.currentUser

        val spinnerAnnee: Spinner = binding.spinnerAnnee
        ArrayAdapter.createFromResource(
            activity!!,
            R.array.annee,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerAnnee.adapter = adapter
        }
        var annee: String? = null
        spinnerAnnee.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                annee = spinnerAnnee.selectedItem.toString()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        })

        val spinnerMois: Spinner = binding.spinnerMois
        ArrayAdapter.createFromResource(
            activity!!,
            R.array.mois,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerMois.adapter = adapter
            spinnerMois.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
                    val item = parent.getItemAtPosition(pos)
                }
                override fun onNothingSelected(parent: AdapterView<*>?) {}
            })
        }
        var mois: String? = null
        spinnerMois.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                mois = spinnerMois.selectedItem.toString()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        })

        val spinnerCat: Spinner = binding.spinnerCategorie
        ArrayAdapter.createFromResource(
            activity!!,
            R.array.categorie,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerCat.adapter = adapter
            spinnerCat.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
                    val item = parent.getItemAtPosition(pos)
                }
                override fun onNothingSelected(parent: AdapterView<*>?) {}
            })
        }
        var categorie: String? = null
        spinnerCat.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                categorie = spinnerCat.selectedItem.toString()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        })

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
                            if (data.amount < 0) {
                                if(categorie !== "---"){
                                    if(data.type == categorie){
                                        mouvementArrayList.add(data!!)
                                    }else{
                                        mouvementArrayList.add(data!!)
                                    }
                                }
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
        val myviewm: View = inflater.inflate(R.layout.ajouter_depense, null)
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
                val SDFormat: SimpleDateFormat = SimpleDateFormat("d/M/yyyy")
                val mDate = SDFormat.format(Date())
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