package com.example.lafo_cheuse.material

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.lafo_cheuse.CategoryChooserActivity
import com.example.lafo_cheuse.R
import com.example.lafo_cheuse.models.Category
import com.example.lafo_cheuse.models.Expense
import com.example.lafo_cheuse.models.Frequency
import com.example.lafo_cheuse.viewmodels.ExpenseViewModel
import com.example.lafo_cheuse.viewmodels.IncomeViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * [RecyclerView.Adapter] for the regular expenses [RecyclerView]
 *
 * @property context - [Activity] where the [RecyclerView] belong
 * @property expensesViewModel - An [ExpenseViewModel] instance to ask expenses from the database
 * @property incomesViewModel - An [IncomeViewModel] instance to ask income sum from the database
 * @property defaultCategory - The default [Category] to set to a freshly created regular [Expense]
 * @property mExpenses - the list of [Expense] to display
 *
 */
class ExpenseSetterAdapter(
    var context : Activity,
    private val expensesViewModel : ExpenseViewModel,
    private val incomesViewModel : IncomeViewModel
    ) : RecyclerView.Adapter<ExpenseSetterAdapter.ViewHolder>() {

    private var defaultCategory : Category? = null
    private var mExpenses: List<Expense> = ArrayList<Expense>()

    /**
     * Inner class that will hold the widgets of one [RecyclerView] item.
     *
     * @param itemView - a [View] representing an item of the layout
     */
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val expenseValueWidget: EditText? = itemView.findViewById(R.id.value)
        val expenseNameWidget: EditText? = itemView.findViewById(R.id.valueName)
        val expenseCategoryButton: Button? = itemView.findViewById(R.id.categoryButton)
        val expenseDeleteButton: FloatingActionButton? = itemView.findViewById(R.id.deleteButton)
        val expenseValidateButton: Button? = itemView.findViewById(R.id.validate_button)
        val addButton : ImageButton? = itemView.findViewById(R.id.addRegularIncomeExpense)
    }

    /**
     * Callback determining which type of layout use for the item at [position] in [mExpenses]
     *
     * @param position - [Int] the position of the item in [mExpenses]
     * @return an [Int] representing the layout id that was chosen.
     *  It can have 2 values :
     *   - [R.layout.add_button] if it is the last element
     *   - [R.layout.budget_setter_item] for any other element
     */
    override fun getItemViewType(position: Int): Int {
        return if (position == mExpenses.size) R.layout.add_button else R.layout.budget_setter_item
    }

    /**
     * Callback function called when the [ViewHolder] is created to let it initialized himself correctly
     *
     * @return a [ViewHolder] initialized
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val itemView : View = if(viewType == R.layout.budget_setter_item){
            inflater.inflate(R.layout.budget_setter_item, parent, false)
        } else {
            inflater.inflate(R.layout.add_button, parent, false)
        }

        return ViewHolder(itemView)
    }

    /**
     * Callback function called when the [holder] is initialized to display data on elements at [position]
     * This function will initialize all the button and widgets by linking them to the operation they should do
     *
     * @param holder - [ViewHolder] holding all the elements
     * @param position - [Int] containing the index of the [View] in [mExpenses]
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (position == mExpenses.size) {
            holder.addButton?.setOnClickListener {
                expensesViewModel.insertExpense(Expense(Frequency.OUNCE_A_MONTH,"",defaultCategory!!,0.0))
            }
        } else {
            // Get the data model based on position
            val expense: Expense = mExpenses[position]
            // Set item views based on your views and data model
            holder.expenseNameWidget?.setText(expense.name)

            if (expense.amount == 0.0) {
                holder.expenseValueWidget?.setText("")
            } else {
                holder.expenseValueWidget?.setText(expense.amount.toString().substring(1))
            }
            holder.expenseValidateButton?.isEnabled = false


            holder.expenseCategoryButton?.text = expense.category?.categoryEmoji

            holder.expenseCategoryButton?.setOnClickListener {
                val bundle = bundleOf("moneyChangeId" to expense.moneyChangeId, "type" to "expense")
                val intent = Intent(context, CategoryChooserActivity::class.java)
                intent.putExtras(bundle)
                context.startActivity(intent)
            }

            holder.expenseValidateButton?.setOnClickListener {
                (context as AppCompatActivity).lifecycleScope.launch(Dispatchers.Main) {
                    launch(Dispatchers.IO) {
                        var incomeSum: Double? = incomesViewModel.getIncomeSumSync()
                        if(incomeSum == null) {
                            incomeSum = 0.0
                        }
                        val partialExpenseSum: Double = expensesViewModel.getMonthlyExpensesSumSync()
                        val expenseSum : Double = partialExpenseSum - expense.amount
                        val expenseNewValue : Double = if(holder.expenseValueWidget?.text.toString() == "") {
                            0.0
                        } else {
                            -holder.expenseValueWidget?.text.toString().toDouble()
                        }
                        val newExpenseSum = expenseSum + expenseNewValue

                        launch(Dispatchers.Main) {
                            if (incomeSum + newExpenseSum >= 0.0 && holder.expenseNameWidget?.text.toString() != "") {
                                expense.amount = expenseNewValue
                                expense.name = holder.expenseNameWidget?.text.toString()
                                expensesViewModel.updateExpense(expense)
                            } else {
                                var toasterText = ""
                                if (holder.expenseNameWidget?.text.toString() == "") {
                                    toasterText = "❌ Donnez un nom"
                                } else if (incomeSum + newExpenseSum < 0.0) {
                                    toasterText = "❌ Vous allez dépasser votre budget"
                                }

                                Toast.makeText(
                                    context,
                                    toasterText, Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                }
            }

            holder.expenseValueWidget?.addTextChangedListener {
                holder.expenseValidateButton?.isEnabled = true
            }

            holder.expenseNameWidget?.addTextChangedListener {
                holder.expenseValidateButton?.isEnabled = true
            }

            val deleteExpense = { dialog: DialogInterface, which: Int ->
                expensesViewModel.deleteExpense(expense)
                Toast.makeText(
                    context,
                    "Suppression réussie", Toast.LENGTH_SHORT
                ).show()
            }

            val cancel = { dialog: DialogInterface, which: Int ->
                Toast.makeText(
                    context,
                    "Annulation de la suppression", Toast.LENGTH_LONG
                ).show()
            }

            holder.expenseDeleteButton?.setOnClickListener {
                val alertDialog: AlertDialog.Builder = AlertDialog.Builder(context)

                with(alertDialog)
                {
                    alertDialog.setTitle("Suppression définitive")
                    alertDialog.setMessage("Êtes-vous sûr de vouloir supprimer cet élément ?")
                    alertDialog.setCancelable(true)
                    setPositiveButton(
                        "Oui",
                        DialogInterface.OnClickListener(function = deleteExpense)
                    )
                    setNeutralButton("Annuler", DialogInterface.OnClickListener(function = cancel))
                    show()
                }
            }
        }
    }

    /**
     * Callback function which send the number of elements in the [RecyclerView]
     * Warning : We added a "+ 1" to take in account the add_button
     *
     * @return [Int] - the size of [mExpenses] + 1
     */
    override fun getItemCount(): Int {
        return mExpenses.size + 1

    }

    /**
     * Update expenses in the [RecyclerView] by updating [mExpenses]
     *
     * @param mExpenses - a list of [Expense]
     */
    fun setExpenses(mExpenses: List<Expense>) {
        this.mExpenses = mExpenses
        notifyDataSetChanged()
    }

    /**
     * Function to keep the default [Category] in the Adapter
     *
     * @param defaultCategory - [Category] of the default [Category]
     */
    fun setDefCategory(defaultCategory : Category) {
        this.defaultCategory = defaultCategory
    }
}