package net.yolopix.moneyz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.launch
import net.yolopix.moneyz.model.AppDatabase
import net.yolopix.moneyz.model.entities.Account

class AddAccountBottomSheet(private val db: AppDatabase) : BottomSheetDialogFragment() {

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		return inflater.inflate(R.layout.bottom_sheet_add_account, container, false)
	}

	// When the bottom sheet is created
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		val buttonAdd =
			view.findViewById<com.google.android.material.button.MaterialButton>(R.id.button_add)

		// Add click listener to the add button
		buttonAdd.setOnClickListener {
			// Fetch the account name in the EditText and add it to the database
			val editTextAccountName = view.findViewById<EditText>(R.id.edit_text_account_name)
			val newAccountName = editTextAccountName?.text.toString()
			lifecycleScope.launch { db.accountDao().insertAccount(Account(0, newAccountName)) }
		}
	}

}