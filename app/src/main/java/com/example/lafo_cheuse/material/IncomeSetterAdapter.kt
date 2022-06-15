package com.example.lafo_cheuse.material

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import com.example.lafo_cheuse.CategoryChooserActivity
import com.example.lafo_cheuse.R
import com.example.lafo_cheuse.models.Category
import com.example.lafo_cheuse.models.Frequency
import com.example.lafo_cheuse.models.Income
import com.example.lafo_cheuse.viewmodels.IncomeViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

/**
 * TODO : Complete documentation
 *
 * @property context
 * @property viewModel
 */
class IncomeSetterAdapter(var context : Activity, var viewModel : IncomeViewModel) : RecyclerView.Adapter<IncomeSetterAdapter.ViewHolder>() {

    private var defaultCategory : Category? = null
    private var mIncomes: List<Income> = ArrayList<Income>()
    private var resultLauncher : ActivityResultLauncher<Intent>? = null

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val incomeValueWidget: EditText? = itemView.findViewById(R.id.value)
        val incomeNameWidget: EditText? = itemView.findViewById(R.id.valueName)
        val incomeCategoryButton: Button? = itemView.findViewById(R.id.categoryButton)
        val incomeDeleteButton: FloatingActionButton? = itemView.findViewById(R.id.deleteButton)
        val incomeValidateButton: Button? = itemView.findViewById(R.id.validate_button)
        val addButton : ImageButton? = itemView.findViewById(R.id.addRegularIncomeExpense)
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == mIncomes.size) R.layout.add_button else R.layout.budget_setter_item
    }

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

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (position == mIncomes.size) {
            holder.addButton?.setOnClickListener {
                viewModel.insertIncome(Income(Frequency.OUNCE_A_MONTH,"",defaultCategory!!,0.0))
            }
        } else {
            // Get the data model based on position
            val income: Income = mIncomes[position]
            // Set item views based on your views and data model
            holder.incomeNameWidget?.setText(income.name)

            if (income.amount == 0.0) {
                holder.incomeValueWidget?.setText("")
            } else {
                holder.incomeValueWidget?.setText(income.amount.toString())
            }
            holder.incomeValidateButton?.isEnabled = false


            holder.incomeCategoryButton?.text = income.category?.categoryEmoji

            holder.incomeCategoryButton?.setOnClickListener {
                val bundle = bundleOf("moneyChangeId" to income.moneyChangeId, "type" to "income")
                val intent = Intent(context, CategoryChooserActivity::class.java)
                intent.putExtras(bundle)
                context.startActivity(intent)
            }

            holder.incomeValidateButton?.setOnClickListener {
                income.amount = holder.incomeValueWidget?.text.toString().toDouble()
                income.name = holder.incomeNameWidget?.text.toString()
                viewModel.updateIncome(income)
            }

            holder.incomeValueWidget?.addTextChangedListener {
                holder.incomeValidateButton?.isEnabled = true
            }

            holder.incomeNameWidget?.addTextChangedListener {
                holder.incomeValidateButton?.isEnabled = true
            }

            val deleteIncome = { dialog: DialogInterface, which: Int ->
                viewModel.deleteIncome(income)
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

            holder.incomeDeleteButton?.setOnClickListener {
                val alertDialog: AlertDialog.Builder = AlertDialog.Builder(context)

                with(alertDialog)
                {
                    alertDialog.setTitle("Suppression définitive")
                    alertDialog.setMessage("Êtes-vous sûr de vouloir supprimer cet élément ?")
                    alertDialog.setCancelable(true)
                    setPositiveButton(
                        "Oui",
                        DialogInterface.OnClickListener(function = deleteIncome)
                    )
                    setNeutralButton("Annuler", DialogInterface.OnClickListener(function = cancel))
                    show()
                }

            }


        }


    }

    override fun getItemCount(): Int {
        return mIncomes.size + 1

    }

    fun setIncomes(mIncomes : List<Income>) {
        this.mIncomes = mIncomes
        notifyDataSetChanged()
    }

    fun setDefCategory(defaultCategory : Category) {
        this.defaultCategory = defaultCategory
    }
}