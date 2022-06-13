package com.example.noappnogain.adapter


import android.app.AlertDialog
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.noappnogain.R
import com.example.noappnogain.model.Data
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.text.DateFormat
import java.util.*


class HomeAdapter(private var dataList: ArrayList<Data>) :
    RecyclerView.Adapter<HomeAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.home_recycler_view,
            parent, false
        )
        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentitem = dataList[position]

        holder.date.text = currentitem.date
        holder.type.text = currentitem.type
        if(currentitem.amount > 0) {
            holder.amount.setTextColor(Color.parseColor("#0dff00"));
        }
        holder.amount.text = currentitem.amount.toString()

        holder.itemView.setOnClickListener { view ->
            var mAuth: FirebaseAuth? = null
            var mUser: FirebaseUser? = null
            var mMouvementDatabase: DatabaseReference? = null
            mAuth = FirebaseAuth.getInstance()
            mUser = mAuth?.currentUser

            if (mAuth!!.currentUser != null) {
                val uid = mUser!!.uid
                mMouvementDatabase =
                    FirebaseDatabase.getInstance().reference.child("MouvementData").child(uid)
            }

            val mydialog = AlertDialog.Builder(view.context)
            val inflater = LayoutInflater.from(view.context)
            val myviewm: View = inflater.inflate(R.layout.modifier_mouvement, null)
            mydialog.setView(myviewm)
            val edtAmount = myviewm.findViewById<EditText>(R.id.montant_edt)
            val edtCat = myviewm.findViewById<TextView>(R.id.categorie_edt)
            val edtNote = myviewm.findViewById<EditText>(R.id.nom_edt)

            var type = dataList[position].type
            val post_key = dataList[position].id
            val note = dataList[position].note
            val amount = dataList[position].amount
            val date = dataList[position].date

            edtCat.setText(type)
            edtNote.setText(note)
            edtAmount.setText(amount.toString())

            val btnUpdate = myviewm.findViewById<Button>(R.id.btn_upd_Update)
            val btnDelete = myviewm.findViewById<Button>(R.id.btnuPD_Delete)
            val dialog = mydialog.create()
            btnUpdate.setOnClickListener {
                val note = edtNote.getText().toString().trim { it <= ' ' }
                val mdAmount = edtAmount.text.toString().trim { it <= ' ' }
                val myAmount = mdAmount.toInt()
                val data = Data(myAmount, type, note, post_key, date)
                mMouvementDatabase?.child(post_key.toString())?.setValue(data)
                dialog.dismiss()
            }

            btnDelete.setOnClickListener {
                mMouvementDatabase?.child(post_key.toString())?.removeValue()
                dialog.dismiss()
            }
            dialog.show()
        }

    }

    override fun getItemCount(): Int {

        return dataList.size
    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val date: TextView = itemView.findViewById(R.id.date_txt_home)
        val type: TextView = itemView.findViewById(R.id.type_txt_home)
        val amount: TextView = itemView.findViewById(R.id.amount_txt_home)



    }

}
