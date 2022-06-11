package com.example.bokudarjan

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.distinctUntilChanged
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bokudarjan.category.Category
import com.example.bokudarjan.category.CategoryViewModel
import com.example.bokudarjan.category.ListAdapterCategory
import com.example.bokudarjan.envelope.EnvelopeViewModel
import com.example.bokudarjan.expense.ExpenseViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.envelope_card.view.*
import kotlinx.android.synthetic.main.envelope_category_card.view.*
import kotlinx.android.synthetic.main.fragment_planification.view.*
import java.util.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [planificationFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class planificationFragment : Fragment() {

    private lateinit var categoryViewModel: CategoryViewModel
    private lateinit var expenseViewModel: ExpenseViewModel
    private lateinit var envelopeViewModel: EnvelopeViewModel
    var list: List<Category> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        categoryViewModel = ViewModelProvider(this).get(CategoryViewModel::class.java)
        expenseViewModel = ViewModelProvider(this).get(ExpenseViewModel::class.java)
        envelopeViewModel = ViewModelProvider(this).get(EnvelopeViewModel::class.java)
    }


    @SuppressLint("SetTextI18n", "ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_planification, container, false)

        // Observer to get the sum
        expenseViewModel.sumOfPositiveExpenses.observe(viewLifecycleOwner, Observer { sumOfPositiveExpenses ->
            expenseViewModel.sumOfNegativeExpenses.observe(viewLifecycleOwner, Observer { sumOfNegativeExpenses ->
                envelopeViewModel.sumOfEnvelopes.observe(viewLifecycleOwner, Observer { sumOfEnvelopes ->
                    if(sumOfPositiveExpenses != null && sumOfNegativeExpenses != null && sumOfEnvelopes != null)
                       view.budget.sumAmount.text = (sumOfPositiveExpenses - sumOfNegativeExpenses + sumOfEnvelopes).toString()
                })
            })
        })


        //RecyclerView for category
        val categoryAdapter = ListAdapterCategory()
        val categoryRecyclerView : RecyclerView = view.recyclerViewCategory
        categoryRecyclerView.adapter = categoryAdapter
        categoryRecyclerView.layoutManager = LinearLayoutManager(requireContext())


        // Observer for list of category
        categoryViewModel.readAllData.observe(viewLifecycleOwner, Observer { category ->
            Log.d("[DAO]", "Category data :$category")
            this.list = category;
            categoryAdapter.setData(category)
        })


        //Views
        val expandBtn = view.findViewById<FloatingActionButton>(R.id.expandButton);
        val btn1 = view.findViewById<FloatingActionButton>(R.id.addCategoryBtn);
        val btn2 = view.findViewById<FloatingActionButton>(R.id.addExpenseBtn);
        val lbl1 = view.findViewById<TextView>(R.id.lbl1);
        val lbl2 = view.findViewById<TextView>(R.id.lbl2);
        val darken = view.findViewById<ImageView>(R.id.darken);
        val scroll = view.findViewById<RecyclerView>(R.id.recyclerViewCategory);

        //Animations
        val animIn = AnimationUtils.loadAnimation(view.context, R.anim.in_up);
        val animOut = AnimationUtils.loadAnimation(view.context, R.anim.out_up);
        val fadeIn = AnimationUtils.loadAnimation(view.context, R.anim.fade_in);
        val fadeOut = AnimationUtils.loadAnimation(view.context, R.anim.fade_out);
        val rot = AnimationUtils.loadAnimation(view.context, R.anim.rot_45);
        val rotRev = AnimationUtils.loadAnimation(view.context, R.anim.rot_45_rev);

        btn2.setOnClickListener {
            var dialog = AddEnvelopeDialog(list);
            dialog.show(childFragmentManager,"")
        }

        btn1.setOnClickListener {
            var dialog = AddCategoryDialog();
            dialog.show(childFragmentManager,"")
        }



        expandBtn.setOnClickListener {
            if(btn1.isVisible){
                scroll.setOnTouchListener { view, ev -> scroll.onTouchEvent(ev) }
                darken.startAnimation(fadeOut)
                darken.visibility = View.INVISIBLE
                btn2.startAnimation(animOut)
                btn1.startAnimation(animOut)
                lbl2.startAnimation(fadeOut)
                lbl1.startAnimation(fadeOut)
                expandBtn.startAnimation(rot);
                expandBtn.rotation = 0F;
                lbl1.visibility = View.INVISIBLE
                lbl2.visibility = View.INVISIBLE
                btn1.visibility = View.INVISIBLE
                btn2.visibility = View.INVISIBLE

            }else{
                scroll.setOnTouchListener { view, motionEvent -> true }
                darken.startAnimation(fadeIn)
                darken.visibility = View.VISIBLE
                lbl1.startAnimation(fadeIn)
                lbl2.startAnimation(fadeIn)
                btn1.startAnimation(animIn)
                btn2.startAnimation(animIn)
                expandBtn.startAnimation(rot);
                expandBtn.rotation = 45F;
                btn1.visibility = View.VISIBLE
                btn2.visibility = View.VISIBLE
                lbl1.visibility = View.VISIBLE
                lbl2.visibility = View.VISIBLE
            }
        }

        return view;
    }

}