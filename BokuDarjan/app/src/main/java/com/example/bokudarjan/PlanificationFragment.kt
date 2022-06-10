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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bokudarjan.category.Category
import com.example.bokudarjan.category.CategoryViewModel
import com.example.bokudarjan.category.ListAdapterCategory
import com.example.bokudarjan.envelope.EnvelopeViewModel
import com.example.bokudarjan.envelope.ListAdapterEnvelope
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.envelope_category_card.view.*
import kotlinx.android.synthetic.main.fragment_list_expense.view.*
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

    private lateinit var envelopeViewModel: EnvelopeViewModel
    private lateinit var categoryViewModel: CategoryViewModel

    fun showToast() {
        Toast.makeText(context,"Ca marche !", Toast.LENGTH_SHORT).show()
    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_planification, container, false)


        //RecyclerView for category
        val categoryAdapter = ListAdapterCategory()
        val categoryRecyclerView : RecyclerView = view.recyclerViewCategory
        categoryRecyclerView.adapter = categoryAdapter
        categoryRecyclerView.layoutManager = LinearLayoutManager(requireContext())


        // CategoryViewModel
        categoryViewModel = ViewModelProvider(this).get(CategoryViewModel::class.java)
        categoryViewModel.readAllData.observe(viewLifecycleOwner, Observer { category ->
            Log.d("[DAO]", "Category data :$category")
            categoryAdapter.setData(category)
        })


        var categoryList : MutableList<Category> = ArrayList<Category>()
        var myWrapper: Any = object : Any() {
            public var listValue : MutableList<Category> = ArrayList<Category>()
        }

        val list: MutableList<String> = ArrayList()
        list.add("one")
        /*
        val a : Category = Category()
        val b : Category = Category()

        val categoryListTest : ArrayList<Category> = ArrayList(
            Arrays.asList(a,b)
        )
        categoryList.addAll(categoryListTest)*/

        Log.i("PlanificationFragment", "categoryList sizea" + (categoryList.size))


        /**var observer : Observer<List<Category>> = Observer { category ->
            val temp : List<Category> = ArrayList<Category>(category)

            categoryList = ArrayList(category)
            for (i : Int in 0..category.size-1) {
                Log.i("PlanificationFragment", "category " + i + (categoryList[i].categoryName))
                categoryList.add((category[i]))
            }
            Log.i("PlanificationFragment", "categoryList sizec" + (categoryList.size))
        }
        categoryViewModel.readAllData.observe(viewLifecycleOwner, observer)**/

        //RecyclerView for envelope
        /**
        val envelopeAdapter = ListAdapterEnvelope()
        val envelopeRecyclerView : RecyclerView = view.findViewById(R.id.recyclerViewEnvelope);
        envelopeRecyclerView.adapter = envelopeAdapter
        envelopeRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        // EnvelopeViewModel
        envelopeViewModel = ViewModelProvider(this).get(EnvelopeViewModel::class.java)
        envelopeViewModel.readAllData.observe(viewLifecycleOwner, Observer { envelope ->
            envelopeAdapter.setData(envelope)
        })**/



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
            var dialog = AddEnvelopeDialog();
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

        //var catLayout = view.findViewById<LinearLayout>(R.id.catLayout);
        //var colors = arrayOf("#FFFF00","#FF00FF","#FF0000","#C0C0C0","#808000","#800080","#00FFFF","#008080","#0000FF","#000080")
        //TODO:Save all the child in an array for access later on
        //Here, get number of categories with names and color
        /*for(i in 0..9){
            val child: View = layoutInflater.inflate(R.layout.envelope_category_card, null)
            catLayout.addView(child);
            child.findViewById<TextView>(R.id.headerText).setText("Catégorie N°" + i)//change text
            var fgCol = Color.parseColor(colors[i])//replace with db color
            var bgCol = fgCol - (127 shl 24) // bit shift to add transparency
            child.findViewById<ImageView>(R.id.header).setColorFilter(fgCol)
            child.findViewById<LinearLayout>(R.id.expensesLayout).background.setTint(bgCol);
            //change categories view with data from db


        }*/

        return view;
    }

}