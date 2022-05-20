package com.example.fluz.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.fluz.R
import com.example.fluz.data.AppDatabase
import com.example.fluz.data.repositories.UserRepository
import com.example.fluz.databinding.LoginBinding
import com.example.fluz.ui.viewmodels.LoginViewModel
import com.example.fluz.ui.viewmodels.LoginViewModelFactory
import com.example.fluz.ui.viewmodels.RegisterViewModel
import com.example.fluz.ui.viewmodels.RegisterViewModelFactory

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class Login : Fragment() {

    private var _binding: LoginBinding? = null

    private val database by lazy { AppDatabase(this.context!!) }
    private val userRepository by lazy { UserRepository(database.UserDao()) }

    private val loginViewModel: LoginViewModel by viewModels {
        LoginViewModelFactory(userRepository)
    }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = LoginBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLoginFinal.setOnClickListener {
            findNavController().navigate(R.id.action_Login_to_LoginOrRegister)
        }

        binding.backArrowLogin.setOnClickListener {
            findNavController().navigate(R.id.action_Login_to_LoginOrRegister)
        }

        loginViewModel.userId.observe(this) { userId ->
            if (userId != -1L) {
                val sharedPref = this.activity!!.getSharedPreferences("shared-pref", Context.MODE_PRIVATE)
                with(sharedPref.edit()) {
                    putLong("connectedUserId", userId!!)
                    apply()

                    val intent = Intent(this@Login.context, HomeActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                }
            }
        }

        loginViewModel.errorMessage.observe(this) { errorMessage ->
            println(errorMessage)
            if (errorMessage != "") {
                val errorText = binding.errorTxtLogin
                errorText.text = errorMessage
            }
        }

        binding.btnLoginFinal.setOnClickListener {
            val emailAddress = binding.etxtEmail.text.toString()
            val password = binding.etxtPassword.text.toString()

            val errorText = binding.errorTxtLogin

            if (TextUtils.isEmpty(emailAddress) || TextUtils.isEmpty(password)) {
                errorText.text = "Please fill all fields"
            } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches()) {
                errorText.text = "Email format is not valid"
            } else if (password.length < 5) {
                errorText.text = "Password length must be at least 5"
            } else {
                loginViewModel.login(emailAddress, password)
            }
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}