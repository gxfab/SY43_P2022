package com.example.fluz.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.fluz.R
import com.example.fluz.databinding.LoginOrRegisterBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class LoginOrRegister : Fragment() {

    private var _binding: LoginOrRegisterBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = LoginOrRegisterBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLogin.setOnClickListener {
            findNavController().navigate(R.id.LoginOrRegister_to_Login)
        }

        binding.btnRegister.setOnClickListener {
            findNavController().navigate(R.id.action_LoginOrRegister_to_Register)
        }

        binding.txtWithoutLogin.setOnClickListener {
            findNavController().navigate(R.id.LoginOrRegister_to_Login)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}