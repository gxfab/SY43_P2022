package com.example.lafo_cheuse

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import com.example.lafo_cheuse.models.Category
import com.example.lafo_cheuse.viewmodels.CategoryViewModel

/**
 * Simple activity to create a new category.
 *
 * @property categoryViewModel - A [categoryViewModel] instance.
 *
 */
class CreateCategoryActivity : AppCompatActivity() {
    private val categoryViewModel : CategoryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_category)

        val confirmButton : View = findViewById<Button>(R.id.buttonConfirmCreation)
        confirmButton.setOnClickListener {
            validateUserEntries()
        }
    }

    /**
     * FUnction to validate the entries from the user :
     *  - The category name should not be empty
     *  - The category emoji should not be null
     * The activity is finished if all criteria are satisfied
     *
     */
    private fun validateUserEntries() {
        val titleEntry : EditText = findViewById<EditText>(R.id.categoryName)
        val emojiEntry : EditText = findViewById<EditText>(R.id.categoryEmoji)

        if (titleEntry.text.toString() == "" ) {
            Toast.makeText(this,
                "Vous devez indiquer un titre", Toast.LENGTH_LONG).show()
        } else if (emojiEntry.text.toString() == "") {
            Toast.makeText(this,
                "Vous devez donner un emoji", Toast.LENGTH_SHORT).show()
        } else {
            categoryViewModel.createCategory(Category(titleEntry.text.toString(),emojiEntry.text.toString()))
            finish()
        }
    }
}