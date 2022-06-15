package net.yolopix.moneyz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import net.yolopix.moneyz.model.AppDatabase
import net.yolopix.moneyz.model.ExpenseType
import net.yolopix.moneyz.model.entities.Category
import kotlin.properties.Delegates

/**
 * A bottom sheet dialog fragment containing widgets
 * to add a new category and its prediction to an account
 * @param db The main database of the app
 * @param monthNumber The month in which the category will be added
 * @param yearNumber The year of the month in which the category will be added
 * @param accountUid: The identifier of the account in which the category will be added
 */
class AddCategoryBottomSheet(
    private val db: AppDatabase,
    private val monthNumber: Int,
    private val yearNumber: Int,
    private val accountUid: Int,
    private var incomeAmount: Float? = Float.MAX_VALUE, // Do not limit category max amount if the income value is not valid
    private val type: ExpenseType
) : BottomSheetDialogFragment() {
    private lateinit var buttonAddCategoryName: Button
    private lateinit var editTextCategoryName: EditText
    private lateinit var editTextCategoryprice: EditText
    private lateinit var categoryNameTextField: TextInputLayout
    private lateinit var categoryPriceTextField: TextInputLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottom_sheet_add_prevision, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonAddCategoryName =
            view.findViewById<com.google.android.material.button.MaterialButton>(R.id.button_add_category_bottom_sheet)
        editTextCategoryName = view.findViewById(R.id.edit_text_category_name)
        editTextCategoryprice = view.findViewById(R.id.edit_text_category_price)
        categoryNameTextField = view.findViewById(R.id.text_field_category_name)
        categoryPriceTextField = view.findViewById(R.id.text_field_category_price)

        // Add click listener to the add button
        buttonAddCategoryName.setOnClickListener {
            addCategory()
        }

        // Check input contents when typing
        editTextCategoryName.addTextChangedListener {
            categoryNameTextField.error =
                if (it.isNullOrBlank()) getString(R.string.error_empty_name) else null
            checkFormErrors()
        }

        editTextCategoryprice.addTextChangedListener {
            categoryPriceTextField.error = when {
                it.toString().isBlank() -> getString(R.string.error_empty_text)
                it.toString().toFloatOrNull() == null -> getString(R.string.error_invalid_amount)
                it.toString().toFloat() == 0f -> getString(R.string.error_amount_zero)
                it.toString()
                    .toFloat() > incomeAmount!! -> getString(R.string.error_prevision_greater_than_salary)
                else -> null
            }
            checkFormErrors()
        }
    }


    private fun addCategory() {
        var newCategoryPrice by Delegates.notNull<Float>()
        // Fetch the account name in the EditText and add it to the database
        val newCategoryName = editTextCategoryName.text.toString()
        newCategoryPrice = if (editTextCategoryprice.text.toString() == "") {
            0F
        } else {
            editTextCategoryprice.text.toString().toFloat()
        }

        val newCategory =
            Category(
                0,
                newCategoryName,
                newCategoryPrice,
                monthNumber,
                yearNumber,
                accountUid,
                type
            )
        runBlocking {
            db.categoryDao().insertCategory(newCategory)
        }
        lifecycleScope.launch {
            val parent = activity as PrevisionActivity
            parent.loadAll()
        }
        dismiss()
    }

    private fun checkFormErrors() {
        buttonAddCategoryName.isEnabled = categoryNameTextField.error == null
                && categoryPriceTextField.error == null
                && editTextCategoryName.text.isNotBlank()
                && editTextCategoryprice.text.isNotBlank()
    }
}