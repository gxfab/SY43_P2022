package com.example.bokudarjan

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.bokudarjan.category.Category
import com.example.bokudarjan.category.CategoryViewModel
import kotlinx.android.synthetic.main.fragment_add_category.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [addCategoryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddCategoryFragment : Fragment() {


    private lateinit var categoryViewModel: CategoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_category, container, false)


        categoryViewModel = ViewModelProvider(this)[CategoryViewModel::class.java]

        view.findViewById<Button>(R.id.addCategoryButton).setOnClickListener(){
            insertDataToDatabase()
        }

        return view
    }

    private fun insertDataToDatabase(){
        val categoryName  : String = addCategoryName.text.toString()

        val category = Category(categoryName)


        //If the input is ok, we add the category to the database
        if(isInputValid(category)){
            categoryViewModel.addCategory(category)
            Toast.makeText(requireContext(), "Successfully added", Toast.LENGTH_SHORT).show()
            Log.i("AddCategoryFragment", "Category successfully added")
        }else{
            Toast.makeText(requireContext(), "Error while adding category to database", Toast.LENGTH_SHORT).show()
        }
    }

    private fun isInputValid(category : Category) : Boolean{
        return !category.categoryName.isEmpty()
    }

}