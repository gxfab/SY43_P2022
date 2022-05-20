package com.example.fluz.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.fluz.R
import com.example.fluz.data.AppDatabase
import com.example.fluz.data.repositories.UserRepository
import com.example.fluz.databinding.FragmentUserInfoBinding
import com.example.fluz.ui.viewmodels.RegisterViewModel
import com.example.fluz.ui.viewmodels.RegisterViewModelFactory

/**
 * A simple [Fragment] subclass.
 * Use the [UserInfo.newInstance] factory method to
 * create an instance of this fragment.
 */
class UserInfo : Fragment() {

    private lateinit var binding: FragmentUserInfoBinding
    private val args: UserInfoArgs by navArgs()

    private val database by lazy { AppDatabase(this.context!!) }
    private val userRepository by lazy { UserRepository(database.UserDao()) }

    private val registerViewModel: RegisterViewModel by viewModels {
        RegisterViewModelFactory(userRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUserInfoBinding.inflate(inflater, container, false)

        binding.backArrowUserInfo.setOnClickListener {
            findNavController().navigate(R.id.action_userInfo_to_Register)
        }

        val currencies = resources.getStringArray(R.array.Currencies)
        val spinnerCurrency = binding.currencySpinner
        val adapterSpinnerCurrency =
            ArrayAdapter(this.context!!, android.R.layout.simple_spinner_dropdown_item, currencies)
        spinnerCurrency.adapter = adapterSpinnerCurrency

        val days = resources.getStringArray(R.array.Days)
        val spinnerDays = binding.dateSpinner
        val adapterSpinnerDate =
            ArrayAdapter(this.context!!, android.R.layout.simple_spinner_dropdown_item, days)
        spinnerDays.adapter = adapterSpinnerDate

        binding.btnContinueUserInfo.setOnClickListener {
            println(spinnerCurrency.selectedItem)
        }

        registerViewModel.allUsers.observe(this) { user ->
            println(user)
        }

        registerViewModel.userId.observe(this) { userId ->
            println(userId)
            if (userId != -1L) {
                val sharedPref = this.activity!!.getSharedPreferences("shared-pref", Context.MODE_PRIVATE)
                with(sharedPref.edit()) {
                    putLong("connectedUserId", userId!!)
                    apply()

                    val intent = Intent(this@UserInfo.context, HomeActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                }
            }
        }

        registerViewModel.errorMessage.observe(this) { errorMessage ->
            println(errorMessage)
            if (errorMessage != "") {
                val errorText = binding.errorTextUserInfo
                errorText.text = registerViewModel.errorMessage.value
            }
        }

        binding.btnContinueUserInfo.setOnClickListener {
            val username: String = binding.etxtUsername.text.toString()
            val currency: String = binding.currencySpinner.selectedItem.toString()
            val startDate: String = binding.dateSpinner.selectedItem.toString()

            val emailAddress = args.userData[0]
            val password = args.userData[1]

            val errorText = binding.errorTextUserInfo

            if (TextUtils.isEmpty(username) || TextUtils.isEmpty(currency) || TextUtils.isEmpty(startDate)) {
                errorText.text = "Please fill all fields"
            } else {
                registerViewModel.register(emailAddress, password, username, currency)
            }

        }

        return binding.root
    }
}