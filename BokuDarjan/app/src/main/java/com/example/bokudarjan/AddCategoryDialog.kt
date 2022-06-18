package com.example.bokudarjan

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.fragment.app.*
import androidx.lifecycle.ViewModelProvider
import com.example.bokudarjan.category.Category
import com.example.bokudarjan.category.CategoryViewModel
import vadiole.colorpicker.ColorModel
import vadiole.colorpicker.ColorPickerDialog

/**
 * A simple [Dialog] allowing the user to add a new category
 */
class AddCategoryDialog() : DialogFragment() {

    private lateinit var categoryViewModel: CategoryViewModel;

    /**
     * Selected color of the category, stored as hex string. Default to #F44336 (red)
     */
    var catColor : String = "#F44336"


    /**
     * Setting up the interface
     */
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            // Use the Builder class for convenient dialog construction

            val builder = AlertDialog.Builder(it)
            categoryViewModel = ViewModelProvider(this)[CategoryViewModel::class.java]
            builder.setView(R.layout.dialog_add_category)
            builder.setMessage("Ajouter une catégorie")
                .setPositiveButton("Ok",
                    DialogInterface.OnClickListener { dialog, id ->
                        insertDataToDatabase()
                    })
                .setNegativeButton("Annuler",
                    DialogInterface.OnClickListener { dialog, id ->
                        // User cancelled the dialog
                    })
            // Create the AlertDialog object and return it

            val out = builder.create()
            out;
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    /**
     * Setting up the color picker and the color of the buttons
     */
    override fun onStart() {
        val res = super.onStart()
        (dialog as AlertDialog).getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#4CAF50"))
        (dialog as AlertDialog).getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor("red"))
        var image = dialog!!.findViewById<ImageView>(R.id.colorView)
        image.setOnClickListener {

            val colorpicker: ColorPickerDialog = ColorPickerDialog.Builder()
                .setInitialColor(Color.parseColor("#F44336"))
                .setColorModel(ColorModel.HSV)
                .setColorModelSwitchEnabled(false)
                .onColorSelected {  color ->
                    catColor = String.format("#%06X", 0xFFFFFF and color)
                    Log.d("[COLOR]",catColor)
                    image.setColorFilter(color)
                }
                .create()
            colorpicker.show(childFragmentManager, "color_picker")

        }
    }

    private fun insertDataToDatabase(){
        val categoryName  : String = this.dialog!!.findViewById<EditText>(R.id.addCategoryName).text.toString()
        val category = Category(categoryName, catColor )


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