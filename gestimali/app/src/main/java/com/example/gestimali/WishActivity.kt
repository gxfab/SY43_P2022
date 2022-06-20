package com.example.gestimali

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.gestimali.adapter.MonthAdapter
import com.example.gestimali.adapter.WishAdapter

class WishActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wish)

        //Setting up the recycler view
        val wishRecyclerView = this.findViewById<RecyclerView>(R.id.WishRecyclerView)
        wishRecyclerView.adapter = WishAdapter()

    }

    /**
     * Function to see wishes activity
     */
    fun seeSavingsOverview(view : View) {
        val intent = Intent(this, SavingActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        startActivity(intent)
    }

    /**
     * Show a popup to add a wish
     */
    fun popupAddWish(view: View){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container_wish, NewWishFragment())
        transaction.addToBackStack("addWishFragment")
        transaction.commit()
    }
}