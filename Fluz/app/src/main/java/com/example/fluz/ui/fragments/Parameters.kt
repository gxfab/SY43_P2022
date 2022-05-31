package com.example.fluz.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fluz.databinding.FragmentParametersBinding

class Parameters : Fragment() {
    private lateinit var binding: FragmentParametersBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentParametersBinding.inflate(layoutInflater)

        return binding.root
    }
}