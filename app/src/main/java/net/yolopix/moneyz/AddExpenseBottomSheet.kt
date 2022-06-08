package net.yolopix.moneyz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.*
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import net.yolopix.moneyz.model.AppDatabase
import net.yolopix.moneyz.model.entities.Category
import net.yolopix.moneyz.model.entities.Expense
import net.yolopix.moneyz.model.entities.Month
import java.time.LocalDate

/**
 * A bottom sheet dialog fragment containing widgets
 * to add a new expense to a specific category in an account
 * @param db The main database of the app
 * @param month The month in which the expense will be added
 */
class AddExpenseBottomSheet(private val db: AppDatabase, private val month: Month) :
    BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottom_sheet_add_expense, container, false)
    }

    // Widgets
    private lateinit var editTextExpenseName: EditText
    private lateinit var editTextExpenseAmount: EditText
    private lateinit var editTextExpenseDay: EditText
    private lateinit var recurringCheckBox: CheckBox
    private lateinit var categorySpinner: Spinner
    private lateinit var nameTextInputLayout: TextInputLayout
    private lateinit var amountTextInputLayout: TextInputLayout
    private lateinit var dateTextInputLayout: TextInputLayout
    private lateinit var buttonAdd: Button

    /**
     * When the bottom sheet view is created
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        editTextExpenseName = view.findViewById(R.id.edit_text_expense_name)
        editTextExpenseAmount = view.findViewById(R.id.edit_text_expense_price)
        editTextExpenseDay = view.findViewById(R.id.editTextDate)
        recurringCheckBox = view.findViewById(R.id.checkBox_recurring)
        categorySpinner = view.findViewById(R.id.spinner_category)
        nameTextInputLayout = view.findViewById(R.id.text_input_layout_name)
        amountTextInputLayout = view.findViewById(R.id.text_input_layout_amount)
        dateTextInputLayout = view.findViewById(R.id.text_input_layout_date)
        buttonAdd = view.findViewById(R.id.button_add)

        // Pre-fill details
        editTextExpenseDay.setText(LocalDate.now().dayOfMonth.toString())

        // Add click listener to the add button
        buttonAdd.setOnClickListener {
            addExpense()
        }

        // When the done button is pressed on the soft keyboard
        editTextExpenseName.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE)
                addExpense()
            true
        }

        // Error checking
        editTextExpenseName.addTextChangedListener {
            nameTextInputLayout.error =
                if (it.isNullOrEmpty()) getString(R.string.error_empty_name) else null
            checkFormErrors()
        }


        editTextExpenseAmount.addTextChangedListener {
            amountTextInputLayout.error = when (it.toString().toFloatOrNull()) {
                null -> getString(R.string.error_empty_text)
                0f -> getString(R.string.error_amount_zero)
                else -> null
            }
            checkFormErrors()
        }

        val monthAsLocalDate: LocalDate = LocalDate.of(month.yearNumber, month.monthNumber, 1)
        editTextExpenseDay.addTextChangedListener {
            dateTextInputLayout.error = when (it.toString().toIntOrNull()) {
                null -> getString(R.string.error_empty_text)
                in 1..monthAsLocalDate.lengthOfMonth() -> null
                else -> getString(R.string.error_invalid_day_of_month)
            }
            checkFormErrors()
        }

        // Date picker
        val datePicker = MaterialDatePicker.Builder.datePicker()
            .setTitleText(R.string.expense_date)
            .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
            .build()

        lifecycleScope.launch {
            categorySpinner.adapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_dropdown_item,
                db.categoryDao()
                    .getCategoriesForMonth(month.monthNumber, month.yearNumber, month.accountUid)
            )
        }

    }

    private fun addExpense() {
        // Fetch the account name in the EditText and add it to the database
        val expenseName = editTextExpenseName.text.toString()
        val expensePrice = editTextExpenseAmount.text.toString().toDouble()
        val expenseDay = editTextExpenseDay.text.toString().toInt()
        val recurring = recurringCheckBox.isChecked
        val category = categorySpinner.selectedItem as Category

        val newExpense = Expense(0, expenseDay, category.uid, expenseName, recurring, expensePrice)

        runBlocking {
            db.expenseDao().insertExpense(newExpense)
        }
        lifecycleScope.launch {
            val parent = activity as ExpensesActivity
            parent.loadExpenses()
        }

        dismiss()
    }

    private fun checkFormErrors() {
        buttonAdd.isEnabled = nameTextInputLayout.error == null
                && amountTextInputLayout.error == null
                && dateTextInputLayout.error == null
                && editTextExpenseName.text.isNotBlank()
                && editTextExpenseAmount.text.isNotBlank()
                && editTextExpenseDay.text.isNotBlank()
    }


}