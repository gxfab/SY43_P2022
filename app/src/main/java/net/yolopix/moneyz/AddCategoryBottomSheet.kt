package net.yolopix.moneyz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import net.yolopix.moneyz.model.AppDatabase
import net.yolopix.moneyz.model.entities.Category
import kotlin.properties.Delegates

class AddCategoryBottomSheet(
    private val db: AppDatabase,
    private val monthNumber: Int,
    private val yearNumber: Int,
    private val accountUid: Int
) : BottomSheetDialogFragment() {
    private lateinit var editTextCategoryName: EditText
    private lateinit var editTextCategoryprice: EditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottom_sheet_add_prevision, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val buttonAddCategoryName =
            view.findViewById<com.google.android.material.button.MaterialButton>(R.id.button_add_category_bottom_sheet)
        editTextCategoryName = view.findViewById(R.id.edit_text_category_name)
        editTextCategoryprice = view.findViewById(R.id.edit_text_category_price)

        // Add click listener to the add button
        buttonAddCategoryName.setOnClickListener {
            addCategory()
        }

        // When the done button is pressed on the soft keyboard
        editTextCategoryName.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                addCategory()
            }
            true
        }
    }


    private fun addCategory() {
        var newCategoryPrice by Delegates.notNull<Float>()
        // Fetch the account name in the EditText and add it to the database
        val newCategoryName = editTextCategoryName.text.toString()
        newCategoryPrice = if (editTextCategoryprice.text.toString() == "") {
            0F
        } else {
            editTextCategoryprice.text.toString().toFloat()
        }

        val newCategory = Category(0, newCategoryName, newCategoryPrice, monthNumber, yearNumber, accountUid)
        runBlocking {
            db.categoryDao().insertCategory(newCategory)
        }
        lifecycleScope.launch {
            val parent = activity as PrevisionActivity
            parent.loadAll()
        }
        dismiss()
    }
}