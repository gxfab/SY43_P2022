package com.example.fluz.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fluz.R
import com.example.fluz.databinding.FragmentTransactionBinding
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class Transaction : Fragment() {
    private lateinit var binding: FragmentTransactionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTransactionBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*val view = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val bar = requireActivity().findViewById<BottomAppBar>(R.id.bottomAppBar)

        val fab = requireActivity().findViewById<FloatingActionButton>(R.id.btn_add)
        view.visibility = View.GONE
        bar.visibility = View.GONE
        fab.hide();*/
    }
}