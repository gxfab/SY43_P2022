package net.yolopix.moneyz.fragments.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import net.yolopix.moneyz.MainActivity
import net.yolopix.moneyz.R
import net.yolopix.moneyz.model.AppDatabase
import net.yolopix.moneyz.model.ExpenseType
import net.yolopix.moneyz.model.entities.Account
import net.yolopix.moneyz.model.entities.Category
import java.time.LocalDate

/**
 * A bottom sheet dialog fragment containing widgets to add a new account in the database
 * @param db The main database of the app
 */
class AddAccountBottomSheet(private val db: AppDatabase) : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottom_sheet_add_account, container, false)
    }

    private lateinit var editTextAccountName: EditText
    private lateinit var accountNameTextField: TextInputLayout
    private lateinit var buttonAdd: Button

    /**
     * This function is called when the bottom sheet is created
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonAdd = view.findViewById(R.id.button_add)
        editTextAccountName = view.findViewById(R.id.edit_text_account_name)
        accountNameTextField = view.findViewById(R.id.text_field_account_name)

        // Add click listener to the add button
        buttonAdd.setOnClickListener {
            addAccount()
        }

        // When the done button is pressed on the soft keyboard
        editTextAccountName.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                checkFormErrors()
                println(buttonAdd.isEnabled)
                if (buttonAdd.isEnabled)
                    addAccount()
            }
            true
        }

        editTextAccountName.addTextChangedListener {
            accountNameTextField.error =
                if (it.isNullOrBlank()) getString(R.string.error_empty_name) else null
            checkFormErrors()
        }
    }

    /**
     * Using the details the user has filled in the fields,
     * create a new account or edit an existing one
     */
    private fun addAccount() {
        // Fetch the account name in the EditText and add it to the database
        val newAccountName = editTextAccountName.text.toString()
        runBlocking {

            val accountNumber = db.accountDao().insertAccount(Account(0, newAccountName))
            val categoryResList = listOf(
                R.array.default_categories_bills,
                R.array.default_categories_envelopes,
                R.array.default_categories_sinking_funds,
                R.array.default_categories_extra_debt,
                R.array.default_categories_extra_savings
            )
            val now = LocalDate.now()

            // Add default categories to the newly created account
            for ((i, categoryRes) in categoryResList.withIndex()) {
                val categoryNames = resources.getStringArray(categoryRes)
                for (names in categoryNames) {
                    val category =
                        Category(
                            0, names, 0.0f, now.monthValue, now.year, accountNumber.toInt(),
                            ExpenseType.getTypeFromInt(i + 1)!!
                        )
                    db.categoryDao().insertCategory(category)
                }
            }
        }
        lifecycleScope.launch {
            (activity as MainActivity).loadAccounts()
        }

        dismiss()
    }

    /**
     * Check if the fields contains errors and prevent the user from continuing if so
     */
    private fun checkFormErrors() {
        buttonAdd.isEnabled = accountNameTextField.error == null
                && editTextAccountName.text.isNotBlank()
    }

}