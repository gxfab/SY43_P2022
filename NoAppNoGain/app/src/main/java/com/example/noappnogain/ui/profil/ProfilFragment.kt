package com.example.noappnogain.ui.profil

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat.finishAffinity
import androidx.fragment.app.Fragment
import com.example.noappnogain.Changer
import com.example.noappnogain.Sidentifier
import com.example.noappnogain.databinding.FragmentProfilBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class ProfilFragment : Fragment() {

    private lateinit var mAuth: FirebaseAuth

    private var _binding: FragmentProfilBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentProfilBinding.inflate(inflater, container, false)
        val root: View = binding.root

        mAuth = FirebaseAuth.getInstance()
        val mUser: FirebaseUser? = mAuth.currentUser
        val uid = mUser?.uid
        val mUserInfoDatabase =
            uid?.let { FirebaseDatabase.getInstance().reference.child("UserInfo").child(it) }
        val valueEventListener: ValueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val userEmail = dataSnapshot.child("Email").value.toString()
                val email: TextView = binding.emailProfile
                email.text = userEmail
            }

            override fun onCancelled(databaseError: DatabaseError) {}
        }
        mUserInfoDatabase?.addListenerForSingleValueEvent(valueEventListener)

        val changepass = binding.btnChangepass
        val logout = binding.btnLogout
        val deleteaccount = binding.btnDeleteaccount

        changepass.setOnClickListener(View.OnClickListener {
            val intent = Intent(activity, Changer::class.java)
            startActivity(intent)
        })

        logout.setOnClickListener(View.OnClickListener {
            val builder = AlertDialog.Builder(activity)
            builder.setTitle("Se déconnecter")
            builder.setMessage("Voulez-vous vraiment vous déconnecter?")
            builder.setPositiveButton(
                "OUI"
            ) { dialog, which ->
                startActivity(Intent(activity, Sidentifier::class.java))
            }
            builder.setNegativeButton(
                "NO"
            ) { dialog, which -> dialog.dismiss() }
            builder.show()

        })

        deleteaccount.setOnClickListener {
            val dialog = AlertDialog.Builder(activity)
            dialog.setTitle("Êtes-vous sûr?")
            dialog.setMessage(
                "La suppression de ce compte entraînera la suppression complète de votre compte de No App No Gain et vous ne pourrez plus accéder à ce compte." +
                        " À l'avenir, si vous souhaitez utiliser le même e-mail, vous devez vous inscrire à nouveau."
            )
            dialog.setPositiveButton(
                "SUPPRIMER"
            ) { dialog, which ->
                mUser!!.delete().addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(
                            activity,
                            "Compte supprimé avec succès..",
                            Toast.LENGTH_LONG
                        ).show()
                        val intent = Intent(activity, Sidentifier::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                        startActivity(intent)
                    } else {
                        Toast.makeText(activity, task.exception!!.message, Toast.LENGTH_LONG)
                            .show()
                    }
                }
            }
            dialog.setNegativeButton(
                "NO"
            ) { dialog, which -> dialog.dismiss() }
            val alertDialog = dialog.create()
            alertDialog.show()
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}