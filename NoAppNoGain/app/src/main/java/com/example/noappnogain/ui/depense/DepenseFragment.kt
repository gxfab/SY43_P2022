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
import com.example.noappnogain.Adapter.CategoryAdapter
import com.example.noappnogain.Model.Category
import com.example.noappnogain.R
import com.example.noappnogain.adapter.ExpenseAdapter
import com.example.noappnogain.databinding.FragmentDepenseBinding
import com.example.noappnogain.model.Data
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import java.text.DateFormat
import java.util.*
import kotlin.collections.ArrayList


class DepenseFragment : Fragment() {

    private var mAuth: FirebaseAuth? = null
    private var mExpenseDatabase: DatabaseReference? = null
    private var mUser: FirebaseUser? = null
    private var _binding: FragmentDepenseBinding? = null
    private var mCategoryDatabase: DatabaseReference? = null


    private lateinit var expenseArrayList: ArrayList<Data>
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
        expenseArrayList = arrayListOf<Data>()
        catArrayList = arrayListOf<Category>()

        val recyclerView: RecyclerView = binding.recyclerIdExpense
        mAuth = FirebaseAuth.getInstance()
        mUser = mAuth?.currentUser

        val btnAjouter: Button = binding.btnAjouter

        btnAjouter.setOnClickListener(View.OnClickListener {
            expenseDataInsert()
        })

        val catRecyclerView: RecyclerView = binding.recyclerIdCategorie
        mCategoryDatabase = FirebaseDatabase.getInstance().reference.child("Category")

        mCategoryDatabase?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()) {
                    for (userSnapshot in snapshot.children) {
                        val category = userSnapshot.getValue(Category::class.java)
                        catArrayList.add(category!!)
                    }

                    catRecyclerView.adapter = CategoryAdapter(catArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })


        if (mAuth!!.currentUser != null) {
            val uid = mUser!!.uid

            mExpenseDatabase =
                FirebaseDatabase.getInstance().reference.child("ExpenseData").child(uid)

        }

        mExpenseDatabase?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()) {
                    for (userSnapshot in snapshot.children) {
                        val data = userSnapshot.getValue(Data::class.java)
                        expenseArrayList.add(data!!)
                    }

                    recyclerView.adapter = ExpenseAdapter(expenseArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

        return root
    }

    fun expenseDataInsert() {
        val mydialog = AlertDialog.Builder(activity)
        val inflater = LayoutInflater.from(activity)
        val myviewm: View = inflater.inflate(R.layout.ajouter_depense, null)
        mydialog.setView(myviewm)
        val dialog = mydialog.create()
        dialog.setCancelable(false)
        val edtAmount = myviewm.findViewById<EditText>(R.id.montant_edt)
        val edtCat = myviewm.findViewById<Spinner>(R.id.categorie_edt)


        edtCat.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
                val item = parent.getItemAtPosition(pos)
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        })

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
                val id: String? = mExpenseDatabase?.push()?.key
                val mDate = DateFormat.getDateInstance().format(Date())
                val data = Data(ouramountinte, type, note, id, mDate)
                if (id != null) {
                    mExpenseDatabase?.child(id)?.setValue(data)
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