package com.example.fluz.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fluz.databinding.FragmentSpendingHistoryBinding

class SpendingHistory : Fragment() {
    private lateinit var binding: FragmentSpendingHistoryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSpendingHistoryBinding.inflate(layoutInflater)

        return binding.root
    }
}