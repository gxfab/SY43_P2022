package com.example.noappnogain.ui.budget

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.noappnogain.databinding.FragmentBudgetBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class BudgetFragment : Fragment() {

    private var mAuth: FirebaseAuth? = null
    private var mUser: FirebaseUser? = null
    private var _binding: FragmentBudgetBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentBudgetBinding.inflate(inflater, container, false)
        val root: View = binding.root

        mAuth = FirebaseAuth.getInstance()
        mUser = mAuth?.currentUser



        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}