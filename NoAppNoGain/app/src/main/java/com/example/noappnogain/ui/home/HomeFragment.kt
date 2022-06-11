package com.example.noappnogain.ui.home


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.noappnogain.adapter.HomeAdapter
import com.example.noappnogain.databinding.FragmentHomeBinding
import com.example.noappnogain.model.Data
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // FirebaseAuth & RealtimeDatabase
    private var mAuth: FirebaseAuth? = null
    private var mIncomeDatabase: DatabaseReference? = null
    private var mExpenseDatabase: DatabaseReference? = null
    private var mUser: FirebaseUser? = null

    private lateinit var incomeArrayList: ArrayList<Data>
    private lateinit var expenseArrayList: ArrayList<Data>
    private lateinit var dataArrayList: ArrayList<Data>
    private lateinit var recyclerView: RecyclerView

    var totalsumexpense : Int = 0
    var totalsumincome : Int = 0
    var balance : Int = 0

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
        var balanceSetResult: TextView = binding.balanceSetResult
        val recyclerView: RecyclerView = binding.verticalRecyclerView
        incomeArrayList = arrayListOf<Data>()
        expenseArrayList = arrayListOf<Data>()

        mAuth = FirebaseAuth.getInstance()
        mUser = mAuth?.currentUser

        if (mAuth!!.currentUser != null) {
            val uid = mUser!!.uid
            mIncomeDatabase =
                FirebaseDatabase.getInstance().reference.child("IncomeData").child(uid)
            mExpenseDatabase =
                FirebaseDatabase.getInstance().reference.child("ExpenseData").child(uid)

        }

        mExpenseDatabase?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()) {
                    for (userSnapshot in snapshot.children) {
                        val data = userSnapshot.getValue(Data::class.java)
                        if (data != null) {
                            totalsumexpense += data.getAmountData().toInt()
                        }
                        expenseArrayList.add(data!!)
                    }

                    recyclerView.adapter = HomeAdapter(expenseArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

        mIncomeDatabase?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()) {
                    for (userSnapshot in snapshot.children) {
                        val data = userSnapshot.getValue(Data::class.java)
                        if (data != null) {
                            totalsumincome += data.getAmountData().toInt()
                        }
                        incomeArrayList.add(data!! )
                    }

                    recyclerView.adapter = HomeAdapter(incomeArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

        balance = totalsumincome - totalsumexpense
        balanceSetResult.setText(balance.toString())

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}

