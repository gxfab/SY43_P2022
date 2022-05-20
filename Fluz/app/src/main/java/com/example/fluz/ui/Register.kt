package com.example.fluz.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.fluz.R
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
            val emailAddress: String = binding.etxtEmail.text.toString()
            val password: String = binding.etxtPassword.text.toString()
            val confirmedPassword: String = binding.etxtPasswordConfirmed.text.toString()

            val errorText = binding.errorTextRegister

            if (emailAddress == "" || password == "" || confirmedPassword == "") {
                errorText.text = "Please fill all fields"
            } else if (password != confirmedPassword) {
                errorText.text = "Confirmed password is different than password"
            } else if (password.length < 5) {
                errorText.text = "Password length must be at least 5"
            } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches()) {
                errorText.text = "Email format is not valid"
            } else {
                val data: Array<String> = arrayOf(emailAddress, password)

                val action = RegisterDirections.actionRegisterToUserInfo(data)

                findNavController().navigate(action)
            }


        }

        binding.backArrowRegister.setOnClickListener {
            findNavController().navigate(R.id.action_Register_to_action_LoginOrRegister)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}