package com.example.gestimali

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.gestimali.wish.Wish
import com.example.gestimali.wish.WishViewModel

class NewWishFragment: Fragment() {

    private lateinit var mWishViewModel: WishViewModel

    override fun onCreateView(
        inflater : LayoutInflater, container : ViewGroup?,
        savedInstanceState : Bundle?
    ) : View?{
        val view = inflater.inflate(R.layout.fragment_new_wish,container,false)

        mWishViewModel = ViewModelProvider(this).get(WishViewModel::class.java)

        view.findViewById<ImageButton>(R.id.validate_add_wish).setOnClickListener{
            insertDateToDateBase(view)

        }
        return view}

    private fun insertDateToDateBase(view: View) {
        val wishName = view.findViewById<EditText>(R.id.editWishName).text.toString()
        val wishAmountNeeded = view.findViewById<EditText>(R.id.editWishValue).text

        if(inputCheck(wishName,wishAmountNeeded)){
            val wish = Wish(0,wishName,wishAmountNeeded.toString().toFloat(), 0f, false)
            mWishViewModel.addWish(wish)

            Toast.makeText(requireContext(),"Wish added !",Toast.LENGTH_LONG).show()

            activity?.supportFragmentManager?.popBackStack("addWishFragment",0)
        }else{
            Toast.makeText(requireContext(),"Please fill out all fields!",Toast.LENGTH_LONG).show()

        }
    }

    private fun inputCheck(wishName : String, wishAmountNeeded : Editable): Boolean {
        return !(TextUtils.isEmpty(wishName) && wishAmountNeeded.isEmpty())
    }
}