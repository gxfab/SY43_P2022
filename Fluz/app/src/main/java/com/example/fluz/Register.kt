package com.example.fluz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.fluz.databinding.RegisterBinding

class Register : Fragment() {
    private var _binding: RegisterBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = RegisterBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnRegisterFinal.setOnClickListener {
            findNavController().navigate(R.id.action_Register_to_action_LoginOrRegister)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}