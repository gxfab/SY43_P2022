package com.example.noappnogain.adapter


import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.noappnogain.R
import com.example.noappnogain.model.Budget
import com.example.noappnogain.model.Data
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import java.util.*


class BalanceAdapter(private var budgetList: ArrayList<Budget>, private var mDate: String? = null) :
    RecyclerView.Adapter<BalanceAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.balance_recycler_view,
            parent, false
        )
        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentitem = budgetList[position]

        holder.depensePlan.text = currentitem.montant.toString()

        var mAuth: FirebaseAuth? = null
        var mUser: FirebaseUser? = null
        var mMouvementDatabase: DatabaseReference? = null
        mAuth = FirebaseAuth.getInstance()
        mUser = mAuth.currentUser

        if (mAuth.currentUser != null) {
            val uid = mUser!!.uid
            mMouvementDatabase =
                FirebaseDatabase.getInstance().reference.child("MouvementData").child(uid)
        }

        mMouvementDatabase?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    var amount = 0
                    for (userSnapshot in snapshot.children) {
                        val data: Data? = userSnapshot.getValue(Data::class.java)
                        if (data != null) {
                            if (data.amount < 0) {
                                if(data.date!!.startsWith(mDate.toString())){
                                    if (data.type == currentitem.category) {
                                        amount -= data.amount
                                    }
                                }
                            }
                        }
                    }
                    val diff = currentitem.montant - amount
                    if(diff < 0){
                        holder.diff.setTextColor(Color.parseColor("#ff0000"))
                    }else{
                        holder.diff.setTextColor(Color.parseColor("#0dff00"))
                    }
                    holder.depenseReel.text = amount.toString()
                    holder.diff.text = (currentitem.montant - amount).toString()
                    holder.nom.text = currentitem.category
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

    }

    override fun getItemCount(): Int {
        return budgetList.size
    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val nom: TextView = itemView.findViewById(R.id.nom)
        val depensePlan: TextView = itemView.findViewById(R.id.depense_planifie)
        val depenseReel: TextView = itemView.findViewById(R.id.depense_reelle)
        val diff: TextView = itemView.findViewById(R.id.diff)


    }

}
