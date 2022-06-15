package com.example.noappnogain

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.noappnogain.adapter.BalanceAdapter
import com.example.noappnogain.databinding.ActivityBalanceAnnuelBinding
import com.example.noappnogain.model.Budget
import com.example.noappnogain.model.Data
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*

class BalanceAnnuel : AppCompatActivity() {

    private var mAuth: FirebaseAuth? = null
    private var mUser: FirebaseUser? = null
    private var mBudgetDatabase: DatabaseReference? = null

    private var mMouvementDatabase: DatabaseReference? = null
    private lateinit var budgetArrayList: ArrayList<Budget>

    var budgetPlanSetResult : TextView? = null
    var budgetPlan : Int = 0

    private lateinit var binding: ActivityBalanceAnnuelBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBalanceAnnuelBinding.inflate(layoutInflater)
        setContentView(binding.root)
        budgetArrayList = arrayListOf()
        mAuth = FirebaseAuth.getInstance()
        mUser = mAuth?.currentUser
        budgetPlanSetResult = binding.budgetPlan
        val recyclerView: RecyclerView = binding.recyclerIdBalanceAnnuel

        val extras = intent.extras
        var mDate : String ? = null
        if (extras != null) {
            mDate = extras.getString("mDate")
        }

        if (mAuth!!.currentUser != null) {
            val uid = mUser!!.uid
            mBudgetDatabase =
                FirebaseDatabase.getInstance().reference.child("BudgetData").child(uid)
            mMouvementDatabase =
                FirebaseDatabase.getInstance().reference.child("MouvementData").child(uid)

        }

        mMouvementDatabase?.addValueEventListener( object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                budgetPlan = 0
                if (snapshot.exists()) {
                    for (userSnapshot in snapshot.children) {
                        val data = userSnapshot.getValue(Data::class.java)
                        if (data != null) {
                            if (data.amount > 0) {
                                if(data.date!!.startsWith(mDate.toString())){
                                    budgetPlan += data.amount
                                }
                            }
                        }
                    }
                    budgetPlanSetResult!!.text = budgetPlan.toString()
                }
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

        mBudgetDatabase?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                budgetArrayList = arrayListOf()
                if (snapshot.exists()) {
                    for (userSnapshot in snapshot.children) {
                        val data = userSnapshot.getValue(Budget::class.java)
                        if (data != null) {
                            budgetArrayList.add(data)
                        }
                    }
                    recyclerView.adapter = BalanceAdapter(budgetArrayList, mDate)
                }
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })


    }


}