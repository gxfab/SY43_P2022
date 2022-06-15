package com.example.noappnogain.ui.home


import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.noappnogain.adapter.HomeAdapter
import com.example.noappnogain.databinding.FragmentHomeBinding
import com.example.noappnogain.model.Data
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import java.text.SimpleDateFormat
import java.util.*


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // FirebaseAuth & RealtimeDatabase
    private var mAuth: FirebaseAuth? = null
    private var mMouvementDatabase: DatabaseReference? = null
    private var mUser: FirebaseUser? = null

    private lateinit var mouvementArrayList: ArrayList<Data>

    var balance : Int = 0

    var spinnerCat: Spinner? = null
    var edtAmount: EditText? = null
    var edtNote: EditText? = null
    var error = false
    var edtType: String? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val balanceSetResult: TextView = binding.balanceSetResult
        val recyclerView: RecyclerView = binding.verticalRecyclerView
        edtAmount = binding.montantEdt
        edtNote = binding.nomEdt
        mouvementArrayList = arrayListOf<Data>()
        mAuth = FirebaseAuth.getInstance()
        mUser = mAuth?.currentUser
        if (mAuth!!.currentUser != null) {
            val uid = mUser!!.uid
            mMouvementDatabase =
                FirebaseDatabase.getInstance().reference.child("MouvementData").child(uid)

        }

        spinnerCat = binding.categorieEdt
        ArrayAdapter.createFromResource(
            activity!!,
            com.example.noappnogain.R.array.categorie_depense,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerCat!!.adapter = adapter
        }
        spinnerCat?.setSelection(Adapter.NO_SELECTION, true)
        spinnerCat?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                edtType = spinnerCat?.selectedItem.toString()
                println("posCat : $position")
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        mMouvementDatabase?.orderByChild("date")?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                balance = 0
                mouvementArrayList = arrayListOf<Data>()
                if (snapshot.exists()) {
                    for (userSnapshot in snapshot.children) {
                        val data = userSnapshot.getValue(Data::class.java)
                        if (data != null) {
                            val sdFormat = SimpleDateFormat("yyyy/M")
                            val mDate = sdFormat.format(Date())
                            if(data.date!!.startsWith(mDate)){
                                balance += data.amount
                            }
                        }
                        // vérifier la balance
                        if(balance > 0) {
                            balanceSetResult.setTextColor(Color.parseColor("#0dff00"))
                        }
                        else{
                            balanceSetResult.setTextColor(Color.parseColor("#ff0000"))
                        }
                        balanceSetResult.text = balance.toString()
                        mouvementArrayList.add(data!!)
                    }
                    mouvementArrayList.reverse()
                    recyclerView.adapter = HomeAdapter(mouvementArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

        val btnAjouter: Button = binding.btnAjouter
        btnAjouter.setOnClickListener({
            dataInsert()
            edtNote!!.text.clear()
            edtAmount!!.text.clear()

        })


        return root
    }

    fun dataInsert() {

        val type = edtType.toString().trim { it <= ' ' }
        val amount = edtAmount?.text.toString().trim { it <= ' ' }
        val note = edtNote?.text.toString().trim { it <= ' ' }
        if (TextUtils.isEmpty(amount) || amount.toInt() > 0) {
            error = true
            Toast.makeText(activity, "Veuillez mettre votre montant en négatif..", Toast.LENGTH_SHORT).show()
        }
        val ouramountinte = amount.toInt()
        if (TextUtils.isEmpty(note)) {
            error = true
            Toast.makeText(activity, "Le champ ne doit pas être vide..", Toast.LENGTH_SHORT).show()
        }
        if (mAuth?.currentUser != null && !error && type != "null") {
            val id: String? = mMouvementDatabase?.push()?.key
            val SDFormat = SimpleDateFormat("yyyy/M/d")
            val mDate = SDFormat.format(Date())
            val data = Data(ouramountinte, type, note, id, mDate)
            if (id != null) {
                mMouvementDatabase?.child(id)?.setValue(data)
                Toast.makeText(activity, "Enregistrement réussi..", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(activity, "Echec de l'enregistrement..", Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(activity, "Echec de l'enregistrement..", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}

