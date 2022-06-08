package net.yolopix.moneyz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Spinner
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.datepicker.MaterialDatePicker
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import net.yolopix.moneyz.model.AppDatabase
import net.yolopix.moneyz.model.entities.Category
import net.yolopix.moneyz.model.entities.Expense
import net.yolopix.moneyz.model.entities.Month

class AddExpenseBottomSheet(private val db: AppDatabase, private val month: Month) :
    BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottom_sheet_add_expense, container, false)
    }

    private lateinit var editTextExpenseName: EditText
    private lateinit var editTextExpenseAmount: EditText
    private lateinit var editTextExpenseDay: EditText
    private lateinit var recurringCheckBox: CheckBox
    private lateinit var categorySpinner: Spinner

    // When the bottom sheet is created
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonAdd =
            view.findViewById<com.google.android.material.button.MaterialButton>(R.id.button_add)
        editTextExpenseName = view.findViewById(R.id.edit_text_expense_name)
        editTextExpenseAmount = view.findViewById(R.id.edit_text_expense_price)
        recurringCheckBox = view.findViewById(R.id.checkBox_recurring)
        editTextExpenseDay = view.findViewById(R.id.editTextDate)
        categorySpinner = view.findViewById(R.id.spinner_category)

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

        // Date picker
        val datePicker = MaterialDatePicker.Builder.datePicker()
            .setTitleText(R.string.expense_date)
            .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
            .build()

        //val adapter = ArrayAdapter.createFromResource(requireContext(), android.R.string ,android.R.layout.item_category)
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
        //if(expensePrice.toFloat() > db.categoryDao().getPredictedAmount())
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


}