package net.yolopix.moneyz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import net.yolopix.moneyz.model.AppDatabase
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
    private lateinit var now: LocalDate
    private var accountUid: Int? = null
    // When the bottom sheet is created
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonAdd =
            view.findViewById<com.google.android.material.button.MaterialButton>(R.id.button_add)
        editTextAccountName = view.findViewById(R.id.edit_text_account_name)

        // Add click listener to the add button
        buttonAdd.setOnClickListener {
            addAccount()
        }

        // When the done button is pressed on the soft keyboard
        editTextAccountName.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                addAccount()
            }
            true
        }
    }

    private fun addAccount() {




        // Fetch the account name in the EditText and add it to the database
        val newAccountName = editTextAccountName.text.toString()
        runBlocking {

            val accountNumber = db.accountDao().insertAccount(Account(0, newAccountName))
            var categoryNames = listOf<String>("🍕Nourriture", "🍺Loisirs", "🏠Loyer", "🎾Sport")
            now = LocalDate.now()

            for(names in categoryNames){
                val category : Category = Category(0, names,0.0f,now.monthValue, now.year,accountNumber.toInt())
                db.categoryDao().insertCategory(category)
            }
        }
        lifecycleScope.launch {
            (activity as MainActivity).loadAccounts()
        }

        dismiss()
    }

}