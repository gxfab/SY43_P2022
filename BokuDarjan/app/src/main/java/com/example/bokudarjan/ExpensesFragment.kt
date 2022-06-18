package com.example.bokudarjan

import android.content.Context
import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bokudarjan.category.CategoryViewModel
import com.example.bokudarjan.category.ListAdapterCategory2
import com.example.bokudarjan.envelope.EnvelopeViewModel
import com.example.bokudarjan.expense.ExpenseViewModel
import com.example.bokudarjan.expense.ListAdapterExpense
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_expenses.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A [Fragment] subclass, used to display the Expenses section on the main activity of the app.
 * It contains a Recyclerview to display monthly expenses as well as a button to add one.
 */
class ExpensesFragment : Fragment() {

    private lateinit var expenseViewModel: ExpenseViewModel
    private lateinit var categoryViewModel: CategoryViewModel
    private lateinit var envelopeViewModel: EnvelopeViewModel

    /**
     * Initializing the fragment (setting up recyclerView, onClickListener, ...)
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_expenses, container, false)



        val tv = Space(view.context)
        val layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            60
        )
        tv.layoutParams = layoutParams


        Log.i("ListAdapterExpense", "Entering fragment view")


        //RecyclerView
        val adapter = ListAdapterExpense()
        val recyclerView : RecyclerView = view.recyclerViewExpense
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        //TODO: Add day directly on expenses


        //RecyclerView2
        val adapter2 = ListAdapterCategory2()
        val catRecycler : RecyclerView = view.catRecycler;
        catRecycler.tag = 1;
        catRecycler.adapter = adapter2
        catRecycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        var pref = requireActivity().getSharedPreferences("pref", Context.MODE_PRIVATE)
        val month = pref.getInt("month", -1)



        // Get expenses and categories in ViewModels
        expenseViewModel = ViewModelProvider(this).get(ExpenseViewModel::class.java)
        envelopeViewModel = ViewModelProvider(this).get(EnvelopeViewModel::class.java)

        expenseViewModel.getMonthData(month).observe(viewLifecycleOwner, Observer { expense ->
            adapter.setData(expense)
        })

        categoryViewModel = ViewModelProvider(this).get(CategoryViewModel::class.java)
        categoryViewModel.readAllData.observe(viewLifecycleOwner, Observer{ cat ->
            adapter2.setData(cat)
        })



        // Display envelope and expense at the bottom of the screen
        var expenses:Float = 0f
        var envelopes:Float = 0f

        expenseViewModel.getSumOfNegativeExpenses(month).observe(viewLifecycleOwner, Observer{
            if(it == null){
                expenses = 0f;
            }else{
                expenses = it;
            }
            view.sumEnvelopes.text = String.format("%.2f",expenses) + "€ de dépenses sur " + String.format("%.2f",envelopes) +"€ de prévues";
        })

       envelopeViewModel.getSumOfEnvelopes(month).observe(viewLifecycleOwner, Observer{
           if(it == null){
               envelopes = 0f;
           }else{
               envelopes = it;
           }
           view.sumEnvelopes.text = String.format("%.2f",expenses) + "€ de dépenses sur " + String.format("%.2f",envelopes) +"€ de prévues";
       })


        //
        setUpButtons(view)


        return view;
    }


    /**
     * Set up animation and onClickListeners for expenses buttons
     */
    fun setUpButtons(view : View){
        val expandBtn2 = view.findViewById<FloatingActionButton>(R.id.expandButton2);
        val catR = view.findViewById<RecyclerView>(R.id.catRecycler);
        val darken2 = view.findViewById<ImageView>(R.id.darken2);
        val catText = view.findViewById<TextView>(R.id.catTextExpense);

        val fadeIn = AnimationUtils.loadAnimation(view.context, R.anim.fade_in);
        val fadeOut = AnimationUtils.loadAnimation(view.context, R.anim.fade_out);
        val rot = AnimationUtils.loadAnimation(view.context, R.anim.rot_45);


        expandBtn2.setOnClickListener {

            if (catR.visibility == View.VISIBLE){
                expandBtn2.startAnimation(rot)
                expandBtn2.rotation = 0F;
                catR.startAnimation(fadeOut)
                catR.visibility = View.INVISIBLE
                darken2.startAnimation(fadeOut)
                darken2.visibility = View.INVISIBLE
                catText.startAnimation(fadeOut)
                catText.visibility = View.INVISIBLE
            }else{
                expandBtn2.startAnimation(rot)
                expandBtn2.rotation = 45F;
                catR.startAnimation(fadeIn)
                catR.visibility = View.VISIBLE
                darken2.startAnimation(fadeIn)
                darken2.visibility = View.VISIBLE
                catText.startAnimation(fadeIn)
                catText.visibility = View.VISIBLE
            }
        }

    }

}