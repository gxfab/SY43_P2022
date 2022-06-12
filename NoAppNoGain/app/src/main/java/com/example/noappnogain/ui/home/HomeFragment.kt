package com.example.noappnogain.ui.home


import android.R
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.AdapterView.OnItemClickListener
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
    private var mMouvementDatabase: DatabaseReference? = null
    private var mUser: FirebaseUser? = null

    private lateinit var mouvementArrayList: ArrayList<Data>

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
        val balanceSetResult: TextView = binding.balanceSetResult
        val recyclerView: RecyclerView = binding.verticalRecyclerView

        mouvementArrayList = arrayListOf<Data>()

        mAuth = FirebaseAuth.getInstance()
        mUser = mAuth?.currentUser

        if (mAuth!!.currentUser != null) {
            val uid = mUser!!.uid
            mMouvementDatabase =
                FirebaseDatabase.getInstance().reference.child("MouvementData").child(uid)

        }

        mMouvementDatabase?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                balance = 0
                mouvementArrayList = arrayListOf<Data>()
                if (snapshot.exists()) {
                    for (userSnapshot in snapshot.children) {
                        val data = userSnapshot.getValue(Data::class.java)
                        if (data != null) {
                            balance += data.amount.toInt()
                        }
                        // vérifier la balance
                        if(balance > 0) {
                            balanceSetResult.setTextColor(Color.parseColor("#0dff00"));
                        }
                        else{
                            balanceSetResult.setTextColor(Color.parseColor("#ff0000"));
                        }
                        balanceSetResult.setText(balance.toString())
                        mouvementArrayList.add(data!!)
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}

